package net.brnbrd.delightful.common.item.knife.compat.twilightforest;

import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.brnbrd.delightful.common.item.knife.Knives;
import net.brnbrd.delightful.compat.Mods;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import java.util.List;

public class KnightmetalKnifeItem extends CompatKnifeItem {
	public KnightmetalKnifeItem(Properties properties) {
		super(Mods.TF, DelightfulItems.ingot("knightmetal"), DelightfulTiers.KNIGHTMETAL, properties);
		MinecraftForge.EVENT_BUS.addListener(this::onHurt);
	}

	private void onHurt(LivingHurtEvent e) {
		LivingEntity target = e.getEntity();
		if (!target.level().isClientSide() && e.getSource().getDirectEntity() instanceof LivingEntity attacker) {
			ItemStack weapon = attacker.getMainHandItem();
			if (!weapon.isEmpty() && target.getArmorValue() > 0 && weapon.is(Knives.KNIGHTMETAL.get())) {
				if (target.getArmorCoverPercentage() > 0) {
					int moreBonus = (int) (2 * target.getArmorCoverPercentage());
					e.setAmount(e.getAmount() + moreBonus);
				} else {
					e.setAmount(e.getAmount() + 2);
				}
				((ServerLevel) target.level()).getChunkSource().broadcastAndSend(target, new ClientboundAnimatePacket(target, 5));
			}
		}
	}

	@Override
	public List<Component> getTools() {
		return List.of(
			Component.translatable("item." + Mods.TF + ".knightmetal_sword.tooltip").withStyle(ChatFormatting.GRAY)
		);
	}
}
