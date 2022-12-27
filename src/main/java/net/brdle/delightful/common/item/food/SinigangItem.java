package net.brdle.delightful.common.item.food;

import net.brdle.delightful.Delightful;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.BowlFoodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class SinigangItem extends BowlFoodItem {
	public SinigangItem(Properties prop) {
		super(prop.food(Nutrition.SINIGANG));
	}

	@Override
	public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> tool, @NotNull TooltipFlag pIsAdvanced) {
		super.appendHoverText(pStack, pLevel, tool, pIsAdvanced);
		tool.add(new TranslatableComponent(Delightful.MODID + ".sinigang.desc").withStyle(ChatFormatting.GRAY));
	}
}
