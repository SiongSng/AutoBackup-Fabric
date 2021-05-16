package siongsng.autobackup.autobackupfabric.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public final class CommandsRegister {
    public static void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("AutoBackup").executes(ABFCommand::AFB));
        dispatcher.register(CommandManager.literal("AutoBackup").then(CommandManager.literal("help").executes(ABFCommand::help)));
    }
}
