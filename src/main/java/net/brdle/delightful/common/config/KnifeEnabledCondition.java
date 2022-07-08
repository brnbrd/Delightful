package net.brdle.delightful.common.config;

import com.google.gson.JsonObject;
import net.brdle.delightful.Delightful;
import net.minecraft.util.GsonHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class KnifeEnabledCondition implements ICondition {
    private static final ResourceLocation NAME = new ResourceLocation(Delightful.MODID, "knife");
    private final String value;

    public KnifeEnabledCondition(String value)
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
        return DelightfulConfig.CONFIG.knives.containsKey(this.value + "_knife") &&
                DelightfulConfig.CONFIG.knives.get(this.value + "_knife").get();
    }

    @Override
    public String toString()
    {
        return "knife(\"" + this.value + "\")";
    }

    public static class Serializer implements IConditionSerializer<KnifeEnabledCondition>
    {
        public static final KnifeEnabledCondition.Serializer INSTANCE = new KnifeEnabledCondition.Serializer();

        @Override
        public void write(JsonObject json, KnifeEnabledCondition condition)
        {
            json.addProperty("value", condition.value);
        }

        @Override
        public KnifeEnabledCondition read(JsonObject json)
        {
            return new KnifeEnabledCondition(GsonHelper.getAsString(json, "value"));
        }

        @Override
        public ResourceLocation getID()
        {
            return KnifeEnabledCondition.NAME;
        }
    }
}
