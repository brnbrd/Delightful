package net.brnbrd.delightful.common.block;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.compat.Mods;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.block.PieBlock;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import java.util.function.Supplier;

public class DPieBlock extends PieBlock {
	private final ResourceLocation pie;

	public DPieBlock(Supplier<Item> pieSlice, ResourceLocation pie) {
		super(Block.Properties.copy(ModBlocks.APPLE_PIE.get()), pieSlice);
		this.pie = pie;
	}

	public boolean enabled() {
		String modid = Util.nameSpace(pieSlice.get());
		if (modid.equals(Delightful.MODID)) {
			return Util.enabled(pieSlice);
		}
		return Mods.stringLoaded(modid);
	}

	@Override
	public @NotNull Item asItem() {
		return Util.item(this.pie, super.asItem());
	}

	public @NotNull ItemStack getStack() {
		return Util.itemStack(this.pie, ItemStack.EMPTY);
	}

	@Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
		ItemStack stack = this.getStack();
		return !stack.isEmpty() ? stack : super.getCloneItemStack(state, target, level, pos, player);
	}
}