package net.epiphany.srvvlcmmndblcks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CommandBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.OperatorOnlyBlockItem;

@Mixin(OperatorOnlyBlockItem.class)
public class OperatorOnlyBlockItemMixin {
    /**
     * Allows operator block items to be placed even if the user is in survival mode.
     */
    @Redirect( method = "Lnet/minecraft/item/OperatorOnlyBlockItem;getPlacementState(Lnet/minecraft/item/ItemPlacementContext;)Lnet/minecraft/block/BlockState;"
             , at = @At( value = "INVOKE"
                       , target = "Lnet/minecraft/entity/player/PlayerEntity;isCreativeLevelTwoOp()Z")
             , require = 1)
    private boolean allowPlaceOperatorBlock(PlayerEntity self) {
        return true;
    }

    /**
     * Restricts the placing of operator blocks to command blocks for players in survival mode.
     */
    @Inject( method = "Lnet/minecraft/item/OperatorOnlyBlockItem;getPlacementState(Lnet/minecraft/item/ItemPlacementContext;)Lnet/minecraft/block/BlockState;"
           , at = @At("HEAD")
           , cancellable = true
           , require = 1)
    private void allowPlaceOnlyCommandBlock(ItemPlacementContext context, CallbackInfoReturnable<BlockState> info) {
        // If the player is a valid operator anyways there's no need to do anything.
        PlayerEntity player = context.getPlayer();
        
        if (player != null && player.isCreativeLevelTwoOp())
            return;


        Block block = ((BlockItem) context.getStack().getItem()).getBlock();

        if (!(block instanceof CommandBlock))
            info.setReturnValue(null);
    }
}
