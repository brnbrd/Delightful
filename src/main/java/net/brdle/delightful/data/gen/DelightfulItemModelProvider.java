package net.brdle.delightful.data.gen;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.common.item.DelightfulItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class DelightfulItemModelProvider extends ItemModelProvider {
    public DelightfulItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Delightful.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        DelightfulItems.ITEMS.getEntries().forEach(entry -> {
            ResourceLocation id = entry.getId();
            if (entry.get() instanceof BlockItem && !(entry.get() instanceof ItemNameBlockItem)) {
                withExistingParent(id.getPath(), new ResourceLocation(this.modid, "block/" + id.getPath()));
            } else {
                basicItem(id);
            }
        });
    }
}