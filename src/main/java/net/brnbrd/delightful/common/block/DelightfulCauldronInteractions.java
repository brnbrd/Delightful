package net.brnbrd.delightful.common.block;

import com.teamabnormals.blueprint.core.api.BlueprintCauldronInteraction;
import com.teamabnormals.neapolitan.core.other.NeapolitanCauldronInteractions;
import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.minecraft.core.cauldron.CauldronInteraction;

public class DelightfulCauldronInteractions {
	public static BlueprintCauldronInteraction MATCHA_MILKSHAKE = BlueprintCauldronInteraction.register(Util.rl(Delightful.MODID, "matcha_milkshake"), CauldronInteraction.newInteractionMap());
	public static BlueprintCauldronInteraction SALMONBERRY_MILKSHAKE = BlueprintCauldronInteraction.register(Util.rl(Delightful.MODID, "salmonberry_milkshake"), CauldronInteraction.newInteractionMap());

	public static void registerCauldronInteractions() {
		NeapolitanCauldronInteractions.addMilkshakeInteractions(DelightfulItems.MATCHA_MILKSHAKE.get(), DelightfulBlocks.MATCHA_MILKSHAKE_CAULDRON.get(), DelightfulItems.MATCHA_ICE_CREAM.get(), MATCHA_MILKSHAKE.map());
		NeapolitanCauldronInteractions.addMilkshakeInteractions(DelightfulItems.SALMONBERRY_MILKSHAKE.get(), DelightfulBlocks.SALMONBERRY_MILKSHAKE_CAULDRON.get(), DelightfulItems.SALMONBERRY_ICE_CREAM.get(), SALMONBERRY_MILKSHAKE.map());
	}
}