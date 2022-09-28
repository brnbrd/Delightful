package net.brdle.delightful.common.block;

import net.brdle.delightful.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.ForgeHooks;
import vectorwing.farmersdelight.common.tag.ForgeTags;

public class MiniMelonBlock extends MiniBlock implements BonemealableBlock {

  public MiniMelonBlock(Properties pProperties) {
    super(pProperties);
  }

  @Override
  public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
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
  public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
    return true;
  }

  @Override
  public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
    if (!level.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
    if (ForgeHooks.onCropsGrowPre(level, pos, state, true)) {
      level.setBlock(pos, Blocks.MELON.withPropertiesOf(state), 2);
      ForgeHooks.onCropsGrowPost(level, pos, state);
    }
  }

  @Override
  public InteractionResult use(BlockState pState, Level world, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
    if (pPlayer.getItemInHand(pHand).is(ForgeTags.TOOLS_KNIVES)) {
      if (!world.isClientSide()) {
        SlicedMiniMelonBlock sliced = (SlicedMiniMelonBlock) DelightfulBlocks.SLICED_MINI_MELON.get();
        world.setBlock(pPos, sliced.defaultBlockState(), 2);
        Util.dropOrGive(sliced.getSliceItem(), world, pPos, pPlayer);
        world.playSound(null, pPos, SoundEvents.WOOD_HIT, SoundSource.PLAYERS, 0.8F, 0.8F);
        pPlayer.getItemInHand(pHand).hurt(1, world.getRandom(), (ServerPlayer) pPlayer);
      }
      return InteractionResult.sidedSuccess(world.isClientSide());
    }
    return InteractionResult.FAIL;
  }
}