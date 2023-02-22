package net.brnbrd.delightful.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.MelonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class MiniBlock extends MelonBlock {

  private static final VoxelShape SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 10.0D, 13.0D);
  public MiniBlock(Properties pProperties) {
    super(pProperties);
  }

  @SuppressWarnings("deprecation")
  @Override
  public @NotNull VoxelShape getShape(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
    return SHAPE;
  }

  @SuppressWarnings("deprecation")
  @Override
  public @NotNull VoxelShape getOcclusionShape(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos) {
    return Shapes.empty();
  }
}