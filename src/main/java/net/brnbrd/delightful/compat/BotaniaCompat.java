package net.brnbrd.delightful.compat;

import com.google.common.collect.Multimap;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.knife.Knives;
import net.brnbrd.delightful.network.DPacketHandler;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.entity.ManaBurstEntity;
import vazkii.botania.common.handler.BotaniaSounds;
import vazkii.botania.common.handler.PixieHandler;
import vazkii.botania.common.item.BotaniaItems;
import vazkii.botania.common.item.equipment.tool.ToolCommons;
import java.util.Objects;
import java.util.function.Supplier;

public class BotaniaCompat {

	public static Supplier<Tier> manasteel() {
		return () -> BotaniaAPI.instance().getManasteelItemTier();
	}

	public static Supplier<Tier> elementium() {
		return () -> BotaniaAPI.instance().getElementiumItemTier();
	}

	public static Supplier<Tier> terrasteel() {
		return () -> BotaniaAPI.instance().getTerrasteelItemTier();
	}

	public static void sendServerMessage() {
		DPacketHandler.INSTANCE.sendToServer(BotaniaLCP.INSTANCE);
	}

	public static int damageItemIfPossible(ItemStack stack, int amount, LivingEntity entity, int manaPerDamage) {
		return ToolCommons.damageItemIfPossible(stack, amount, entity, manaPerDamage);
	}

	public static boolean requestManaExactForTool(ItemStack stack, Player player, int manaToGet, boolean remove) {
		return ManaItemHandler.instance().requestManaExactForTool(stack, player, manaToGet, remove);
	}

	public static void handlePixies(Multimap<Attribute, AttributeModifier> ret, @NotNull EquipmentSlot slot) {
		ret.put(PixieHandler.PIXIE_SPAWN_CHANCE, PixieHandler.makeModifier(slot, "Sword modifier", 0.05));
	}

	public static ManaBurstEntity getBurst(Player player, ItemStack stack, int manaPerDamage) {
		ManaBurstEntity burst = new ManaBurstEntity(player);
		float motionModifier = 7F;
		burst.setColor(0x20FF20);
		burst.setMana(manaPerDamage);
		burst.setStartingMana(manaPerDamage);
		burst.setMinManaLoss(40);
		burst.setManaLossPerTick(4F);
		burst.setGravity(0F);
		burst.setDeltaMovement(burst.getDeltaMovement().scale(motionModifier));
		if (stack.is(Knives.ALF.get()) && Mods.loaded("mythicbotany")) {
			burst.setSourceLens(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(Util.rl("mythicbotany", "alfsteel_sword"))).getDefaultInstance().copy());
		} else {
			burst.setSourceLens(BotaniaItems.terraSword.getDefaultInstance().copy());
		}
		return burst;
	}

	public static ManaBurstEntity getAlfBurst(Player player, ItemStack stack, int manaPerDamage) {
		ManaBurstEntity burst = getBurst(player, stack, manaPerDamage);
		burst.setColor(0xF79100);
		burst.setMana(manaPerDamage);
		burst.setStartingMana(manaPerDamage);
		burst.setMinManaLoss(20);
		burst.setManaLossPerTick(2.0F);
		return burst;
	}

	public static void trySpawnBurst(Player player, ItemStack stack, int manaPerDamage, float attackStr, boolean alf) {
		if (attackStr == 1) {
			ManaBurstEntity burst = alf ?
				getAlfBurst(player, stack, manaPerDamage) : getBurst(player, stack, manaPerDamage);
			player.level().addFreshEntity(burst);
			stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(InteractionHand.MAIN_HAND));
			player.level().playSound(
				null,
				player.getX(),
				player.getY(),
				player.getZ(),
				BotaniaSounds.terraBlade,
				SoundSource.PLAYERS,
				1F,
				1F
			);
		}
	}
}
