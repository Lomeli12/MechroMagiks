package net.lomeli.magiks.addons.thermalexpansion;

import java.util.logging.Level;

import net.lomeli.magiks.core.helper.LogHelper;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.Loader;

import thermalexpansion.api.crafting.ISmelterManager;
import thermalexpansion.api.crafting.ISmelterRecipe;

public class CheckTE implements ISmelterManager
{

	public static void isTEInstalled(String modID)
	{
		if (Loader.isModLoaded(modID))
        {
            try
            {
            	LogHelper.log(Level.INFO, "Thermal Expansion Installed");
            }
            catch(Exception ex)
            {
            	LogHelper.log(Level.INFO, "Thermal Expansion not installed");
            }
        }
	}

	@Override
	public boolean addRecipe(int energy, ItemStack primaryInput,
			ItemStack secondaryInput, ItemStack primaryOutput, boolean overwrite) {
		return false;
	}

	@Override
	public boolean addRecipe(int energy, ItemStack primaryInput,
			ItemStack secondaryInput, ItemStack primaryOutput) {
		return false;
	}

	@Override
	public boolean addRecipe(int energy, ItemStack primaryInput,
			ItemStack secondaryInput, ItemStack primaryOutput,
			ItemStack secondaryOutput, boolean overwrite) {
		return false;
	}

	@Override
	public boolean addRecipe(int energy, ItemStack primaryInput,
			ItemStack secondaryInput, ItemStack primaryOutput,
			ItemStack secondaryOutput) {
		return false;
	}

	@Override
	public boolean addRecipe(int energy, ItemStack primaryInput,
			ItemStack secondaryInput, ItemStack primaryOutput,
			ItemStack secondaryOutput, int secondaryChance, boolean overwrite) {
		return false;
	}

	@Override
	public boolean addRecipe(int energy, ItemStack primaryInput,
			ItemStack secondaryInput, ItemStack primaryOutput,
			ItemStack secondaryOutput, int secondaryChance) {
		return false;
	}

	@Override
	public ISmelterRecipe[] getRecipeList() {
		return null;
	}
}
