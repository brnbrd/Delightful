package net.brnbrd.delightful.data.gen;

import net.brnbrd.delightful.common.block.*;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class DelightfulBlockLoot extends BlockLoot {

    @Override
    protected void addTables() {
        // Cabinets
        DelightfulBlocks.BLOCKS.getEntries().stream()
                .map(RegistryObject::get)
                .filter(block -> block instanceof DelightfulCabinetBlock)
                .forEach(this::dropSelf);

        // Pies
        this.empty(DelightfulBlocks.SALMONBERRY_PIE);
        this.empty(DelightfulBlocks.PUMPKIN_PIE);
        this.empty(DelightfulBlocks.SOURCE_BERRY_PIE);
        this.empty(DelightfulBlocks.GREEN_APPLE_PIE);
        this.empty(DelightfulBlocks.BLUEBERRY_PIE);
        this.empty(DelightfulBlocks.CRIMSON_BERRY_PIE);
        this.empty(DelightfulBlocks.NIGHTSHADE_BERRY_PIE);

        // Whole melons
        this.dropSelf(DelightfulBlocks.MINI_MELON.get());
        this.dropSelf(DelightfulBlocks.CANTALOUPE.get());

        // Sliced
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

        // Sacks
        this.dropSelf(DelightfulBlocks.SALMONBERRY_SACK.get());
        this.dropSelf(DelightfulBlocks.ACORN_SACK.get());

        // Salmonberry Bush drops Pips
        this.dropSelf(DelightfulBlocks.SALMONBERRY_BUSH.get());
        this.empty(DelightfulBlocks.WILD_SALMONBERRIES);

        // Ice Cream Blocks
        this.dropSelf(DelightfulBlocks.SALMONBERRY_ICE_CREAM_BLOCK.get());
        this.dropSelf(DelightfulBlocks.MATCHA_ICE_CREAM_BLOCK.get());
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return DelightfulBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

    public void empty(RegistryObject<Block> block) {
        this.add(block.get(), LootTable.lootTable());
    }
}
