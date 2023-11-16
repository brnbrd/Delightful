package net.brnbrd.delightful.common.item.knife.compat.aether;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import java.util.Iterator;
import java.util.UUID;

public class ZaniteKnifeItem extends AetherKnifeItem {
	private final UUID DAMAGE_MODIFIER_UUID = UUID.fromString("CAE1DE8D-8A7F-4391-B6BD-C060B1DD49C5");

	public ZaniteKnifeItem(Properties properties) {
		super(DelightfulItemTags.GEMS_ZANITE, DelightfulTiers.ZANITE, properties);
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
		Multimap<Attribute, AttributeModifier> map = super.getAttributeModifiers(slot, stack);
		if (slot == EquipmentSlot.MAINHAND) {
			ImmutableMultimap.Builder<Attribute, AttributeModifier> attributeBuilder = ImmutableMultimap.builder();
			attributeBuilder.putAll(map);
			attributeBuilder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(DAMAGE_MODIFIER_UUID, "Damage modifier", calculateIncrease(map, stack), AttributeModifier.Operation.ADDITION));
			return attributeBuilder.build();
		}
		return map;
	}

	private int calculateIncrease(Multimap<Attribute, AttributeModifier> map, ItemStack stack) {
		double baseDamage = 0.0;
		for (Iterator<AttributeModifier> it = map.get(Attributes.ATTACK_DAMAGE).stream().iterator(); it.hasNext();) {
			AttributeModifier modifier = it.next();
			baseDamage += modifier.getAmount();
		}
		double boostedDamage = baseDamage * (2.0 * ((double) stack.getDamageValue()) / ((double) stack.getMaxDamage()) + 0.5);
		boostedDamage -= baseDamage;
		if (boostedDamage < 0.0) {
			boostedDamage = 0.0;
		}
		return (int) Math.round(boostedDamage);
	}
}
