package net.brdle.delightful.common.loot;

import net.brdle.delightful.Delightful;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DelightfulLootModifiers {

	private static final DeferredRegister<GlobalLootModifierSerializer<?>> GLM = DeferredRegister.create(ForgeRegistries.Keys.LOOT_MODIFIER_SERIALIZERS, Delightful.MODID);

	public static final RegistryObject<AddItemLootModifier.Serializer> ADD_ITEM = GLM.register("add_item", AddItemLootModifier.Serializer::new);

	public static void create(IEventBus bus) {
		GLM.register(bus);
	}

}
