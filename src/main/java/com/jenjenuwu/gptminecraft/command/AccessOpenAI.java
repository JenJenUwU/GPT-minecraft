package com.jenjenuwu.gptminecraft.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class AccessOpenAI {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("gpt-minecraft")
                .then(CommandManager.literal("hi")
                        .executes(context -> run(context.getSource()))
                )
        );
    }

    public static int run(ServerCommandSource source) {
        source.sendFeedback(() -> Text.of("Hello, world!"), false);
        return 1;
    }
}
