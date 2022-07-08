package net.brdle.delightful.common.item;

import net.brdle.delightful.common.config.DelightfulConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.item.KnifeItem;
import javax.annotation.Nullable;
import java.util.List;

public class DelightfulKnifeItem extends KnifeItem {
    public DelightfulKnifeItem(Tier tier, float attackDamageIn, float attackSpeedIn, Properties properties) {
        super(tier, attackDamageIn, attackSpeedIn, properties);
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> tool, TooltipFlag pIsAdvanced) {
        if (!DelightfulConfig.CONFIG.knives.get(this.getRegistryName().getPath()).get()) {
            tool.add(new TextComponent("Disabled.").withStyle(ChatFormatting.UNDERLINE));
        }
    }
}
