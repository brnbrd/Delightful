package net.brnbrd.delightful.common.item.knife.compat.nourished_nether;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class NecroniumKnifeItem extends CompatKnifeItem {

	public NecroniumKnifeItem(Properties properties) {
		super("nourished_nether", DelightfulItems.ingot("necronium"), DelightfulTiers.NECRONIUM, properties);
	}

	@Override
	public List<Component> getTools() {
		return List.of(
			Component.literal("Afterlife").withStyle(ChatFormatting.AQUA)
		);
	}

	@Override
	public boolean hurtEnemy(@NotNull ItemStack stack,  @NotNull LivingEntity target, @NotNull LivingEntity attacker) {
		ResourceLocation stasis = Util.rl("nourished_nether", "stasis");
		if (super.hurtEnemy(stack, target, attacker) && target.getRandom().nextInt(1, 4) == 1) {
			if (ForgeRegistries.MOB_EFFECTS.containsKey(stasis)) {
				target.addEffect(new MobEffectInstance(ForgeRegistries.MOB_EFFECTS.getValue(stasis), 40, 0));
			}
			return true;
		}
		return false;
	}
}