package net.epiphany.srvvlcmmndblcks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.block.CommandBlock;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.entity.player.PlayerEntity;

@Mixin(CommandBlock.class)
public class CommandBlockMixin {
    /**
     * Makes all instances of {@link CommandBlock} breakable like an iron block.
     * @note This also applies to any custom command blocks (assuming they derive or are an instance of {@link CommandBlock}), 
     *      pretty cool.
     */
    @ModifyVariable( method = "Lnet/minecraft/block/CommandBlock;<init>(Lnet/minecraft/block/AbstractBlock$Settings;Z)V"
                   , at = @At("HEAD")
                   , argsOnly = true
                   , require = 1)
    private static Settings makeMineable(Settings blockSettings) {
        return blockSettings.hardness(5.0f);
    }

    /**
     * Allows players to interact with command blocks in survival.
     */
    @Redirect( method = "Lnet/minecraft/block/CommandBlock;onUse(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/Hand;Lnet/minecraft/util/hit/BlockHitResult;)Lnet/minecraft/util/ActionResult;"
             , at = @At( value = "INVOKE"
                       , target = "Lnet/minecraft/entity/player/PlayerEntity;isCreativeLevelTwoOp()Z")
             , require = 1)
    private boolean allowEditCommandBlock(PlayerEntity self) {
        return true;
    }
}
