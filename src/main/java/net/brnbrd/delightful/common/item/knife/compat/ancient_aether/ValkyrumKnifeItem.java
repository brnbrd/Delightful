package net.brnbrd.delightful.common.item.knife.compat.ancient_aether;

import com.google.common.collect.Multimap;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.compat.aether.AetherKnifeItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;

public class ValkyrumKnifeItem extends AetherKnifeItem implements ValkyrumReach {

	public ValkyrumKnifeItem(Properties properties) {
		super("ancient_aether", Util.it("ancient_aether", "valkyrum"), DelightfulTiers.VALKYRUM, properties);
	}

	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
		return extendReachModifier(super.getAttributeModifiers(slot, stack), slot);
	}
}
