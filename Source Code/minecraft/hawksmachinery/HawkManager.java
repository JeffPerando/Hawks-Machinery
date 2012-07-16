
package hawksmachinery;

import java.io.*;

import net.minecraft.client.*;
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
	
	public static String guiPath = "/hawksmachinery/textures/gui";
	
	public static final String blockTextureFile = "/hawksmachinery/textures/blocks.png";
	public static final String itemTextureFile = "/hawksmachinery/textures/items.png";
	
	public static File hawksFolder = (new File(Minecraft.getMinecraftDir() + "/config/HawksMachinery"));
	
	public static File configuration = (new File(hawksFolder + "/Config.cfg"));

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
		
		grinderID = HMConfig.getOrCreateIntProperty("Grinder", Configuration.CATEGORY_BLOCK, 3960).getInt(3960);
		machineBlockID = HMConfig.getOrCreateIntProperty("Machine Block", Configuration.CATEGORY_BLOCK, 3961).getInt(3961);

		dust1ID	= HMConfig.getOrCreateIntProperty("Coal Dust", Configuration.CATEGORY_ITEM, 24150).getInt(24150);
		dust2ID = HMConfig.getOrCreateIntProperty("Diamond Dust", Configuration.CATEGORY_ITEM, 24151).getInt(24151);
		dust3ID = HMConfig.getOrCreateIntProperty("Unrefined Gold Dust", Configuration.CATEGORY_ITEM, 24152).getInt(24152);
		dust4ID = HMConfig.getOrCreateIntProperty("Ender Dust", Configuration.CATEGORY_ITEM, 24153).getInt(24153);
		dust5ID = HMConfig.getOrCreateIntProperty("Glass Dust", Configuration.CATEGORY_ITEM, 24154).getInt(24154);
		dust6ID = HMConfig.getOrCreateIntProperty("Unrefined Iron Dust", Configuration.CATEGORY_ITEM, 24155).getInt(24155);
		dust7ID = HMConfig.getOrCreateIntProperty("Unrefined Copper Dust", Configuration.CATEGORY_ITEM, 24156).getInt(24156);
		dust8ID = HMConfig.getOrCreateIntProperty("Unrefined Tin Dust", Configuration.CATEGORY_ITEM, 24157).getInt(24157);
		dust9ID = HMConfig.getOrCreateIntProperty("Iron Dust", Configuration.CATEGORY_ITEM, 24158).getInt(24158);
		dust10ID = HMConfig.getOrCreateIntProperty("Gold Dust", Configuration.CATEGORY_ITEM, 24159).getInt(24159);
		dust11ID = HMConfig.getOrCreateIntProperty("Copper Dust", Configuration.CATEGORY_ITEM, 24160).getInt(24160);
		dust12ID = HMConfig.getOrCreateIntProperty("Tin Dust", Configuration.CATEGORY_ITEM, 24161).getInt(24161);

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
		UERecipeManager.addShapelessRecipe((new ItemStack(BasicComponents.ItemSteelIngot, 5)), new Object[]{BasicComponents.ItemSteelPlate});
		UERecipeManager.addShapelessRecipe((new ItemStack(Item.pumpkinSeeds, 4)), new Object[]{Block.pumpkinLantern});

		UERecipeManager.addSmelting(mod_HawksMachinery.glassDust, new ItemStack(Block.thinGlass));
		UERecipeManager.addSmelting(mod_HawksMachinery.ironDust, new ItemStack(Item.ingotIron));
		UERecipeManager.addSmelting(mod_HawksMachinery.goldDust, new ItemStack(Item.ingotGold));
		UERecipeManager.addSmelting(mod_HawksMachinery.copperDust, new ItemStack(BasicComponents.ItemCopperIngot));
		UERecipeManager.addSmelting(mod_HawksMachinery.tinDust, new ItemStack(BasicComponents.ItemTinIngot));
		
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.diamond.shiftedIndex, new ItemStack(mod_HawksMachinery.diamondDust, 4), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.oreGold.blockID, new ItemStack(mod_HawksMachinery.goldDustUnref, 2), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.enderPearl.shiftedIndex, new ItemStack(mod_HawksMachinery.enderDust, 2), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.glass.blockID, new ItemStack(mod_HawksMachinery.glassDust, 4), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.oreIron.blockID, new ItemStack(mod_HawksMachinery.ironDustUnref, 2), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(BasicComponents.ItemCopperIngot.shiftedIndex, new ItemStack(mod_HawksMachinery.copperDust), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(BasicComponents.ItemTinIngot.shiftedIndex, new ItemStack(mod_HawksMachinery.tinDust), 1);		
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.blazeRod.shiftedIndex, new ItemStack(Item.blazePowder, 2), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.bone.shiftedIndex, new ItemStack(Item.dyePowder, 3, 15), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.stone.blockID, new ItemStack(Block.gravel), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.cobblestone.blockID, new ItemStack(Block.sand), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.gravel.blockID, new ItemStack(Item.flint), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.eyeOfEnder.shiftedIndex, new ItemStack(mod_HawksMachinery.enderDust), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.pumpkin.blockID, new ItemStack(Item.pumpkinSeeds, 4), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.pistonBase.blockID, new ItemStack(Item.ingotIron), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.pistonStickyBase.blockID, new ItemStack(Item.ingotIron), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.pumpkinLantern.blockID, new ItemStack(Item.pumpkinSeeds, 4), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.dispenser.blockID, new ItemStack(Item.bow), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.stoneOvenIdle.blockID, new ItemStack(Block.cobblestone, 8), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.thinGlass.blockID, new ItemStack(mod_HawksMachinery.glassDust), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.glowStone.blockID, new ItemStack(Item.lightStoneDust, 4), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.redstoneLampIdle.blockID, new ItemStack(Item.lightStoneDust, 4), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.enchantmentTable.blockID, new ItemStack(Item.diamond, 2), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Item.brewingStand.shiftedIndex, new ItemStack(Item.blazePowder, 2), 1);
		HawkProcessingRecipes.addHawkProcessingRecipe(Block.sandStone.blockID, new ItemStack(Block.sand, 4), 1);

		HawkProcessingRecipes.addHawkMetaProcessingRecipe(Item.coal.shiftedIndex, 0, new ItemStack(mod_HawksMachinery.coalDust), 1);
		HawkProcessingRecipes.addHawkMetaProcessingRecipe(Item.coal.shiftedIndex, 1, new ItemStack(mod_HawksMachinery.coalDust), 1);
		HawkProcessingRecipes.addHawkMetaProcessingRecipe(UEOreManager.getOre(BasicComponents.CopperOreID).blockID, UEOreManager.getOreMetadata(BasicComponents.CopperOreID), new ItemStack(mod_HawksMachinery.copperDustUnref, 2), 1);
		HawkProcessingRecipes.addHawkMetaProcessingRecipe(UEOreManager.getOre(BasicComponents.TinOreID).blockID, UEOreManager.getOreMetadata(BasicComponents.TinOreID), new ItemStack(mod_HawksMachinery.tinDustUnref, 2), 1);
		HawkProcessingRecipes.addHawkMetaProcessingRecipe(Block.plantRed.blockID, 1, new ItemStack(Item.dyePowder, 2, 1), 1);
		HawkProcessingRecipes.addHawkMetaProcessingRecipe(Block.plantYellow.blockID, 11, new ItemStack(Item.dyePowder, 2, 11), 1);
		HawkProcessingRecipes.addHawkMetaProcessingRecipe(Block.stoneBrick.blockID, 0, new ItemStack(Block.cobblestone), 1);
		HawkProcessingRecipes.addHawkMetaProcessingRecipe(Block.stoneBrick.blockID, 1, new ItemStack(Block.cobblestone), 1);
		HawkProcessingRecipes.addHawkMetaProcessingRecipe(Block.stoneBrick.blockID, 2, new ItemStack(Block.cobblestoneMossy), 1);
		HawkProcessingRecipes.addHawkMetaProcessingRecipe(Block.stoneBrick.blockID, 3, new ItemStack(Block.cobblestone), 1);

	}
}
