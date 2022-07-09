package net.brdle.delightful.common.item.knife;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.ModList;
import javax.annotation.Nullable;
import java.util.List;

public class CompatKnifeItem extends DelightfulKnifeItem {
    private final String modid;

    public CompatKnifeItem(Tier tier, float attackDamageIn, float attackSpeedIn, Properties properties, String modid) {
        super(tier, attackDamageIn, attackSpeedIn, properties);
        this.modid = modid;
    }

    public boolean isLoaded() {
        return ModList.get().isLoaded(this.modid);
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> tool, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, tool, pIsAdvanced);
        if (!this.isLoaded()) {
            tool.add(new TextComponent("Requires modid:"));
            tool.add(new TextComponent(modid).withStyle(ChatFormatting.UNDERLINE));
        }
    }
}
