package net.brnbrd.delightful.common.block;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.IConfigured;
import net.minecraft.world.item.crafting.Ingredient;
import vectorwing.farmersdelight.common.block.CabinetBlock;

public class DelightfulCabinetBlock extends CabinetBlock implements IConfigured {
    private final Ingredient ingredient;

    public DelightfulCabinetBlock(Ingredient ingredient, Properties properties) {
        super(properties);
        this.ingredient = ingredient;
    }

    public Ingredient getIngredient() {
        return this.ingredient;
    }

    @Override
    public boolean enabled() {
        return Util.enabled(Util.name(this));
    }
}
