package net.brnbrd.delightful.common.item.knife.nethers_exoticism;

import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.brnbrd.delightful.data.DelightfulItemTags;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class KiwanoKnifeItem extends CompatKnifeItem {
	public KiwanoKnifeItem(Properties properties) {
		super("nethers_exoticism", DelightfulItemTags.KIWANO_PEEL, DelightfulTiers.KIWANO, properties,
			Component.literal("Burning").withStyle(ChatFormatting.BLUE), null, ChatFormatting.YELLOW);
	}

	@Override
	public boolean hurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity target, @NotNull LivingEntity attacker) {
		boolean result = super.hurtEnemy(stack, target, attacker);
		if (result && !target.getLevel().isClientSide() && !target.fireImmune()) {
			target.setSecondsOnFire(15);
		} else {
			for (int var1 = 0; var1 < 20; ++var1) {
				double px = target.getX() + target.getLevel().getRandom().nextFloat() * target.getBbWidth() * 2.0F - target.getBbWidth();
				double py = target.getY() + target.getLevel().getRandom().nextFloat() * target.getBbHeight();
				double pz = target.getZ() + target.getLevel().getRandom().nextFloat() * target.getBbWidth() * 2.0F - target.getBbWidth();
				target.getLevel().addParticle(ParticleTypes.FLAME, px, py, pz, 0.02, 0.02, 0.02);
			}
		}
		return result;
	}
}