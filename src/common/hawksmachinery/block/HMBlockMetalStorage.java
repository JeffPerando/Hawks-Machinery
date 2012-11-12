
package hawksmachinery.block;

import java.util.List;
import hawksmachinery.item.HMItemBlockMetalStorage;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMBlockMetalStorage extends HMBlock
{
	public HMBlockMetalStorage(int id)
	{
		super(id, Material.iron, -1, null);
		setStepSound(Block.soundMetalFootstep);
		GameRegistry.registerBlock(this, HMItemBlockMetalStorage.class);
		setCreativeTab(CreativeTabs.tabBlock);
		
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int meta)
	{
		return 243 + meta;
	}
	
	@Override
	public void getSubBlocks(int id, CreativeTabs tab, List list)
	{
		list.add(new ItemStack(this, 1, 0));
		list.add(new ItemStack(this, 1, 1));
		
	}
	
}
