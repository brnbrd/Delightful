package net.brnbrd.delightful.data.gen;

import net.brnbrd.delightful.common.block.*;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import java.util.Collections;
import java.util.List;

public class DelightfulBlockLoot extends BlockLootSubProvider {

    private final static List<ResourceLocation> NO_GEN = List.of(
        DelightfulBlocks.WILD_SALMONBERRIES.getId()
    );

    protected DelightfulBlockLoot() {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        // Cabinets
        DelightfulBlocks.BLOCKS.getEntries().stream()
            .map(RegistryObject::get)
            .filter(block -> block instanceof DelightfulCabinetBlock)
            .forEach(this::dropSelf);

        // Pies
        this.empty(DelightfulBlocks.SALMONBERRY_PIE);
        this.empty(DelightfulBlocks.PUMPKIN_PIE);
        this.empty(DelightfulBlocks.SOURCE_BERRY_PIE);
        this.empty(DelightfulBlocks.GLOOMGOURD_PIE);
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

        // Salmonberry Bush drops Pips and optional Berry
        this.add(DelightfulBlocks.SALMONBERRY_BUSH.get(), (b) -> {
                LootTable.Builder loot = LootTable.lootTable().withPool(
                    LootPool.lootPool().add(LootItem.lootTableItem(DelightfulItems.SALMONBERRY_PIPS.get()))
                );
                for (int i = 2; i <= SalmonberryBushBlock.MAX_AGE; i++) {
                    loot = loot.withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(b)
                            .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SalmonberryBushBlock.AGE, i)))
                        .add(LootItem.lootTableItem(DelightfulItems.SALMONBERRIES.get()))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, (float) i - 1))));
                }
                return applyExplosionDecay(b, loot);
            }
        );

        // Crates
        this.dropSelf(DelightfulBlocks.SALMONBERRY_SACK.get());
        this.dropSelf(DelightfulBlocks.ACORN_SACK.get());
        this.dropSelf(DelightfulBlocks.BLUEBERRY_SACK.get());
        this.dropSelf(DelightfulBlocks.CRIMSON_BERRY_SACK.get());
        this.dropSelf(DelightfulBlocks.NIGHTSHADE_BERRY_SACK.get());
        this.dropSelf(DelightfulBlocks.GREEN_APPLE_CRATE.get());
        this.dropSelf(DelightfulBlocks.JOSHUA_FRUIT_CRATE.get());
        this.dropSelf(DelightfulBlocks.BAOBAB_FRUIT_CRATE.get());

        // Ice Cream Blocks
        this.dropSelf(DelightfulBlocks.SALMONBERRY_ICE_CREAM_BLOCK.get());
        this.dropSelf(DelightfulBlocks.MATCHA_ICE_CREAM_BLOCK.get());
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return DelightfulBlocks.BLOCKS.getEntries()
            .stream()
            .filter(o -> !NO_GEN.contains(o.getId()))
            .flatMap(RegistryObject::stream)
            ::iterator;
    }

    public void empty(RegistryObject<Block> block) {
        this.add(block.get(), LootTable.lootTable());
    }
}
