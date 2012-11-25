
package hawksmachinery;

import hawksmachinery.api.HMTeleportationHelper;
import hawksmachinery.item.HMItem;
import hawksmachinery.tileentity.HMTileEntityEndiumChunkloader;
import hawksmachinery.tileentity.HMTileEntityTeleporter;
import java.io.File;
import java.util.List;
import java.util.Random;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityVillager;
import net.minecraft.src.IInventory;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MerchantRecipe;
import net.minecraft.src.MerchantRecipeList;
import net.minecraft.src.World;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.ForgeChunkManager.LoadingCallback;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.Property;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ICraftingHandler;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMManager implements LoadingCallback, IVillageTradeHandler, ICraftingHandler, IFuelHandler
{
	public static HawksMachinery BASEMOD;
	private static int chunkLimit;
	
	public static int ACHprospector;
	public static int ACHtimeToCrush;
	public static int ACHminerkiin;
	public static int ACHwash;
	
	public static int crusherTicks;
	public static int washerTicks;
	public static int maxChunksLoaded;
	
	public static boolean generateEndium;
	public static boolean generateCobalt;
	public static boolean enableUpdateChecking;
	public static boolean enableAutoDL;
	public static boolean enableChunkloader;
	
	public static Configuration HMConfig = new Configuration(new File(Loader.instance().getConfigDir(), "HawksMachinery/HMConfig.cfg"));
	
	public HMManager(HawksMachinery Basemod)
	{
		BASEMOD = Basemod;
		
	}
	
	public void loadConfig()
	{
		HMConfig.load();
		
		generateEndium = HMConfig.get(Configuration.CATEGORY_GENERAL, "Generate Endium", true).getBoolean(true);
		generateCobalt = HMConfig.get(Configuration.CATEGORY_GENERAL, "Generate Cobalt", true).getBoolean(true);
		enableUpdateChecking = HMConfig.get(Configuration.CATEGORY_GENERAL, "Enable Update Checking", true).getBoolean(true);
		enableAutoDL = HMConfig.get(Configuration.CATEGORY_GENERAL, "Enable Auto DL", true).getBoolean(true);
		enableChunkloader = HMConfig.get(Configuration.CATEGORY_GENERAL, "Enable Chunkloader Crafting", true).getBoolean(true);
		maxChunksLoaded = HMConfig.get("Max Chunks Loaded", Configuration.CATEGORY_GENERAL, 25).getInt(25);
		
		ACHprospector = HMConfig.get(Configuration.CATEGORY_GENERAL, "ACH Prospector", 1500).getInt(1500);
		ACHtimeToCrush = HMConfig.get(Configuration.CATEGORY_GENERAL, "ACH Time To Crush", 1501).getInt(1501);
		ACHminerkiin = HMConfig.get(Configuration.CATEGORY_GENERAL, "ACH Minerkiin", 1503).getInt(1503);
		ACHwash = HMConfig.get(Configuration.CATEGORY_GENERAL, "ACH Wash", 1504).getInt(1504);
		
		if (FMLCommonHandler.instance().getSide().isServer())
		{
			HMConfig.addCustomCategoryComment("advanced_settings", "Advanced server OP settings, don't be a moron with them.");
			crusherTicks = HMConfig.get("advanced_settings", "Crusher Ticks", 180).getInt(180);
			washerTicks = HMConfig.get("advanced_settings", "Washer Ticks", 100).getInt(100);
			
		}
		
		HMConfig.save();
		
	}
	
	public int getID(String name, String category, int defaultID, boolean unshiftID)
	{
		HMConfig.load();
		Property prop = HMConfig.get(category, name, defaultID - ((unshiftID) ? 256 : 0));
		HMConfig.save();
		return prop.getInt(defaultID - ((unshiftID) ? 256 : 0));
	}
	
	public int getBlockID(String name, int defaultID)
	{
		return this.getID(name, Configuration.CATEGORY_BLOCK, defaultID, false);
	}
	
	public int getItemID(String name, int defaultID)
	{
		return this.getID(name, Configuration.CATEGORY_ITEM, defaultID, true);
	}
	
	@Override
	public void ticketsLoaded(List<Ticket> tickets, World world)
	{
		for (Ticket ticket : tickets)
		{
			int xPos = ticket.getModData().getInteger("xCoord");
			int yPos = ticket.getModData().getInteger("yCoord");
			int zPos = ticket.getModData().getInteger("zCoord");
			
			if (world.getBlockTileEntity(xPos, yPos, zPos) != null)
			{
				if (world.getBlockTileEntity(xPos, yPos, zPos) instanceof HMTileEntityEndiumChunkloader)
				{
					((HMTileEntityEndiumChunkloader)world.getBlockTileEntity(xPos, yPos, zPos)).forceChunkLoading(ticket);
					
				}
				else if (world.getBlockTileEntity(xPos, yPos, zPos) instanceof HMTileEntityTeleporter)
				{
					((HMTileEntityTeleporter)world.getBlockTileEntity(xPos, yPos, zPos)).forceChunkLoading(ticket);
					
				}
				
			}
			
		}
		
	}
	
	@Override
	public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random)
	{
		int profession = villager.getProfession();
		
		if (profession == 1)
		{
			for (int emeralds = 10; emeralds <= 58; ++emeralds)
			{
				if (emeralds <= 17)
				{
					recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, emeralds), new ItemStack(Item.writableBook, 1), new ItemStack(HMItem.blueprints, 1, 7)));
					
				}
				
				if (emeralds >= 24 && emeralds <= 32)
				{
					for (int meta = 0; meta <= 6; ++meta)
					{
						recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, emeralds), new ItemStack(Item.writableBook, 1), new ItemStack(HMItem.blueprints, 1, meta)));
					}
					
				}
				
				if (emeralds >= 52)
				{
					recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, emeralds), new ItemStack(Item.writableBook, 1), new ItemStack(HMItem.blueprints, 1, 8)));
				}
				
			}
			
		}
		
		if (profession == 2)
		{
			
		}
		
		if (profession == 3)
		{
			recipeList.add(new MerchantRecipe(new ItemStack(HMItem.ingots, 1, 0), new ItemStack(Item.emerald, 12)));
			recipeList.add(new MerchantRecipe(new ItemStack(HMItem.ingots, 2, 0), new ItemStack(Item.emerald, 12)));
			
			recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald), new ItemStack(HMItem.ingots, 1, 1)));
			
		}
		
	}
	
	@Override
	public void onCrafting(EntityPlayer player, ItemStack item, IInventory craftMatrix){}
	
	@Override
	public void onSmelting(EntityPlayer player, ItemStack item){}

	@Override
	public int getBurnTime(ItemStack fuel)
	{
		if (fuel.isItemEqual(new ItemStack(HMItem.dustRaw, 1, 0)))
		{
			return 1600;
		}
		
		return 0;
	}
	
	@ForgeSubscribe
	public void Save(WorldEvent event)
	{
		if (!event.world.isRemote)
		{
			HMTeleportationHelper.instance().deleteAllCoords();
			
		}
		
	}
	
}
