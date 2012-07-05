/**
 * 
 */
package hawksmachinery;

import java.io.*;

import net.minecraft.client.*;
import net.minecraft.src.*;
import net.minecraft.src.forge.*;

/**
 * @author Elusivehawk
 * 
 * @Description This basically takes care of random stuff.
 */
public class IHawkMiscHandler
{
	public static int dust1ID;
	public static int dust2ID;
	public static int dust3ID;
	public static int dust4ID;
	public static int dust5ID;
	public static int energyCrystalID;
	public static int cubicZirconiumID;
	public static int hawkBrassIngotID;
	public static int machineID;
	public static int miscID;
	public static int oreID;
	
	public static File configuration = (new File(Minecraft.getMinecraftDir(), "config/UniversalElectricity/HawksMachinery/machineryConfig.cfg"));

	public static int initProps()
	{	
		try
		{
			configuration.createNewFile();
			System.out.println("Hawk's Machinery: Config file created");
		}
		catch (IOException e)
		{
			System.out.println("Hawk's Machinery: Something went wrong while creating the config file. Reason: ");
			System.out.println(e);
		}
		
		Configuration HMConfig = new Configuration(configuration);
		
		HMConfig.load();
		
		machineID = HMConfig.getOrCreateIntProperty("machineID", Configuration.CATEGORY_BLOCK, 195).getInt(195);

		dust1ID	= HMConfig.getOrCreateIntProperty("dust1ID", Configuration.CATEGORY_ITEM, 16001).getInt(16001);
		dust2ID = HMConfig.getOrCreateIntProperty("dust2ID", Configuration.CATEGORY_ITEM, 16002).getInt(16002);
		dust3ID = HMConfig.getOrCreateIntProperty("dust3ID", Configuration.CATEGORY_ITEM, 16003).getInt(16003);
		dust4ID = HMConfig.getOrCreateIntProperty("dust4ID", Configuration.CATEGORY_ITEM, 16004).getInt(16004);
		dust5ID = HMConfig.getOrCreateIntProperty("dust5ID", Configuration.CATEGORY_ITEM, 16005).getInt(16005);

		HMConfig.save();
		
		return machineID;
	}

	public static mod_HawksMachinery getModInstance()
	{
		return mod_HawksMachinery.instance;
	}
	
	public static HawkTileEntityGrinder getGrinderInstance()
	{
		return HawkTileEntityGrinder.instance;
	}
}
