package net.brnbrd.delightful.compat;

import net.brnbrd.delightful.common.item.knife.Knives;
import net.brnbrd.delightful.common.item.knife.compat.aether.aether_redux.VeridiumKnifeItem;
import net.zepalesque.redux.item.util.ReduxItemTiers;
import java.util.function.Supplier;

public class AetherReduxCompat {
	public static Supplier<VeridiumKnifeItem.Uninfused> VERIDIUM = () -> new VeridiumKnifeItem.Uninfused(ReduxItemTiers.VERIDIUM, Knives.props());
	public static Supplier<VeridiumKnifeItem> INFUSED_VERIDIUM = () -> new VeridiumKnifeItem(ReduxItemTiers.INFUSED_VERIDIUM, Knives.props(), VERIDIUM);
}