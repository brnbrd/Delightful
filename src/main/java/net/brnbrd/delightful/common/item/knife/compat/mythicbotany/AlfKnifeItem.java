package net.brnbrd.delightful.common.item.knife.compat.mythicbotany;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.knife.Knives;
import net.brnbrd.delightful.common.item.knife.compat.botania.TerraKnifeItem;
import net.minecraft.ChatFormatting;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Lazy;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nonnull;

public class AlfKnifeItem extends TerraKnifeItem {
	private final Lazy<Multimap<Attribute, AttributeModifier>> defaultModifiers;

	public AlfKnifeItem(Properties properties, ChatFormatting... formatting) {
		super(properties, DelightfulItems.ingot("alfsteel"), new AlfsteelTier(), formatting);
		if (isLoaded()) {
			MinecraftForge.EVENT_BUS.addListener(this::onLeftClick);
		}
		ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
		builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", 9.5F, AttributeModifier.Operation.ADDITION));
		builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", 4.0F, AttributeModifier.Operation.ADDITION));
		this.defaultModifiers = Lazy.of(builder::build);
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(@NotNull EquipmentSlot slot, @NotNull ItemStack stack) {
		return (enabled() && slot == EquipmentSlot.MAINHAND) ?
			this.defaultModifiers.get() :
			super.getDefaultAttributeModifiers(slot);
	}

	@Override
	public String[] getModid() {
		return new String[] { "mythicbotany" };
	}

	@Override
	public ImmutablePair<Ingredient, Ingredient> getSmithing() {
		return new ImmutablePair<>(
			Ingredient.of(Util.it("mythicbotany", "alf_upgrade")),
			Util.ing(Knives.TERRA)
		);
	}

	@Override
	public int getManaPerDamage() {
		return 2 * super.getManaPerDamage();
	}

	@Override
	public boolean isValidRepairItem(@Nonnull ItemStack toRepair, @Nonnull ItemStack repair) {
		return enabled() && repair.is(DelightfulItems.ingot("alfsteel"));
	}
}
