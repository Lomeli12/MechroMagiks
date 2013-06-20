package net.lomeli.magiks.api.magiks;

import net.minecraft.tileentity.TileEntity;

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
