package net.brdle.delightful.data;

import net.brdle.delightful.common.block.DelightfulBlocks;
import net.brdle.delightful.common.block.DelightfulCabinetBlock;
import net.brdle.delightful.common.item.DelightfulItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
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
        this.dropSelf(DelightfulBlocks.MINI_MELON.get());
        this.dropSelf(DelightfulBlocks.SALMONBERRY_SACK.get());
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
