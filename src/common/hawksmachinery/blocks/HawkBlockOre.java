
package hawksmachinery.blocks;

import hawksmachinery.HawksMachinery;
import hawksmachinery.itemblocks.HawkItemBlockOre;
import hawksmachinery.misc.HawkAchievements;
import java.util.ArrayList;
import java.util.List;
import railcraft.common.api.carts.bore.IBoreHead;
import railcraft.common.api.carts.bore.IMineable;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityMinecart;
import net.minecraft.src.EntityPlayer;
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
public class HawkBlockOre extends HawkBlock implements IMineable
{
	public HawkBlockOre(int id)
	{
		super(id, Material.rock);
		setHardness(1.0F);
		setResistance(5.0F);
		setBlockName("Ore");
		setCreativeTab(CreativeTabs.tabBlock);
		
		MinecraftForge.setBlockHarvestLevel(this, 3, "pickaxe", 5);
		
		OreDictionary.registerOre("oreTitanium", new ItemStack(this, 1, 0));
		OreDictionary.registerOre("oreAluminum", new ItemStack(this, 1, 1));
		OreDictionary.registerOre("oreSilver", new ItemStack(this, 1, 2));
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int meta)
	{
		return 16 + meta;
	}
	
	@Override
	public void getSubBlocks(int id, CreativeTabs tabs, List itemList)
	{
		itemList.add(new ItemStack(this, 1, 0));
		itemList.add(new ItemStack(this, 1, 1));
		itemList.add(new ItemStack(this, 1, 2));
		itemList.add(new ItemStack(this, 1, 3));
		
	}
	
	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}
	
	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int metadata, EntityPlayer player)
	{
		if (metadata == 3)
		{
			player.addStat(HawkAchievements.minerkiin, 1);
		}
		else
		{
			player.addStat(HawkAchievements.prospector, 1);
		}
		
	}
	
	@Override
	public boolean canMineBlock(World world, int i, int j, int k, EntityMinecart bore, ItemStack head)
	{
		if (world.getBlockMetadata(i, j, k) == 3 && ((IBoreHead)head.getItem()).getHarvestLevel() >= 5)
		{
			return true;
		}
		else if (world.getBlockMetadata(i, j, k) != 3 && ((IBoreHead)head.getItem()).getHarvestLevel() >= 4)
		{
			return true;
		}
		
		return false;
	}
	
	@Override
	public void registerSelf()
	{
		GameRegistry.registerBlock(this, HawkItemBlockOre.class);
		
	}
	
}
