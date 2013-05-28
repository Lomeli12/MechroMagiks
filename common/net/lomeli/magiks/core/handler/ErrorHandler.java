package net.lomeli.magiks.core.handler;

import java.util.logging.Level;

import net.lomeli.magiks.Magiks;
import net.lomeli.magiks.api.magiks.EnumMagiksType;
import net.lomeli.magiks.api.magiks.TileEntityMagiks;

public class ErrorHandler
{
	public static void unknowEnumMagiksType(TileEntityMagiks tile, EnumMagiksType type)
	{
		Magiks.logger.log(Level.WARNING, tile.toString() + " did not recognize " + type.toString());
	}
}
