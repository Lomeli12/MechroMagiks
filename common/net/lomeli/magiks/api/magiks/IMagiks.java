package net.lomeli.magiks.api.magiks;

import net.lomeli.magiks.api.tiles.TileEntityMagiks;

public interface IMagiks
{
    int getMistLevel();

    void setMistLevel(int value);

    void addToMistLevel(int value);

    int getHeatLevel();

    void setHeatLevel(int temp);

    void addToHeatLevel(int temp);

    int getMaxMistLevel();

    boolean hasMist();
    
    boolean isConnected();
    
    void setConnection(TileEntityMagiks tileEntity);
    
    TileEntityMagiks getConnection();
}
