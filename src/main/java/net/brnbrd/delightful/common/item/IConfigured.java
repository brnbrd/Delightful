package net.brnbrd.delightful.common.item;

import joptsimple.internal.Strings;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.compat.Mods;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import java.util.Arrays;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface IConfigured extends ItemLike {
	// Can be empty, but not null
	default @NotNull Modid[] getModid() {
		return Util.EMPTY;
	}

	// Can be empty, but not null
	default @NotNull Modid[] getConflicts() {
		return Util.EMPTY;
	}

	default boolean hasConflict() { // Checks that any defined conflict is loaded
		return (
			this.getConflicts().length > 0 &&
			Mods.loaded(Mods.Strategy.OR, this.getConflicts())
		);
	}

	// Tag should be filled (for enabling)
	default @Nullable TagKey<Item> getDependencyTag() {
		return null;
	}

	// Returns true if tag has an entry or is null
	default boolean isDependencyTag() {
		return Util.tagPopulated(this.getDependencyTag());
	}

	// Tag should be empty (for enabling)
	default @Nullable TagKey<Item> getEmptyTag() {
		return null;
	}

	// Returns true if tag is empty or is null
	default boolean isEmptyTag() {
		return Util.tagEmpty(this.getEmptyTag());
	}

	default boolean isLoaded() {
		final Modid[] dependencies = this.getModid();
		return (
			dependencies.length < 1 || // Empty modid means just load
			Mods.loaded(getStrategy(), dependencies)
		);
	}

	default boolean enabled() {
		return (
			Util.configEnabled(this.asItem()) &&
			this.isLoaded() &&
			!this.hasConflict() &&
			this.isDependencyTag() &&
			this.isEmptyTag()
		);
	}

	default boolean enabledText(List<Component> comps) {
		if (!this.enabled()) {
			comps.add(Util.tooltip("disabled").withStyle(ChatFormatting.UNDERLINE));
			if (!this.isLoaded() && this.getModid().length > 0) {
				final String[] notLoaded = Arrays.stream(this.getModid())
					.filter(modid -> !modid.loaded())
					.map(Modid::get)
					.toArray(String[]::new);
				if (notLoaded.length > 0) {
					comps.add(Util.tooltip("requires_modid"));
					comps.add(Component.literal(Strings.join(notLoaded, ", ")).withStyle(ChatFormatting.UNDERLINE));
				}
			}
			if (!this.isDependencyTag() && this.getDependencyTag() != null) {
				comps.add(Util.tooltip("requires_tag"));
				comps.add(Util.tagComponent(this.getDependencyTag()).withStyle(ChatFormatting.UNDERLINE));
			}
			if (!this.isEmptyTag() && this.getEmptyTag() != null) {
				comps.add(Util.tooltip("requires_empty_tag"));
				comps.add(Util.tagComponent(this.getEmptyTag()).withStyle(ChatFormatting.UNDERLINE));
			}
			return false;
		}
		return true;
	}

	default Mods.Strategy getStrategy() {
		return Mods.Strategy.OR;
	}
}