package net.brnbrd.delightful.common.block;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.IConfigured;
import net.minecraft.world.item.crafting.Ingredient;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import java.util.function.Supplier;

public class DelightfulCabinetBlock extends CabinetBlock implements IConfigured {
    private final Supplier<Ingredient> ingredient;

    public DelightfulCabinetBlock(Supplier<Ingredient> ingredient, Properties properties) {
        super(properties);
        this.ingredient = ingredient;
    }

    public Supplier<Ingredient> getIngredient() {
        return this.ingredient;
    }

    @Override
    public boolean isEnabled() {
        return Util.enabled(Util.name(this));
    }
}
