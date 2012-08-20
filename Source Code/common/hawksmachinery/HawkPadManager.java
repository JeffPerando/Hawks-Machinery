
package hawksmachinery;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import hawksmachinery.padAPI.IHawkPadEffect;
import hawksmachinery.padAPI.IHawkPadElectricity;
import hawksmachinery.padAPI.IHawkPadTexture;

/**
 * 
 * Manages the vanilla Pads.
 * 
 * @author Elusivehawk
 */
public class HawkPadManager implements IHawkPadTexture, IHawkPadElectricity, IHawkPadEffect
{
	@Override
	public int getRequiredElectricityForPad(ItemStack padItem, float electricityStored, boolean isBeingRedstoned)
	{
		if (padItem.itemID == Item.redstone.shiftedIndex)
		{
			if (isBeingRedstoned)
			{
				return 30;
			}
			
			return 20;
		}
		
		return 10;
	}

	@Override
	public boolean canConductElectricity(ItemStack padItem, float electricityStored, boolean isBeingRedstoned)
	{
		return true;
	}
	
	@Override
	public int getPadElectricityLimit(ItemStack padItem, float electricityStored, boolean isBeingRedstoned)
	{
		if (padItem.itemID == Item.redstone.shiftedIndex)
		{
			return 1100;
		}
		
		return 1000;
	}
	
	@Override
	public String getPadTextureFile(ItemStack padItem, boolean isBeingRedstoned, float electricityStored)
	{
		return HawkManager.BLOCK_TEXTURE_FILE;
	}
	
	@Override
	public int getPadTextureLocation(ItemStack padItem, boolean isBeingRedstoned, float electricityStored)
	{
		if (padItem.itemID == Item.redstone.shiftedIndex)
		{
			if (isBeingRedstoned)
			{
				return 32;
			}
			
			return 33;
		}
		
		return 57;
	}
	
	@Override
	public int getPadColor(ItemStack padItem, float electricityStored)
	{
		return 16777215;
	}
	
	@Override
	public void getPadEffect(ItemStack padItem, World world, int x, int y, int z, Entity entity, boolean isBeingRedstoned, float electricityStored)
	{
		
	}
	
	@Override
	public boolean isItemValidForPad(ItemStack padItem, World world, int x, int y, int z, EntityPlayer player)
	{
		if (padItem.itemID == Item.redstone.shiftedIndex)
		{
			return true;
		}
		
		return false;
	}
	
}
