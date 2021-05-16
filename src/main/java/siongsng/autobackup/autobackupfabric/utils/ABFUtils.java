package siongsng.autobackup.autobackupfabric.utils;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public final class ABFUtils {
    private static final Object syncMessage = new Object();

    public static void broadcast(String message) {
        broadcast(message);
    }

    public static CommandContext<ServerCommandSource> msgInfo(@Nullable CommandContext<ServerCommandSource> context, String messageText) {
        return msgInfo(context, messageText, false);
    }

    public static CommandContext<ServerCommandSource> msgInfo(@Nullable CommandContext<ServerCommandSource> context, String messageText, boolean broadcastToOps) {
        return message(context, messageText, broadcastToOps);
    }

    private static CommandContext<ServerCommandSource> message(@Nullable CommandContext<ServerCommandSource> context, String messageText, boolean broadcastToOps) {
        if (context != null) {
            synchronized (syncMessage) {
                Text text = new LiteralText(messageText);
                context.getSource().sendFeedback(text, broadcastToOps);
            }
            return context;
        } else {
            broadcast(messageText);
            return null;
        }
    }

}
