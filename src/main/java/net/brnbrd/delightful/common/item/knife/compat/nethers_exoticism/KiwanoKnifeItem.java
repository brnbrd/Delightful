package net.brnbrd.delightful.common.item.knife.compat.nethers_exoticism;

import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import java.util.List;

public class KiwanoKnifeItem extends CompatKnifeItem {
	public KiwanoKnifeItem(Properties properties) {
		super("nethers_exoticism", DelightfulItemTags.KIWANO_PEEL, DelightfulTiers.KIWANO, properties, ChatFormatting.YELLOW);
	}

	@Override
	public List<Component> getTools() {
		return List.of(
			Component.literal("Burning").withStyle(ChatFormatting.BLUE)
		);
	}
}