package siongsng.autobackup.autobackupfabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.server.ServerStartCallback;
import net.fabricmc.fabric.api.registry.CommandRegistry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.MinecraftDedicatedServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import siongsng.autobackup.autobackupfabric.commands.CommandsRegister;


public final class AutoBackupFabric implements ModInitializer, ServerStartCallback {
    public static final Logger LOGGER = LogManager.getLogger("autobackup-fabric");

    @Override
    public void onInitialize() {
        LOGGER.info("Hello AutoBackupFabric World");
        LOGGER.info("註冊指令中，請稍後...");
        CommandRegistry.INSTANCE.register(false, CommandsRegister::registerCommands);
        ServerStartCallback.EVENT.register(this);
    }
    @Override
    public void onStartServer(MinecraftServer server) {
        if (!(server instanceof MinecraftDedicatedServer)){
            LOGGER.error("AutoBackupFabric 只能在伺服器端執行，請勿將它裝入客戶端。");
            throw new RuntimeException("AutoBackupFabric 只能在伺服器端執行，請勿將它裝入客戶端。");
        }
    }
}
