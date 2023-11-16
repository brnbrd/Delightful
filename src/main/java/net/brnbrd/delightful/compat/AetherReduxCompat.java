package net.brnbrd.delightful.compat;

import net.brnbrd.delightful.common.item.knife.Knives;
import net.brnbrd.delightful.common.item.knife.compat.aether_redux.VeridiumKnifeItem;
import net.minecraft.world.item.Item;
import net.zepalesque.redux.item.util.ReduxItemTiers;
import java.util.function.Supplier;

public class AetherReduxCompat {
	public static Supplier<Item> VERIDIUM = () -> new VeridiumKnifeItem(ReduxItemTiers.VERIDIUM, Knives.props());
	public static Supplier<Item> INFUSED_VERIDIUM = () -> new VeridiumKnifeItem(ReduxItemTiers.INFUSED_VERIDIUM, Knives.props());
}
