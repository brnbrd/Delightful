package net.brnbrd.delightful.common.item.knife.compat.botania.mythicbotany;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.brnbrd.delightful.common.item.knife.compat.botania.TerraKnifeItem;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Lazy;
import org.jetbrains.annotations.Nullable;
import javax.annotation.Nonnull;

public class AlfKnifeItem extends TerraKnifeItem {
	private final Lazy<Multimap<Attribute, AttributeModifier>> defaultModifiers;

	public AlfKnifeItem(Properties properties) {
		super(properties, DelightfulItemTags.ingot("alfsteel"), new AlfsteelTier());
		if (this.isLoaded()) {
			MinecraftForge.EVENT_BUS.addListener(this::onLeftClick);
		}
		this.defaultModifiers = Lazy.of(() -> {
			ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
			builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID,
					"Attack damage modifier", 9.5D, AttributeModifier.Operation.ADDITION));
			builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID,
					"Attack speed modifier", 4.0D, AttributeModifier.Operation.ADDITION));
			return builder.build();
		});
	}

	@Override
	public @Nullable Lazy<Multimap<Attribute, AttributeModifier>> getModifiers(EquipmentSlot slot, ItemStack stack) {
		return defaultModifiers;
	}

	@Override
	public String[] getModid() {
		return new String[]{"mythicbotany"};
	}

	@Override
	public int getManaPerDamage() {
		return super.getManaPerDamage() * 2;
	}

	@Override
	public boolean isValidRepairItem(@Nonnull ItemStack toRepair, @Nonnull ItemStack repair) {
		return this.enabled() && repair.is(DelightfulItemTags.ingot("alfsteel"));
	}

	@Override
	public @Nullable RecipeType<?> getRecipeType() {
		return RecipeType.SMITHING;
	}
}