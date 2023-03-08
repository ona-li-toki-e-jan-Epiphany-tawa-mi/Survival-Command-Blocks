package net.epiphany.srvvlcmmndblcks.mixin;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.block.AbstractBlock;
import net.minecraft.util.Identifier;

@Mixin(AbstractBlock.class)
public interface AbstractBlockAccessor extends net.fabricmc.fabric.mixin.object.builder.AbstractBlockAccessor {
    /**
     * @attention Make sure to also change the loot table id within the abstract block's settings.
     */
    @Accessor("lootTableId")
    public void setLootTableID(@Nullable Identifier lootTableID);
}
