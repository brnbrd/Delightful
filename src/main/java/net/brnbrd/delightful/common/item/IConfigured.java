package net.brnbrd.delightful.common.item;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.compat.Mods;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.ItemLike;
import java.util.List;

public interface IConfigured extends ItemLike {

    default String[] getConflicts() {
        return new String[0];
    }

    default boolean enabled() {
        return Util.enabled(this.asItem()) && !Mods.orLoaded(this.getConflicts());
    }

    default boolean enabledText(List<Component> comps) {
        if (!this.enabled()) {
            comps.add(Component.translatable("tooltip.config_disabled").withStyle(ChatFormatting.UNDERLINE));
            return false;
        }
        return true;
    }
}
