package net.brnbrd.delightful.common.item.knife.compat.nourished_nether;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.DKnifeItem;
import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class NecroniumKnifeItem extends DKnifeItem {
	public NecroniumKnifeItem(Properties properties) {
		super(DelightfulItemTags.ingot("necronium"), DelightfulTiers.NECRONIUM, properties, Modid.NN);
	}

	@Override
	public List<Component> getTools() {
		return List.of(Component.literal("Afterlife").withStyle(ChatFormatting.AQUA));
	}

	@Override
	public boolean hurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity target, @NotNull LivingEntity attacker) {
		boolean supHurt = super.hurtEnemy(stack, target, attacker);
		ResourceLocation stasisLoc = Util.rl("nourished_nether", "stasis");
		if (
			supHurt &&
			Util.effectExists(stasisLoc) &&
			target.getRandom().nextInt(0, 3) == 0
		) {
			MobEffect stasis = Util.effect(stasisLoc);
			if (stasis != null) target.addEffect(new MobEffectInstance(stasis, 40, 0));
			return true;
		}
		return supHurt;
	}
}