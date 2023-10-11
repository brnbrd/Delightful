package net.brnbrd.delightful.common.item.knife;

import net.brnbrd.delightful.compat.Mods;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CompatKnifeItem extends DelightfulKnifeItem {
    private final String modid;
    private final ChatFormatting[] formatting;

    public CompatKnifeItem(String modid, @Nullable TagKey<Item> tag, Tier tier, Properties properties, ChatFormatting... formatting) {
        super(tag, tier, properties);
        this.modid = modid;
        this.formatting = formatting;
    }

    public String getModid() {
        return this.modid;
    }

    public boolean isLoaded() {
        return Mods.loaded(this.getModid());
    }

    @Override
    public boolean isEnabled() {
        return super.isEnabled() && this.isLoaded();
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> comps, @NotNull TooltipFlag pIsAdvanced) {
        if (!this.isLoaded()) {
            comps.add(Component.translatable("tooltip.requires_modid"));
            return;
        }
        super.appendHoverText(stack, level, comps, pIsAdvanced);
        if (this.isEnabled() && !this.getTools().isEmpty()) {
            comps.addAll(this.getTools());
        }
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        return (this.isEnabled() && this.formatting.length > 0) ?
            super.getName(stack).copy().withStyle(this.formatting) :
            super.getName(stack);
    }

    @Override
    protected boolean allowedIn(@NotNull CreativeModeTab ct) {
        return super.allowedIn(ct) && this.isEnabled();
    }
}
