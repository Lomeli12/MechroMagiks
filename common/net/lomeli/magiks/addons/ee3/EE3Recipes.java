package net.lomeli.magiks.addons.ee3;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;

import net.lomeli.magiks.api.libs.MagiksArrays;
import net.lomeli.magiks.blocks.ModBlocksMagiks;
import net.lomeli.magiks.items.ModItemsMagiks;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EE3Recipes 
{
	public static final int WILDCARD_DAMAGE_VALUE = Short.MAX_VALUE;
	
	public static List<ItemStack>transmutationStones = new ArrayList<ItemStack>();
	
	public static void addStones()
	{
		transmutationStones.add(EE3ItemAPI.getItem("miniumStone"));
		transmutationStones.add(EE3ItemAPI.getItem("philStone"));
	}
	
	public static void registerTransmutations()
	{
		addStones();
		/* added this in just so I can get the philosopher's stone in my single player world =P
		GameRegistry.addRecipe(EE3ItemAPI.getItem("philStone"), new Object[] {
			"RGR","GMG","RGR", 'R',Item.redstone, 'G',Item.lightStoneDust,
			'M',EE3ItemAPI.getItem("miniumStone")
		});*/
		
		for(ItemStack stone : transmutationStones)
		{
			MagiksArrays.rechargeableItems.add(stone);
			
			// 1 Neonite -> 2 Emeralds
			GameRegistry.addShapelessRecipe(new ItemStack(Item.emerald, 2), 
				new Object[] { ModItemsMagiks.neoniteGem,
				(new ItemStack(stone.getItem(), 1, WILDCARD_DAMAGE_VALUE)) });
			
			// 2 Emeralds -> 1 Neonite
			GameRegistry.addShapelessRecipe(new ItemStack(ModItemsMagiks.neoniteGem, 1), 
					new Object[] { Item.emerald, Item.emerald,
					(new ItemStack(stone.getItem(), 1, WILDCARD_DAMAGE_VALUE)) });
			
			// 1 Neonite Block -> 2 Emerald Blocks
			GameRegistry.addShapelessRecipe(new ItemStack(Block.blockEmerald, 2), 
					new Object[] { ModBlocksMagiks.neoniteBlock,
					(new ItemStack(stone.getItem(), 1, WILDCARD_DAMAGE_VALUE)) });
						
			// 2 Emerald Blocks -> 1 Neonite Block
			GameRegistry.addShapelessRecipe(new ItemStack(ModBlocksMagiks.neoniteBlock, 1), 
					new Object[] { Block.blockEmerald, Block.blockEmerald,
					(new ItemStack(stone.getItem(), 1, WILDCARD_DAMAGE_VALUE)) });
			
			// 1 Mancery Brick -> 1 Mancery Stone
			GameRegistry.addShapelessRecipe(new ItemStack(ModBlocksMagiks.manceryBlock), 
				new Object[]{ ModBlocksMagiks.manceryBrick, 
				(new ItemStack(stone.getItem(), 1, WILDCARD_DAMAGE_VALUE)) });
			
			// 5 Statmatic Ingot -> 1 Ignious Ingot
			GameRegistry.addShapelessRecipe(new ItemStack(ModItemsMagiks.ingotIgnious, 1), 
				new Object[]{ ModItemsMagiks.ingotStamatic, ModItemsMagiks.ingotStamatic, 
				ModItemsMagiks.ingotStamatic, ModItemsMagiks.ingotStamatic, ModItemsMagiks.ingotStamatic,
				(new ItemStack(stone.getItem(), 1, WILDCARD_DAMAGE_VALUE)) });
			
			// 1 Ignious Ingot -> 5 Statmatic Ingot
			GameRegistry.addShapelessRecipe(new ItemStack(ModItemsMagiks.ingotStamatic, 5), 
					new Object[]{ ModItemsMagiks.ingotIgnious,
					(new ItemStack(stone.getItem(), 1, WILDCARD_DAMAGE_VALUE)) });
		}
	}
}
