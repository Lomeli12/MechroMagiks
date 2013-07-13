package net.lomeli.magiks.api.magiks;

import net.minecraft.tileentity.TileEntity;

/**
 * Plain TileEntity for Mist Machines. Extend
 * this if you want your machines to work
 * properly with Power Coils
 * @author Lomeli12
 */
public class TileEntityMagiks extends TileEntity implements IMagiks
{
	public EnumMachineTypes getType()
	{
		return null;
	}
	
    @Override
    public int getMistLevel()
    {
        return 0;
    }
    
    public int getMistPercentage()
    {
    	return 0;
    }

    @Override
    public void setMistLevel(int value)
    {}

    @Override
    public void addToMistLevel(int value)
    {}

    @Override
    public float getHeatLevel()
    {
        return 0F;
    }

    @Override
    public void setHeatLevel(float temp)
    {}

    @Override
    public void addToHeatLevel(float temp)
    {}

    @Override
    public int getMaxMistLevel()
    {
        return 0;
    }
    
    public void setMaxMistLevel(int value)
    {}
    
    public int getGenerationTime()
    {
    	return 0;
    }
    
    public void setGenerationTime(int value)
    {}
    
    public void addToGenerationTime(int value)
    {}
    
    public int getCoolTime()
    {
    	return this.getCoolTime();
    }
    
    public void setCoolTime(int value)
    {}
    
    public void addToCoolTime(int value)
    {}

    @Override
    public boolean hasMist()
    {
        return false;
    }

	@Override
    public boolean isConnected() 
	{
		return false;
	}

	public void setConnection(TileEntityMagiks tileEntity) 
	{}

	@Override
    public TileEntityMagiks getConnection() 
	{
		return null;
	}
	
	public int getMistScaled(int scaleVal)
    {
        return 0;
    }

	@Override
    public void setConnection(TileEntity tileEntity)
    {}

}
