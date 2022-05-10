package net.brdle.delightful.common;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public enum DelightfulTiers implements Tier {
    COPPER(2, 150, 5.0F, 1.5F, 14, () -> Ingredient.of(Items.COPPER_INGOT)),
    STEEL(2, 484, 6.5F, 2.0F, 16, DelightfulIngredients.steel),
    ENDERITE(5, 2401, 10.0F, 5.0F, 15, DelightfulIngredients.enderite),
    OBSIDIAN_INFUSED_ENDERITE(5, 2771, 11.0F, 6.0F, 15, DelightfulIngredients.obsidianInfusedEnderite);

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