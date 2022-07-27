package net.brdle.delightful.common.item.food;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.item.MelonJuiceItem;
import java.util.function.Supplier;

public class DrinkItem extends MelonJuiceItem {
    private final Supplier<MobEffect> effect;
    private final int duration;
    private final int amplifier;
    private final float heal;
    private final int feed;

    public DrinkItem(Item.Properties properties, Supplier<MobEffect> effect, int duration, int amplifier) {
        super(properties);
        this.effect = effect;
        this.duration = duration;
        this.amplifier = amplifier;
        this.heal = 0.0F;
        this.feed = 0;
    }

    public DrinkItem(Item.Properties properties, Supplier<MobEffect> effect, int duration, int amplifier, float heal) {
        super(properties);
        this.effect = effect;
        this.duration = duration;
        this.amplifier = amplifier;
        this.heal = heal;
        this.feed = 0;
    }

    public DrinkItem(Item.Properties properties, Supplier<MobEffect> effect, int duration, int amplifier, float heal, int feed) {
        super(properties);
        this.effect = effect;
        this.duration = duration;
        this.amplifier = amplifier;
        this.heal = heal;
        this.feed = feed;
    }

    @Override
    public void affectConsumer(ItemStack stack, Level worldIn, LivingEntity consumer) {
        consumer.addEffect(new MobEffectInstance(this.effect.get(), this.duration, this.amplifier));
        if (this.heal > 0.0F) consumer.heal(this.heal);
        if (consumer instanceof ServerPlayer player && this.feed > 0) {
            player.getFoodData().setFoodLevel(Math.min(player.getFoodData().getFoodLevel() + this.feed, 20));
        }
    }
}
