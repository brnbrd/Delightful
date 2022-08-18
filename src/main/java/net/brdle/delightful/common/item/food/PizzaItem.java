package net.brdle.delightful.common.item.food;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import javax.annotation.Nullable;
import java.util.List;

public class PizzaItem extends Item {
    public PizzaItem(Properties properties) {
        super(properties);
    }

    @Override
    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> components, TooltipFlag pIsAdvanced) {
        components.add(Component.literal("WIP").withStyle(ChatFormatting.RED));
    }
}
