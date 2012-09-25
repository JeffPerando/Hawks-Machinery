
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
public abstract class HawkBlock extends Block
{
	public static HawksMachinery BASEMOD;
	
	public HawkBlock(int id, Material mat)
	{
		super(id, mat);
		setHardness(1.0F);
		setResistance(5.0F);
		registerSelf();
		setTextureFile(BASEMOD.BLOCK_TEXTURE_FILE);
	}
	
	public void registerSelf()
	{
		GameRegistry.registerBlock(this);
	}
	
}
