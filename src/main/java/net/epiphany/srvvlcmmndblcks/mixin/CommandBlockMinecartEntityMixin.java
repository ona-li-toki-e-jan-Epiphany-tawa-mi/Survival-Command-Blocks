package net.epiphany.srvvlcmmndblcks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.entity.vehicle.CommandBlockMinecartEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

@Mixin(CommandBlockMinecartEntity.class)
public class CommandBlockMinecartEntityMixin {
    /**
     * Rewrites the command block minecart to drop itself rather than an empty minecart.
     * 
     * @author ona li toki e jan Epiphany tawa mi.
     * @reason The get item method is extremly simple and any other mod being loaded will likely not modify it either. Even if
     *      they do, the same result would likely be returned.
     */
    @Overwrite
    public Item getItem() {
        return Items.COMMAND_BLOCK_MINECART;
    }
}
