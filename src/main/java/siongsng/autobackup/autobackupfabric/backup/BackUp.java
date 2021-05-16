package siongsng.autobackup.autobackupfabric.backup;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static siongsng.autobackup.autobackupfabric.utils.ABFUtils.msgInfo;

public class BackUp {
    private static final int SUCCESS = 1;
    private static final int FAILED = -1;

    public static int Run(CommandContext<ServerCommandSource> ctx, String BackupName) {
        final char[] ILLEGAL_CHARACTERS = {'/', '\n', '\r', '\t', '\0', '\f', '`', '?', '*', '\\', '<', '>', '|', '\"', ':'};
        for (char c : ILLEGAL_CHARACTERS) {
            if (BackupName.contains(String.valueOf(c))) {
                msgInfo(ctx, String.format("壓縮檔案名稱必須符合此條件： \"%c\"。", c));
                return FAILED;
            }
        }
        MinecraftServer server = ctx.getSource().getMinecraftServer();
        if (BackupName.equals("")) {
            BackupName = String.valueOf(new Date().getTime());
        }

        String srcDir = server.getRunDirectory().getPath() + "world";
        String destDir = String.format("%s/AutoBackup File/%s.zip", server.getRunDirectory().getPath(), BackupName);
        msgInfo(ctx,"輸出後的備份檔案路徑位於: "+ destDir);

        long start = System.currentTimeMillis();
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(destDir))) {
            File sourceFile = new File(srcDir);
            compress(sourceFile, zos, sourceFile.getName(), true);
            long end = System.currentTimeMillis();
            msgInfo(ctx, "壓縮世界檔案完成，耗時：" + (end - start) + " 毫秒");
        } catch (Exception ignored) {
        }
        return SUCCESS;
    }

    private static void compress(File sourceFile, ZipOutputStream zos, String name, boolean KeepDirStructure)
            throws Exception {
        byte[] buf = new byte[2 * 1024];
        if (sourceFile.isFile()) {
            zos.putNextEntry(new ZipEntry(name));
            int len;
            FileInputStream in = new FileInputStream(sourceFile);
            while ((len = in.read(buf)) != -1) {
                zos.write(buf, 0, len);
            }
            zos.closeEntry();
            in.close();
        } else {
            File[] listFiles = sourceFile.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                if (KeepDirStructure) {
                    zos.putNextEntry(new ZipEntry(name + "/"));
                    zos.closeEntry();
                }
            } else {
                for (File file : listFiles) {
                    if (KeepDirStructure) {
                        compress(file, zos, name + "/" + file.getName(), KeepDirStructure);
                    } else {
                        compress(file, zos, file.getName(), KeepDirStructure);
                    }
                }
            }
        }
    }
}
