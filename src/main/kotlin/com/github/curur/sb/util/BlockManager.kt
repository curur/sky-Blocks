package com.github.curur.sb.util

import com.github.curur.customconfig.config.configBuilder
import com.github.curur.sb.plugin.Main
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.Chest
import kotlin.math.max
import kotlin.math.min

class BlockManager(private val plugin: Main) {

    fun saveBlocksAsFile(
        blockLocation1: Location,
        blockLocation2: Location,
        fileName: String
    ) {
        val configM = plugin.configBuilder.createCustomConfig(fileName, plugin.dataFolder)
        val config = configM.config

        val x1 = min(blockLocation1.blockX, blockLocation2.blockX)
        val y1 = min(blockLocation1.blockY, blockLocation2.blockY)
        val z1 = min(blockLocation1.blockZ, blockLocation2.blockZ)

        val x2 = max(blockLocation1.blockX, blockLocation2.blockX)
        val y2 = max(blockLocation1.blockY, blockLocation2.blockY)
        val z2 = max(blockLocation1.blockZ, blockLocation2.blockZ)

        for(x in x1..x2) {
            for(y in y1..y2) {
                for(z in z1..z2) {
                    val location = Location(blockLocation1.world, x.toDouble(), y.toDouble(), z.toDouble())
                    val block = location.block

                    config.set("block.${x - x1}.${y - y1}.${z - z1}", block.type)

                    if(block.type == Material.CHEST) {
                        val chest = block.state as Chest
                        val inventory = chest.inventory

                        for(i in 0 until inventory.size) {
                            if(inventory.getItem(i) == null) continue
                            if(inventory.getItem(i)!!.type == Material.AIR) continue
                            config.set("block.${x - x1}.${y - y1}.${z - z1}.$i", inventory.getItem(i)!!.type)
                        }
                    }
                }
            }
        }
    }

    fun loadBlocksFromFile(fileName: String): HashMap<Location, Block>? {
        val block = HashMap<Location, Block>()
        val config = plugin.configBuilder.createCustomConfig(fileName, plugin.dataFolder)
        val keys = config.config.getConfigurationSection("block")?.getKeys(false) ?: return null

        for (i in keys) {
            println(i)
        }
        return null
    }

}