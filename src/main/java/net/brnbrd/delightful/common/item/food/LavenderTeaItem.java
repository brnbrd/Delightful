package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.Util;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITagManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class LavenderTeaItem extends DrinkItem {
	public LavenderTeaItem(Properties properties, float heal, boolean hasPotionEffectTooltip, boolean hasCustomTooltip) {
		super(properties, heal, hasPotionEffectTooltip, hasCustomTooltip);
	}

	@Override
	public boolean enabled() {
		return this.isTag() && super.enabled();
	}

	public boolean isTag() {
		TagKey<Item> lavender = Util.it("forge", "lavender");
		ITagManager<Item> tags = ForgeRegistries.ITEMS.tags();
		return tags != null && tags.isKnownTagName(lavender) && !tags.getTag(lavender).isEmpty();
	}

	@Override
	public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> comps, @NotNull TooltipFlag pIsAdvanced) {
		if (!this.isTag()) {
			comps.add(Component.translatable("tooltip.requires_tag"));
			comps.add(Component.literal("forge:lavender").withStyle(ChatFormatting.UNDERLINE));
		} else {
			super.appendHoverText(stack, level, comps, pIsAdvanced);
		}
	}
}
