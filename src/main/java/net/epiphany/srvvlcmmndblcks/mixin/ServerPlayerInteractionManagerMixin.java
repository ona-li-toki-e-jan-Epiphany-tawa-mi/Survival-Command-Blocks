package net.epiphany.srvvlcmmndblcks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;

@Mixin(ServerPlayerInteractionManager.class)
public class ServerPlayerInteractionManagerMixin {
    /**
     * Allows players to break implementations of {@link net.minecraft.block.OperatorBlock}, namely command blocks.
     * 
     * The other implementation are also unbreakable, so this should only effect command blocks.
     */
    @Redirect( method = "Lnet/minecraft/server/network/ServerPlayerInteractionManager;tryBreakBlock(Lnet/minecraft/util/math/BlockPos;)Z"
             , at = @At( value = "INVOKE"
                       , target = "Lnet/minecraft/server/network/ServerPlayerEntity;isCreativeLevelTwoOp()Z")
             , require = 1)
    private boolean allowBreakOperatorBlock(ServerPlayerEntity self) {
        return true;
    }
}
