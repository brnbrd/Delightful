package net.brnbrd.delightful.common.item.knife;

import com.google.common.collect.ImmutableList;
import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.config.DelightfulConfig;
import net.brnbrd.delightful.common.item.IConfigured;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.item.KnifeItem;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class DelightfulKnifeItem extends KnifeItem implements IConfigured {
    private final TagKey<Item> tag;
    private final Supplier<Ingredient> smithingBase;
    protected final ImmutableList<CreativeModeTab> tabs = ImmutableList.of(CreativeModeTab.TAB_SEARCH, FarmersDelight.CREATIVE_TAB);

    public DelightfulKnifeItem(TagKey<Item> tag, Tier tier, Properties properties, Supplier<Ingredient> smithingBase) {
        super(tier, 0.5F, -2.0F, properties);
        this.tag = tag;
        this.smithingBase = smithingBase;
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> tool, @NotNull TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, tool, pIsAdvanced);
        if (!this.config()) {
            tool.add(Component.translatable(Delightful.MODID + ".disabled.desc").withStyle(ChatFormatting.UNDERLINE));
        } else if (!this.isTag()) {
            tool.add(Component.translatable(Delightful.MODID + ".disabled.requirestag"));
            tool.add(Component.literal(this.getTag().location().toString()).withStyle(ChatFormatting.UNDERLINE));
        }
    }

    public TagKey<Item> getTag() {
        return this.tag;
    }

    // Returns true if there is an entry within the tag
    public boolean isTag() {
        return !Objects.requireNonNull(ForgeRegistries.ITEMS.tags()).getTag(this.getTag()).isEmpty();
    }

    public boolean config() {
        return DelightfulConfig.verify(this);
    }

    @Override
    public boolean isEnabled() {
        return this.config() && this.isTag();
    }

    public Supplier<Ingredient> getRod() {
        return Util.ing(Tags.Items.RODS_WOODEN);
    }

    @Nullable
    public Ingredient getSmithingBase() {
        return this.isSmithing() ? this.smithingBase.get() : null;
    }

    public boolean isSmithing() {
        return this.smithingBase != null;
    }

    public boolean genRecipe() {
        return true;
    }

    public boolean hasCustomName() {
        return false;
    }

    @Override
    protected boolean allowedIn(@NotNull CreativeModeTab cat) {
        return tabs.contains(cat) && this.config();
    }
}
