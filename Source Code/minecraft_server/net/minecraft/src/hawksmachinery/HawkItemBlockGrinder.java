
package net.minecraft.src.hawksmachinery;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraft.src.forge.ITextureProvider;

/**
 * 
 * Used solely to fix the achievements "Time to Grind" not being received properly.
 * 
 * @author Elusivehawk
 */
public class HawkItemBlockGrinder extends ItemBlock implements ITextureProvider
{
	public static mod_HawksMachinery BASEMOD;
	
	public HawkItemBlockGrinder(int id)
    {
	    super(id);
    }
	
	@Override
	public int getBlockID()
	{
		return BASEMOD.blockGrinder.blockID;
	}
	
	@Override
	public int getMetadata(int meta)
	{
		return 0;
	}
	
	@Override
	public String getTextureFile()
	{
		return HawkManager.BLOCK_TEXTURE_FILE;
	}
	
	@Override
	public void onCreated(ItemStack item, World world, EntityPlayer player)
	{
		player.addStat(HawkAchievements.timeToGrind, 1);
	}
}
