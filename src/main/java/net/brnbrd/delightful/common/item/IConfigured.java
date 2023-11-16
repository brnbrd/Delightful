package net.brnbrd.delightful.common.item;

import net.brnbrd.delightful.Util;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import java.util.List;

public interface IConfigured extends ItemLike {

    default boolean enabled() {
        return Util.enabled(this.asItem());
    }

    default boolean enabledText(List<Component> comps) {
        if (!this.enabled()) {
            comps.add(Component.translatable("tooltip.config_disabled").withStyle(ChatFormatting.UNDERLINE));
            return false;
        }
        return true;
    }

    default ItemStack getCreative() {
        return this.enabled() ? this.asItem().getDefaultInstance() : ItemStack.EMPTY;
    }
}
