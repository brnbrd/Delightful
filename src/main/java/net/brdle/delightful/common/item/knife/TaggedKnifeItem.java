package net.brdle.delightful.common.item.knife;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class TaggedKnifeItem extends DelightfulKnifeItem {
    private final ResourceLocation tag;
    private final boolean smith;

    public TaggedKnifeItem(ResourceLocation tag, Tier tier, float attackDamageIn, float attackSpeedIn, Item.Properties properties) {
        super(() -> Ingredient.of(ItemTags.create(tag)), tier, attackDamageIn, attackSpeedIn, properties);
        this.tag = tag;
        this.smith = false;
    }

    public TaggedKnifeItem(Supplier<Ingredient> base, ResourceLocation tag, Tier tier, float attackDamageIn, float attackSpeedIn, Item.Properties properties) {
        super(base, tier, attackDamageIn, attackSpeedIn, properties);
        this.tag = tag;
        this.smith = true;
    }

    @Nullable
    public ResourceLocation getTag() {
        return this.tag;
    }

    // Returns true if there is an entry within the tag
    public boolean isTag() {
        return !Objects.requireNonNull(ForgeRegistries.ITEMS.tags()).getTag(ItemTags.create(this.tag))
                .isEmpty();
    }

    @Override
    public boolean isEnabled() {
        return super.isEnabled() && this.isTag();
    }

    public boolean isSmithing() {
        return this.smith;
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> tool, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, tool, pIsAdvanced);
        if (!this.isTag() && this.config()) {
            tool.add(new TextComponent("Requires non-empty tag:"));
            tool.add(new TextComponent(this.tag.getNamespace() + ":" + this.tag.getPath()).withStyle(ChatFormatting.UNDERLINE));
        }
    }
}
