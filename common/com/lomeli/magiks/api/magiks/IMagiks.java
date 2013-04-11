package com.lomeli.magiks.api.magiks;

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
}
