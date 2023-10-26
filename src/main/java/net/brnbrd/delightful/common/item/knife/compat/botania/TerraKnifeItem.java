package net.brnbrd.delightful.common.item.knife.compat.botania;

import net.brnbrd.delightful.common.item.knife.compat.mythicbotany.AlfKnifeItem;
import net.brnbrd.delightful.compat.BotaniaCompat;
import net.minecraft.ChatFormatting;
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

	public TerraKnifeItem(Properties properties, TagKey<Item> tag, Tier tier, ChatFormatting... formatting) {
		super(
			properties,
			tag,
			tier,
			formatting
		);
		if (isLoaded()) {
			MinecraftForge.EVENT_BUS.addListener(this::onLeftClick);
		}
	}

	public void onLeftClick(PlayerInteractEvent.LeftClickEmpty e) {
		if (
			isEnabled() &&
			e.getEntity().level().isClientSide() &&
			!e.getItemStack().isEmpty() &&
			e.getItemStack().is(this)
		) {
			BotaniaCompat.sendServerMessage();
		}
	}

	public void handle(Player p, ItemStack stack, float scale) {
		if (
			isEnabled() &&
			stack.is(this) &&
			!p.level().isClientSide() &&
			!p.isSpectator()
		) {
			BotaniaCompat.trySpawnBurst(p, stack, this.getManaPerDamage(), scale, this instanceof AlfKnifeItem);
		}
	}

	@Override
	public int getManaPerDamage() {
		return (int) (super.getManaPerDamage() * 1.67F);
	}

	@Override
	public boolean hurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity target, @NotNull LivingEntity attacker) {
		if (isEnabled() && attacker instanceof Player p) {
			handle(p, stack, p.getAttackStrengthScale(0F));
		}
		return false;
	}
}
