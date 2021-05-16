package siongsng.autobackup.autobackupfabric.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public final class CommandsRegister {
    public static void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("AutoBackup").executes(ABFCommand::AFB));

        dispatcher.register(CommandManager.literal("AutoBackup").then(CommandManager.literal("help").executes(ABFCommand::help))); //幫助指令
        dispatcher.register(CommandManager.literal("AutoBackup").then(CommandManager.literal("create ").executes(ABFCommand::create)).then(CommandManager.argument("備份檔案名稱", StringArgumentType.greedyString()).executes(ABFCommand::create))); //創建備份檔案指令

    }
}
