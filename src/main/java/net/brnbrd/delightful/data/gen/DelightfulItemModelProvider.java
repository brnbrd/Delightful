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
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import java.util.List;

public class DelightfulItemModelProvider extends ItemModelProvider {
	private static final List<ResourceLocation> FLAT_BLOCKS = List.of(
			DelightfulItems.WILD_SALMONBERRIES.getId(),
			DelightfulItems.STUFFED_CANTALOUPE_BLOCK.getId()
	);
	private static final List<ResourceLocation> ITEM_BLOCKS = List.of(
			DelightfulItems.SALMONBERRY_PIE.getId(),
			DelightfulItems.BAKLAVA.getId()
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
			if (EMISSIVE.contains(id)) {
				emissive(id);
			} else if (entry.get() instanceof DKnifeItem) {
				handheld(id);
			} else if (FLAT_BLOCKS.contains(id)) {
				flatBlock(id);
			} else if (entry.get() instanceof BlockItem &&
					!(entry.get() instanceof ItemNameBlockItem) &&
					!ITEM_BLOCKS.contains(id)) {
				withExistingParent(id.getPath(), Util.rl(this.modid, "block/" + id.getPath()));
			} else {
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