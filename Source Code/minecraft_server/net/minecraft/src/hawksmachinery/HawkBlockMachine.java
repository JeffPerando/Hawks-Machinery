
package net.minecraft.src.hawksmachinery;

import java.util.ArrayList;

import net.minecraft.src.*;
import net.minecraft.src.forge.*;

/**
 * @author Elusivehawk
 *
 */
public class HawkBlockMachine extends Block implements ITextureProvider
{
	public HawkBlockMachine(int id, Material mat)
    {
	    super(id, mat);
	    ModLoader.addName(this, "Machine Block");
	    ModLoader.registerBlock(this, HawkItemBlockMachine.class);
    }
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int meta)
	{
		switch (meta)
		{
			case 0: return 1;
			case 1: return 18;
			case 2: return 17;
			case 3: return 19;
			case 4: return 21;
			case 5: return 20;
			case 6: return 22;
			default: return 0;
		}
	}
	
	@Override
	public void addCreativeItems(ArrayList itemList)
	{
		for (int counter = 0; counter < 7; ++counter)
		{
			itemList.add(new ItemStack(this, 1, counter));
		}
	}
	
	@Override
	public String getTextureFile()
	{
		return HawkManager.BLOCK_TEXTURE_FILE;
	}

}
