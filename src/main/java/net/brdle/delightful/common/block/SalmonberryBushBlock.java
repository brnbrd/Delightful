package net.brdle.delightful.common.block;

import net.brdle.delightful.common.item.DelightfulItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

public class SalmonberryBushBlock extends BushBlock implements BonemealableBlock {
  public static final int MAX_AGE = 3;
  public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
  private static final VoxelShape SAPLING_SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D);
  private static final VoxelShape MID_GROWTH_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

  public SalmonberryBushBlock(BlockBehaviour.Properties pProperties) {
    super(pProperties);
    this.registerDefaultState(this.stateDefinition.any().setValue(AGE, Integer.valueOf(0)));
  }

  public ItemStack getCloneItemStack(BlockGetter pLevel, BlockPos pPos, BlockState pState) {
    return new ItemStack(DelightfulItems.SALMONBERRY_PIPS.get());
  }

  public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
    if (pState.getValue(AGE) == 0) {
      return SAPLING_SHAPE;
    } else {
      return pState.getValue(AGE) < 3 ? MID_GROWTH_SHAPE : super.getShape(pState, pLevel, pPos, pContext);
    }
  }

  /**
   * @return whether this block needs random ticking.
   */
  public boolean isRandomlyTicking(BlockState pState) {
    return pState.getValue(AGE) < 3;
  }

  /**
   * Performs a random tick on a block.
   */
  public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
    int i = pState.getValue(AGE);
    if (i < 3 && pLevel.getRawBrightness(pPos.above(), 0) >= 9 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(pLevel, pPos, pState, pRandom.nextInt(5) == 0)) {
      BlockState blockstate = pState.setValue(AGE, i + 1);
      pLevel.setBlock(pPos, blockstate, 2);
      pLevel.gameEvent(GameEvent.BLOCK_CHANGE, pPos);
      net.minecraftforge.common.ForgeHooks.onCropsGrowPost(pLevel, pPos, pState);
    }

  }

  public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
    int i = pState.getValue(AGE);
    boolean flag = i == 3;
    if (!flag && pPlayer.getItemInHand(pHand).is(Items.BONE_MEAL)) {
      return InteractionResult.PASS;
    } else if (i > 1) {
      int j = 1 + pLevel.random.nextInt(2);
      popResource(pLevel, pPos, new ItemStack(DelightfulItems.SALMONBERRIES.get(), j + (flag ? 1 : 0)));
      pLevel.playSound(null, pPos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + pLevel.random.nextFloat() * 0.4F);
      BlockState blockstate = pState.setValue(AGE, 1);
      pLevel.setBlock(pPos, blockstate, 2);
      pLevel.gameEvent(pPlayer, GameEvent.BLOCK_CHANGE, pPos);
      return InteractionResult.sidedSuccess(pLevel.isClientSide);
    } else {
      return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }
  }

  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
    pBuilder.add(AGE);
  }

  /**
   * @return whether bonemeal can be used on this block
   */
  public boolean isValidBonemealTarget(BlockGetter pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
    return pState.getValue(AGE) < 3;
  }

  public boolean isBonemealSuccess(Level pLevel, Random pRandom, BlockPos pPos, BlockState pState) {
    return true;
  }

  public void performBonemeal(ServerLevel pLevel, Random pRandom, BlockPos pPos, BlockState pState) {
    pLevel.setBlock(pPos, pState.setValue(AGE, Math.min(3, pState.getValue(AGE) + 1)), 2);
  }

  @Override
  public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
    return new ItemStack(DelightfulItems.SALMONBERRIES.get());
  }
}