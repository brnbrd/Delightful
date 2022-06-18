package net.brdle.delightful.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.item.KnifeItem;
import javax.annotation.Nullable;
import java.util.List;

public class TaggedKnifeItem extends KnifeItem {
    private final TagKey<Item> tag;

    public TaggedKnifeItem(Tier tier, float attackDamageIn, float attackSpeedIn, Item.Properties properties, ResourceLocation resTag) {
        super(tier, attackDamageIn, attackSpeedIn, properties);
        this.tag = TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), resTag);
    }

    // Returns true if there is an entry within the tag
    private boolean isTag() {
        return !ForgeRegistries.ITEMS.tags().getTag(this.tag).isEmpty();
    }

    @Override
    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> tool, TooltipFlag pIsAdvanced) {
        if (!this.isTag()) {
            tool.add(new TextComponent("Requires non-empty tag:"));
            tool.add(new TextComponent(this.tag.location().getNamespace() + ":" + this.tag.location().getPath()).withStyle(ChatFormatting.UNDERLINE));
        }
    }

    @Override
    public void fillItemCategory(CreativeModeTab pCategory, NonNullList<ItemStack> pItems) {
        if (this.isTag()
                && (pCategory == FarmersDelight.CREATIVE_TAB) || this.allowdedIn(pCategory)) {
            pItems.add(new ItemStack(this));
        }
    }
}
