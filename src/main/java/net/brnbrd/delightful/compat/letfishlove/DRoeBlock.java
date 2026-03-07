package net.brnbrd.delightful.compat.letfishlove;

import com.uraneptus.letfishlove.common.blocks.RoeBlock;
import com.uraneptus.letfishlove.core.other.LFLProperties;
import net.brnbrd.delightful.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;

public class DRoeBlock extends RoeBlock {
	public DRoeBlock(Supplier<EntityType<?>> fish) {
		super(fish, LFLProperties.roeBlockProperties());
	}

	public DRoeBlock(@NotNull ResourceLocation fish) {
		super(() -> Util.entity(fish), LFLProperties.roeBlockProperties());
	}
}