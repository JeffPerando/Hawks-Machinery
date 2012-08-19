
package hawksmachinery.padAPI;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraftforge.common.ForgeDirection;

/**
 * 
 * Call registerPadInterfaceUser() here if you want to use Pad interfaces.
 * 
 * @author Elusivehawk
 */
public class HawkPadAPICore implements IHawkPadEffect, IHawkPadElectricity, IHawkPadMisc, IHawkPadRedstone, IHawkPadTexture, IHawkPadUpdate
{
	public static List<Object> MODS = new ArrayList<Object>();
	
	public HawkPadAPICore()
	{
		
	}
	
	/**
	 * 
	 * Call this in order to use the Pad API.
	 * 
	 * @param networkmod
	 * @param name
	 */
	public static void registerPadInterfaceUser(Object networkmod, String name)
	{
		MODS.add(networkmod);
		
		System.out.println("Registered Pad Interface User: " + name);
	}
	
	@Override
	public void onPadUpdate(ItemStack padItem, int electricityStored, boolean isBeingRedstoned, int x, int y, int z, World world)
	{
		for (Object mod : MODS)
		{
			if (mod instanceof IHawkPadUpdate)
			{
				((IHawkPadUpdate)mod).onPadUpdate(padItem, electricityStored, isBeingRedstoned, x, y, z, world);
			}
		}
	}
	
	@Override
	public String getPadTextureFile(ItemStack padItem, boolean isBeingRedstoned, int electricityStored)
	{
		for (Object mod : MODS)
		{
			if (mod instanceof IHawkPadTexture)
			{
				return ((IHawkPadTexture)mod).getPadTextureFile(padItem, isBeingRedstoned, electricityStored);
			}
		}
		
		return null;
	}
	
	@Override
	public int getPadTextureLocation(ItemStack padItem, boolean isBeingRedstoned, int electricityStored)
	{
		for (Object mod : MODS)
		{
			if (mod instanceof IHawkPadTexture)
			{
				return ((IHawkPadTexture)mod).getPadTextureLocation(padItem, isBeingRedstoned, electricityStored);
			}
		}
		
		return 0;
	}
	
	@Override
	public int getPadColor(ItemStack padItem, boolean isBeingRedstoned, int electricityStored)
	{
		for (Object mod : MODS)
		{
			if (mod instanceof IHawkPadTexture)
			{
				return ((IHawkPadTexture)mod).getPadColor(padItem, isBeingRedstoned, electricityStored);
			}
		}
		
		return 16777215;
	}
	
	@Override
	public void onRedstoneOn(ItemStack padItem, int electricityStored)
	{
		for (Object mod : MODS)
		{
			if (mod instanceof IHawkPadRedstone)
			{
				((IHawkPadRedstone)mod).onRedstoneOn(padItem, electricityStored);
			}
		}
	}
	
	@Override
	public void onRedstoneOff(ItemStack padItem, int electricityStored)
	{
		for (Object mod : MODS)
		{
			if (mod instanceof IHawkPadRedstone)
			{
				((IHawkPadRedstone)mod).onRedstoneOff(padItem, electricityStored);
			}
		}
	}
	
	@Override
	public boolean canPadItemBeDroppedOnDestroyed(ItemStack padItem, boolean isBeingRedstoned)
	{
		for (Object mod : MODS)
		{
			if (mod instanceof IHawkPadMisc)
			{
				return ((IHawkPadMisc)mod).canPadItemBeDroppedOnDestroyed(padItem, isBeingRedstoned);
			}
		}
		
		return true;
	}
	
	@Override
	public int getRequiredElectricityForPad(ItemStack padItem, int electricityStored, boolean isBeingRedstoned)
	{
		for (Object mod : MODS)
		{
			if (mod instanceof IHawkPadElectricity)
			{
				return ((IHawkPadElectricity)mod).getRequiredElectricityForPad(padItem, electricityStored, isBeingRedstoned);
			}
		}
		
		return 10;
	}
	
	@Override
	public boolean canConductElectricity(ItemStack padItem, int electricityStored, boolean isBeingRedstoned)
	{
		for (Object mod : MODS)
		{
			if (mod instanceof IHawkPadElectricity)
			{
				return ((IHawkPadElectricity)mod).canConductElectricity(padItem, electricityStored, isBeingRedstoned);
			}
		}
		
		return true;
	}
	
	@Override
	public void getPadEffect(ItemStack padItem, World world, int x, int y, int z, Entity entity, boolean isBeingRedstoned, int electricityStored)
	{
		for (Object mod : MODS)
		{
			if (mod instanceof IHawkPadEffect)
			{
				((IHawkPadEffect)mod).getPadEffect(padItem, world, x, y, z, entity, isBeingRedstoned, electricityStored);
			}
		}
	}
	
	@Override
	public boolean isItemValidForPad(ItemStack padItem, World world, int x, int y, int z, EntityPlayer player, ForgeDirection padDirection, boolean isBeingRedstoned)
	{
		for (Object mod : MODS)
		{
			if (mod instanceof IHawkPadEffect)
			{
				return ((IHawkPadEffect)mod).isItemValidForPad(padItem, world, x, y, z, player, padDirection, isBeingRedstoned);
			}
		}
		
		return false;
	}
	
}
