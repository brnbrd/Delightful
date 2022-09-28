package net.brdle.delightful.common.block;

import com.mojang.datafixers.util.Pair;
import net.brdle.delightful.Util;
import net.brdle.delightful.common.config.DelightfulConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.MelonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.items.ItemHandlerHelper;
import vectorwing.farmersdelight.common.tag.ForgeTags;

import java.util.function.Supplier;

public class SlicedMiniMelonBlock extends MelonBlock implements ISliceable {

  protected static final VoxelShape SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 10.0D, 13.0D);
  public static final IntegerProperty BITES = IntegerProperty.create("bites", 1, 5);
  private final Supplier<Item> sliceItem;
  private final Supplier<Item> juiceItem;


  public SlicedMiniMelonBlock(Properties pProperties, Supplier<Item> sliceItem, Supplier<Item> juiceItem) {
    super(pProperties);
    this.registerDefaultState(this.stateDefinition.any().setValue(BITES, 1));
    this.sliceItem = sliceItem;
    this.juiceItem = juiceItem;
  }

  @Override
  public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
    return SHAPE;
  }

  @Override
  public VoxelShape getOcclusionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
    return Shapes.empty();
  }

  public ItemStack getSliceItem() {
    return this.sliceItem.get().getDefaultInstance();
  }

  public ItemStack getJuiceItem() {
    return this.juiceItem.get().getDefaultInstance();
  }

  public int getMaxBites() {
    return 5;
  }

  @Override
  public BlockState getStateForPlacement(BlockPlaceContext context) {
    return this.defaultBlockState();
  }

  @Override
  public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
    ItemStack heldStack = player.getItemInHand(hand);
    if (heldStack.is(ForgeTags.TOOLS_KNIVES)) {
      return this.cutSlice(level, pos, state, player, hand);
    } else if (heldStack.is(Items.GLASS_BOTTLE) && this.juiceItem.get() != null && DelightfulConfig.MELON_JUICING.get()) {
      return this.bottleJuice(level, pos, state, player, hand);
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
      } else if (bites < this.getMaxBites()){
        level.setBlock(pos, state.setValue(BITES, bites + 1), 3);
      }
      Util.dropOrGive(this.getSliceItem(), level, pos, player);
      level.playSound(null, pos, SoundEvents.WOOD_HIT, SoundSource.PLAYERS, 0.8F, 0.8F);
      player.getItemInHand(hand).hurt(1, level.getRandom(), (ServerPlayer) player);
    }
    return InteractionResult.sidedSuccess(level.isClientSide());
  }

  protected InteractionResult bottleJuice(Level level, BlockPos pos, BlockState state, Player player, InteractionHand hand) {
    if (!level.isClientSide()) {
      int bites = state.getValue(BITES);
      int bites_left = ((this.getMaxBites() + 1) - bites);
      if (bites_left == 4) {
        level.removeBlock(pos, false);
      } else if (bites_left > 4) {
        level.setBlock(pos, state.setValue(BITES, bites + 4), 3);
      } else {
        return InteractionResult.FAIL;
      }
      player.getItemInHand(hand).shrink(1);
      ItemHandlerHelper.giveItemToPlayer(player, this.juiceItem.get().getDefaultInstance(), 0);
      level.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.PLAYERS, 0.8F, 0.8F);
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
