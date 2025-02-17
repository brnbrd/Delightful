package net.brnbrd.delightful.common.item.knife.compat.allthemodium;

import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.DKnifeItem;
import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.function.Consumer;

public class AllthemodiumKnifeItem extends DKnifeItem {
	public AllthemodiumKnifeItem(Properties properties) {
		super(DelightfulItemTags.PLATES_ALLTHEMODIUM, DelightfulTiers.ALLTHEMODIUM, properties, Modid.ATM);
	}

	@Override
	public @NotNull Component getName(@NotNull ItemStack stack) {
		Component name = super.getName(stack);
		return this.enabled() ? name.copy().withStyle(ChatFormatting.LIGHT_PURPLE) : name;
	}

	@SuppressWarnings("NoTranslation")
	@Override
	public List<Component> getTools() {
		return List.of(Component.translatable("indestructible").withStyle(ChatFormatting.GOLD));
	}

	@Override
	public Ingredient getRod() {
		return Ingredient.of(DelightfulItemTags.RODS_ALLTHEMODIUM);
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