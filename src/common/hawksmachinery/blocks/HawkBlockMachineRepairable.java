
package hawksmachinery.blocks;

import java.util.Random;
import hawksmachinery.IHawkSapper;
import hawksmachinery.tileentity.HawkTileEntityRepairable;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.Material;
import net.minecraft.src.World;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public abstract class HawkBlockMachineRepairable extends HawkBlockMachine
{
	public HawkTileEntityRepairable tileEntity;
	
	public HawkBlockMachineRepairable(String name, int id)
	{
		super(name, id, Material.iron);
	}
	
	@Override
	public boolean onMachineActivated(World world, int x, int y, int z, EntityPlayer player)
	{
		if (player.getCurrentEquippedItem().getItem() instanceof IHawkSapper)
		{
			this.tileEntity.sapper = (IHawkSapper)player.getCurrentEquippedItem().getItem();
			
			if (this.tileEntity.sapper.isSapperConsumed() && !player.capabilities.isCreativeMode)
			{
				--player.getCurrentEquippedItem().stackSize;
			}
			
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean onUseWrench(World world, int x, int y, int z, EntityPlayer player)
	{
		if (this.tileEntity.isBeingSapped)
		{
			return this.tileEntity.attemptToUnSap(player);
		}
		
		return false;
	}
	
	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		if (this.tileEntity.isBeingSapped)
		{
			if (!this.tileEntity.sapper.isSapperSilent())
			{
				world.spawnParticle("largesmoke", x + 0.5, y + 2, z + 0.5, 0, 0, 0);
				world.spawnParticle("largesmoke", x + 0.5, y + 2, z + 0.5, 0, 0, 0);
				world.spawnParticle("largesmoke", x + 0.5, y + 2, z + 0.5, 0, 0, 0);
				
			}
			
		}
		
	}
	
}
