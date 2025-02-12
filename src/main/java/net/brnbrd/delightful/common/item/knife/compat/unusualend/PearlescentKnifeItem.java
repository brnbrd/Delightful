package net.brnbrd.delightful.common.item.knife.compat.unusualend;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.DKnifeItem;
import net.brnbrd.delightful.compat.Mods;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.util.Lazy;
import org.jetbrains.annotations.Nullable;

public class PearlescentKnifeItem extends DKnifeItem {
	private final Lazy<Multimap<Attribute, AttributeModifier>> defaultModifiers;

	public PearlescentKnifeItem(Properties properties) {
		super(DelightfulItemTags.INGOTS_PEARLESCENT, DelightfulTiers.PEARLESCENT, properties, Mods.DD);
		this.defaultModifiers = Lazy.of(() -> {
			ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
			builder.put(ForgeMod.BLOCK_REACH.get(), new AttributeModifier(Util.BLOCK_REACH,
					"Block reach modifier", 1.5D, AttributeModifier.Operation.ADDITION));
			builder.put(ForgeMod.ENTITY_REACH.get(), new AttributeModifier(Util.ENTITY_REACH,
					"Entity reach modifier", 0.5D, AttributeModifier.Operation.ADDITION));
			return builder.build();
		});
	}

	@Override
	public @Nullable Lazy<Multimap<Attribute, AttributeModifier>> getModifiers(EquipmentSlot slot, ItemStack stack) {
		return defaultModifiers;
	}

	@Override
	public @Nullable RecipeType<?> getRecipeType() {
		return RecipeType.SMITHING;
	}
}