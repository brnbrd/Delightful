package net.brnbrd.delightful.common.item.knife;

import com.google.common.collect.ImmutableList;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.IConfigured;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITagManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.item.KnifeItem;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class DelightfulKnifeItem extends KnifeItem implements IConfigured {
    private final TagKey<Item> tag;

    public DelightfulKnifeItem(TagKey<Item> tag, Tier tier, Properties properties) {
        super(tier, 0.5F, -2.0F, properties);
        this.tag = tag;
    }

    @Override
    public boolean isValidRepairItem(@NotNull ItemStack pToRepair, @NotNull ItemStack pRepair) {
        return super.isValidRepairItem(pToRepair, pRepair);
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> tool, @NotNull TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, tool, pIsAdvanced);
        if (!this.config()) {
            tool.add(Component.translatable("tooltip.config_disabled").withStyle(ChatFormatting.UNDERLINE));
        } else if (!this.isTag() && this.getTag() != null) {
            tool.add(Component.translatable("tooltip.requires_tag"));
            tool.add(Component.literal(this.getTag().location().toString()).withStyle(ChatFormatting.UNDERLINE));
        }
    }

    @Nullable
    public TagKey<Item> getTag() {
        return this.tag;
    }

    // Returns true if there is an entry within the tag
    public boolean isTag() {
        ITagManager<Item> tags = ForgeRegistries.ITEMS.tags();
        TagKey<Item> tag = this.getTag();
        return (
            tag != null &&
            tags != null &&
            tags.isKnownTagName(tag)
        );
    }

    public boolean config() {
        return Util.enabled(this);
    }

    @Override
    public boolean isEnabled() {
        return this.config() && this.isTag();
    }

    public Ingredient getRod() {
        return Ingredient.of(Tags.Items.RODS_WOODEN);
    }

    public Supplier<Ingredient> getSmithingBase() {
        return () -> Ingredient.EMPTY;
    }

    public @Nullable RecipeType<?> getRecipeType() {
        return getSmithingBase().get().isEmpty() ? RecipeType.CRAFTING : RecipeType.SMITHING;
    }

    public boolean hasCustomName() {
        return false;
    }

    public List<Component> getTools() {
        return List.of();
    }

    @Override
    protected boolean allowedIn(@NotNull CreativeModeTab cat) {
        return ImmutableList.of(
            CreativeModeTab.TAB_SEARCH,
            FarmersDelight.CREATIVE_TAB
        ).contains(cat) && this.config();
    }
}
