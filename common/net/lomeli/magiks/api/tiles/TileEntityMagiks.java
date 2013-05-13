package net.lomeli.magiks.api.tiles;

import net.lomeli.magiks.api.magiks.IMagiks;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMagiks extends TileEntity implements IMagiks
{
	public TileEntityMagiks connected;
    
	public int maxMistLevel, mistLevel, heatLevel, generationTime = 0,
            coolDown = 0, spawnParticle = 0;

    public int getMistLevel()
    {
        return this.mistLevel;
    }
    
    public int getMistPercentage()
    {
    	return (this.mistLevel / this.maxMistLevel);
    }

    public void setMistLevel(int value)
    {
    	this.mistLevel = value;
    }

    public void addToMistLevel(int value)
    {
    	this.mistLevel += value;
    }

    public int getHeatLevel()
    {
        return this.heatLevel;
    }

    public void setHeatLevel(int temp)
    {
    	this.mistLevel = temp;
    }

    public void addToHeatLevel(int temp)
    {
    	this.mistLevel += temp;
    }

    public int getMaxMistLevel()
    {
        return this.maxMistLevel;
    }
    
    public void setMaxMistLevel(int value)
    {
    	this.maxMistLevel = value;
    }
    
    public int getGenerationTime()
    {
    	return this.generationTime;
    }
    
    public void setGenerationTime(int value)
    {
    	this.generationTime = value;
    }
    
    public void addToGenerationTime(int value)
    {
    	this.generationTime += value;
    }
    
    public int getCoolTime()
    {
    	return this.getCoolTime();
    }
    
    public void setCoolTime(int value)
    {
    	this.coolDown = value;
    }
    
    public void addToCoolTime(int value)
    {
    	this.coolDown += value;
    }

    public boolean hasMist()
    {
        if (this.getMistLevel() >= 0)
            return true;
        else
            return false;
    }

	public boolean isConnected() {
		if(this.connected != null)
				return true;
			else
				return false;
	}

	public void setConnection(TileEntityMagiks tileEntity) {
		if(getConnection() == null)
			this.connected = tileEntity;
		else
		{
		}
	}

	public TileEntityMagiks getConnection() {
		return this.connected;
	}
	
	public int getMistScaled(int scaleVal)
    {
        return this.getMistLevel() * scaleVal / 100;
    }

}
