package net.brnbrd.delightful.common.item.knife.compat.botania;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.brnbrd.delightful.compat.BotaniaCompat;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public class ElementiumKnifeItem extends ManasteelKnifeItem {

	public ElementiumKnifeItem(Properties properties, TagKey<Item> tag, Tier tier) {
		super(properties, tag, tier);
	}

	@Override
	public Ingredient getRod() {
		return Ingredient.of(DelightfulItemTags.DREAMWOOD_TWIG);
	}

	@NotNull
	@Override
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@NotNull EquipmentSlot slot) {
		var ret = super.getDefaultAttributeModifiers(slot);
		if (isEnabled() && slot == EquipmentSlot.MAINHAND) {
			ret = HashMultimap.create(ret);
			BotaniaCompat.handlePixies(ret, slot);
		}
		return ret;
	}
}
