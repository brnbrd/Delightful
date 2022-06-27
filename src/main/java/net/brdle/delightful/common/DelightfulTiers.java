package net.brdle.delightful.common;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;

public enum DelightfulTiers implements Tier {
    COPPER(2, 150, 5.0F, 1.5F, 14, () -> Ingredient.of(Items.COPPER_INGOT)),
    STEEL(2, 484, 6.5F, 2.5F, 16, DelightfulIngredients.steel),
    ENDERITE(5, 2401, 10.0F, 5.0F, 15, DelightfulIngredients.enderite),
    OBSIDIAN_INFUSED_ENDERITE(5, 2771, 11.0F, 6.0F, 15, DelightfulIngredients.obsidianInfusedEnderite),
    BRONZE(2, 375, 3.0F, 2.0F, 10, DelightfulIngredients.bronze),
    OSMIUM(3, 1024, 4.0F, 4.0F, 14, DelightfulIngredients.osmium),
    REFINED_GLOWSTONE(2, 384, 15.0F, 2.0F, 20, DelightfulIngredients.refinedGlowstone),
    REFINED_OBSIDIAN(6, 4096, 12.0F, 8.0F, 18, DelightfulIngredients.refinedObsidian),
    LAPIS_LAZULI(1, 128, 4.0F, 1.0F, 32, DelightfulIngredients.lapis),
    LARGE_AMETHYST(4, 2625, 12.0F, 4.0F, 35, DelightfulIngredients.largeAmethyst),
    BLACK_OPAL(5, 5250, 16.0F, 5.0F, 15, DelightfulIngredients.blackOpal),
    NETHERITE_OPAL(6, 6300, 16.0F, 6.0F, 20, DelightfulIngredients.blackOpal);

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final java.util.function.Supplier<Ingredient> repairIngredient;

    DelightfulTiers(int level, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = Suppliers.memoize(repairIngredient);
    }

    public int getUses() {
        return this.uses;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getAttackDamageBonus() {
        return this.damage;
    }

    public int getLevel() {
        return this.level;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}