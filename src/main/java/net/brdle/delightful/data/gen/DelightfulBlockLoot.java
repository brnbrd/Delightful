package net.brdle.delightful.data.gen;

import net.brdle.delightful.common.block.*;
import net.brdle.delightful.common.item.DelightfulItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

public class DelightfulBlockLoot extends BlockLoot {

    @Override
    protected void addTables() {
        // Cabinets
        DelightfulBlocks.BLOCKS.getEntries().stream()
                .map(RegistryObject::get)
                .filter(block -> block instanceof DelightfulCabinetBlock)
                .forEach(this::dropSelf);

        this.dropSelf(DelightfulBlocks.PIZZA_PEEL.get());
        this.dropSelf(DelightfulBlocks.PIZZA_STONE.get());
        this.add(DelightfulBlocks.SALMONBERRY_PIE.get(), LootTable.lootTable());
        this.add(DelightfulBlocks.PUMPKIN_PIE.get(), LootTable.lootTable());
        this.dropSelf(DelightfulBlocks.MINI_MELON.get());
        this.dropSelf(DelightfulBlocks.CANTALOUPE.get());
        this.add(DelightfulBlocks.SLICED_MINI_MELON.get(), (b) -> {
                LootTable.Builder loot = LootTable.lootTable();
                int maxbites = ((SlicedMiniMelonBlock)DelightfulBlocks.SLICED_MINI_MELON.get()).getMaxBites();
                for (int i = 1; i <= maxbites; i++) {
                    final float left = (float) i;
                    loot = loot.withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(b)
                            .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlicedMiniMelonBlock.BITES, i)))
                        .add(LootItem.lootTableItem(((SlicedMiniMelonBlock) b).getSliceItem().getItem()))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, ((float)maxbites) - left + 1.0F))));
                }
                return applyExplosionDecay(b, loot);
            }
        );
        this.add(DelightfulBlocks.SLICED_CANTALOUPE.get(), (b) -> {
                LootTable.Builder loot = LootTable.lootTable();
                int maxbites = ((SlicedMiniMelonBlock)DelightfulBlocks.SLICED_CANTALOUPE.get()).getMaxBites();
                for (int i = 1; i <= maxbites; i++) {
                    final float left = (float) i;
                    loot = loot.withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(b)
                            .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlicedMiniMelonBlock.BITES, i)))
                        .add(LootItem.lootTableItem(((SlicedMiniMelonBlock) b).getSliceItem().getItem()))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, ((float)maxbites) - left + 1.0F))));
                }
                return applyExplosionDecay(b, loot);
            }
        );
        this.add(DelightfulBlocks.SLICED_MELON.get(), (b) -> {
                LootTable.Builder loot = LootTable.lootTable();
                int maxbites = ((SlicedMelonBlock)DelightfulBlocks.SLICED_MELON.get()).getMaxBites();
                for (int i = 1; i <= maxbites; i++) {
                    final float left = (float) i;
                    loot = loot.withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(b)
                            .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlicedMelonBlock.BITES, i)))
                        .add(LootItem.lootTableItem(((SlicedMelonBlock) b).getSliceItem().getItem()))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, ((float)maxbites) - left + 1.0F))));
                }
                return applyExplosionDecay(b, loot);
            }
        );
        this.add(DelightfulBlocks.SLICED_PUMPKIN.get(), (b) -> {
                LootTable.Builder loot = LootTable.lootTable();
                int maxbites = ((SlicedPumpkinBlock)DelightfulBlocks.SLICED_PUMPKIN.get()).getMaxBites();
                for (int i = 1; i <= maxbites; i++) {
                    final float left = (float) i;
                    loot = loot.withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(b)
                            .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlicedPumpkinBlock.BITES, i)))
                        .add(LootItem.lootTableItem(((SlicedPumpkinBlock) b).getSliceItem().getItem()))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, ((float)maxbites) - left + 1.0F))));
                }
                return applyExplosionDecay(b, loot);
            }
        );
        this.dropSelf(DelightfulBlocks.SALMONBERRY_SACK.get());
        this.dropSelf(DelightfulBlocks.ACORN_SACK.get());
        this.add(DelightfulBlocks.WILD_SALMONBERRIES.get(), (p_124096_) ->
            applyExplosionDecay(p_124096_, LootTable.lootTable()
                .withPool(LootPool.lootPool()
                    .add(LootItem.lootTableItem(DelightfulItems.SALMONBERRIES.get()))
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                    .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));
        this.dropSelf(DelightfulBlocks.SALMONBERRY_BUSH.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return DelightfulBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
