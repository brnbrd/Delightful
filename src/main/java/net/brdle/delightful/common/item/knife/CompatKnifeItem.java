package net.brdle.delightful.common.item.knife;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.ModList;
import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class CompatKnifeItem extends TaggedKnifeItem {
    private final String modid;
    private final Component tool;

    public CompatKnifeItem(String modid, ResourceLocation tag, Tier tier, float attackDamageIn, float attackSpeedIn, Properties properties) {
        super(tag, tier, attackDamageIn, attackSpeedIn, properties);
        this.modid = modid;
        this.tool = new TextComponent("");
    }

    public CompatKnifeItem(String modid, ResourceLocation tag, Tier tier, float attackDamageIn, float attackSpeedIn, Properties properties, Component tool) {
        super(tag, tier, attackDamageIn, attackSpeedIn, properties);
        this.modid = modid;
        this.tool = tool;
    }

    public CompatKnifeItem(String modid, Supplier<Ingredient> base, ResourceLocation tag, Tier tier, float attackDamageIn, float attackSpeedIn, Properties properties) {
        super(base, tag, tier, attackDamageIn, attackSpeedIn, properties);
        this.modid = modid;
        this.tool = new TextComponent("");
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
            tool.add(new TextComponent("Disabled.").withStyle(ChatFormatting.UNDERLINE));
        } else if (!this.isLoaded()) {
            tool.add(new TextComponent("Requires modid:"));
            tool.add(new TextComponent(modid).withStyle(ChatFormatting.UNDERLINE));
        } else if (!this.tool.getString().equals(new TextComponent("").getString())) {
            tool.add(this.tool);
        }
    }

    @Override
    protected boolean allowdedIn(CreativeModeTab pCategory) {
        return super.allowdedIn(pCategory) && this.isEnabled();
    }
}
