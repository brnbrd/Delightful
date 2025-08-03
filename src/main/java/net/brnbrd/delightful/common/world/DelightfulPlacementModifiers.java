package net.brnbrd.delightful.common.world;

import com.mojang.serialization.Codec;
import net.brnbrd.delightful.Delightful;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class DelightfulPlacementModifiers {
	public static final DeferredRegister<PlacementModifierType<?>> PLACEMENT_MODIFIERS = DeferredRegister.create(BuiltInRegistries.PLACEMENT_MODIFIER_TYPE.key(), Delightful.MODID);

	public static final RegistryObject<PlacementModifierType<ConfigEnabledFilter>> ENABLED = PLACEMENT_MODIFIERS.register("enabled", () -> typeConvert(ConfigEnabledFilter.CODEC));

	private static <P extends PlacementModifier> PlacementModifierType<P> typeConvert(Codec<P> codec) {
		return () -> codec;
	}

	public static void create(IEventBus bus) {
		PLACEMENT_MODIFIERS.register(bus);
	}
}