package net.brdle.delightful.common.item.knife;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.ModList;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class CompatKnifeItem extends DelightfulKnifeItem {
    private final String modid;
    private final Component tool;

    public CompatKnifeItem(String modid, TagKey<Item> tag, Tier tier, float attackDamageIn, float attackSpeedIn, Properties properties, Optional<Supplier<Ingredient>> base) {
        super(tag, tier, attackDamageIn, attackSpeedIn, properties, base);
        this.modid = modid;
        this.tool = Component.empty();
    }

    // With tooltip
    public CompatKnifeItem(String modid, TagKey<Item> tag, Tier tier, float attackDamageIn, float attackSpeedIn, Properties properties, Component tool, Optional<Supplier<Ingredient>> base) {
        super(tag, tier, attackDamageIn, attackSpeedIn, properties, base);
        this.modid = modid;
        this.tool = tool;
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
    protected boolean allowedIn(CreativeModeTab cat) {
        return super.allowedIn(cat) && this.isEnabled();
    }
}
