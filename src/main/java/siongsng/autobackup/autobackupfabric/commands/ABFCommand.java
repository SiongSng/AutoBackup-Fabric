package siongsng.autobackup.autobackupfabric.commands;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import siongsng.autobackup.autobackupfabric.backup.BackUp;

import static siongsng.autobackup.autobackupfabric.utils.ABFUtils.msgInfo;

public final class ABFCommand {
    static int SUCCESS = 1;
    static int FAILED = -1;

    public static int help(CommandContext<ServerCommandSource> context) {
        msgInfo(context, "§lAutoBackupFabric§r 指令列表");
        msgInfo(context, "§e/AutoBackup help §a取得說明");
        msgInfo(context, "§e/AutoBackup create §6<§b備份檔案名稱§6> §a建立伺服器地圖備份檔案");
        return SUCCESS;
    }
    public static int create(CommandContext<ServerCommandSource> ctx) {
        String cmd = ctx.getInput().split("\\s+")[1];

        msgInfo(ctx, "正在建立備份檔案中，請稍後...");
        return BackUp.Run(ctx , cmd);
    }

    public static int AFB(CommandContext<ServerCommandSource> context) {
        if (context.getInput().equals("/AutoBackup")) {
            msgInfo(context, "輸入 /AutoBackup help 取得說明");
        }else {
         msgInfo(context,"感謝您使用此模組。輸入 /AutoBackup help 取得說明。");
        }
        return SUCCESS;
    }

}
