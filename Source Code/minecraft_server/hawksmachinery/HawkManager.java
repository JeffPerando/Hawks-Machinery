
package hawksmachinery;

import java.io.*;

import net.minecraft.server.*;
import net.minecraft.src.*;
import net.minecraft.src.basiccomponents.BasicComponents;
import net.minecraft.src.forge.*;
import net.minecraft.src.universalelectricity.ore.UEOreManager;
import net.minecraft.src.universalelectricity.recipe.UERecipeManager;

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
	public static int dust9ID;
	public static int dust10ID;
	public static int dust11ID;
	public static int dust12ID;
	
	public static int machineBlockID;
	public static int grinderID;
	
	public static final String guiPath = "/hawksmachinery/textures/gui";
	public static final String configPath = "/config/HawksMachinery";
	
	public static final String blockTextureFile = "/hawksmachinery/textures/blocks.png";
	public static final String itemTextureFile = "/hawksmachinery/textures/items.png";
	
	public static File configuration = (new File(configPath + "/Config.cfg"));

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

		dust1ID	= HMConfig.getOrCreateIntProperty("Coal Dust", Configuration.CATEGORY_ITEM, 21150).getInt(21150);
		dust2ID = HMConfig.getOrCreateIntProperty("Diamond Dust", Configuration.CATEGORY_ITEM, 21151).getInt(21151);
		dust3ID = HMConfig.getOrCreateIntProperty("Unrefined Gold Dust", Configuration.CATEGORY_ITEM, 21152).getInt(21152);
		dust4ID = HMConfig.getOrCreateIntProperty("Ender Dust", Configuration.CATEGORY_ITEM, 21153).getInt(21153);
		dust5ID = HMConfig.getOrCreateIntProperty("Glass Dust", Configuration.CATEGORY_ITEM, 21154).getInt(21154);
		dust6ID = HMConfig.getOrCreateIntProperty("Unrefined Iron Dust", Configuration.CATEGORY_ITEM, 21155).getInt(21155);
		dust7ID = HMConfig.getOrCreateIntProperty("Unrefined Copper Dust", Configuration.CATEGORY_ITEM, 21156).getInt(21156);
		dust8ID = HMConfig.getOrCreateIntProperty("Unrefined Tin Dust", Configuration.CATEGORY_ITEM, 21157).getInt(21157);
		dust9ID = HMConfig.getOrCreateIntProperty("Iron Dust", Configuration.CATEGORY_ITEM, 21158).getInt(21158);
		dust10ID = HMConfig.getOrCreateIntProperty("Gold Dust", Configuration.CATEGORY_ITEM, 21159).getInt(21159);
		dust11ID = HMConfig.getOrCreateIntProperty("Copper Dust", Configuration.CATEGORY_ITEM, 21160).getInt(21160);
		dust12ID = HMConfig.getOrCreateIntProperty("Tin Dust", Configuration.CATEGORY_ITEM, 21161).getInt(21161);

		HMConfig.save();
		
		return grinderID;
	}

	public static mod_HawksMachinery getModInstance()
	{
		return mod_HawksMachinery.instance;
	}
	
	/**
	 * Loads all of the recipes for Hawk's Machinery, including regular crafting recipes.
	 */
	public static void loadRecipes()
	{
		UERecipeManager.addRecipe((new ItemStack(mod_HawksMachinery.blockEmptyMachine, 1)), new Object[]{"oxo", "xvx", "oxo", 'o', BasicComponents.ItemSteelIngot, 'x', BasicComponents.ItemSteelPlate, 'v', Item.blazePowder});
		UERecipeManager.addRecipe((new ItemStack(mod_HawksMachinery.blockProcessor, 1)), new Object[]{"xix", "xpx", "xex", 'x', BasicComponents.ItemSteelIngot, 'p', Item.pickaxeSteel, 'e', mod_HawksMachinery.blockEmptyMachine, 'i', new ItemStack(BasicComponents.ItemCircuit, 1, 1)});
		UERecipeManager.addRecipe((new ItemStack(BasicComponents.ItemSteelIngot, 24)), new Object[]{"e", 'e', mod_HawksMachinery.blockEmptyMachine});
		UERecipeManager.addRecipe((new ItemStack(BasicComponents.ItemBattery)), new Object[]{" x ", "xcx", "xrx", 'x', BasicComponents.ItemTinIngot, 'c', mod_HawksMachinery.coalDust, 'r', Item.redstone});
		UERecipeManager.addRecipe((new ItemStack(Block.torchWood, 4)), new Object[]{"c", "s", 'c', mod_HawksMachinery.coalDust, 's', Item.stick});
		
		UERecipeManager.addShapelessRecipe((new ItemStack(BasicComponents.ItemSteelClump, 1)), new Object[]{Item.ingotIron, mod_HawksMachinery.coalDust});
		UERecipeManager.addShapelessRecipe((new ItemStack(mod_HawksMachinery.ironDust, 2)), new Object[]{Item.bucketWater, mod_HawksMachinery.ironDustUnref, mod_HawksMachinery.ironDustUnref});
		UERecipeManager.addShapelessRecipe((new ItemStack(mod_HawksMachinery.goldDust, 2)), new Object[]{Item.bucketWater, mod_HawksMachinery.goldDustUnref, mod_HawksMachinery.goldDustUnref});
		UERecipeManager.addShapelessRecipe((new ItemStack(mod_HawksMachinery.copperDust, 2)), new Object[]{Item.bucketWater, mod_HawksMachinery.copperDustUnref, mod_HawksMachinery.copperDustUnref});
		UERecipeManager.addShapelessRecipe((new ItemStack(mod_HawksMachinery.tinDust, 2)), new Object[]{Item.bucketWater, mod_HawksMachinery.tinDustUnref, mod_HawksMachinery.tinDustUnref});
		
		UERecipeManager.addSmelting(mod_HawksMachinery.glassDust, new ItemStack(Block.thinGlass));
		UERecipeManager.addSmelting(mod_HawksMachinery.ironDust, new ItemStack(Item.ingotIron));
		UERecipeManager.addSmelting(mod_HawksMachinery.goldDust, new ItemStack(Item.ingotGold));
		UERecipeManager.addSmelting(mod_HawksMachinery.copperDust, new ItemStack(BasicComponents.ItemCopperIngot));
		UERecipeManager.addSmelting(mod_HawksMachinery.tinDust, new ItemStack(BasicComponents.ItemTinIngot));
		
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.coal.shiftedIndex, new ItemStack(mod_HawksMachinery.coalDust, 1), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.diamond.shiftedIndex, new ItemStack(mod_HawksMachinery.diamondDust, 4), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.oreGold.blockID, new ItemStack(mod_HawksMachinery.goldDustUnref, 2), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.enderPearl.shiftedIndex, new ItemStack(mod_HawksMachinery.enderDust, 2), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.glass.blockID, new ItemStack(mod_HawksMachinery.glassDust, 4), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.oreIron.blockID, new ItemStack(mod_HawksMachinery.ironDustUnref, 2), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(BasicComponents.ItemCopperIngot.shiftedIndex, new ItemStack(mod_HawksMachinery.copperDust), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(BasicComponents.ItemTinIngot.shiftedIndex, new ItemStack(mod_HawksMachinery.tinDust), 1);
		
		HawkProcessingRecipes.addHawkMetaProcessingRecipe(UEOreManager.getOre(BasicComponents.CopperOreID).blockID, UEOreManager.getOreMetadata(BasicComponents.CopperOreID), new ItemStack(mod_HawksMachinery.copperDustUnref, 2), 1);
		HawkProcessingRecipes.addHawkMetaProcessingRecipe(UEOreManager.getOre(BasicComponents.TinOreID).blockID, UEOreManager.getOreMetadata(BasicComponents.TinOreID), new ItemStack(mod_HawksMachinery.tinDustUnref, 2), 1);
		
	}
}
