package net.epiphany.srvvlcmmndblcks;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.block.CommandBlock;
import net.minecraft.entity.player.PlayerEntity;

@Mixin(CommandBlock.class)
public class CommandBlockMixin {
    /**
     * Allows players to interact with command blocks in survival.
     */
    @Redirect( method = "Lnet/minecraft/block/CommandBlock;onUse(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/Hand;Lnet/minecraft/util/hit/BlockHitResult;)Lnet/minecraft/util/ActionResult;"
             , at = @At( value = "INVOKE"
                       , target = "Lnet/minecraft/entity/player/PlayerEntity;isCreativeLevelTwoOp()Z"))
    private boolean allowEditCommandBlock(PlayerEntity self) {
        return true;
    }
}
