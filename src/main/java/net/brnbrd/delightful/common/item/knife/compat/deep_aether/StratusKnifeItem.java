package net.brnbrd.delightful.common.item.knife.compat.deep_aether;

import com.aetherteam.aether.item.combat.abilities.weapon.GravititeWeapon;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import teamrazor.deepaether.init.DATiers;

public class StratusKnifeItem extends DummyStratusKnifeItem implements GravititeWeapon {

	public StratusKnifeItem(Properties props) {
		super(props, DATiers.STRATUS);
	}

	@Override
	public boolean hurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity target, @NotNull LivingEntity attacker) {
		this.launchEntity(target, attacker);
		return super.hurtEnemy(stack, target, attacker);
	}
}
