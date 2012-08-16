
package hawksmachinery;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenMinable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkBlockOre extends Block
{
	public HawkBlockOre(int id)
	{
		super(id, Material.rock);
		setHardness(1.0F);
		setResistance(5.0F);
		setBlockName("Ore");
		GameRegistry.registerBlock(this, HawkItemBlockOre.class);
		setCreativeTab(CreativeTabs.tabBlock);
		
		MinecraftForge.setBlockHarvestLevel(this, 10, "pickaxe", 5);
		
		OreDictionary.registerOre("oreTitanium", new ItemStack(this, 1, 0));
		OreDictionary.registerOre("oreAluminum", new ItemStack(this, 1, 1));
		OreDictionary.registerOre("oreSilver", new ItemStack(this, 1, 2));
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int meta)
	{
		switch (meta)
		{
			case 0: return 25;
			case 1: return 26;
			case 2: return 27;
			case 5: return 41;
			case 6: return 42;
			case 7: return 43;
			case 10: return 30;
			default: return 0;
		}
	}
	
	@Override
	public void getSubBlocks(int id, CreativeTabs tabs, List itemList)
	{
		itemList.add(new ItemStack(this, 1, 0));
		itemList.add(new ItemStack(this, 1, 1));
		itemList.add(new ItemStack(this, 1, 2));
		itemList.add(new ItemStack(this, 1, 5));
		itemList.add(new ItemStack(this, 1, 6));
		itemList.add(new ItemStack(this, 1, 7));
		itemList.add(new ItemStack(this, 1, 10));

	}
	
	@Override
	public String getTextureFile()
	{
		return HawkManager.BLOCK_TEXTURE_FILE;
	}
	
}
