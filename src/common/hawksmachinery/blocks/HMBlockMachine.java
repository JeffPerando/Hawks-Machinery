
package hawksmachinery.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import hawksmachinery.HawksMachinery;
import net.minecraft.src.Material;
import universalelectricity.prefab.BlockMachine;

/**
 * 
 * Just a wrapper for {@link BlockMachine}.
 * 
 * @author Elusivehawk
 */
public abstract class HMBlockMachine extends BlockMachine
{
	public static HawksMachinery BASEMOD;
	
	public HMBlockMachine(String name, int id, Material mat)
	{
		super(name, id, mat);
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
