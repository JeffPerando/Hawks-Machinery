
package hawksmachinery;

import java.io.*;

import net.minecraft.client.*;
import net.minecraft.src.*;
import net.minecraft.src.forge.*;

/**
 * @author Elusivehawk
 * 
 * @Description This class takes cares and manages of all general things
 */
public class HawkManager
{
	public static int dust1ID;
	public static int dust2ID;
	public static int dust3ID;
	public static int dust4ID;
	public static int dust5ID;
	public static int dust6ID;
	public static int dust7ID;
	public static int dust8ID;
	
	public static int machineBlockID;
	public static int grinderID;
	
	public static String guiPath = "/hawksmachinery/gui";
	
	
	public static final String blockTextureFile = "/hawksmachinery/textures/blocks.png";
	public static final String itemTextureFile = "/hawksmachinery/textures/items.png";
	
	public static File configuration = (new File(Minecraft.getMinecraftDir(), "config/UniversalElectricity/HawksMachinery.cfg"));

	public static int initProps()
	{	
		try
		{
			configuration.createNewFile();
			System.out.println("Hawk's Machinery: Config file created/read.");
		}
		catch (IOException e)
		{
			System.out.println("Hawk's Machinery: Something went wrong while creating the config file. Reason: ");
			System.out.println(e);
		}
		
		Configuration HMConfig = new Configuration(configuration);
		
		HMConfig.load();
		
		grinderID = HMConfig.getOrCreateIntProperty("Grinder", Configuration.CATEGORY_BLOCK, 3950).getInt(3950);
		machineBlockID = HMConfig.getOrCreateIntProperty("Machine Block", Configuration.CATEGORY_BLOCK, 3951).getInt(3951);

		dust1ID	= HMConfig.getOrCreateIntProperty("Coal Dust", Configuration.CATEGORY_ITEM, 16001).getInt(16001);
		dust2ID = HMConfig.getOrCreateIntProperty("Diamond Dust", Configuration.CATEGORY_ITEM, 16002).getInt(16002);
		dust3ID = HMConfig.getOrCreateIntProperty("Gold Dust", Configuration.CATEGORY_ITEM, 16003).getInt(16003);
		dust4ID = HMConfig.getOrCreateIntProperty("Ender Dust", Configuration.CATEGORY_ITEM, 16004).getInt(16004);
		dust5ID = HMConfig.getOrCreateIntProperty("Glass Dust", Configuration.CATEGORY_ITEM, 16005).getInt(16005);
		dust6ID = HMConfig.getOrCreateIntProperty("Unrefined Iron Dust", Configuration.CATEGORY_ITEM, 16006).getInt(16006);
		dust7ID = HMConfig.getOrCreateIntProperty("Unrefined Copper Dust", Configuration.CATEGORY_ITEM, 16007).getInt(16007);
		dust8ID = HMConfig.getOrCreateIntProperty("Unrefined Tin Dust", Configuration.CATEGORY_ITEM, 16008).getInt(16008);

		HMConfig.save();
		
		return grinderID;
	}

	public static mod_HawksMachinery getModInstance()
	{
		return mod_HawksMachinery.instance;
	}
}
