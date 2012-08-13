
package net.minecraft.src.hawksmachinery;

import java.util.ArrayList;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenMinable;
import net.minecraft.src.forge.ITextureProvider;
import net.minecraft.src.forge.MinecraftForge;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkBlockOre extends Block implements ITextureProvider
{
	public HawkBlockOre(int id)
	{
		super(id, Material.rock);
		ModLoader.registerBlock(this, HawkItemBlockOre.class);
		setHardness(1.0F);
		setResistance(50.0F);
		
		MinecraftForge.setBlockHarvestLevel(this, 10, "pickaxe", 5);
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
	public void addCreativeItems(ArrayList itemList)
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
