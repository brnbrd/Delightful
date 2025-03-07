package net.brnbrd.delightful.common.events;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.block.DelightfulCauldronInteractions;
import net.brnbrd.delightful.common.crafting.EnabledCondition;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.IConfigured;
import net.brnbrd.delightful.common.item.knife.DKnifeItem;
import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.brnbrd.delightful.network.DPacketHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.common.crafting.CompoundIngredient;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.forgespi.locating.IModFile;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.tags.ITagManager;
import net.minecraftforge.resource.PathPackResources;
import org.apache.commons.lang3.StringUtils;
import vectorwing.farmersdelight.common.registry.ModCreativeTabs;
import java.util.Collections;

public class ModEvents {

	@SubscribeEvent
	void setup(FMLCommonSetupEvent e) {
		DPacketHandler.init(); // Botania
		e.enqueueWork(() -> {
			// Flammables

			// Compostables
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.ACORN.get(), 0.3F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.ROASTED_ACORN.get(), 0.3F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.SALMONBERRIES.get(), 0.3F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.SALMONBERRY_PIPS.get(), 0.3F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.SALMONBERRY_PIE.get(), 1F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.SALMONBERRY_PIE_SLICE.get(), 0.85F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.PUMPKIN_PIE_SLICE.get(), 0.85F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.MULBERRY_PIE_SLICE.get(), 0.85F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.PASSION_FRUIT_TART_SLICE.get(), 0.85F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.BAKLAVA.get(), 1F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.BAKLAVA_SLICE.get(), 0.85F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.CHORUS_MUFFIN.get(), 1F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.CHORUS_PIE_SLICE.get(), 0.85F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.GLOOMGOURD_PIE_SLICE.get(), 0.85F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.BLUEBERRY_PIE_SLICE.get(), 0.85F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.GREEN_APPLE_PIE_SLICE.get(), 0.85F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.SOURCE_BERRY_PIE_SLICE.get(), 0.85F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.SOURCE_BERRY_COOKIE.get(), 0.85F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.GREEN_TEA_LEAF.get(), 0.3F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.MATCHA.get(), 0.6F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.CHOPPED_CLOVER.get(), 0.5F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.CACTUS_FLESH.get(), 0.25F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.CACTUS_STEAK.get(), 0.25F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.CANTALOUPE_SLICE.get(), 0.6F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.CANTALOUPE_SEEDS.get(), 0.3F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.MINI_MELON.get(), 0.65F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.CANTALOUPE.get(), 0.75F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.WILD_SALMONBERRIES.get(), 0.65F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.SMORE.get(), 1F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.ACORN_SACK.get(), 1F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.SALMONBERRY_SACK.get(), 1F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.BLUEBERRY_SACK.get(), 1F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.MENDOSTEEN_CRATE.get(), 1F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.BASTION_FRUIT_CRATE.get(), 1F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.FROSTAYA_CRATE.get(), 1F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.BOMBEGRANATE_CRATE.get(), 1F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.GREEN_APPLE_CRATE.get(), 1F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.YUCCA_FRUIT_CRATE.get(), 1F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.BAOBAB_FRUIT_CRATE.get(), 1F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.CANTALOUPE_BREAD.get(), 1F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.STUFFED_CANTALOUPE_BLOCK.get(), 1F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.SALMONBERRY_GUMMY.get(), 0.65F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.MATCHA_GUMMY.get(), 0.65F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.CANTALOUPE_GUMMY.get(), 0.65F);
			ComposterBlock.COMPOSTABLES.put(DelightfulItems.SOURCE_BERRY_GUMMY.get(), 0.65F);

			// Animal Foods
			Chicken.FOOD_ITEMS = CompoundIngredient.of(
					Chicken.FOOD_ITEMS,
					Util.ing(DelightfulItems.SALMONBERRY_PIPS),
					Util.ing(DelightfulItems.CANTALOUPE_SEEDS)
			);
			Collections.addAll(
					Parrot.TAME_FOOD,
					DelightfulItems.SALMONBERRY_PIPS.get(),
					DelightfulItems.CANTALOUPE_SEEDS.get()
			);
			if (Modid.N.loaded()) {
				DelightfulCauldronInteractions.registerCauldronInteractions();
			}
		});
	}

	// Adds Delightful conditions
	@SubscribeEvent
	void registerSerializers(RegisterEvent e) {
		if (e.getRegistryKey() == ForgeRegistries.RECIPE_SERIALIZERS.getRegistryKey()) {
			CraftingHelper.register(EnabledCondition.Serializer.INSTANCE);
		}
	}

	@SubscribeEvent
	public void buildContents(BuildCreativeModeTabContentsEvent event) {
		IForgeRegistry<Item> reg = ForgeRegistries.ITEMS;
		ITagManager<Item> tags = reg.tags();
		ResourceLocation TOAST_WITH_BLUEBERRIES = Util.rl(Modid.MOD, "toast_with_blueberries");
		// Delightful Items
		if (event.getTabKey() == ModCreativeTabs.TAB_FARMERS_DELIGHT.getKey()) {
			DelightfulItems.ITEMS.getEntries().stream().filter(RegistryObject::isPresent).forEach((item) -> {
				Item i = item.get();
				ItemStack inst = i instanceof DKnifeItem ? ((DKnifeItem) i).getCreativeItem() : new ItemStack(i);
				if (
					(i instanceof IConfigured conf && conf.enabled() && !inst.isEmpty()) ||
					!(i instanceof IConfigured)
				) {
					event.accept(inst, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
				}
			});
		} else if (
			Modid.MOD.loaded() &&
			event.getTabKey().location().getNamespace().equals(Modid.MOD.get()) &&
			tags != null
		) {
			if (
				!Modid.NF.loaded() &&
				reg.containsKey(TOAST_WITH_BLUEBERRIES) &&
				tags.isKnownTagName(DelightfulItemTags.FRUITS_BLUEBERRIES)
			) {
				Item toast = reg.getValue(TOAST_WITH_BLUEBERRIES);
				if (toast != null) event.accept(toast);
			}
		}
	}

	@SubscribeEvent
	public void addPackFinders(AddPackFindersEvent e) {
		if (e.getPackType() == PackType.CLIENT_RESOURCES) {
			String name = "overhauls";
			String path = Delightful.MODID + ":" + name;
			e.addRepositorySource(consumer -> {
				IModFile file = ModList.get().getModFileById(Delightful.MODID).getFile();
				try (PathPackResources packResources = new PathPackResources(path, true, file.findResource("builtin/" + name))) {
					consumer.accept(Pack.readMetaAndCreate(
						path,
						Component.literal(StringUtils.capitalize(Delightful.MODID) + " ")
							.append(Component.translatable(Delightful.MODID + "." + name)),
						false,
						(id) -> packResources,
						PackType.CLIENT_RESOURCES,
						Pack.Position.TOP,
						PackSource.BUILT_IN
					));
				}
			});
		}
	}
}