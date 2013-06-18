package net.lomeli.magiks.api.magiks;

public enum EnumMachineTypes
{
	GENERATOR	(0),
	MACHINE		(1),
	BATBOX		(2),
	;
	
	private final int type;
	
	private EnumMachineTypes(int types)
	{
		type = types;
	}
	
	public int getType()
	{
		return type;
	}
}
