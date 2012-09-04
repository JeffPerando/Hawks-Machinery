
package hawksmachinery;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;
import net.minecraft.src.World;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkBlockMetalStorage extends Block
{
	public static HawksMachinery BASEMOD;
	
	public HawkBlockMetalStorage(int id)
	{
		super(id, Material.iron);
		setHardness(1.5F);
		setResistance(12.0F);
		setBlockName("Metal Storage");
		GameRegistry.registerBlock(this, HawkItemBlockMetalStorage.class);
		setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int meta)
	{
		switch (meta)
		{
			case 0: return 41;
			case 1: return 42;
			case 2: return 43;
			case 3: return 44;
			default: return 0;
		}
	}
	
	@Override
	public void getSubBlocks(int id, CreativeTabs tabs, List itemList)
	{
		for (int counter = 0; counter <= 3; ++counter)
		{
			itemList.add(new ItemStack(this, 1, counter));
		}
	}
	
	public String getTextureFile()
	{
		return BASEMOD.BLOCK_TEXTURE_FILE;
	}
	
	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
		if (world.getBlockMetadata(x, y, z) == 3)
		{
			return 15;
		}
		
		return 0;
	}
	
	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}
	
	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		if (world.getBlockMetadata(x, y, z) == 3)
		{
			int randomInt = random.nextInt(50);
			
			if (randomInt > 2)
			{
				world.spawnParticle("portal", x + 0.5F, y, z + 0.5F, 0, random.nextDouble() + random.nextDouble(), 0);
			}
			
		}
	}
	
}
