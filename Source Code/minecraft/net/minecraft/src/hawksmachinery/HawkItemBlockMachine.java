
package net.minecraft.src.hawksmachinery;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraft.src.forge.ITextureProvider;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkItemBlockMachine extends ItemBlock
{
	public static mod_HawksMachinery BASEMOD;
	
	public HawkItemBlockMachine(int id)
    {
	    super(id);
	    setMaxDamage(0);
	    setHasSubtypes(true);
    }
	
	@Override
	public String getItemDisplayName(ItemStack item)
	{
		switch (item.getItemDamage())
		{
			case 0: return "Empty Machine Block";
			case 1: return "Basic Machine Block";
			case 2: return "Advanced Machine Block";
			case 3: return "Elite Machine Block";
			case 4: return "Basic Redstone-Treated Machine Block";
			case 5: return "Advanced Redstone-Treated Machine Block";
			case 6: return "Elite Redstone-Treated Machine Block";
			default: return "Buggy coding!";
		}
	}
	
	@Override
	public int getBlockID()
	{
		return BASEMOD.blockEmptyMachine.blockID;
	}
	
	@Override
	public int getMetadata(int meta)
	{
		return meta;
	}
	
	@Override
	public void onCreated(ItemStack item, World world, EntityPlayer player)
	{
		int dmg = item.getItemDamage();
		
		if (dmg == 0)
		{
			player.addStat(HawkAchievements.shellOfAMachine, 1);
		}
		
		if (dmg <= 3 && dmg != 0)
		{
			player.addStat(HawkAchievements.buildABetterMachineBlock, 1);
		}
		
		if (dmg <= 6 && dmg > 3)
		{
			player.addStat(HawkAchievements.redstonedWithCare, 1);
		}
	}
}
