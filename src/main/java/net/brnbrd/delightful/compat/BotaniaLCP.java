package net.brnbrd.delightful.compat;

import net.brnbrd.delightful.common.item.knife.compat.botania.TerraKnifeItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import vazkii.botania.network.BotaniaPacket;

public class BotaniaLCP implements BotaniaPacket {
	public static final BotaniaLCP INSTANCE = new BotaniaLCP();

	public void handle(MinecraftServer server, ServerPlayer player) {
		// The swing packet will run immediately, so fetch the strength ahead
		float strengthScale = player.getAttackStrengthScale(0F);
		ItemStack stack = player.getMainHandItem();
		if (stack.getItem() instanceof TerraKnifeItem terra) {
			server.execute(() -> terra.handle(player, stack, strengthScale));
		}
	}

	public static BotaniaLCP decode(FriendlyByteBuf buf) {
		return INSTANCE;
	}

	@Override
	public void encode(FriendlyByteBuf friendlyByteBuf) {

	}

	@Override
	public ResourceLocation getFabricId() {
		return null;
	}
}
