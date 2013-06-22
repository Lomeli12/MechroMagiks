package net.lomeli.magiks.api.magiks;

/**
 * All machines must have a machine type in their tile entity in order to work properly with
 * other machines. 
 * @author Lomeli12
 *
 */
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
