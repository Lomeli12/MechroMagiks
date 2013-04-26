package net.lomeli.magiks.tileentity;

import net.lomeli.magiks.api.magiks.IMagiks;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMagiks extends TileEntity implements IMagiks
{
	public TileEntityMagiks connected;
    public int maxMistLevel, mistLevel, heatLevel, generationTime = 0,
            coolDown = 0, spawnParticle = 0;

    @Override
    public int getMistLevel()
    {
        if (mistLevel != 0)
            return mistLevel;
        else
            return 0;
    }

    @Override
    public void setMistLevel(int value)
    {
        mistLevel = value;
    }

    @Override
    public void addToMistLevel(int value)
    {
        mistLevel += value;
    }

    @Override
    public int getHeatLevel()
    {
        return heatLevel;
    }

    @Override
    public void setHeatLevel(int temp)
    {
        mistLevel = temp;
    }

    @Override
    public void addToHeatLevel(int temp)
    {
        mistLevel += temp;
    }

    @Override
    public int getMaxMistLevel()
    {
        return maxMistLevel;
    }

    @Override
    public boolean hasMist()
    {
        if (this.getMistLevel() >= 0)
            return true;
        else
            return false;
    }

	@Override
	public boolean isConnected() {
		if(connected != null)
				return true;
			else
				return false;
	}

	@Override
	public void setConnection(TileEntityMagiks tileEntity) {
		if(getConnection() == null)
			connected = tileEntity;
		else
		{
		}
	}

	@Override
	public TileEntityMagiks getConnection() {
		return connected;
	}

}
