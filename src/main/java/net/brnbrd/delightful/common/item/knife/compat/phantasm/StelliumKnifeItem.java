package net.brnbrd.delightful.common.item.knife.compat.phantasm;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.brnbrd.delightful.common.item.knife.Knives;
import net.brnbrd.delightful.compat.Mods;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class StelliumKnifeItem extends CompatKnifeItem {
	public StelliumKnifeItem(Properties properties) {
		super(Mods.EP, DelightfulItemTags.STELLIUM_INGOT, DelightfulTiers.STELLIUM, properties);
		MinecraftForge.EVENT_BUS.addListener(this::onHurt);
	}

	private void onHurt(LivingHurtEvent e) {
		LivingEntity target = e.getEntity();
		if (!target.getLevel().isClientSide() && e.getSource().getDirectEntity() instanceof LivingEntity attacker) {
			ItemStack weapon = attacker.getMainHandItem();
			if (!weapon.isEmpty() &&
				weapon.is(Knives.STELLIUM.get()) &&
				attacker.getLevel().dimension() != Level.END &&
				!(Util.hasTagString(weapon, "socket", "starlit"))) {
				e.setAmount(Math.min(1.0F, e.getAmount()));
			}
		}
	}
}