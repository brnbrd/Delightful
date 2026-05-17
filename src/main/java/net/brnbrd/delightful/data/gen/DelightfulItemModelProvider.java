package net.brnbrd.delightful.data.gen;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.knife.DKnifeItem;
import net.brnbrd.delightful.common.item.knife.Knives;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import java.util.List;

public class DelightfulItemModelProvider extends ItemModelProvider {
	private static final List<ResourceLocation> HANDHELD = List.of(
		DelightfulItems.MARSHMALLOW_STICK.getId(),
		DelightfulItems.COOKED_MARSHMALLOW_STICK.getId(),
		DelightfulItems.ROCK_CANDY.getId()
	);
	private static final List<ResourceLocation> FLAT_BLOCKS = List.of(
		DelightfulItems.WILD_SALMONBERRIES.getId(),
		DelightfulItems.STUFFED_CANTALOUPE_BLOCK.getId()
	);
	private static final List<ResourceLocation> ITEM_BLOCKS = List.of(
		DelightfulItems.SALMONBERRY_PIE.getId(),
		DelightfulItems.BAKLAVA.getId(),
		DelightfulItems.STURGEON_ROE.getId()
	);
	private static final List<ResourceLocation> EMISSIVE = List.of(
		Knives.FIERY.getId()
	);
	public DelightfulItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
		super(output, Delightful.MODID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		for (RegistryObject<Item> entry : DelightfulItems.ITEMS.getEntries()) {
			ResourceLocation id = entry.getId();
			if (id != null) {
				if (DelightfulItems.NO_GEN.contains(id)) {
					continue;
				} else if (id.getPath().startsWith("strong_")) {
					getBuilder(id.toString())
						.parent(new ModelFile.UncheckedModelFile("item/generated"))
						.texture("layer0", Util.delight("item/" + id.getPath().replace("strong_", "")));
					continue;
				} else if (id.getPath().startsWith("long_")) {
					getBuilder(id.toString())
						.parent(new ModelFile.UncheckedModelFile("item/generated"))
						.texture("layer0", Util.delight("item/" + id.getPath().replace("long_", "")));
					continue;
				} else if (EMISSIVE.contains(id)) {
					emissive(id);
					continue;
				} else if (HANDHELD.contains(id) || entry.get() instanceof DKnifeItem) {
					handheld(id);
					continue;
				} else if (FLAT_BLOCKS.contains(id)) {
					flatBlock(id);
					continue;
				} else if (
					entry.get() instanceof BlockItem b &&
					!(entry.get() instanceof ItemNameBlockItem) &&
					!ITEM_BLOCKS.contains(id)
				) {
					if (b.getBlock() instanceof WallBlock) {
						wallInventory(id.getPath(), Util.rl(id.getNamespace(), "block/" + id.getPath().replace("_wall", "s")));
					} else {
						withExistingParent(id.getPath(), Util.rl(this.modid, "block/" + id.getPath()));
					}
					continue;
				}
				basicItem(id);
			}
		}
	}

	public void flatBlock(ResourceLocation id) {
		getBuilder(id.toString()).parent(new ModelFile.UncheckedModelFile("item/generated"))
			.texture("layer0", new ResourceLocation(id.getNamespace(), "block/" + id.getPath()));
	}

	public void handheld(ResourceLocation item) {
		withExistingParent(item.getPath(), "item/handheld").texture("layer0", Util.delight("item/" + item.getPath()));
	}

	public void emissive(ResourceLocation item) {
		withExistingParent(item.getPath(), "item/handheld")
			.texture("layer0", Util.delight("item/" + item.getPath()))
			.guiLight(BlockModel.GuiLight.FRONT);
	}
}