
package net.minecraft.src.hawksmachinery;

import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;
import net.minecraft.src.forge.ITextureProvider;

/**
 * @author Elusivehawk
 *
 */
public class HawkItemBlockMachine extends ItemBlock implements ITextureProvider
{
	public HawkItemBlockMachine(int id)
    {
	    super(id);
	    setMaxDamage(0);
	    setHasSubtypes(true);
    }
	
	@Override
	public int getBlockID()
	{
		return HawkManager.machineBlockID;
	}
	
	@Override
	public int getMetadata(int meta)
	{
		return meta;
	}
	
	@Override
	public String getTextureFile()
	{
		return HawkManager.BLOCK_TEXTURE_FILE;
	}

}
