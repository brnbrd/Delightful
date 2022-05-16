package net.brdle.delightful;

import net.brdle.delightful.proxy.ClientProxy;
import net.brdle.delightful.proxy.CommonProxy;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
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
    }

    public static Logger getLogger() {
        return LOGGER;
    }
}
