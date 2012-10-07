
package hawksmachinery.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import hawksmachinery.HawksMachinery;
import hawksmachinery.interfaces.HMRepairInterfaces.IHMRepairable;
import hawksmachinery.interfaces.HMRepairInterfaces.IHMSapper;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;
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
	
	@Override
	public boolean onMachineActivated(World world, int x, int y, int z, EntityPlayer player)
	{
		ItemStack playerItem = player.getCurrentEquippedItem();
		
		if (playerItem.getItem() instanceof IHMSapper)
		{
			if (((IHMSapper)playerItem.getItem()).sappersRequired(playerItem) > 0)
			{
				IHMSapper sapper = ((IHMSapper)playerItem.getItem());
				
				if (world.getBlockTileEntity(x, y, z) instanceof IHMRepairable)
				{
					return ((IHMRepairable)world.getBlockTileEntity(x, y, z)).setSapper(new ItemStack(playerItem.getItem(), sapper.sappersRequired(playerItem), playerItem.getItemDamage()));
				}
				
			}
			
		}
		
		return false;
	}
	
	@Override
	public boolean onSneakUseWrench(World world, int x, int y, int z, EntityPlayer player)
	{
		if (world.getBlockTileEntity(x, y, z) instanceof IHMRepairable)
		{
			((IHMRepairable)world.getBlockTileEntity(x, y, z)).attemptToUnSap(player);
			return true;
		}
		
		return false;
	}
	
	public void registerSelf()
	{
		GameRegistry.registerBlock(this);
	}
	
	public int getItemBlockIconIndex()
	{
		return 0;
	}
	
}
