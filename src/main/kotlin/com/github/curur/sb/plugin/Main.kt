package com.github.curur.sb.plugin

import net.kyori.adventure.key.Key
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.Recipe
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.plugin.java.JavaPlugin

class Main: JavaPlugin() {

    override fun onEnable() {
        logger.info("enable")

//        Bukkit.addRecipe()
    }

    private val endPortalRecipe = ShapedRecipe(NamespacedKey.minecraft("minecraft"), ItemStack(Material.END_PORTAL_FRAME)).apply {
        shape("", "", "")
//        setIngredient("", Material.)
    }


}