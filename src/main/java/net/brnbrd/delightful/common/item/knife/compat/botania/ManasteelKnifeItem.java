package net.brnbrd.delightful.common.item.knife.compat.botania;

import net.brnbrd.delightful.common.item.knife.DKnifeItem;
import net.brnbrd.delightful.compat.BotaniaCompat;
import net.brnbrd.delightful.compat.Mods;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import java.util.function.Consumer;

public class ManasteelKnifeItem extends DKnifeItem {
	public ManasteelKnifeItem(Properties properties, TagKey<Item> tag, Tier tier) {
		super(tag, tier, properties, Mods.BTA);
	}

	@Override
	public Ingredient getRod() {
		return Ingredient.of(DelightfulItemTags.LIVINGWOOD_TWIG);
	}

	public int getManaPerDamage() {
		return 60;
	}

	@Override
	public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
		return (
			this.enabled() ?
				BotaniaCompat.damageItemIfPossible(stack, amount, entity, this.getManaPerDamage()) :
				super.damageItem(stack, amount, entity, onBroken)
		);
	}

	@Override
	public void inventoryTick(@NotNull ItemStack stack, @NotNull Level world, @NotNull Entity entity, int slot, boolean selected) {
		if (
			this.enabled() &&
			!world.isClientSide() &&
			entity instanceof Player player &&
			stack.getDamageValue() > 0 &&
			BotaniaCompat.requestManaExactForTool(stack, player, 2 * this.getManaPerDamage(), true)
		) {
			stack.setDamageValue(stack.getDamageValue() - 1);
		}
	}
}