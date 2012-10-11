
package hawksmachinery.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import hawksmachinery.HawksMachinery;
import net.minecraft.src.Block;
import net.minecraft.src.Material;

/**
 * 
 * My personal preferences. Extend this instead of {@link Block} and you'll save yourself some trouble.
 * 
 * @author Elusivehawk
 */
public class HMBlock extends Block
{
	public static HawksMachinery BASEMOD;
	
	public HMBlock(int id, Material mat, int textureID)
	{
		super(id, mat);
		setHardness(1.0F);
		setResistance(5.0F);
		setTextureFile(BASEMOD.BLOCK_TEXTURE_FILE);
		
		if (textureID >= 0)
		{
			this.blockIndexInTexture = textureID;
		}
		
	}
	
}
