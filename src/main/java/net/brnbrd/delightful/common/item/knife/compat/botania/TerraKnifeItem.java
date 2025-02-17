package net.brnbrd.delightful.common.item.knife.compat.botania;

import net.brnbrd.delightful.common.item.knife.compat.botania.mythicbotany.AlfKnifeItem;
import net.brnbrd.delightful.compat.botania.BotaniaCompat;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

public class TerraKnifeItem extends ManasteelKnifeItem {
	public TerraKnifeItem(Properties properties, TagKey<Item> tag, Tier tier) {
		super(properties, tag, tier);
		if (isLoaded()) {
			MinecraftForge.EVENT_BUS.addListener(this::onLeftClick);
		}
	}

	@Override
	public @NotNull Component getName(@NotNull ItemStack stack) {
		Component name = super.getName(stack);
		return this.enabled() ? name.copy().withStyle(ChatFormatting.YELLOW) : name;
	}

	public void onLeftClick(PlayerInteractEvent.LeftClickEmpty e) {
		if (
			this.enabled() &&
			e.getEntity().level().isClientSide() &&
			!e.getItemStack().isEmpty() &&
			e.getItemStack().is(this)
		) {
			BotaniaCompat.sendServerMessage();
		}
	}

	public boolean handle(Player p, ItemStack stack, float scale) {
		if (
			this.enabled() &&
			stack.is(this) &&
			!p.level().isClientSide() &&
			!p.isSpectator()
		) {
			return BotaniaCompat.trySpawnBurst(p, stack, this.getManaPerDamage(), scale, this instanceof AlfKnifeItem);
		}
		return false;
	}

	@Override
	public int getManaPerDamage() {
		return (int) (super.getManaPerDamage() * 1.67F);
	}

	@Override
	public boolean hurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity target, @NotNull LivingEntity attacker) {
		if (this.enabled() && attacker instanceof Player p) {
			return handle(p, stack, p.getAttackStrengthScale(0F));
		}
		return false;
	}
}