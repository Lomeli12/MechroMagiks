package net.lomeli.magiks.api.magiks;

import net.minecraft.tileentity.TileEntity;

/**
 * Interface for Mist Machines. 
 * 
 * Mod authors are asked to extend TileEntityMagiks instead, or else
 * it will not work properly with Coils (coils check if
 * the tile entity extends TileEntityMagiks)
 * @author Lomeli12
 *
 */
public interface IMagiks
{	
    int getMistLevel();

    void setMistLevel(int value);

    void addToMistLevel(int value);

    float getHeatLevel();

    void setHeatLevel(float temp);

    void addToHeatLevel(float temp);

    int getMaxMistLevel();

    boolean hasMist();
    
    boolean isConnected();
    
    void setConnection(TileEntity tileEntity);
    
    TileEntity getConnection();
}
