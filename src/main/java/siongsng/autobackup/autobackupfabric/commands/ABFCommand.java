package siongsng.autobackup.autobackupfabric.commands;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;

import static siongsng.autobackup.autobackupfabric.utils.ABFUtils.msgInfo;

public final class ABFCommand {
    static int SUCCESS = 1;
    static int FAILED = -1;

    public static int help(CommandContext<ServerCommandSource> context) {
        msgInfo(context, "AutoBackupFabric 指令列表");
        return SUCCESS;
    }

    public static int AFB(CommandContext<ServerCommandSource> context) {
        if (context.getInput().equals("/AutoBackup")) {
            msgInfo(context, "輸入 /AutoBackup help 取得說明");
        }else {
         msgInfo(context,"感謝您使用此模組。");
        }
        return SUCCESS;
    }

}
