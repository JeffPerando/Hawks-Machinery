
package hawksmachinery.core.common;

import hawksmachinery.core.common.item.*;
import hawksmachinery.core.common.tileentity.*;
import java.io.File;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.ForgeChunkManager.LoadingCallback;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.Property;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.world.WorldEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ICraftingHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMCommonProxy implements LoadingCallback, IVillageTradeHandler, ICraftingHandler
{
	public int ACHprospector;
	public int ACHtimeToCrush;
	public int ACHminerkiin;
	public int ACHwash;
	
	public int crusherTicks;
	public int washerTicks;
	public int maxChunksLoaded;
	
	public boolean generateEndium;
	public boolean generateCobalt;
	public boolean enableUpdateChecking;
	public boolean enableAutoDL;
	public boolean enableChunkloader;
	public boolean enableWasherSourceBlockConsump;
	
	public Configuration HMConfig = new Configuration(new File(Loader.instance().getConfigDir(), "HawksMachinery/HMConfig.cfg"));
	
	public void registerRenderInformation()
	{
		GameRegistry.registerTileEntity(HMTileEntityMulti.class, "HMMulti");
		GameRegistry.registerTileEntity(HMTileEntityEndiumChunkloader.class, "HMChunkloader");
		
		GameRegistry.registerBlock(HMCore.ore, HMItemBlockOre.class);
		GameRegistry.registerBlock(HMCore.metalBlock, HMItemBlockMetalStorage.class);
		GameRegistry.registerBlock(HMCore.endiumChunkloader, HMItemBlockEndium.class);
		
	}
	
	public void loadConfig()
	{
		this.HMConfig.load();
		
		this.generateEndium = HMConfig.get(Configuration.CATEGORY_GENERAL, "Generate Endium", true).getBoolean(true);
		this.generateCobalt = HMConfig.get(Configuration.CATEGORY_GENERAL, "Generate Cobalt", true).getBoolean(true);
		this.enableUpdateChecking = HMConfig.get(Configuration.CATEGORY_GENERAL, "Enable Update Checking", true).getBoolean(true);
		this.enableAutoDL = HMConfig.get(Configuration.CATEGORY_GENERAL, "Enable Auto DL", true).getBoolean(true);
		this.enableChunkloader = HMConfig.get(Configuration.CATEGORY_GENERAL, "Enable Chunkloader Crafting", true).getBoolean(true);
		this.enableWasherSourceBlockConsump = HMConfig.get(Configuration.CATEGORY_GENERAL, "Washer Physical Water Consump", true).getBoolean(true);
		this.maxChunksLoaded = HMConfig.get("Max Chunks Loaded", Configuration.CATEGORY_GENERAL, 25).getInt(25);
		
		this.ACHprospector = HMConfig.get(Configuration.CATEGORY_GENERAL, "ACH Prospector", 1500).getInt(1500);
		this.ACHtimeToCrush = HMConfig.get(Configuration.CATEGORY_GENERAL, "ACH Time To Crush", 1501).getInt(1501);
		this.ACHminerkiin = HMConfig.get(Configuration.CATEGORY_GENERAL, "ACH Minerkiin", 1503).getInt(1503);
		this.ACHwash = HMConfig.get(Configuration.CATEGORY_GENERAL, "ACH Wash", 1504).getInt(1504);
		
		if (FMLCommonHandler.instance().getSide().isServer())
		{
			this.HMConfig.addCustomCategoryComment("advanced_settings", "Advanced server OP settings, don't be a moron with them.");
			this.crusherTicks = HMConfig.get("advanced_settings", "Crusher Ticks", 180).getInt(180);
			this.washerTicks = HMConfig.get("advanced_settings", "Washer Ticks", 100).getInt(100);
			
		}
		
		this.HMConfig.save();
		
	}
	
	public int getID(String name, String category, int defaultID, boolean unshiftID)
	{
		this.HMConfig.load();
		Property prop = HMConfig.get(category, name, defaultID - ((unshiftID) ? 256 : 0));
		this.HMConfig.save();
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
				
			}
			
		}
		
	}
	
	@Override
	public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random)
	{
		int profession = villager.getProfession();
		
		if (profession == 2)
		{
			recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald), new ItemStack(HMCore.ingots, 1, 1)));
			
		}
		
		if (profession == 3)
		{
			recipeList.add(new MerchantRecipe(new ItemStack(HMCore.ingots, 1, 0), new ItemStack(Item.emerald, 6)));
			recipeList.add(new MerchantRecipe(new ItemStack(HMCore.ingots, 2, 0), new ItemStack(Item.emerald, 6)));
			
		}
		
	}
	
	@Override
	public void onCrafting(EntityPlayer player, ItemStack item, IInventory craftMatrix){}
	
	@Override
	public void onSmelting(EntityPlayer player, ItemStack item){}
	
	@ForgeSubscribe
	public void Load(WorldEvent event)
	{
		
	}
	
	@ForgeSubscribe
	public void Save(WorldEvent event)
	{
		
	}
	
	@ForgeSubscribe
	public void LivingUpdateEvent(LivingEvent event)
	{
		/*
		if (event.entityLiving instanceof EntityPlayerMP)
		{
			if (((EntityPlayerMP)event.entityLiving).worldObj.getTotalWorldTime() % 40L == 0L)
			{
				for (int counter = 0; counter < ((EntityPlayerMP)event.entityLiving).inventory.getSizeInventory(); ++counter)
				{
					ItemStack playerItem = ((EntityPlayerMP)event.entityLiving).inventory.getStackInSlot(counter);
					
					if (playerItem != null)
					{
						if (playerItem.isItemEnchanted() && playerItem.isItemStackDamageable() && playerItem.isItemDamaged() && playerItem.getItem().isRepairable())
						{
							NBTTagList list = ((EntityPlayerMP)event.entityLiving).inventory.getStackInSlot(counter).stackTagCompound.getTagList("ench");
							
							for (int tag = 0; tag < list.tagCount(); ++tag)
							{
								if (((NBTTagCompound)list.tagAt(tag)).getShort("id") == 0)
								{
									playerItem.setItemDamage(playerItem.getItemDamage() + 1);
									
								}
								
							}
							
						}
						
					}
					
				}
				
			}
			
		}
		*/
	}
	
}
