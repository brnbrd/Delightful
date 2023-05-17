package net.brnbrd.delightful.data.gen;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.knife.DelightfulKnifeItem;
import net.brnbrd.delightful.common.item.knife.Knives;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.loaders.ItemLayersModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class DelightfulItemModelProvider extends ItemModelProvider {
    public DelightfulItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Delightful.MODID, existingFileHelper);
    }

    private static final List<ResourceLocation> FLAT_BLOCKS = List.of(
        DelightfulItems.WILD_SALMONBERRIES.getId()
    );
    private static final List<ResourceLocation> ITEM_BLOCKS = List.of(
        DelightfulItems.SALMONBERRY_PIE.getId()
    );
    private static final List<ResourceLocation> EMISSIVE = List.of(
        Knives.FIERY.getId()
    );

    @Override
    protected void registerModels() {
        for (RegistryObject<Item> entry : DelightfulItems.ITEMS.getEntries()) {
            ResourceLocation id = entry.getId();
            if (EMISSIVE.contains(id)) {
                emissive(id);
            } else if (entry.get() instanceof DelightfulKnifeItem) {
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

    public ItemModelBuilder flatBlock(ResourceLocation id) {
        return getBuilder(id.toString()).parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(id.getNamespace(), "block/" + id.getPath()));
    }

    public ItemModelBuilder handheld(ResourceLocation item) {
        return withExistingParent(item.getPath(), "item/handheld").texture("layer0", Util.rl(Delightful.MODID, "item/" + item.getPath()));
    }

    public ItemModelBuilder emissive(ResourceLocation item) {
        return withExistingParent(item.getPath(), "item/handheld")
            .texture("layer0", Util.rl(Delightful.MODID, "item/" + item.getPath()))
            .customLoader(ItemLayersModelBuilder::begin).emissive(0).end();
    }
}