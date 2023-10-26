package net.brnbrd.delightful.common.block;

import net.brnbrd.delightful.common.item.DelightfulItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
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
import net.minecraftforge.common.ForgeHooks;
import org.jetbrains.annotations.NotNull;

public class SalmonberryBushBlock extends BushBlock implements BonemealableBlock {
  public static final int MAX_AGE = 4;
  public static final IntegerProperty AGE = BlockStateProperties.AGE_4;
  private static final VoxelShape SAPLING_SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D);
  private static final VoxelShape MID_GROWTH_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

  public SalmonberryBushBlock(BlockBehaviour.Properties pProperties) {
    super(pProperties);
    this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
  }

  @Override
  public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
    return state.getValue(AGE) <= 2 ? DelightfulItems.SALMONBERRY_PIPS.get().getDefaultInstance() : DelightfulItems.SALMONBERRIES.get().getDefaultInstance();
  }

  @SuppressWarnings("deprecation")
  @Override
  public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
    if (state.getValue(AGE) == 0) {
      return SAPLING_SHAPE;
    } else {
      return this.isMaxAge(state) ? super.getShape(state, pLevel, pPos, pContext) : MID_GROWTH_SHAPE;
    }
  }

  /**
   * @return whether this block needs random ticking.
   */
  @Override
  public boolean isRandomlyTicking(@NotNull BlockState state) {
    return !this.isMaxAge(state);
  }

  /**
   * Performs a random tick on a block.
   */
  @SuppressWarnings("deprecation")
  @Override
  public void randomTick(BlockState pState, @NotNull ServerLevel pLevel, @NotNull BlockPos pPos, @NotNull RandomSource pRandom) {
    int i = pState.getValue(AGE);
    if (i < MAX_AGE && pLevel.getRawBrightness(pPos.above(), 0) >= 8){
      int speed = 20;
      Biome biome = pLevel.getBiome(pPos).value();
      if (pLevel.isRainingAt(pPos) &&
          biome.getPrecipitationAt(pPos) == Biome.Precipitation.RAIN &&
          biome.warmEnoughToRain(pPos)) { // Hard raining ON the block
        speed -= 15;
      } else if (pLevel.isRaining()) { // Level just raining
        speed -= 12;
      }
      if (ForgeHooks.onCropsGrowPre(pLevel, pPos, pState, pRandom.nextInt(speed) == 0)) {
        BlockState blockstate = pState.setValue(AGE, i + 1);
        pLevel.setBlock(pPos, blockstate, 2);
        pLevel.gameEvent(GameEvent.BLOCK_CHANGE, pPos, GameEvent.Context.of(blockstate));
        ForgeHooks.onCropsGrowPost(pLevel, pPos, pState);
      }
    }
  }

  @SuppressWarnings("deprecation")
  @Override
  public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull Player pPlayer, @NotNull InteractionHand pHand, @NotNull BlockHitResult pHit) {
    boolean flag = this.isMaxAge(state);
    if (!flag && pPlayer.getItemInHand(pHand).is(Items.BONE_MEAL)) {
      return InteractionResult.PASS;
    } else if (state.getValue(AGE) > 2) {
      popResource(pLevel, pPos, new ItemStack(DelightfulItems.SALMONBERRIES.get(), (flag ? 2 + pLevel.random.nextInt(2) : 1)));
      pLevel.playSound(null, pPos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + pLevel.random.nextFloat() * 0.4F);
      BlockState blockstate = state.setValue(AGE, 1);
      pLevel.setBlock(pPos, blockstate, 2);
      pLevel.gameEvent(GameEvent.BLOCK_CHANGE, pPos, GameEvent.Context.of(pPlayer, blockstate));
      return InteractionResult.sidedSuccess(pLevel.isClientSide);
    } else {
      return super.use(state, pLevel, pPos, pPlayer, pHand, pHit);
    }
  }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
    pBuilder.add(AGE);
  }

  @Override
  public boolean isValidBonemealTarget(@NotNull LevelReader pLevel, @NotNull BlockPos pPos, @NotNull BlockState state, boolean pIsClient) {
    return !this.isMaxAge(state);
  }

  @Override
  public boolean isBonemealSuccess(@NotNull Level pLevel, @NotNull RandomSource pRandom, @NotNull BlockPos pPos, @NotNull BlockState pState) {
    return true;
  }

  @Override
  public void performBonemeal(ServerLevel pLevel, @NotNull RandomSource pRandom, @NotNull BlockPos pPos, BlockState pState) {
    pLevel.setBlock(pPos, pState.setValue(AGE, Math.min(MAX_AGE, pState.getValue(AGE) + 1)), 2);
  }

  public boolean isMaxAge(BlockState state) {
    return state.getValue(AGE) >= MAX_AGE;
  }
}
