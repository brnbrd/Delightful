package net.brnbrd.delightful.common.item.knife.compat.spirit;

import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.brnbrd.delightful.compat.SpiritCompat;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class SoulSteelKnifeItem extends CompatKnifeItem {

	public SoulSteelKnifeItem(Properties properties) {
		super("spirit", DelightfulItemTags.SOUL_STEEL_INGOT, DelightfulTiers.SOUL_STEEL, properties, ChatFormatting.AQUA);
	}

	@Override
	public Ingredient getRod() {
		return Ingredient.of(Items.NETHERRACK);
	}

	@Override
	public int getBarColor(@NotNull ItemStack pStack) {
		return 0xFF00fffb;
	}

	@Override
	public boolean hurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity target, @NotNull LivingEntity attacker) {
		if (this.isLoaded()) {
			SpiritCompat.handleOnHitEntity(stack, target, attacker);
		}
		return super.hurtEnemy(stack, target, attacker);
	}

	@Override
	public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tool, @NotNull TooltipFlag pIsAdvanced) {
		if (this.isLoaded() && this.enabled()) {
			SpiritCompat.appendEmpoweredText(stack, tool);
		}
		super.appendHoverText(stack, level, tool, pIsAdvanced);
	}
}
