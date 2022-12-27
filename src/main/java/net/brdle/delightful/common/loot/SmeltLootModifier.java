package net.brdle.delightful.common.loot;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.items.ItemHandlerHelper;
import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

//Code adapted from https://github.com/MinecraftForge/MinecraftForge/blob/1.18.x/src/test/java/net/minecraftforge/debug/gameplay/loot/GlobalLootModifiersTest.java
//LICENSE: https://github.com/MinecraftForge/MinecraftForge/blob/1.18.x/LICENSE.txt

public class SmeltLootModifier extends LootModifier {
    public SmeltLootModifier(LootItemCondition[] conditions) {
        super(conditions);
    }

    @Nonnull
    @Override
    public List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        return generatedLoot.stream().map(stack -> {
                var smelted = context.getLevel().getRecipeManager().getRecipeFor(
                        RecipeType.SMELTING, new SimpleContainer(stack), context.getLevel()
                    ).map(SmeltingRecipe::getResultItem)
                    .filter(itemStack -> !itemStack.isEmpty())
                    .map(itemStack -> ItemHandlerHelper.copyStackWithSize(itemStack, stack.getCount() * itemStack.getCount()))
                    .orElse(stack);
                if (smelted != stack) {
                    ExperienceOrb.award(context.getLevel(), context.getParam(LootContextParams.ORIGIN), context.getRandom().nextInt(3) + 1);
                }
                return smelted;
            }
        ).collect(Collectors.toList());
    }

    public static class Serializer extends GlobalLootModifierSerializer<SmeltLootModifier> {

        @Override
        public SmeltLootModifier read(ResourceLocation name, JsonObject json, LootItemCondition[] conditions) {
            return new SmeltLootModifier(conditions);
        }

        @Override
        public JsonObject write(SmeltLootModifier instance) {
            return makeConditions(instance.conditions);
        }
    }
}