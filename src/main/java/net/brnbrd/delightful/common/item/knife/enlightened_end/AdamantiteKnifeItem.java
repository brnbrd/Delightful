package net.brnbrd.delightful.common.item.knife.enlightened_end;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.brnbrd.delightful.compat.Mods;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.registry.ModItems;
import java.util.function.Consumer;

public class AdamantiteKnifeItem extends CompatKnifeItem {
	public AdamantiteKnifeItem(Properties properties) {
		super(Mods.EN, DelightfulItems.ingot("adamantite"), DelightfulTiers.ADAMANTITE, properties,
			Component.literal("Unbreakable").withStyle(ChatFormatting.LIGHT_PURPLE), Util.ing(ModItems.IRON_KNIFE));
	}

	@Override
	public boolean hurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity target, @NotNull LivingEntity attacker) {
		ResourceLocation rad = Util.rl(Mods.EN, "radiated");
		if (super.hurtEnemy(stack, target, attacker)) {
			if (ForgeRegistries.MOB_EFFECTS.containsKey(rad)) {
				target.addEffect(new MobEffectInstance(ForgeRegistries.MOB_EFFECTS.getValue(rad), 4000, 0));
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean isDamageable(ItemStack stack) {
		return false;
	}

	@Override
	public boolean isDamaged(ItemStack stack) {
		return false;
	}

	@Override
	public int getMaxDamage(ItemStack stack) {
		return 0;
	}


	@Override
	public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
		return 0;
	}

	@Override
	public boolean canBeDepleted() {
		return false;
	}

	@Override
	public boolean isEnchantable(@NotNull ItemStack stack) {
		return true;
	}
}
