package net.brnbrd.delightful.common.block;

import net.brnbrd.delightful.common.item.DelightfulItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
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
import net.minecraftforge.common.PlantType;
import org.jetbrains.annotations.NotNull;

public class CantaloupePlantBlock extends BushBlock implements BonemealableBlock {
	public static final int MAX_AGE = 3;
	public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
	private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
		Block.box(3.0D, 0.0D, 3.0D, 13.0D, 2.0D, 13.0D),
		Block.box(2.0D, 0.0D, 2.0D, 14.0D, 5.0D, 14.0D),
		Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
		Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D)
	};

	public CantaloupePlantBlock(BlockBehaviour.Properties pProperties) {
		super(pProperties);
		this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
	}

	@Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
		return this.isMaxAge(state) ?
			DelightfulItems.CANTALOUPE.get().getDefaultInstance() :
			DelightfulItems.CANTALOUPE_SEEDS.get().getDefaultInstance();
	}

	@Override
	protected boolean mayPlaceOn(BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos) {
		return pState.is(BlockTags.SAND);
	}

	@Override
	public @NotNull VoxelShape getShape(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
		return SHAPE_BY_AGE[pState.getValue(AGE)];
	}

	@Override
	public void tick(BlockState pState, @NotNull ServerLevel pLevel, @NotNull BlockPos pPos, @NotNull RandomSource pRandom) {
		if (!pState.canSurvive(pLevel, pPos)) {
			pLevel.destroyBlock(pPos, true);
		}
	}

	/**
	 * @return whether this block needs random ticking.
	 */
	@Override
	public boolean isRandomlyTicking(@NotNull BlockState state) {
		return !this.isMaxAge(state);
	}

	@Override
	public void randomTick(BlockState pState, @NotNull ServerLevel pLevel, @NotNull BlockPos pPos, @NotNull RandomSource pRandom) {
		int i = pState.getValue(AGE);
		if (
			i < MAX_AGE &&
			pLevel.getRawBrightness(pPos.above(), 0) >= 8 &&
			ForgeHooks.onCropsGrowPre(pLevel, pPos, pState, pRandom.nextInt(16) == 0)
		) {
			BlockState blockstate = pState.setValue(AGE, i + 1);
			pLevel.setBlock(pPos, blockstate, 2);
			pLevel.gameEvent(GameEvent.BLOCK_CHANGE, pPos, GameEvent.Context.of(blockstate));
			ForgeHooks.onCropsGrowPost(pLevel, pPos, pState);
		}
	}

	@Override
	public boolean canSurvive(@NotNull BlockState pState, LevelReader pLevel, BlockPos pPos) {
		if (pLevel.getBlockState(pPos.below()).canSustainPlant(pLevel, pPos.below(), Direction.UP, this)) {
			return true;
		}
		BlockState blockstate = pLevel.getBlockState(pPos.below());
		if (blockstate.is(BlockTags.SAND)) {
			BlockPos blockpos = pPos.below();
			for (Direction direction : Direction.Plane.HORIZONTAL) {
				if (
					pState.canBeHydrated(pLevel, pPos, pLevel.getFluidState(blockpos.relative(direction)), blockpos.relative(direction)) ||
					pLevel.getBlockState(blockpos.relative(direction)).is(Blocks.FROSTED_ICE)
				) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull Player pPlayer, @NotNull InteractionHand pHand, @NotNull BlockHitResult pHit) {
		boolean flag = this.isMaxAge(state);
		if (!flag && pPlayer.getItemInHand(pHand).is(Items.BONE_MEAL)) {
			return InteractionResult.PASS;
		} else if (flag) {
			popResource(pLevel, pPos, new ItemStack(DelightfulItems.CANTALOUPE.get(), 1));
			pLevel.playSound(null, pPos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + pLevel.random.nextFloat() * 0.4F);
			BlockState blockstate = state.setValue(AGE, 0);
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

	@Override
	public net.minecraftforge.common.PlantType getPlantType(BlockGetter world, BlockPos pos) {
		return PlantType.BEACH;
	}

	@Override
	public @NotNull BlockState getPlant(@NotNull BlockGetter world, @NotNull BlockPos pos) {
		return this.defaultBlockState();
	}

	public boolean isMaxAge(BlockState state) {
		return state.getValue(AGE) >= MAX_AGE;
	}
}