package net.brnbrd.delightful.common.item.knife.compat.aether;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.compat.Mods;
import net.brnbrd.delightful.data.tags.DelightfulEntityTags;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

public class HolystoneKnifeItem extends AetherKnifeItem {
	public HolystoneKnifeItem(Properties properties) {
		super(DelightfulItemTags.HOLYSTONE, DelightfulTiers.HOLYSTONE, properties);
	}

	@Override
	public boolean hurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity target, @NotNull LivingEntity attacker) {
		if (!(attacker instanceof Player player) || player.getAttackStrengthScale(1.0F) == 1.0F) {
			if (!target.getType().is(DelightfulEntityTags.NO_AMBROSIUM_DROPS) && target.level().getRandom().nextInt(25) == 0) {
				Item ambrosium = ForgeRegistries.ITEMS.getValue(Util.rl(Mods.AE, "ambrosium_shard"));
				if (Mods.loaded(Mods.AE) && ambrosium != null) {
					target.spawnAtLocation(ambrosium);
				}
			}
		}
		return super.hurtEnemy(stack, target, attacker);
	}
}
