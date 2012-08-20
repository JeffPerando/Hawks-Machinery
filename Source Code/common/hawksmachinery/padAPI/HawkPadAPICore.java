
package hawksmachinery.padAPI;

import hawksmachinery.HawkManager;
import java.util.ArrayList;
import java.util.List;
import com.google.common.collect.Lists;
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
public class HawkPadAPICore
{
	private static List<IHawkPadEffect> effectHandlers = Lists.newArrayList();
	private static List<IHawkPadElectricity> electricityHandlers = Lists.newArrayList();
	private static List<IHawkPadMisc> miscHandlers = Lists.newArrayList();
	private static List<IHawkPadRedstone> redstoneHandlers = Lists.newArrayList();
	private static List<IHawkPadTexture> textureHandlers = Lists.newArrayList();
	private static List<IHawkPadUpdate> updateHandlers = Lists.newArrayList();
	private static List<IHawkPadInteraction> interactionHandlers = Lists.newArrayList();
	
	public HawkPadAPICore() {}
	
	public static void registerEffectHandler(IHawkPadEffect mod)
	{
		effectHandlers.add(mod);
	}
	
	public static void registerElectricityHandler(IHawkPadElectricity mod)
	{
		electricityHandlers.add(mod);
	}
	
	public static void registerMiscHandler(IHawkPadMisc mod)
	{
		miscHandlers.add(mod);
	}
	
	public static void registerRedstoneHandler(IHawkPadRedstone mod)
	{
		redstoneHandlers.add(mod);
	}
	
	public static void registerTextureHandler(IHawkPadTexture mod)
	{
		textureHandlers.add(mod);
	}
	
	public static void registerInteractionHandler(IHawkPadInteraction mod)
	{
		interactionHandlers.add(mod);
	}
	
	public static void onPadUpdate(ItemStack padItem, float electricityStored, boolean isBeingRedstoned, int x, int y, int z, World world)
	{
		for (IHawkPadUpdate mod : updateHandlers)
		{
			mod.onPadUpdate(padItem, electricityStored, isBeingRedstoned, x, y, z, world);
		}
	}
	
	public static String getPadTextureFile(ItemStack padItem, boolean isBeingRedstoned, float electricityStored)
	{
		for (IHawkPadTexture mod : textureHandlers)
		{
			return mod.getPadTextureFile(padItem, isBeingRedstoned, electricityStored);
		}
		
		return HawkManager.BLOCK_TEXTURE_FILE;
	}
	
	public static int getPadTextureLocation(ItemStack padItem, boolean isBeingRedstoned, float electricityStored)
	{
		for (IHawkPadTexture mod : textureHandlers)
		{
			return mod.getPadTextureLocation(padItem, isBeingRedstoned, electricityStored);
		}
		
		return 57;
	}
	
	public static int getPadColor(ItemStack padItem, float electricityStored)
	{
		for (IHawkPadTexture mod : textureHandlers)
		{
			return mod.getPadColor(padItem, electricityStored);
		}
		
		return 16777215;
	}
	
	public static void onRedstoneOn(ItemStack padItem, float electricityStored)
	{
		for (IHawkPadRedstone mod : redstoneHandlers)
		{
			mod.onRedstoneOn(padItem, electricityStored);
		}
	}
	
	public static void onRedstoneOff(ItemStack padItem, float electricityStored)
	{
		for (IHawkPadRedstone mod : redstoneHandlers)
		{
			mod.onRedstoneOff(padItem, electricityStored);
		}
	}
	
	public static boolean canPadItemBeDroppedOnDestroyed(ItemStack padItem, boolean isBeingRedstoned)
	{
		for (IHawkPadMisc mod : miscHandlers)
		{
			return mod.canPadItemBeDroppedOnDestroyed(padItem, isBeingRedstoned);
		}
		
		return true;
	}
	
	public static int getRequiredElectricityForPad(ItemStack padItem, float electricityStored, boolean isBeingRedstoned)
	{
		for (IHawkPadElectricity mod : electricityHandlers)
		{
			return mod.getRequiredElectricityForPad(padItem, electricityStored, isBeingRedstoned);
		}
		
		return 10;
	}
	
	public static boolean canConductElectricity(ItemStack padItem, float electricityStored, boolean isBeingRedstoned)
	{
		for (IHawkPadElectricity mod : electricityHandlers)
		{
			return mod.canConductElectricity(padItem, electricityStored, isBeingRedstoned);
		}
		
		return true;
	}
	
	public static int getPadElectricityLimit(ItemStack padItem, float electricityStored, boolean isBeingRedstoned)
	{
		for (IHawkPadElectricity mod : electricityHandlers)
		{
			return mod.getPadElectricityLimit(padItem, electricityStored, isBeingRedstoned);
		}
		
		return 1000;
	}
	
	public static void getPadEffect(ItemStack padItem, World world, int x, int y, int z, Entity entity, boolean isBeingRedstoned, float electricityStored)
	{
		for (IHawkPadEffect mod : effectHandlers)
		{
			mod.getPadEffect(padItem, world, x, y, z, entity, isBeingRedstoned, electricityStored);
		}
	}
	
	public static boolean isItemValidForPad(ItemStack padItem, World world, int x, int y, int z, EntityPlayer player)
	{
		for (IHawkPadEffect mod : effectHandlers)
		{
			return mod.isItemValidForPad(padItem, world, x, y, z, player);
		}
		
		return false;
	}
	
	public static boolean onPadActivated(ItemStack padItem, World world, int x, int y, int z, EntityPlayer player)
	{
		for (IHawkPadInteraction mod : interactionHandlers)
		{
			return mod.onPadActivated(padItem, world, x, y, z, player);
		}
		
		return false;
	}
	
	public static boolean onPadWrenched(ItemStack padItem, World world, int x, int y, int z, EntityPlayer player)
	{
		for (IHawkPadInteraction mod : interactionHandlers)
		{
			return mod.onPadWrenched(padItem, world, x, y, z, player);
		}
		
		return false;
	}
	
}
