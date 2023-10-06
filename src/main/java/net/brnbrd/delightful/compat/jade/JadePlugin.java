package net.brnbrd.delightful.compat.jade;

import net.brnbrd.delightful.common.block.SalmonberryBushBlock;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class JadePlugin implements IWailaPlugin {

	@Override
	public void register(IWailaCommonRegistration registration) {
		// TODO Register data providers and config options here
	}

	@Override
	public void registerClient(IWailaClientRegistration registration) {
		registration.registerBlockComponent(DCropProgress.INSTANCE, SalmonberryBushBlock.class);
	}
}
