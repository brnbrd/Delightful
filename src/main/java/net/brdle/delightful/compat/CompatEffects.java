package net.brdle.delightful.compat;

import com.farmersrespite.core.registry.FREffects;
import net.minecraft.world.effect.MobEffect;
import java.util.function.Supplier;

public class CompatEffects {
  public static final Supplier<MobEffect> CAFFEINATED = () -> FREffects.CAFFEINATED.get();
}
