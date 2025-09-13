package net.brnbrd.delightful.common.block;

import com.mojang.datafixers.util.Pair;
import net.brnbrd.delightful.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;

public class SlicedGourdBlock extends Block implements ISliceable {
	public static final Integer MAX_BITES = 3;
	public static final IntegerProperty BITES = IntegerProperty.create("bites", 1, MAX_BITES);
	private final Supplier<Item> sliceItem;

	public SlicedGourdBlock(Properties properties, Supplier<Item> sliceItem) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(BITES, 1));
		this.sliceItem = sliceItem;
	}

	@Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
		ItemStack item = this.getSliceItem();
		return item != null ? item : ItemStack.EMPTY;
	}

	public VoxelShape byBite(BlockState state) {
		return Block.box(0.0D, 0.0D, 0.0D, 16.0D, this.getHeight(state.getValue(BITES)), 16.0D);
	}

	@Override
	public @NotNull VoxelShape getShape(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
		return byBite(pState);
	}

	@Override
	public @NotNull VoxelShape getOcclusionShape(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos) {
		return byBite(pState);
	}

	@Override
	public int getMaxBites() {
		return MAX_BITES;
	}

	@Override
	public IntegerProperty getBitesProperty() {
		return BITES;
	}

	@Override
	public ItemStack getSliceItem() {
		return Util.getStack(this.sliceItem, 1);
	}

	@Override
	public BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
		return this.defaultBlockState();
	}

	@Override
	public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
		ItemStack heldStack = player.getItemInHand(hand);
		if (heldStack.is(ForgeTags.TOOLS_KNIVES)) {
			return this.cutSlice(level, pos, state, player, hand, hit);
		}
		return this.consumeBite(level, pos, state, player);
	}

	protected InteractionResult consumeBite(Level level, BlockPos pos, BlockState state, Player playerIn) {
		if (!playerIn.canEat(false)) {
			return InteractionResult.PASS;
		} else if (!level.isClientSide()) {
			ItemStack sliceStack = this.getSliceItem();
			Item slice = sliceStack.getItem();
			FoodProperties sliceFood = slice.getFoodProperties(sliceStack, playerIn);
			playerIn.getFoodData().eat(slice, sliceStack, playerIn);
			if (slice.isEdible() && sliceFood != null) {
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
			} else if (bites < this.getMaxBites()) {
				level.setBlock(pos, state.setValue(BITES, bites + 1), 3);
			}
			level.playSound(null, playerIn.blockPosition().above(), SoundEvents.GENERIC_EAT, SoundSource.PLAYERS, 1F, 1F);
		}
		return InteractionResult.sidedSuccess(level.isClientSide());
	}

	protected InteractionResult cutSlice(Level level, BlockPos pos, BlockState state, Player player, InteractionHand hand, BlockHitResult hit) {
		if (!level.isClientSide()) {
			int bites = state.getValue(BITES);
			if (bites == this.getMaxBites()) {
				level.removeBlock(pos, false);
			} else if (bites < this.getMaxBites()) {
				level.setBlock(pos, state.setValue(BITES, bites + 1), 3);
			}
			Direction direction = hit.getDirection();
			Util.dropOrGive(
				this.getSliceItem(),
				level,
				pos,
				direction.getAxis() == Direction.Axis.Y ? player.getDirection().getOpposite() : direction,
				player
			);
			level.playSound(null, pos, SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS, 1F, 1F);
			player.getItemInHand(hand).hurtAndBreak(1, player, onBroken -> onBroken.broadcastBreakEvent(hand));
		}
		return InteractionResult.sidedSuccess(level.isClientSide());
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(BITES);
	}

	@Override
	public int getAnalogOutputSignal(BlockState blockState, @NotNull Level level, @NotNull BlockPos pos) {
		return this.getMaxBites() - blockState.getValue(BITES);
	}

	@Override
	public boolean hasAnalogOutputSignal(@NotNull BlockState state) {
		return true;
	}

	@Override
	public boolean isPathfindable(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull PathComputationType type) {
		return false;
	}
}