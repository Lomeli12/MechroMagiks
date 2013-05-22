package net.lomeli.magiks.api.magiks;

import net.minecraft.tileentity.TileEntity;

public class TileEntityMagiks extends TileEntity implements IMagiks
{
    public int getMistLevel()
    {
        return 0;
    }
    
    public int getMistPercentage()
    {
    	return 0;
    }

    public void setMistLevel(int value)
    {}

    public void addToMistLevel(int value)
    {}

    public int getHeatLevel()
    {
        return 0;
    }

    public void setHeatLevel(int temp)
    {}

    public void addToHeatLevel(int temp)
    {}

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

    public boolean hasMist()
    {
        return false;
    }

	public boolean isConnected() 
	{
		return false;
	}

	public void setConnection(TileEntityMagiks tileEntity) 
	{}

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
