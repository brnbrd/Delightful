package net.brdle.delightful.data.gen;

import net.brdle.delightful.Util;
import net.brdle.delightful.common.item.DelightfulItems;
import net.brdle.delightful.common.item.knife.DelightfulKnifeItem;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.utility.TextUtils;
import java.util.function.Consumer;

public class DelightfulAdvancementProvider extends AdvancementProvider {
    public DelightfulAdvancementProvider(DataGenerator generatorIn, ExistingFileHelper fileHelperIn) {
        super(generatorIn, fileHelperIn);
    }

    private String getNameId(String id) {
        return FarmersDelight.MODID + ":" + id;
    }

    /**
     * Override this method for registering and generating custom Advancements.
     * Just use Advancement.Builder to build your Advancements, you don't need an extra consumer like the vanilla classes.
     *
     * @param consumer used for the register function from {@link Advancement.Builder}
     * @param fileHelper used for the register function from {@link Advancement.Builder}
     */
    @Override
    protected void registerAdvancements(Consumer<Advancement> consumer, ExistingFileHelper fileHelper) {
        // root advancement
        Advancement farmersDelight = Advancement.Builder.advancement()
            .display(ModItems.COOKING_POT.get(),
                TextUtils.getTranslation("advancement.root"),
                TextUtils.getTranslation("advancement.root.desc"),
                new ResourceLocation("minecraft:textures/block/bricks.png"),
                FrameType.TASK, false, false, false)
            .addCriterion("seeds", InventoryChangeTrigger.TriggerInstance.hasItems(new ItemLike[]{}))
            .build(new ResourceLocation("farmersdelight", "main/root"));

        // craft_knife
        Advancement.Builder huntAndGatherB = getAdvancement(farmersDelight, ModItems.FLINT_KNIFE.get(), "craft_knife", FrameType.TASK, true, true, false)
            .addCriterion("flint_knife", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FLINT_KNIFE.get()))
            .addCriterion("iron_knife", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_KNIFE.get()))
            .addCriterion("diamond_knife", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DIAMOND_KNIFE.get()))
            .addCriterion("golden_knife", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GOLDEN_KNIFE.get()))
            .addCriterion("netherite_knife", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NETHERITE_KNIFE.get()));
        DelightfulItems.ITEMS.getEntries().stream()
            .map(RegistryObject::get)
            .filter(item -> item instanceof DelightfulKnifeItem)
            .forEach(k -> huntAndGatherB.addCriterion(Util.name(k), InventoryChangeTrigger.TriggerInstance.hasItems(k)));
        Advancement huntAndGather = huntAndGatherB.requirements(RequirementsStrategy.OR).save(consumer, getNameId("main/craft_knife"));
    }

    protected static Advancement.Builder getAdvancement(Advancement parent, ItemLike display, String name, FrameType frame, boolean showToast, boolean announceToChat, boolean hidden) {
        return Advancement.Builder.advancement().parent(parent).display(display,
            TextUtils.getTranslation("advancement." + name),
            TextUtils.getTranslation("advancement." + name + ".desc"),
            null, frame, showToast, announceToChat, hidden);
    }
}
