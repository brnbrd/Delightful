package net.brnbrd.delightful.common.item.knife.compat.undergarden;

import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.DKnifeItem;
import net.brnbrd.delightful.compat.Mods;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import java.util.List;

public class FroststeelKnifeItem extends DKnifeItem {
	public FroststeelKnifeItem(Properties properties) {
		super(DelightfulItemTags.ingot("froststeel"), DelightfulTiers.FROSTSTEEL, properties, Mods.UG);
		MinecraftForge.EVENT_BUS.addListener(this::onHurt);
	}

	@Override
	public String[] getConflicts() {
		return new String[]{Mods.UGD};
	}

	@Override
	public List<Component> getTools() {
		return List.of(Component.translatable("tooltip.froststeel_sword").withStyle(ChatFormatting.AQUA));
	}

	private void onHurt(LivingHurtEvent e) {
		if (
			this.enabled() &&
			e.getSource().getEntity() instanceof Player player &&
			player.getMainHandItem().is(this)
		) {
			e.getEntity().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600, 1));
		}
	}
}