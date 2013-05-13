package net.lomeli.magiks.items.tools;

public enum EnumWandStrength 
{
	STONE		(600, 264, 1, "Stone"),
	IRON		(700, 502, 2, "Iron"),
	DIAMOND		(800, 3124, 3, "Diamond"),
	;
	private final int harvestLevel;
	private final int id;
	private final int durability;
	private final String name;
	
	private EnumWandStrength(int ID, int durability, int harvestLevel, String name)
	{
		this.id = ID;
		this.durability = durability;
		this.harvestLevel = harvestLevel;
		this.name = name;
	}
	
	public int getHarvestLevel()
	{
		return this.harvestLevel;
	}
	
	public int getID()
	{
		return this.id;
	}
	
	public int getDurability()
	{
		return this.durability;
	}
	
	public String getName()
	{
		return this.name;
	}
}
