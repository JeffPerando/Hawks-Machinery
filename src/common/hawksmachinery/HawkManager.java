
package hawksmachinery;

import hawksmachinery.tileentity.HawkTileEntityChunkloader;
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
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.ForgeChunkManager.LoadingCallback;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
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
public class HawkManager implements LoadingCallback, IWorldGenerator, IVillageTradeHandler
{
	public static HawksMachinery BASEMOD;
	private static int chunkLimit;
	
	public static int crusherID;
	public static int endiumOreID;
	public static int washerID;
	public static int chunkloaderID;
	public static int sintererID;
	public static int refrigeratorID;
	
	public static int dustRawID;
	public static int dustRefinedID;
	public static int partsID;
	public static int blueprintID;
	public static int endiumAlloyID;
	
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
	
	public static Achievement prospector;
	public static Achievement timeToCrush;
	//public static Achievement compactCompact = new Achievement(BASEMOD.ACHcompactCompact, "Compact Compact", 0, -2, new ItemStack(BASEMOD.blockMetalStorage, 1, 2), prospector).registerAchievement();
	//public static Achievement minerkiin = new Achievement(BASEMOD.ACHminerkiin, "Minerkiin", -5, 2, new ItemStack(BASEMOD.blockOre, 1, 3), AchievementList.theEnd2).registerAchievement();
	public static Achievement wash;
	
	public static AchievementPage HAWKSPAGE;
	
	public static Configuration HMConfig = new Configuration(new File(Loader.instance().getConfigDir(), "HawksMachinery/HMConfig.cfg"));
	
	public HawkManager(HawksMachinery Basemod)
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
		sintererID = HMConfig.getBlock("Sinterer", 3964).getInt(3965);
		refrigeratorID = HMConfig.getBlock("Refridgerator", 3965).getInt(3965);
		
		generateEndium = HMConfig.get(Configuration.CATEGORY_GENERAL, "Generate Endium", true).getBoolean(true);
		enableUpdateChecking = HMConfig.get(Configuration.CATEGORY_GENERAL, "Enable Update Checking", true).getBoolean(true);
		enableAutoDL = HMConfig.get(Configuration.CATEGORY_GENERAL, "Enable Auto DL", true).getBoolean(true);
		enableChunkloader = HMConfig.get(Configuration.CATEGORY_GENERAL, "Enable Chunkloader Block", true).getBoolean(true);
		
		if (enableChunkloader)
		{
			chunkloaderID = HMConfig.getBlock("Endium Chunkloader", 3964).getInt(3964);
			maxChunksLoaded = HMConfig.get("Max Chunks Loaded", Configuration.CATEGORY_GENERAL, 25).getInt(25);
			
		}
		
		dustRawID = HMConfig.get(Configuration.CATEGORY_ITEM, "Raw Dusts", 24150).getInt(24150);
		dustRefinedID = HMConfig.get(Configuration.CATEGORY_ITEM, "Refined Dusts", 24151).getInt(24151);
		partsID = HMConfig.get(Configuration.CATEGORY_ITEM, "Parts", 24152).getInt(24152);
		blueprintID = HMConfig.get(Configuration.CATEGORY_ITEM, "Blueprints", 24153).getInt(24153);
		endiumAlloyID = HMConfig.get(Configuration.CATEGORY_ITEM, "Endium Alloy", 24154).getInt(24154);
		
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
		
		prospector = new Achievement(ACHprospector, "Prospector", -1, 0, new ItemStack(Item.pickaxeSteel, 1), AchievementList.buildBetterPickaxe).registerAchievement();
		timeToCrush = new Achievement(ACHtimeToCrush, "Time to Crush", -2, -3, new ItemStack(BASEMOD.crusher, 1, 0), prospector).registerAchievement().setSpecial();
		
		
		wash = new Achievement(ACHwash, "Wash", 0, -4, new ItemStack(BASEMOD.washer, 1, 0), timeToCrush).registerAchievement().setSpecial();
		
		HAWKSPAGE = new AchievementPage("Hawk's Machinery", timeToCrush, prospector, wash);
		
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
			//generateNether(world, random, chunkX << 4, chunkZ << 4);
		}
		else if (chunkGenerator instanceof ChunkProviderEnd)
		{
			generateEnd(world, random, chunkX << 4, chunkZ << 4);
		}
	}
	
	public void generateSurface(World world, Random random, int chunkX, int chunkZ)
	{
		
	}
	
	public void generateNether(World world, Random random, int chunkX, int chunkZ)
	{
		//TODO: Do SOMETHING with The Nether.
	}
	
	public void generateEnd(World world, Random random, int chunkX, int chunkZ)
	{
		/*
		if (BASEMOD.generateEndium)
		{
			for (int counter = 0; counter <= 16; ++counter)
			{
				int randPosX = chunkX + random.nextInt(16);
				int randPosY = random.nextInt(50);
				int randPosZ = chunkZ + random.nextInt(16);
				int randAmount = random.nextInt(9);
				
				if (randAmount > 0 && randPosY < 70)
				{
					(new HawkWorldOreBlock(BASEMOD.blockOre.blockID, 3, randAmount)).generate(world, random, randPosX, randPosY, randPosZ);
				}
			}
		}
		*/
	}
	
	@Override
	public void ticketsLoaded(List<Ticket> tickets, World world)
	{
		for (Ticket ticket : tickets)
		{
			int xPos = ticket.getModData().getInteger("xCoord");
			int yPos = ticket.getModData().getInteger("yCoord");
			int zPos = ticket.getModData().getInteger("zCoord");
			
			if (world.getBlockTileEntity(xPos, yPos, zPos) instanceof HawkTileEntityChunkloader)
			{
				((HawkTileEntityChunkloader)world.getBlockTileEntity(xPos, yPos, zPos)).forceChunkLoading(ticket);
				
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
				recipeList.add(new MerchantRecipe(new ItemStack(BASEMOD.chunkloader, 1), new ItemStack(Item.emerald, 12)));
			}
			
		}
		
		if (profession == 3)
		{
			//TODO Add Blacksmith trades.
		}
		
	}
	
}
