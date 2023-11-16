package net.brnbrd.delightful.common.item;

import net.brnbrd.delightful.compat.Mods;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import java.util.List;

public interface ICompat extends IConfigured {
	enum Strategy { AND, OR }
	default Strategy getStrategy() {
		return Strategy.OR;
	}
	String[] getModid();

	default boolean isLoaded() {
		return (this.getStrategy() == Strategy.AND) ?
			Mods.loaded(this.getModid()) :
			Mods.orLoaded(this.getModid());
	}

	@Override
	default boolean enabled() {
		return this.isLoaded() && IConfigured.super.enabled();
	}

	@Override
	default boolean enabledText(List<Component> comps) {
		if (!this.isLoaded()) {
			comps.add(Component.translatable("tooltip.requires_modid"));
			StringBuilder ids = new StringBuilder();
			for (String s : this.getModid()) {
				ids.append(s).append(", ");
			}
			comps.add(Component.literal(ids.substring(0, ids.length() - 2)).withStyle(ChatFormatting.UNDERLINE));
			return false;
		}
		return IConfigured.super.enabledText(comps);
	}
}
