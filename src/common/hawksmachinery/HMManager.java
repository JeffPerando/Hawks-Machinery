
package hawksmachinery;

import hawksmachinery.tileentity.HMTileEntityEndiumChunkloader;
import java.io.File;
import java.util.List;
import java.util.Random;
import net.minecraft.src.Achievement;
import net.minecraft.src.AchievementList;
import net.minecraft.src.ChunkProviderEnd;
import net.minecraft.src.ChunkProviderGenerate;
import net.minecraft.src.ChunkProviderHell;
import net.minecraft.src.EntityVillager;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MerchantRecipe;
import net.minecraft.src.MerchantRecipeList;
import net.minecraft.src.World;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.ForgeChunkManager.LoadingCallback;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMManager implements LoadingCallback, IWorldGenerator, IVillageTradeHandler
{
	public static HawksMachinery BASEMOD;
	private static int chunkLimit;
	
	public static int crusherID;
	public static int endiumOreID;
	public static int washerID;
	public static int endiumChunkloaderID;
	
	public static int dustRawID;
	public static int dustRefinedID;
	public static int partsID;
	public static int blueprintID;
	public static int endiumPlateID;
	public static int rivetsID;
	public static int rivetGunID;
	public static int ingotsID;
	
	public static int ACHprospector;
	public static int ACHtimeToCrush;
	public static int ACHminerkiin;
	public static int ACHwash;
	
	public static int crusherTicks;
	public static int washerTicks;
	public static int maxChunksLoaded = 100;
	
	public static boolean generateEndium;
	public static boolean enableUpdateChecking;
	public static boolean enableAutoDL;
	public static boolean enableChunkloader;
	
	public static Configuration HMConfig = new Configuration(new File(Loader.instance().getConfigDir(), "HawksMachinery/HMConfig.cfg"));
	
	public HMManager(HawksMachinery Basemod)
	{
		BASEMOD = Basemod;
		
	}
	
	public int loadConfig()
	{

		HMConfig.load();
		
		crusherID = HMConfig.getBlock("Crusher", 3960).getInt(3960);
		endiumOreID = HMConfig.getBlock("Endium Ore", 3961).getInt(3961);
		washerID = HMConfig.getBlock("Washer", 3962).getInt(3962);
		//NOTE ID #3963 saved for Endium Chunkloader.
		
		generateEndium = HMConfig.get(Configuration.CATEGORY_GENERAL, "Generate Endium", true).getBoolean(true);
		enableUpdateChecking = HMConfig.get(Configuration.CATEGORY_GENERAL, "Enable Update Checking", true).getBoolean(true);
		enableAutoDL = HMConfig.get(Configuration.CATEGORY_GENERAL, "Enable Auto DL", true).getBoolean(true);
		enableChunkloader = HMConfig.get(Configuration.CATEGORY_GENERAL, "Enable Chunkloader Block", true).getBoolean(true);
		
		if (enableChunkloader)
		{
			endiumChunkloaderID = HMConfig.getBlock("Endium Chunkloader", 3964).getInt(3964);
			maxChunksLoaded = HMConfig.get("Max Chunks Loaded", Configuration.CATEGORY_GENERAL, 25).getInt(25);
			
		}
		
		dustRawID = HMConfig.get(Configuration.CATEGORY_ITEM, "Raw Dusts", 24150).getInt(24150);
		dustRefinedID = HMConfig.get(Configuration.CATEGORY_ITEM, "Refined Dusts", 24151).getInt(24151);
		partsID = HMConfig.get(Configuration.CATEGORY_ITEM, "Parts", 24152).getInt(24152);
		blueprintID = HMConfig.get(Configuration.CATEGORY_ITEM, "Blueprints", 24153).getInt(24153);
		endiumPlateID = HMConfig.get(Configuration.CATEGORY_ITEM, "Endium Plate", 24154).getInt(24154);
		rivetsID = HMConfig.get(Configuration.CATEGORY_ITEM, "Rivets", 24155).getInt(24155);
		rivetGunID = HMConfig.get(Configuration.CATEGORY_ITEM, "Rivet Gun", 24156).getInt(24156);
		ingotsID = HMConfig.get(Configuration.CATEGORY_ITEM, "Ingots", 24157).getInt(24157);
		
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
		
		return crusherID;
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		if (chunkGenerator instanceof ChunkProviderGenerate)
		{
			generateSurface(world, random, chunkX << 4, chunkZ << 4);
		}
		else if (chunkGenerator instanceof ChunkProviderHell)
		{
			generateNether(world, random, chunkX << 4, chunkZ << 4);
		}
		else if (chunkGenerator instanceof ChunkProviderEnd)
		{
			generateEnd(world, random, chunkX << 4, chunkZ << 4);
		}
	}
	
	public void generateSurface(World world, Random random, int chunkX, int chunkZ){}
	
	public void generateNether(World world, Random random, int chunkX, int chunkZ){}
	
	public void generateEnd(World world, Random random, int chunkX, int chunkZ)
	{
		if (BASEMOD.MANAGER.generateEndium)
		{
			for (int counter = 0; counter <= 16; ++counter)
			{
				int randPosX = chunkX + random.nextInt(16);
				int randPosY = random.nextInt(50);
				int randPosZ = chunkZ + random.nextInt(16);
				int randAmount = random.nextInt(9);
				
				if (randAmount > 0 && randPosY < 70)
				{
					(new HMWorldOreBlock(BASEMOD.endiumOre.blockID, 0, randAmount)).generate(world, random, randPosX, randPosY, randPosZ);
				}
				
			}
			
		}
		
	}
	
	@Override
	public void ticketsLoaded(List<Ticket> tickets, World world)
	{
		for (Ticket ticket : tickets)
		{
			int xPos = ticket.getModData().getInteger("xCoord");
			int yPos = ticket.getModData().getInteger("yCoord");
			int zPos = ticket.getModData().getInteger("zCoord");
			
			if (world.getBlockTileEntity(xPos, yPos, zPos) instanceof HMTileEntityEndiumChunkloader)
			{
				((HMTileEntityEndiumChunkloader)world.getBlockTileEntity(xPos, yPos, zPos)).forceChunkLoading(ticket);
				
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
					recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, emeralds), new ItemStack(Item.writableBook, 1), new ItemStack(BASEMOD.blueprints, 1, 7)));
					
				}
				
				if (emeralds >= 24 && emeralds <= 32)
				{
					for (int meta = 0; meta <= 6; ++meta)
					{
						recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, emeralds), new ItemStack(Item.writableBook, 1), new ItemStack(BASEMOD.blueprints, 1, meta)));
					}
					
				}
				
				if (emeralds >= 52)
				{
					recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, emeralds), new ItemStack(Item.writableBook, 1), new ItemStack(BASEMOD.blueprints, 1, 8)));
				}
				
			}
			
		}
		
		if (profession == 2)
		{
			if (enableChunkloader)
			{
				recipeList.add(new MerchantRecipe(new ItemStack(BASEMOD.endiumChunkloader, 1), new ItemStack(Item.emerald, 12)));
			}
			
		}
		
		if (profession == 3)
		{
			//TODO Add Blacksmith trades.
		}
		
	}
	
}
