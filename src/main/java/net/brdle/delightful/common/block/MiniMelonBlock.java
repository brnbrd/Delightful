package net.brdle.delightful.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.MelonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;
import java.util.Random;

public class MiniMelonBlock extends MelonBlock implements BonemealableBlock {

  protected static final VoxelShape SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 10.0D, 13.0D);

  public MiniMelonBlock(Properties pProperties) {
    super(pProperties);
  }

  @Override
  public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
    return SHAPE;
  }

  @Override
  public VoxelShape getOcclusionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
    return Shapes.empty();
  }

  @Override
  public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
    if (!pLevel.isAreaLoaded(pPos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
    if (pLevel.getRawBrightness(pPos, 0) >= 5 &&
      pLevel.getBlockState(pPos.below()).is(Blocks.DIRT) &&
      ForgeHooks.onCropsGrowPre(pLevel, pPos, pState, pRandom.nextInt(10) == 0)) {
        pLevel.setBlock(pPos, Blocks.MELON.withPropertiesOf(pState), 2);
        ForgeHooks.onCropsGrowPost(pLevel, pPos, pState);
    }
  }

  /**
   * @param pLevel
   * @param pPos
   * @param pState
   * @param pIsClient
   * @return whether bonemeal can be used on this block
   */
  @Override
  public boolean isValidBonemealTarget(BlockGetter pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
    return true;
  }

  @Override
  public boolean isBonemealSuccess(Level pLevel, Random pRandom, BlockPos pPos, BlockState pState) {
    return true;
  }

  @Override
  public void performBonemeal(ServerLevel pLevel, Random pRandom, BlockPos pPos, BlockState pState) {
    if (!pLevel.isAreaLoaded(pPos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
    if (ForgeHooks.onCropsGrowPre(pLevel, pPos, pState, true)) {
      pLevel.setBlock(pPos, Blocks.MELON.withPropertiesOf(pState), 2);
      ForgeHooks.onCropsGrowPost(pLevel, pPos, pState);
    }
  }
}
