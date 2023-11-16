package net.brnbrd.delightful.common.item.knife.compat.deep_aether;

import com.google.common.collect.Multimap;
import net.brnbrd.delightful.common.item.knife.compat.aether.AetherKnifeItem;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import teamrazor.deepaether.DeepAetherConfig;
import teamrazor.deepaether.init.DATiers;
import teamrazor.deepaether.tags.SkyjadeWeapon;

public class SkyjadeKnifeItem extends AetherKnifeItem implements SkyjadeWeapon {
	public SkyjadeKnifeItem(Properties properties) {
		super("deep_aether", DelightfulItemTags.GEMS_SKYJADE, DATiers.SKYJADE, properties);
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
		return this.increaseDamage(super.getAttributeModifiers(slot, stack), stack, slot);
	}

	@Override
	public boolean isEnchantable(@NotNull ItemStack itemStack) {
		return this.enabled() && DeepAetherConfig.COMMON.skyjade_enchant.get();
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return this.enabled() && DeepAetherConfig.COMMON.skyjade_enchant.get();
	}
}
