package net.brdle.delightful.common.block;

import net.brdle.delightful.Util;
import net.brdle.delightful.common.config.DelightfulConfig;
import net.brdle.delightful.common.item.IConfigured;
import net.brdle.delightful.common.item.ISingleIngredient;
import net.minecraft.world.item.crafting.Ingredient;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import java.util.function.Supplier;

public class DelightfulCabinetBlock extends CabinetBlock implements IConfigured, ISingleIngredient {
    private final Supplier<Ingredient> ingredient;

    public DelightfulCabinetBlock(Supplier<Ingredient> ingredient, Properties properties) {
        super(properties);
        this.ingredient = ingredient;
    }

    @Override
    public Supplier<Ingredient> getIngredient() {
        return this.ingredient;
    }

    @Override
    public boolean isEnabled() {
        return DelightfulConfig.CONFIG.stuff.get(Util.name(this)).get();
    }
}
