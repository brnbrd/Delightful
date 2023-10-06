package net.brnbrd.delightful.common.item.knife.compat.seeds;

import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import java.util.List;

public class LeafKnifeItem extends CompatKnifeItem {
	public LeafKnifeItem(Properties properties) {
		super("seeds", DelightfulItemTags.SHARP_LEAF, DelightfulTiers.LEAF, properties, ChatFormatting.YELLOW);
	}

	@Override
	public List<Component> getTools() {
		return List.of(
			Component.literal("Very sharp").withStyle(ChatFormatting.GRAY)
		);
	}
}