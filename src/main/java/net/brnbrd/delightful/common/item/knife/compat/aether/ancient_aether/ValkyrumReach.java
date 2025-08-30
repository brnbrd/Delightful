package net.brnbrd.delightful.common.item.knife.compat.aether.ancient_aether;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.brnbrd.delightful.Util;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.common.ForgeMod;

public interface ValkyrumReach {

	default Multimap<Attribute, AttributeModifier> extendReachModifier(Multimap<Attribute, AttributeModifier> map, EquipmentSlot slot) {
		if (slot == EquipmentSlot.MAINHAND) {
			ImmutableMultimap.Builder<Attribute, AttributeModifier> attributeBuilder = ImmutableMultimap.builder();
			attributeBuilder.putAll(map);
			attributeBuilder.put(ForgeMod.BLOCK_REACH.get(), new AttributeModifier(
				Util.BLOCK_REACH,
				"Block reach modifier",
				getModifier(),
				AttributeModifier.Operation.ADDITION
			));
			attributeBuilder.put(ForgeMod.ENTITY_REACH.get(), new AttributeModifier(
				Util.ENTITY_REACH,
				"Entity reach modifier",
				getModifier(),
				AttributeModifier.Operation.ADDITION
			));
			map = attributeBuilder.build();
		}
		return map;
	}

	private double getModifier() {
		return 1;
	}
}