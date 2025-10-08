package net.brnbrd.delightful.common.item;

import joptsimple.internal.Strings;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.compat.Mods;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public interface ICompat extends IConfigured {
	@NotNull Modid[] getModid(); // Can be empty, but not null

	default boolean isLoaded() {
		Modid[] dependencies = getModid();
		return (
			getModid() == null || // Should not be possible, but worth a check
			getModid().length < 1 || // Empty modid means just load
			Mods.loaded(getStrategy(), dependencies)
		);
	}

	@Override
	default boolean enabled() {
		return this.isLoaded() && IConfigured.super.enabled();
	}

	@Override
	default boolean enabledText(List<Component> comps) {
		boolean configured = IConfigured.super.enabledText(comps);
		if (!isLoaded() && getModid().length > 0) {
			comps.add(Util.translation("tooltip", "requires_modid"));
			comps.add(Component.literal(Strings.join(Mods.names(getModid()), ", ")).withStyle(ChatFormatting.UNDERLINE));
			return false;
		}
		return configured && isLoaded();
	}

	default Mods.Strategy getStrategy() {
		return Mods.Strategy.OR;
	}
}