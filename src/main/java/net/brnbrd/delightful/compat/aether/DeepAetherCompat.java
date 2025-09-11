package net.brnbrd.delightful.compat.aether;

import net.brnbrd.delightful.common.item.knife.Knives;
import net.brnbrd.delightful.common.item.knife.compat.aether.deep_aether.SkyjadeKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.aether.deep_aether.StratusKnifeItem;
import net.minecraft.world.item.Item;
import teamrazor.deepaether.init.DATiers;
import java.util.function.Supplier;

public class DeepAetherCompat {
	public static final Supplier<Item> SKYJADE = () -> new SkyjadeKnifeItem(Knives.props());
	public static final Supplier<Item> STRATUS = () -> new StratusKnifeItem(Knives.props(), DATiers.STRATUS);
}