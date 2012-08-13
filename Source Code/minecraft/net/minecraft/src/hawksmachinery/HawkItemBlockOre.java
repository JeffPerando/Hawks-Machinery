
package net.minecraft.src.hawksmachinery;

import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;
import net.minecraft.src.forge.ITextureProvider;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkItemBlockOre extends ItemBlock implements ITextureProvider
{
	public static mod_HawksMachinery BASEMOD;
	
	public HawkItemBlockOre(int id)
	{
		super(id);
		setHasSubtypes(true);
		setMaxDamage(0);
	}
	
	@Override
	public String getItemDisplayName(ItemStack item)
	{
		switch (item.getItemDamage())
		{
			case 0: return "Titanium Ore";
			case 1: return "Bauxium Ore";
			case 2: return "Silver Ore";
			case 5: return "Nether Titanium Ore";
			case 6: return "Nether Bauxium Ore";
			case 7: return "Nether Silver Ore";
			case 10: return "Dragonite Ore";
			default: return "Buggy coding!";
		}
	}
	
	@Override
	public int getBlockID()
	{
		return BASEMOD.blockOre.blockID;
	}
	
	@Override
	public int getMetadata(int meta)
	{
		return meta;
	}
	
	@Override
	public String getTextureFile()
	{
		return HawkManager.BLOCK_TEXTURE_FILE;
	}
	
}
