package net.brnbrd.delightful.common.item.knife.compat.undergarden;

import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.brnbrd.delightful.compat.Mods;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import java.util.List;

public class FroststeelKnifeItem extends CompatKnifeItem {
	public FroststeelKnifeItem(Properties properties) {
		super(Mods.UG, DelightfulItems.ingot("froststeel"), DelightfulTiers.FROSTSTEEL, properties);
		MinecraftForge.EVENT_BUS.addListener(this::onHurt);
	}

	@Override
	public List<Component> getTools() {
		return List.of(Component.translatable("tooltip.froststeel_sword").withStyle(ChatFormatting.AQUA));
	}

	private void onHurt(LivingHurtEvent e) {
		if (
			this.isEnabled() &&
			e.getSource().getEntity() instanceof Player player &&
			player.getMainHandItem().is(this)
		) {
			e.getEntity().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600, 1));
		}
	}
}
