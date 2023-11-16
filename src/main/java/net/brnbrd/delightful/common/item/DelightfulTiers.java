package net.brnbrd.delightful.common.item;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.compat.Mods;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;
import java.util.Locale;

public enum DelightfulTiers implements Tier {
    BONE(1, 190, 5F, 1.5F, 9, Ingredient.of(Tags.Items.BONES)),
    AMETHYST(2, 44, 15F, 0.5F, 1, Ingredient.of(Tags.Items.GEMS_AMETHYST)),
    EMERALD(2, 250, 14F, 3.5F, 24, Ingredient.of(Tags.Items.GEMS_EMERALD)),
    COPPER(2, 150, 5F, 1.5F, 14, DelightfulItems.getIngot("copper")),
    TIN(1, 120, 13F, 1.5F, 18, DelightfulItems.getIngot("tin")),
    STEEL(2, 484, 6.5F, 2.5F, 16, DelightfulItems.getIngot("steel")),
    SILVER(2, 484, 6F, 2F, 16, DelightfulItems.getIngot("silver")),
    BRASS(2, 484, 6F, 2.5F, 16, DelightfulItems.getIngot("brass")),
    ENDERITE(5, 2401, 10F, 5F, 15, DelightfulItems.getIngot("enderite")),
    OBSIDIAN_INFUSED_ENDERITE(5, 2771, 11F, 6F, 15, DelightfulItems.getIngot("obsidian_infused_enderite")),
    BRONZE(2, 375, 3F, 2F, 10, DelightfulItems.getIngot("bronze")),
    OSMIUM(3, 1024, 4F, 4F, 14, DelightfulItems.getIngot("osmium")),
    REFINED_GLOWSTONE(2, 384, 15F, 2F, 20, DelightfulItems.getIngot("refined_glowstone")),
    REFINED_OBSIDIAN(6, 4096, 12F, 8F, 18, DelightfulItems.getIngot("refined_obsidian")),
    LAPIS_LAZULI(1, 128, 4F, 1F, 32, DelightfulItems.getGem("lapis")),
    LARGE_AMETHYST(4, 2625, 12F, 4F, 35, DelightfulItems.getGem("large_amethyst")),
    BLACK_OPAL(5, 5250, 16F, 5F, 15, DelightfulItems.getGem("black_opal")),
    NETHERITE_OPAL(6, 6300, 16F, 6F, 20, DelightfulItems.getGem("black_opal")),
    CONSTANTAN(2, 250, 5.5F, 2F, 10, DelightfulItems.getIngot("constantan")),
    ELECTRUM(2, 96, 13F, 2F, 28, DelightfulItems.getIngot("electrum")),
    INVAR(2, 300, 7F, 2.5F, 13, DelightfulItems.getIngot("invar")),
    LEAD(1, 130, 12F, 1.5F, 16, DelightfulItems.getIngot("lead")),
    NICKEL(2, 225, 7F, 2.5F, 12, DelightfulItems.getIngot("nickel")),
    IRONWOOD(2, 512, 6.5F, 2, 25, DelightfulItems.getIngot("ironwood")),
    FIERY(4, 1024, 9F, 4, 10, DelightfulItems.getIngot("fiery")),
    STEELEAF(3, 131, 8F, 3, 9, DelightfulItems.getIngot("steeleaf")),
    KNIGHTMETAL(3, 512, 8F, 3, 8, DelightfulItems.getIngot("knightmetal")),
    LIVING(2, 192, 6F, 2F, 18, Ingredient.EMPTY),
    DRACO_ARCANUS(4, 2661, 12F, 7F, 20, Ingredient.of(Util.it("forbidden_arcanus", "dragon_scale"))),
    DEORUM(3, 1861, 9F, 3.5F, 26, DelightfulItems.getIngot("deorum")),
    REINFORCED_DEORUM(3, 2561, 9F, 3.5F, 26, Ingredient.of(Util.it("forbidden_arcanus", "stellarite_piece"))),
    MYTHRIL(Tiers.IRON.getLevel(), 800, 8F, 3F, 12, DelightfulItems.getIngot("mythril")),
    ADAMANTIUM(Tiers.IRON.getLevel(), 1150, 14F, 3F, 3, DelightfulItems.getIngot("adamantium")),
    ONYX(Tiers.NETHERITE.getLevel(), 3280, 10F, 5F, 15, DelightfulItems.getIngot("onyx")),
    THYRIUM(Tiers.DIAMOND.getLevel(), 2000, 22F, 6F, 28, DelightfulItems.getIngot("thyrium")),
    SINISITE(5, 4100, 18F, 8F, 11, DelightfulItems.getIngot("sinisite")),
    ALLTHEMODIUM(5, 15000, 10, 11F, 85, Ingredient.of(DelightfulItemTags.PLATES_ALLTHEMODIUM)),
    PENDORITE(5, 2500, 10F, 4F, 15, Ingredient.of(DelightfulItemTags.INGOTS_PENDORITE)),
    WARDEN(4, 2464, 11F, 7F, 21, Ingredient.of(DelightfulItemTags.REINFORCED_ECHO_SHARD)),
    ZINC(2, 250, 7F, 2F, 11, Ingredient.of(DelightfulItemTags.INGOTS_ZINC)),
    GILDED_QUARTZ(3, 1644, 9F, 4F, 14, Ingredient.of(DelightfulItemTags.POLISHED_ROSE_QUARTZ)),
    EXPERIENCE(2, 270, 7F, 1.5F, 800, Ingredient.of(DelightfulItemTags.HEAP_EXPERIENCE)),
    KIWANO(2, 250, 6F, 2F, 14, Ingredient.of(DelightfulItemTags.KIWANO_PEEL)),
    LEAF(1, 16, 20F, 4F, 14, Ingredient.of(DelightfulItemTags.SHARP_LEAF)),
    BLAZING(3, 450, 12F, 2.5F, 2, Ingredient.of(Tags.Items.INGOTS_GOLD)),
    ADAMANTITE(4, 0, 8F, 3.5F, 15, DelightfulItems.getIngot("adamantite")),
    CRYSTALLINE(2, 183, 1F, 3.5F, 11, Ingredient.of(DelightfulItemTags.CRYSTAL_SPIKE_TIPS)),
    STELLIUM(4, 1337, 7.5F, 5F, 17, Ingredient.of(DelightfulItemTags.INGOTS_STELLIUM)),
    ROSE_GOLD(2, 900, 9F, 2F, 17, Ingredient.of(Tags.Items.INGOTS_COPPER)),
    GILDED_NETHERITE(4, 2031, 10F, 4F, 20, Ingredient.of(Tags.Items.INGOTS_NETHERITE)),
    SOUL_STEEL(3, 200, 9F, 3.5F, 25, Ingredient.EMPTY),
    NECRONIUM(3, 956, 7F, 2F, 2, DelightfulItems.getIngot("necronium")),
    CLOGGRUM(2, 286, 6.0F, 3.0F, 8, DelightfulItems.getIngot("cloggrum")),
    FROSTSTEEL(2, 575, 7.0F, 2.0F, 20, DelightfulItems.getIngot("froststeel")),
    UTHERIUM(3, 1279, 8.5F, 3.5F, 17, DelightfulItems.getIngot("utherium")),
    FORGOTTEN(4, 1876, 8.0F, 3.0F, 2, DelightfulItems.getIngot("forgotten_metal")),
    SKYROOT(0, 59, 2.0F, 0.0F, 15, Ingredient.of(Util.it(Mods.AE, "skyroot_repairing"))),
    HOLYSTONE(1, 131, 4.0F, 1.0F, 5, Ingredient.of(Util.it(Mods.AE, "holystone_repairing"))),
    ZANITE(2, 250, 6.0F, 2.0F, 14, Ingredient.of(Util.it(Mods.AE, "zanite_repairing"))),
    GRAVITITE(3, 1561, 8.0F, 3.0F, 10, Ingredient.of(Util.it(Mods.AE, "gravitite_repairing"))),
    VERIDIUM(2, 750, 2.25F, 1.0F, 0, Ingredient.of(DelightfulItemTags.INGOTS_VERIDIUM)),
    INFUSED_VERIDIUM(2, 750, 7.0F, 1.0F, 0, Ingredient.of(DelightfulItemTags.INGOTS_VERIDIUM));

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Ingredient repairIngredient;

    DelightfulTiers(int level, int uses, float speed, float damage, int enchantmentValue, Ingredient repairIngredient) {
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

    public int getLevel() {
        return this.level;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public @NotNull Ingredient getRepairIngredient() {
        return this.repairIngredient;
    }

    public static Tier get(String name) {
        return valueOf(name.toUpperCase(Locale.ROOT));
    }
}