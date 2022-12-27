package net.brdle.delightful.common.crafting;

import com.google.gson.JsonObject;
import net.brdle.delightful.Delightful;
import net.brdle.delightful.Util;
import net.brdle.delightful.common.config.DelightfulConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class EnabledCondition implements ICondition {
    private static final ResourceLocation NAME = Util.rl(Delightful.MODID, "enabled");
    private final String value;

    public EnabledCondition(String value) {
        this.value = value;
    }

    @Override
    public ResourceLocation getID() {
        return NAME;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean test() {
        return DelightfulConfig.CONFIG.stuff.containsKey(this.value) &&
            DelightfulConfig.CONFIG.stuff.get(this.value).get();
    }

    @Override
    public boolean test(IContext context) {
        return DelightfulConfig.CONFIG.stuff.containsKey(this.value) &&
          DelightfulConfig.CONFIG.stuff.get(this.value).get();
    }

    @Override
    public String toString()
    {
        return "enabled(\"" + this.value + "\")";
    }

    public static class Serializer implements IConditionSerializer<EnabledCondition> {
        public static final EnabledCondition.Serializer INSTANCE = new EnabledCondition.Serializer();

        @Override
        public void write(JsonObject json, EnabledCondition condition) {
            json.addProperty("value", condition.value);
        }

        @Override
        public EnabledCondition read(JsonObject json) {
            return new EnabledCondition(GsonHelper.getAsString(json, "value"));
        }

        @Override
        public ResourceLocation getID() {
            return EnabledCondition.NAME;
        }
    }
}
