package net.brdle.modname;

import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ModName.MODID)
public class ModName
{
    public static final String MODID = "modname";
    public static ModName instance;
    public static CommonProxy proxy;
    private static final Logger LOGGER = LogManager.getLogger();

    public ModName() {
        instance = this;
        proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);
        proxy.start();
    }
}
