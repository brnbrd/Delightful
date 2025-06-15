package net.brnbrd.delightful.compat.undergarden;

import net.brnbrd.delightful.common.block.SlicedGourdBlock;
import net.brnbrd.delightful.compat.Modid;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;

public class SlicedGloomgourdBlock extends SlicedGourdBlock {
	public SlicedGloomgourdBlock(Properties properties) {
		super(properties, () -> Modid.UGD.loaded() ? Modid.UGD.item("gloomgourd_slice") : Items.AIR);
	}

	@Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
		if (!Modid.UGD.loaded() && Modid.UG.loaded()) {
			return Modid.UG.itemStack("gloomgourd");
		} else {
			return super.getCloneItemStack(state, target, level, pos, player);
		}
	}
}