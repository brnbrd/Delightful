package net.brdle.delightful.common.config;

import com.google.gson.JsonObject;
import net.brdle.delightful.Delightful;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class BlockEnabledCondition implements ICondition {
    private static final ResourceLocation NAME = new ResourceLocation(Delightful.MODID, "block");
    private final String value;

    public BlockEnabledCondition(String value)
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
        return DelightfulConfig.CONFIG.blocks.containsKey(this.value) &&
                DelightfulConfig.CONFIG.blocks.get(this.value).get();
    }

    @Override
    public String toString()
    {
        return "block(\"" + this.value + "\")";
    }

    public static class Serializer implements IConditionSerializer<BlockEnabledCondition>
    {
        public static final BlockEnabledCondition.Serializer INSTANCE = new BlockEnabledCondition.Serializer();

        @Override
        public void write(JsonObject json, BlockEnabledCondition condition)
        {
            json.addProperty("value", condition.value);
        }

        @Override
        public BlockEnabledCondition read(JsonObject json)
        {
            return new BlockEnabledCondition(GsonHelper.getAsString(json, "value"));
        }

        @Override
        public ResourceLocation getID()
        {
            return BlockEnabledCondition.NAME;
        }
    }
}
