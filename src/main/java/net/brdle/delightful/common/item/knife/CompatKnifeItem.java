package net.brdle.delightful.common.item.knife;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.compat.Mods;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nullable;

public class CompatKnifeItem extends DelightfulKnifeItem {
    private final String modid;
    private final Component tool;
    private final ChatFormatting[] formatting;

    public CompatKnifeItem(String modid, TagKey<Item> tag, Tier tier, Properties properties, Supplier<Ingredient> base, ChatFormatting... formatting) {
        super(tag, tier, properties, base);
        this.modid = modid;
        this.tool = Component.empty();
        this.formatting = formatting;
    }

    // With tooltip
    public CompatKnifeItem(String modid, TagKey<Item> tag, Tier tier, Properties properties, Component tool, Supplier<Ingredient> base, ChatFormatting... formatting) {
        super(tag, tier, properties, base);
        this.modid = modid;
        this.tool = tool;
        this.formatting = formatting;
    }

    public String getModid() {
        return this.modid;
    }

    public boolean isLoaded() {
        return Mods.loaded(this.modid);
    }

    @Override
    public boolean isEnabled() {
        return super.isEnabled() && this.isLoaded();
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> tool, TooltipFlag pIsAdvanced) {
        if (!this.config()) {
            tool.add(Component.translatable(Delightful.MODID + ".disabled.desc").withStyle(ChatFormatting.UNDERLINE));
        } else if (!this.isLoaded()) {
            tool.add(Component.translatable(Delightful.MODID + ".disabled.requires"));
            tool.add(Component.literal(this.modid).withStyle(ChatFormatting.UNDERLINE));
        } else if (!this.tool.equals(Component.empty())) {
            tool.add(this.tool);
        }
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        if (this.isEnabled() && this.formatting.length > 0) {
            MutableComponent comp = super.getName(stack).copy();
            for (ChatFormatting f : Arrays.stream(this.formatting).toList()) {
                comp = comp.withStyle(f);
            }
            return comp;
        }
        return super.getName(stack);
    }

    @Override
    protected boolean allowedIn(@NotNull CreativeModeTab cat) {
        return super.allowedIn(cat) && this.isEnabled();
    }
}
