package net.brnbrd.delightful.compat;

import me.codexadrian.spirit.data.ToolType;
import me.codexadrian.spirit.utils.ToolUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import java.util.List;

public class SpiritCompat {

	public static void appendEmpoweredText(ItemStack itemStack, List<Component> list) {
		ToolUtils.appendEmpoweredText(itemStack, list);
	}

	public static void handleOnHitEntity(ItemStack itemStack, LivingEntity victim, LivingEntity attacker) {
		if (attacker instanceof Player p) {
			ToolUtils.handleOnHitEntity(itemStack, ToolType.SWORD, victim, p);
		}
	}
}
