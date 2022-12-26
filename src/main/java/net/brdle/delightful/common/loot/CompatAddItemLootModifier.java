package net.brdle.delightful.common.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.brdle.delightful.compat.Mods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

public class CompatAddItemLootModifier extends AddItemLootModifier {
	public static final Codec<CompatAddItemLootModifier> CODEC = RecordCodecBuilder.create(inst -> codecStart(inst)
		.and(ForgeRegistries.ITEMS.getCodec().fieldOf("item").forGetter(g -> g.item))
		.and(Codec.INT.fieldOf("minAmount").forGetter(g -> g.minAmount))
		.and(Codec.INT.fieldOf("maxAmount").forGetter(g -> g.maxAmount))
		.and(Codec.BOOL.fieldOf("unique").forGetter(g -> g.unique))
		.and(Codec.STRING.fieldOf("modid").forGetter(g -> g.modid))
		.and(Codec.BOOL.fieldOf("enable").forGetter(g -> g.enable))
		.apply(inst, CompatAddItemLootModifier::new));
	String modid;
	boolean enable;

	public CompatAddItemLootModifier(LootItemCondition[] conditions, Item item, int minAmount, int maxAmount, boolean unique, String modid, boolean enable) {
		super(conditions, item, minAmount, maxAmount, unique);
		this.modid = modid;
		this.enable = enable;
	}

	@Override
	protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
		if (Mods.loaded(this.modid) && this.enable || ((!Mods.loaded(this.modid)) && !this.enable)) {
			return super.doApply(generatedLoot, context);
		}
		return generatedLoot;
	}
}
