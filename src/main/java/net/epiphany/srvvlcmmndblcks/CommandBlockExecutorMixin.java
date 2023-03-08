package net.epiphany.srvvlcmmndblcks;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.CommandBlockExecutor;

@Mixin(CommandBlockExecutor.class)
public class CommandBlockExecutorMixin {
    /**
     * Allows players to interact with command block minecarts in survival.
     */
    @Redirect( method = "Lnet/minecraft/world/CommandBlockExecutor;interact(Lnet/minecraft/entity/player/PlayerEntity;)Lnet/minecraft/util/ActionResult;"
             , at = @At( value = "INVOKE"
                       , target = "Lnet/minecraft/entity/player/PlayerEntity;isCreativeLevelTwoOp()Z"))
    private boolean allowInteractWithExecutor(PlayerEntity self) {
        return true;
    }
}
