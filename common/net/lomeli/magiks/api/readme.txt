MechroMagik API version 0.0.8
=============================

This api is for a mod that is still in under heavy
development. That means that this api is either
lacking in features, messy, undocumented, 
or just completely broken. This is more or 
less used to test out what the mod can do at 
the moment and to possibly gain new ideas for 
the mod by things other people create (what's
better than user input?).

How to use
==========

Simply drag the "net" folder into which ever
directory you have your source for your addon

Most of the useful functions can be used
via the MechroMagiksAPI class, ItemAPI class, 
and BlockAPI class, and it is recommened to use them.

!IMPORTANT!
-----------
As the API is bound to change between versions,
it's important to use only the parts you need.
For most people, that will include the root api package
(i.e net.lomeli.magiks.api), the crafting package
(i.e net.lomeli.magiks.api.crafting), and the lib package
(i.e net.lomeli.magiks.api.lib) as those will most likely
rarely change.

It's also important to make sure your addon/mod doesn't
break if MechroMagiks isn't installed. The easiest way to
do this is to use the following

Code:
import cpw.mods.fml.common.Loader;

public class myMod
{
	public static void randomName()
	{
		if(Loader.isModLoaded("magiks")
		{
			try
			{
				// Stuff
			}
			catch(Exception ex)
			{
				System.out.println(ex.getMessage());
			}
		}
	}
}

That should keep your addon/mod from breaking.

There should be some comments ATM that should help.