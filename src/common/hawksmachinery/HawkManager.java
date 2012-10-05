
package hawksmachinery;

import hawksmachinery.tileentity.HawkTileEntityChunkloader;
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
import net.minecraftforge.common.ForgeChunkManager.LoadingCallback;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import cpw.mods.fml.common.IWorldGenerator;
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
	
	public static Achievement prospector = new Achievement(BASEMOD.ACHprospector, "Prospectpr", -1, 0, new ItemStack(Item.pickaxeSteel, 1), AchievementList.buildBetterPickaxe).registerAchievement();
	public static Achievement timeToCrush = new Achievement(BASEMOD.ACHtimeToCrush, "Time to Crush", -2, -3, new ItemStack(BASEMOD.crusher, 1, 0), prospector).registerAchievement().setSpecial();
	//public static Achievement compactCompact = new Achievement(BASEMOD.ACHcompactCompact, "Compact Compact", 0, -2, new ItemStack(BASEMOD.blockMetalStorage, 1, 2), prospector).registerAchievement();
	//public static Achievement minerkiin = new Achievement(BASEMOD.ACHminerkiin, "Minerkiin", -5, 2, new ItemStack(BASEMOD.blockOre, 1, 3), AchievementList.theEnd2).registerAchievement();
	public static Achievement wash = new Achievement(BASEMOD.ACHwash, "Wash", 0, -4, new ItemStack(BASEMOD.washer, 1, 0), timeToCrush).registerAchievement().setSpecial();
	
	public static AchievementPage HAWKSPAGE = new AchievementPage("Hawk's Machinery", timeToCrush, prospector, wash);
	
	public HawkManager(HawksMachinery Basemod)
	{
		BASEMOD = Basemod;
		
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
			if (BASEMOD.enableChunkloader)
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
