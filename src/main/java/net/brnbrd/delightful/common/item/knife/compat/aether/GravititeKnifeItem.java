package net.brnbrd.delightful.common.item.knife.compat.aether;

import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.compat.Mods;
import net.brnbrd.delightful.data.tags.DelightfulEntityTags;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GravititeKnifeItem extends AetherKnifeItem {
	public GravititeKnifeItem(Properties properties) {
		super(DelightfulItemTags.ENCHANTED_GRAVITITE, DelightfulTiers.GRAVITITE, properties);
	}

	@Override
	public boolean hurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity target, @NotNull LivingEntity attacker) {
		if (!(attacker instanceof Player player) || player.getAttackStrengthScale(1.0F) == 1.0F) {
			if (!target.getType().is(DelightfulEntityTags.UNLAUNCHABLE) && (target.onGround() || target.isInFluidType())) {
				target.push(0.0, 1.0, 0.0);
				if (target instanceof ServerPlayer serverPlayer) {
					serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(serverPlayer));
				}
			}
		}
		return super.hurtEnemy(stack, target, attacker);
	}

	@Override
	public TagKey<Item> getDependencyTag() {
		return (
			Mods.loaded(Mods.AER) ?
			DelightfulItemTags.INGOTS_GRAVITITE :
			DelightfulItemTags.ENCHANTED_GRAVITITE
		);
	}

	@Override
	public @Nullable RecipeType<?> getRecipeType() {
		return null;
	}
}