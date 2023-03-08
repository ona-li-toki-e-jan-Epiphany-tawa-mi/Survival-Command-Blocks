package net.epiphany.srvvlcmmndblcks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {
    /**
     * Allows players to set the command inside a command block in survival mode.
     */
    @Redirect( method = "Lnet/minecraft/server/network/ServerPlayNetworkHandler;onUpdateCommandBlock(Lnet/minecraft/network/packet/c2s/play/UpdateCommandBlockC2SPacket;)V"
             , at = @At( value = "INVOKE"
                       , target = "Lnet/minecraft/server/network/ServerPlayerEntity;isCreativeLevelTwoOp()Z"))
    private boolean allowUpdateCommandBlock(ServerPlayerEntity self) {
        return true;
    }

    /**
     * Allows players to set the command inside a command block minecart in survival mode.
     */
    @Redirect( method = "Lnet/minecraft/server/network/ServerPlayNetworkHandler;onUpdateCommandBlockMinecart(Lnet/minecraft/network/packet/c2s/play/UpdateCommandBlockMinecartC2SPacket;)V"
             , at = @At( value = "INVOKE"
                       , target = "Lnet/minecraft/server/network/ServerPlayerEntity;isCreativeLevelTwoOp()Z"))
    private boolean allowUpdateCommandBlockMinecart(ServerPlayerEntity self) {
        return true;
    }
}
