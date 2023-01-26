package net.brnbrd.delightful;

import net.brnbrd.delightful.common.DelightfulConfig;
import net.brnbrd.delightful.proxy.ClientProxy;
import net.brnbrd.delightful.proxy.CommonProxy;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Delightful.MODID)
public class Delightful
{
    public static final String MODID = "delightful";
    public static Delightful instance;
    public static CommonProxy proxy;
    private static final Logger LOGGER = LogManager.getLogger();

    public Delightful() {
        instance = this;
        proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);
        proxy.start();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DelightfulConfig.SPEC);
    }

    public static Logger getLogger() {
        return LOGGER;
    }
}
