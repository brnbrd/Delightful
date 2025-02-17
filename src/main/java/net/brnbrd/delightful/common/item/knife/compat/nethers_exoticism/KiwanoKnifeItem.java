package net.brnbrd.delightful.common.item.knife.compat.nethers_exoticism;

import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.DKnifeItem;
import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class KiwanoKnifeItem extends DKnifeItem {
	public KiwanoKnifeItem(Properties properties) {
		super(DelightfulItemTags.KIWANO_PEEL, DelightfulTiers.KIWANO, properties, Modid.NE);
	}

	@Override
	public @NotNull Component getName(@NotNull ItemStack stack) {
		Component name = super.getName(stack);
		return this.enabled() ? name.copy().withStyle(ChatFormatting.YELLOW) : name;
	}

	@Override
	public List<Component> getTools() {
		return List.of(Component.literal("Burning").withStyle(ChatFormatting.BLUE));
	}
}