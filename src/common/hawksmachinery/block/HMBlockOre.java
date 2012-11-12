
package hawksmachinery.block;

import java.util.List;
import hawksmachinery.item.HMItemBlockOre;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.src.Achievement;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMBlockOre extends HMBlock
{
	public HMBlockOre(int id)
	{
		super(id, Material.rock, -1, null);
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(Block.soundStoneFootstep);
		setCreativeTab(CreativeTabs.tabBlock);
		GameRegistry.registerBlock(this, HMItemBlockOre.class);
		
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int meta)
	{
		return 227 + meta;
	}
	
	@Override
	public boolean canDragonDestroy(World world, int x, int y, int z)
	{
		return world.getBlockMetadata(x, y, z) != 0;
	}
	
	@Override
	public void getSubBlocks(int id, CreativeTabs tab, List list)
	{
		list.add(new ItemStack(this, 1, 0));
		list.add(new ItemStack(this, 1, 1));
		
	}
	
}
