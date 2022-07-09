package net.brdle.delightful.common.item;

import net.brdle.delightful.common.config.DelightfulConfig;
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

public class TaggedKnifeItem extends DelightfulKnifeItem {
    private final ResourceLocation tag;

    public TaggedKnifeItem(Tier tier, float attackDamageIn, float attackSpeedIn, Item.Properties properties, ResourceLocation resTag) {
        super(tier, attackDamageIn, attackSpeedIn, properties);
        this.tag = resTag;
    }

    // Returns true if there is an entry within the tag
    public boolean isTag() {
        return !ForgeRegistries.ITEMS.tags().getTag(
                TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), this.tag))
                .isEmpty();
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> tool, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, tool, pIsAdvanced);
        if (!this.isTag()) {
            tool.add(new TextComponent("Requires non-empty tag:"));
            tool.add(new TextComponent(this.tag.getNamespace() + ":" + this.tag.getPath()).withStyle(ChatFormatting.UNDERLINE));
        }
    }
}
