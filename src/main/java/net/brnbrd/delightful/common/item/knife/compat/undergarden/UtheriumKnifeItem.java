package net.brnbrd.delightful.common.item.knife.compat.undergarden;

import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.DKnifeItem;
import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.registries.ForgeRegistries;
import java.util.List;

public class UtheriumKnifeItem extends DKnifeItem {
	public UtheriumKnifeItem(Properties properties) {
		super(DelightfulItemTags.ingot("utherium"), DelightfulTiers.UTHERIUM, properties, Modid.UG);
		MinecraftForge.EVENT_BUS.addListener(this::onHurt);
	}

	@Override
	public Modid[] getConflicts() {
		return new Modid[]{Modid.UGD};
	}

	@Override
	public List<Component> getTools() {
		return List.of(Component.translatable("tooltip.utheric_sword").withStyle(ChatFormatting.RED));
	}

	void onHurt(LivingHurtEvent e) {
		if (
			this.enabled() &&
			e.getSource().getEntity() instanceof Player player &&
			player.getMainHandItem().is(this) &&
			e.getEntity().getType().is(TagKey.create(ForgeRegistries.Keys.ENTITY_TYPES, Modid.UG.rl("rotspawn")))
		) {
			e.setAmount(e.getAmount() * 1.5F);
		}
	}
}