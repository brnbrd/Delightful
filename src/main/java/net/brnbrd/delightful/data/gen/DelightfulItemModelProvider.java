package net.brnbrd.delightful.data.gen;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.knife.DelightfulKnifeItem;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class DelightfulItemModelProvider extends ItemModelProvider {
    public DelightfulItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Delightful.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (var entry : DelightfulItems.ITEMS.getEntries()) {
            ResourceLocation id = entry.getId();
            if (entry.get() instanceof BlockItem && !(entry.get() instanceof ItemNameBlockItem) && !entry.getId().getPath().equals("salmonberry_pie")) {
                withExistingParent(id.getPath(), Util.rl(this.modid, "block/" + id.getPath()));
            } else if (entry.get() instanceof DelightfulKnifeItem) {
                handheld(id);
            } else {
                basicItem(id);
            }
        }
    }

    public ItemModelBuilder handheld(ResourceLocation item) {
        return withExistingParent(item.getPath(), "item/handheld").texture("layer0", Util.rl(Delightful.MODID, "item/" + item.getPath()));
    }
}