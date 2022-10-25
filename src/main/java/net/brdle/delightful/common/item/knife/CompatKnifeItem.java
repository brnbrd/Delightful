package net.brdle.delightful.common.item.knife;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.ModList;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

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
        return ModList.get().isLoaded(this.modid);
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
            tool.add(Component.literal("Disabled.").withStyle(ChatFormatting.UNDERLINE));
        } else if (!this.isLoaded()) {
            tool.add(Component.literal("Requires modid:"));
            tool.add(Component.literal(modid).withStyle(ChatFormatting.UNDERLINE));
        } else if (!this.tool.equals(Component.empty())) {
            tool.add(this.tool);
        }
    }

    @Override
    public Component getName(ItemStack pStack) {
        if (this.isEnabled() && formatting.length > 0) {
            MutableComponent comp = super.getName(pStack).copy();
            for (ChatFormatting f : Arrays.stream(formatting).toList()) {
                comp = comp.withStyle(f);
            }
            return comp;
        }
        return super.getName(pStack);
    }

    @Override
    protected boolean allowedIn(CreativeModeTab cat) {
        return super.allowedIn(cat) && this.isEnabled();
    }
}
