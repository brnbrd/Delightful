package net.brnbrd.delightful.common.block;

import com.mojang.datafixers.util.Pair;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PumpkinBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import java.util.function.Supplier;

public class SlicedPumpkinBlock extends PumpkinBlock implements ISliceable {

  private static final VoxelShape BITE1 = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D);
  private static final VoxelShape BITE2 = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
  private static final VoxelShape BITE3 = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D);

  public static final IntegerProperty BITES = IntegerProperty.create("bites", 1, 3);
  private final Supplier<Item> sliceItem;

  public SlicedPumpkinBlock(Properties pProperties, Supplier<Item> sliceItem) {
    super(pProperties);
    this.registerDefaultState(this.stateDefinition.any().setValue(BITES, 1));
    this.sliceItem = sliceItem;
  }

  @Override
  public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
    return byBite(pState);
  }

  @Override
  public VoxelShape getOcclusionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
    return byBite(pState);
  }

  public VoxelShape byBite(BlockState state) {
    return switch (state.getValue(BITES)) {
      case 2 -> BITE2;
      case 3 -> BITE3;
      default -> BITE1;
    };
  }

  public ItemStack getSliceItem() {
    return new ItemStack(this.sliceItem.get());
  }

  public int getMaxBites() {
    return 3;
  }

  @Override
  public BlockState getStateForPlacement(BlockPlaceContext context) {
    return this.defaultBlockState();
  }

  @Override
  public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
    ItemStack heldStack = player.getItemInHand(hand);
    if (heldStack.is(DelightfulItemTags.SCAVENGING_TOOLS)) {
      return this.cutSlice(level, pos, state, player, hand);
    }
    return this.consumeBite(level, pos, state, player);
  }

  protected InteractionResult consumeBite(Level level, BlockPos pos, BlockState state, Player playerIn) {
    if (!playerIn.canEat(false)) {
      return InteractionResult.PASS;
    } else if (!level.isClientSide()) {
      ItemStack sliceStack = this.getSliceItem();
      FoodProperties sliceFood = sliceStack.getItem().getFoodProperties();
      playerIn.getFoodData().eat(sliceStack.getItem(), sliceStack);
      if (this.getSliceItem().getItem().isEdible() && sliceFood != null) {
        for (Pair<MobEffectInstance, Float> pair : sliceFood.getEffects()) {
          var effect = pair.getFirst();
          if (effect != null && level.random.nextFloat() < pair.getSecond()) {
            playerIn.addEffect(new MobEffectInstance(effect));
          }
        }
      }
      int bites = state.getValue(BITES);
      if (bites == this.getMaxBites()) {
        level.removeBlock(pos, false);
      } else if (bites < this.getMaxBites()){
        level.setBlock(pos, state.setValue(BITES, bites + 1), 3);
      }
      level.playSound(null, pos, SoundEvents.GENERIC_EAT, SoundSource.PLAYERS, 0.8F, 0.8F);
    }
    return InteractionResult.sidedSuccess(level.isClientSide());
  }

  protected InteractionResult cutSlice(Level level, BlockPos pos, BlockState state, Player player, InteractionHand hand) {
    if (!level.isClientSide()) {
      int bites = state.getValue(BITES);
      if (bites == this.getMaxBites()) {
        level.removeBlock(pos, false);
      } else if (bites < this.getMaxBites()) {
        level.setBlock(pos, state.setValue(BITES, bites + 1), 3);
      }
      Util.dropOrGive(this.getSliceItem(), level, pos, player);
      level.playSound(null, pos, SoundEvents.WOOD_HIT, SoundSource.PLAYERS, 0.8F, 0.8F);
      player.getItemInHand(hand).hurtAndBreak(1, player, onBroken -> onBroken.broadcastBreakEvent(hand));
    }
    return InteractionResult.sidedSuccess(level.isClientSide());
  }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    builder.add(BITES);
  }

  @Override
  public int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos pos) {
    return this.getMaxBites() - blockState.getValue(BITES);
  }

  @Override
  public boolean hasAnalogOutputSignal(BlockState state) {
    return true;
  }

  @Override
  public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType type) {
    return false;
  }
}