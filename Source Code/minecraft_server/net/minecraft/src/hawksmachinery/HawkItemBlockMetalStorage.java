
package net.minecraft.src.hawksmachinery;

import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkItemBlockMetalStorage extends ItemBlock
{
	public mod_HawksMachinery BASEMOD;
	
	public HawkItemBlockMetalStorage(int id)
	{
		super(id);
		setHasSubtypes(true);
		setMaxDamage(0);
	}
	
	@Override
	public int getBlockID()
	{
		return BASEMOD.blockMetalStorage.blockID;
	}
	
	@Override
	public int getMetadata(int meta)
	{
		return meta;
	}
	
	
}
