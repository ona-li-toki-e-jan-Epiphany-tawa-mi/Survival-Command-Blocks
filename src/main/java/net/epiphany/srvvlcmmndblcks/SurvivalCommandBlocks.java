package net.epiphany.srvvlcmmndblcks;

import org.jetbrains.annotations.Nullable;

import net.epiphany.srvvlcmmndblcks.mixin.AbstractBlockAccessor;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.mixin.object.builder.AbstractBlockSettingsAccessor;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;

public class SurvivalCommandBlocks implements ModInitializer {
    public static final String MINECRAFT_NAMESPACE = "minecraft";

    @Override
    public void onInitialize() {
        // By default, the command blocks do not have a loot table defined.
        changeLootTableID(Blocks.COMMAND_BLOCK, new Identifier(MINECRAFT_NAMESPACE, "blocks/command_block"));
        changeLootTableID(Blocks.CHAIN_COMMAND_BLOCK, new Identifier(MINECRAFT_NAMESPACE, "blocks/chain_command_block"));
        changeLootTableID(Blocks.REPEATING_COMMAND_BLOCK, new Identifier(MINECRAFT_NAMESPACE, "blocks/repeating_command_block"));
    }



    /**
     * Changes the loot table of a preexisting block.
     * 
     * @param block       The block to change the loot table of.
     * @param lootTableID The loot table to use.
     */
    public static void changeLootTableID(AbstractBlock block, @Nullable Identifier lootTableID) {
        AbstractBlockAccessor blockAccessor = (AbstractBlockAccessor) block;
        
        blockAccessor.setLootTableID(lootTableID);
        ((AbstractBlockSettingsAccessor) blockAccessor.getSettings()).setLootTableId(lootTableID);
    }
}
