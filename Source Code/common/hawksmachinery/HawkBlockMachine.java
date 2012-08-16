
package hawksmachinery;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.src.*;
import net.minecraftforge.*;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkBlockMachine extends Block
{
	public HawkBlockMachine(int id, Material mat)
	{
		super(id, mat);
		setBlockName("Machine Blocks");
		setCreativeTab(CreativeTabs.tabMaterials);
		GameRegistry.registerBlock(this, HawkItemBlockMachine.class);
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int meta)
	{
		switch (meta)
		{
			case 0: return 1;
			case 1: return 18;
			case 2: return 17;
			case 3: return 19;
			case 4: return 21;
			case 5: return 20;
			case 6: return 22;
			default: return 0;
		}
	}
	
	@Override
	public void getSubBlocks(int id, CreativeTabs tabs, List itemList)
	{
		for (int counter = 0; counter <= 6; ++counter)
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
