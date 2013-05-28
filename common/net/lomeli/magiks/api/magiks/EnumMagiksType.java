package net.lomeli.magiks.api.magiks;

public enum EnumMagiksType
{
	GENERATOR	(0),
	MACHINE		(1),
	BATBOX		(2),
	;
	
	private final int type;
	
	private EnumMagiksType(int types)
	{
		type = types;
	}
	
	public int getType()
	{
		return type;
	}
}
