package net.brnbrd.delightful.data.gen;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.knife.DelightfulKnifeItem;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.utility.TextUtils;
import java.util.function.Consumer;

public class DelightfulAdvancementProvider implements ForgeAdvancementProvider.AdvancementGenerator {

    @Override
    public void generate(HolderLookup.@NotNull Provider registries, @NotNull Consumer<Advancement> consumer, @NotNull ExistingFileHelper existingFileHelper) {
        Advancement root = Advancement.Builder.advancement()
            .display(ModItems.COOKING_POT.get(),
                TextUtils.getTranslation("advancement.root"),
                TextUtils.getTranslation("advancement.root.desc"),
                new ResourceLocation("minecraft:textures/block/bricks.png"),
                FrameType.TASK, false, false, false)
            .addCriterion("seeds", InventoryChangeTrigger.TriggerInstance.hasItems(new ItemLike[]{}))
            .save(consumer, FarmersDelight.MODID + ":main/root");

        // craft_knife
        Advancement.Builder huntAndGatherB = getAdvancement(root, ModItems.FLINT_KNIFE.get(), "craft_knife", FrameType.TASK, true, true, false)
            .addCriterion("flint_knife", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FLINT_KNIFE.get()))
            .addCriterion("iron_knife", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_KNIFE.get()))
            .addCriterion("diamond_knife", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DIAMOND_KNIFE.get()))
            .addCriterion("golden_knife", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GOLDEN_KNIFE.get()))
            .addCriterion("netherite_knife", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NETHERITE_KNIFE.get()));
        DelightfulItems.ITEMS.getEntries().stream()
            .map(RegistryObject::get)
            .filter(item -> item instanceof DelightfulKnifeItem)
            .forEach(k -> huntAndGatherB.addCriterion(Util.name(k), InventoryChangeTrigger.TriggerInstance.hasItems(k)));
        huntAndGatherB.requirements(RequirementsStrategy.OR).save(consumer, FarmersDelight.MODID + ":main/craft_knife");
    }

    protected static Advancement.Builder getAdvancement(Advancement parent, ItemLike display, String name, FrameType frame, boolean showToast, boolean announceToChat, boolean hidden) {
        return Advancement.Builder.advancement().parent(parent).display(display,
            TextUtils.getTranslation("advancement." + name),
            TextUtils.getTranslation("advancement." + name + ".desc"),
            null, frame, showToast, announceToChat, hidden);
    }
}
