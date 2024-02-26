package net.brnbrd.delightful.common.block;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.PlantType;
import org.jetbrains.annotations.NotNull;

public class CantaloupePlantBlock extends BushBlock implements BonemealableBlock {
	public static final int MAX_AGE = 3;
	public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
	protected static final VoxelShape SHAPE = Shapes.block();

	public CantaloupePlantBlock(BlockBehaviour.Properties pProperties) {
		super(pProperties);
		this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
	}

	@Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
		return state.getValue(AGE) <= 2 ? DelightfulItems.CANTALOUPE_SEEDS.get().getDefaultInstance() : DelightfulItems.CANTALOUPE.get().getDefaultInstance();
	}

	@Override
	protected boolean mayPlaceOn(BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos) {
		return pState.is(BlockTags.SAND);
	}

	@Override
	public @NotNull VoxelShape getShape(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
		return SHAPE;
	}

	/**
	 * @return whether this block needs random ticking.
	 */
	@Override
	public boolean isRandomlyTicking(@NotNull BlockState state) {
		return !this.isMaxAge(state);
	}

	@Override
	public void tick(BlockState pState, @NotNull ServerLevel pLevel, @NotNull BlockPos pPos, @NotNull RandomSource pRandom) {
		if (!pState.canSurvive(pLevel, pPos)) {
			pLevel.destroyBlock(pPos, true);
		}

	}

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

	@Override
	public boolean canSurvive(@NotNull BlockState pState, LevelReader pLevel, BlockPos pPos) {
		BlockState soil = pLevel.getBlockState(pPos.below());
		if (soil.canSustainPlant(pLevel, pPos.below(), Direction.UP, this)) return true;
		BlockState blockstate = pLevel.getBlockState(pPos.below());
		if (blockstate.is(BlockTags.SAND)) {
			BlockPos blockpos = pPos.below();
			for (Direction direction : Direction.Plane.HORIZONTAL) {
				BlockState blockstate1 = pLevel.getBlockState(blockpos.relative(direction));
				FluidState fluidstate = pLevel.getFluidState(blockpos.relative(direction));
				if (pState.canBeHydrated(pLevel, pPos, fluidstate, blockpos.relative(direction)) || blockstate1.is(Blocks.FROSTED_ICE)) {
					return true;
				}
			}
		}
		return false;
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