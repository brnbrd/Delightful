package net.brdle.delightful.common.item.knife.seeds;

import net.brdle.delightful.common.item.DelightfulTiers;
import net.brdle.delightful.common.item.knife.CompatKnifeItem;
import net.brdle.delightful.data.DelightfulItemTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class LeafKnifeItem extends CompatKnifeItem {
	public LeafKnifeItem(Properties properties) {
		super("seeds", DelightfulItemTags.SHARP_LEAF, DelightfulTiers.LEAF, 0.5F, -2.0F, properties, Optional.empty(), ChatFormatting.YELLOW);
	}

	/**
	 * allows items to add custom lines of information to the mouseover description
	 */
	@Override
	public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> tool, TooltipFlag pIsAdvanced) {
		super.appendHoverText(pStack, pLevel, tool, pIsAdvanced);
		if (this.isEnabled()) {
			tool.add(Component.literal("Very sharp"));
		}
	}
}
