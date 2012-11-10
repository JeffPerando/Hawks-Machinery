
package hawksmachinery.blocks;

import net.minecraft.src.Achievement;
import net.minecraft.src.Material;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMBlockOre extends HMBlock
{
	public static String[] en_USNames = new String[]{"Endium", "Cobalt"};
	
	public HMBlockOre(int id)
	{
		super(id, Material.rock, -1, null);
		
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int meta)
	{
		return 227 + meta;
	}
	
}
