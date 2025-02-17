package net.brnbrd.delightful.common.item.knife.compat.spirit;

import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.DKnifeItem;
import net.brnbrd.delightful.compat.Modid;
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

public class SoulSteelKnifeItem extends DKnifeItem {
	public SoulSteelKnifeItem(Properties properties) {
		super(DelightfulItemTags.SOUL_STEEL_INGOT, DelightfulTiers.SOUL_STEEL, properties, Modid.SP);
	}

	@Override
	public @NotNull Component getName(@NotNull ItemStack stack) {
		Component name = super.getName(stack);
		return this.enabled() ? name.copy().withStyle(ChatFormatting.AQUA) : name;
	}

	@Override
	public int getBarColor(@NotNull ItemStack pStack) {
		return 0xFF00fffb;
	}

	@Override
	public Ingredient getRod() {
		return Ingredient.of(Items.NETHERRACK);
	}

	@Override
	public boolean hurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity target, @NotNull LivingEntity attacker) {
		if (this.enabled()) {
			SpiritCompat.handleOnHitEntity(stack, target, attacker);
		}
		return super.hurtEnemy(stack, target, attacker);
	}

	@Override
	public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tool, @NotNull TooltipFlag pIsAdvanced) {
		if (this.enabled()) {
			SpiritCompat.appendEmpoweredText(stack, tool);
		}
		super.appendHoverText(stack, level, tool, pIsAdvanced);
	}
}