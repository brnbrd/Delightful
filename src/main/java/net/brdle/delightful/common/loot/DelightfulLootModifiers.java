package net.brdle.delightful.common.loot;

import com.mojang.serialization.Codec;
import net.brdle.delightful.Delightful;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DelightfulLootModifiers {

	private static final DeferredRegister<Codec<? extends IGlobalLootModifier>> GLM = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Delightful.MODID);

	public static final RegistryObject<Codec<AddItemLootModifier>> ADD_ITEM = GLM.register("add_item", () -> AddItemLootModifier.CODEC);
	public static final RegistryObject<Codec<CompatAddItemLootModifier>> COMPAT_ADD_ITEM = GLM.register("compat_add_item", () -> CompatAddItemLootModifier.CODEC);
	public static final RegistryObject<Codec<SmeltLootModifier>> SMELT = GLM.register("smelt", () -> SmeltLootModifier.CODEC);

	public static void create(IEventBus bus) {
		GLM.register(bus);
	}

}
