package net.brdle.delightful.common.config;

import com.google.gson.JsonObject;
import net.brdle.delightful.Delightful;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class FoodEnabledCondition implements ICondition {
    private static final ResourceLocation NAME = new ResourceLocation(Delightful.MODID, "food");
    private final String value;

    public FoodEnabledCondition(String value)
    {
        this.value = value;
    }

    @Override
    public ResourceLocation getID()
    {
        return NAME;
    }

    @Override
    public boolean test(IContext context)
    {
        return this.test();
    }

    /**
     * @deprecated Use {@linkplain #test(IContext) the other more general overload}.
     */
    @Override
    @Deprecated
    public boolean test() {
        return DelightfulConfig.CONFIG.foods.containsKey(this.value) &&
                DelightfulConfig.CONFIG.foods.get(this.value).get();
    }

    @Override
    public String toString()
    {
        return "food(\"" + this.value + "\")";
    }

    public static class Serializer implements IConditionSerializer<FoodEnabledCondition>
    {
        public static final FoodEnabledCondition.Serializer INSTANCE = new FoodEnabledCondition.Serializer();

        @Override
        public void write(JsonObject json, FoodEnabledCondition condition)
        {
            json.addProperty("value", condition.value);
        }

        @Override
        public FoodEnabledCondition read(JsonObject json)
        {
            return new FoodEnabledCondition(GsonHelper.getAsString(json, "value"));
        }

        @Override
        public ResourceLocation getID()
        {
            return FoodEnabledCondition.NAME;
        }
    }
}
