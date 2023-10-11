package net.brnbrd.delightful.network;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.compat.BotaniaLCP;
import net.brnbrd.delightful.compat.Mods;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class DPacketHandler {
	interface TriConsumer<T, U, R> {
		void accept(T var1, U var2, R var3);
	}

	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
		Util.rl(Delightful.MODID, "main"),
		() -> PROTOCOL_VERSION,
		PROTOCOL_VERSION::equals,
		PROTOCOL_VERSION::equals
	);

	public static void init() {
		// Serverbound
		if (Mods.loaded(Mods.BTA)) {
			INSTANCE.registerMessage(0, BotaniaLCP.class, BotaniaLCP::encode, BotaniaLCP::decode,
				makeServerBoundHandler(BotaniaLCP::handle));
		}
	}

	private static <T> BiConsumer<T, Supplier<NetworkEvent.Context>> makeServerBoundHandler(TriConsumer<T, MinecraftServer, ServerPlayer> handler) {
		return (m, ctx) -> {
			handler.accept(m, Objects.requireNonNull(ctx.get().getSender()).getServer(), ctx.get().getSender());
			ctx.get().setPacketHandled(true);
		};
	}

	private static <T> BiConsumer<T, Supplier<NetworkEvent.Context>> makeClientBoundHandler(Consumer<T> consumer) {
		return (m, ctx) -> {
			consumer.accept(m);
			ctx.get().setPacketHandled(true);
		};
	}
}
