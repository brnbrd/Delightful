package net.brnbrd.delightful.common.item.knife.compat.seeds;

import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.DKnifeItem;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class LeafKnifeItem extends DKnifeItem {
	public LeafKnifeItem(Properties properties) {
		super(DelightfulItemTags.SHARP_LEAF, DelightfulTiers.LEAF, properties, "seeds");
	}

	@Override
	public @NotNull Component getName(@NotNull ItemStack stack) {
		Component name = super.getName(stack);
		return this.enabled() ? name.copy().withStyle(ChatFormatting.YELLOW) : name;
	}

	@Override
	public List<Component> getTools() {
		return List.of(Component.literal("Very sharp").withStyle(ChatFormatting.GRAY));
	}
}