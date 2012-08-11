
package net.minecraft.src.hawksmachinery;

import java.util.ArrayList;

import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;
import net.minecraft.src.forge.ITextureProvider;

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
		ModLoader.addName(this, "Ores");
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int meta)
	{
		switch (meta)
		{
			default: return 0;
		}
	}
	
	@Override
	public void addCreativeItems(ArrayList itemList)
	{
		for (int counter = 0; counter < 7; ++counter)
		{
			itemList.add(new ItemStack(this, 1, counter));
		}
	}
	
	@Override
	public String getTextureFile()
	{
		return HawkManager.BLOCK_TEXTURE_FILE;
	}

}
