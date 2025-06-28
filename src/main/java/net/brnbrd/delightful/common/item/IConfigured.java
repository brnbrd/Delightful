package net.brnbrd.delightful.common.item;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.compat.Mods;
import net.brnbrd.delightful.compat.Strategy;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public interface IConfigured extends ItemLike {
	default Modid[] getConflicts() {
		return Util.EMPTY;
	}

	default boolean hasConflict() { // Checks that any defined conflict is loaded
		return this.getConflicts().length > 0 && Mods.loaded(Strategy.OR, this.getConflicts());
	}

	// Tag should be filled (for enabling)
	default @Nullable TagKey<Item> getDependencyTag() {
		return null;
	}

	// Returns true if tag has an entry or is null
	default boolean isDependencyTag() {
		return Util.tagPopulated(getDependencyTag());
	}

	// Tag should be empty (for enabling)
	default @Nullable TagKey<Item> getEmptyTag() {
		return null;
	}

	// Returns true if tag is empty or is null
	default boolean isEmptyTag() {
		return Util.tagEmpty(getEmptyTag());
	}

	default boolean enabled() {
		return Util.configEnabled(this.asItem()) && !this.hasConflict() && this.isDependencyTag() && this.isEmptyTag();
	}

	default boolean enabledText(List<Component> comps) {
		if (!this.enabled()) {
			comps.add(Util.tooltip("disabled").withStyle(ChatFormatting.UNDERLINE));
			if (!this.isDependencyTag() && this.getDependencyTag() != null) {
				comps.add(Util.translation("tooltip", "requires_tag"));
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
}