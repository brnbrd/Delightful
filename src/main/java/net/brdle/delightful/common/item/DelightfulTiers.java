package net.brdle.delightful.common.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;
import java.util.function.Supplier;

public enum DelightfulTiers implements Tier {
    BONE(1, 190, 5.0F, 1.5F, 9, () -> Ingredient.of(Tags.Items.BONES)),
    AMETHYST(2, 44, 15.0F, 0.5F, 1, () -> Ingredient.of(Tags.Items.GEMS_AMETHYST)),
    EMERALD(2, 250, 14.0F, 3.5F, 24, () -> Ingredient.of(Tags.Items.GEMS_EMERALD)),
    COPPER(2, 150, 5.0F, 1.5F, 14, DelightfulItems.getIngot("copper")),
    TIN(1, 120, 13.0F, 1.5F, 18, DelightfulItems.getIngot("tin")),
    STEEL(2, 484, 6.5F, 2.5F, 16, DelightfulItems.getIngot("steel")),
    SILVER(2, 484, 6.0F, 2.0F, 16, DelightfulItems.getIngot("silver")),
    BRASS(2, 484, 6.0F, 2.5F, 16, DelightfulItems.getIngot("brass")),
    ENDERITE(5, 2401, 10.0F, 5.0F, 15, DelightfulItems.getIngot("enderite")),
    OBSIDIAN_INFUSED_ENDERITE(5, 2771, 11.0F, 6.0F, 15, DelightfulItems.getIngot("obsidian_infused_enderite")),
    BRONZE(2, 375, 3.0F, 2.0F, 10, DelightfulItems.getIngot("bronze")),
    OSMIUM(3, 1024, 4.0F, 4.0F, 14, DelightfulItems.getIngot("osmium")),
    REFINED_GLOWSTONE(2, 384, 15.0F, 2.0F, 20, DelightfulItems.getIngot("refined_glowstone")),
    REFINED_OBSIDIAN(6, 4096, 12.0F, 8.0F, 18, DelightfulItems.getIngot("refined_obsidian")),
    LAPIS_LAZULI(1, 128, 4.0F, 1.0F, 32, DelightfulItems.getGem("lapis")),
    LARGE_AMETHYST(4, 2625, 12.0F, 4.0F, 35, DelightfulItems.getGem("large_amethyst")),
    BLACK_OPAL(5, 5250, 16.0F, 5.0F, 15, DelightfulItems.getGem("black_opal")),
    NETHERITE_OPAL(6, 6300, 16.0F, 6.0F, 20, DelightfulItems.getGem("black_opal")),
    CONSTANTAN(2, 250, 5.5F, 2.0F, 10, DelightfulItems.getIngot("constantan")),
    ELECTRUM(2, 96, 13.0F, 2.0F, 28, DelightfulItems.getIngot("electrum")),
    INVAR(2, 300, 7.0F, 2.5F, 13, DelightfulItems.getIngot("invar")),
    LEAD(1, 130, 12.0F, 1.5F, 16, DelightfulItems.getIngot("lead")),
    NICKEL(2, 225, 7.0F, 2.5F, 12, DelightfulItems.getIngot("nickel")),
    IRONWOOD(2, 512, 6.5F, 2, 25, DelightfulItems.getIngot("ironwood")),
    FIERY(4, 1024, 9F, 4, 10, DelightfulItems.getIngot("fiery")),
    STEELEAF(3, 131, 8.0F, 3, 9, DelightfulItems.getIngot("steeleaf")),
    KNIGHTMETAL(3, 512, 8.0F, 3, 8, DelightfulItems.getIngot("knightmetal")),
    LIVING(2, 192, 6.0f, 2.0f, 18, () -> null),
    DRACO_ARCANUS(4, 2661, 12.0F, 7.0F, 20, () -> Ingredient.of(ItemTags.create(new ResourceLocation("forbidden_arcanus", "dragon_scale")))),
    DEORUM(3, 1861, 9.0F, 3.5F, 26, DelightfulItems.getIngot("deorum")),
    REINFORCED_DEORUM(3, 2561, 9.0F, 3.5F, 26, () -> Ingredient.of(ItemTags.create(new ResourceLocation("forbidden_arcanus", "stellarite_piece")))),
    MYTHRIL(Tiers.IRON.getLevel(), 800, 8.0F, 3.0F, 12, DelightfulItems.getIngot("mythril")),
    ADAMANTIUM(Tiers.IRON.getLevel(),1150, 14.0F, 3.0F, 3, DelightfulItems.getIngot("adamantium")),
    ONYX(Tiers.NETHERITE.getLevel(), 3280, 10.0F, 5.0F, 15, DelightfulItems.getIngot("onyx")),
    THYRIUM(Tiers.DIAMOND.getLevel(), 2000, 22.0F, 6.0F, 28, DelightfulItems.getIngot("thyrium")),
    SINISITE(5, 4100, 18.0F, 8.0F, 11, DelightfulItems.getIngot("sinisite"));

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient;

    DelightfulTiers(int level, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = repairIngredient;
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

    @SuppressWarnings("deprecation")
    public int getLevel() {
        return this.level;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public @NotNull Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}