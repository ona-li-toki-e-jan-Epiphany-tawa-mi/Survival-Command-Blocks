package net.epiphany.srvvlcmmndblcks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.mojang.brigadier.tree.CommandNode;

import net.minecraft.server.command.CommandManager;

@Mixin(CommandManager.class)
public class CommandManagerMixin {
    /**
     * Allows players to see all server commands and suggestions for them even if they are not an operator.
     * Note: this doesn't allow them to execute the commands, just to look at them.
     */
    @Redirect( method = "Lnet/minecraft/server/command/CommandManager;makeTreeForSource(Lcom/mojang/brigadier/tree/CommandNode;Lcom/mojang/brigadier/tree/CommandNode;Lnet/minecraft/server/command/ServerCommandSource;Ljava/util/Map;)V"
             , at = @At( value = "INVOKE"
                       , target = "Lcom/mojang/brigadier/tree/CommandNode;canUse(Ljava/lang/Object;)Z")
             , require = 1)
    private boolean allowSeeCommandSuggestions(CommandNode self, Object source) {
        return true;
    }
}
