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

    // TODO Loot tables do not currently work,
    @Override
    public void onInitialize() {
        changeLootTableID(Blocks.COMMAND_BLOCK, new Identifier(MINECRAFT_NAMESPACE, "command_block"));
        changeLootTableID(Blocks.CHAIN_COMMAND_BLOCK, new Identifier(MINECRAFT_NAMESPACE, "chain_command_block"));
        changeLootTableID(Blocks.REPEATING_COMMAND_BLOCK, new Identifier(MINECRAFT_NAMESPACE, "repeating_command_block"));
    }

    public static void changeLootTableID(AbstractBlock block, @Nullable Identifier lootTableID) {
        AbstractBlockAccessor blockAccessor = (AbstractBlockAccessor) block;
        
        blockAccessor.setLootTableID(lootTableID);
        ((AbstractBlockSettingsAccessor) blockAccessor.getSettings()).setLootTableId(lootTableID);
    }
}
