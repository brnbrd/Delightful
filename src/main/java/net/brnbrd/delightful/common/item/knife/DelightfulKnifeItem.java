package net.brnbrd.delightful.common.item.knife;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.ICompat;
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
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.item.KnifeItem;
import java.util.Collections;
import java.util.List;

public class DelightfulKnifeItem extends KnifeItem implements IConfigured {
    private final TagKey<Item> tag;

    public DelightfulKnifeItem(TagKey<Item> tag, Tier tier, Properties properties) {
        super(tier, 0.5F, -2.0F, properties);
        this.tag = tag;
    }

    @Override
    public boolean isValidRepairItem(@NotNull ItemStack pToRepair, @NotNull ItemStack pRepair) {
        return this.enabled() && super.isValidRepairItem(pToRepair, pRepair);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> comps, @NotNull TooltipFlag pIsAdvanced) {
        if (
            this.enabledText(comps) &&
            !(this instanceof ICompat) &&
            Util.enabled(this) &&
            !this.isTag() &&
            this.getTag() != null
        ) {
            comps.add(Component.translatable("tooltip.requires_tag"));
            comps.add(Component.literal(this.getTag().location().toString()).withStyle(ChatFormatting.UNDERLINE));
        }
        super.appendHoverText(stack, level, comps, pIsAdvanced);
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
            tags.isKnownTagName(tag) &&
            !tags.getTag(tag).isEmpty()
        );
    }

    @Override
    public boolean enabled() {
        return IConfigured.super.enabled() && this.isTag();
    }

    public Ingredient getRod() {
        return Ingredient.of(Tags.Items.RODS_WOODEN);
    }

    public ImmutablePair<Ingredient, Ingredient> getSmithing() {
        return ImmutablePair.nullPair();
    }

    public @Nullable RecipeType<?> getRecipeType() {
        return getSmithing().equals(ImmutablePair.nullPair()) ? RecipeType.CRAFTING : RecipeType.SMITHING;
    }

    public boolean hasCustomName() {
        return false;
    }

    public List<Component> getTools() {
        return Collections.emptyList();
    }
}
