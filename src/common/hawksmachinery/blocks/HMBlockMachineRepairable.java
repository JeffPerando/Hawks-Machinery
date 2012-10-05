
package hawksmachinery.blocks;

import java.util.Random;
import hawksmachinery.interfaces.IHMSapper;
import hawksmachinery.tileentity.HMTileEntityRepairable;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public abstract class HMBlockMachineRepairable extends HMBlockMachine
{
	public HMTileEntityRepairable tileEntity;
	
	public HMBlockMachineRepairable(String name, int id, Material mat)
	{
		super(name, id, mat);
	}
	
	@Override
	public boolean onMachineActivated(World world, int x, int y, int z, EntityPlayer player)
	{
		if (player.getCurrentEquippedItem().getItem() instanceof IHMSapper)
		{
			ItemStack potentialSapper = player.getCurrentEquippedItem();
			
			if (((IHMSapper)potentialSapper.getItem()).isSapper(potentialSapper))
			{
				int quantity = ((IHMSapper)potentialSapper.getItem()).getSapperQuantity(potentialSapper);
				
				if (quantity <= potentialSapper.stackSize)
				{
					this.tileEntity.sapper = new ItemStack(potentialSapper.getItem(), quantity, potentialSapper.getItemDamage());
					
					if (!player.capabilities.isCreativeMode)
					{
						player.getCurrentEquippedItem().stackSize -= quantity;
					}
					
					((IHMSapper)this.tileEntity.sapper.getItem()).onSapperAttached(world, x, y, z);
					
					return true;
				}
				
			}
			
		}
		
		return false;
	}
	
	@Override
	public boolean onSneakUseWrench(World world, int x, int y, int z, EntityPlayer player)
	{
		if (this.tileEntity.isBeingSapped())
		{
			return this.tileEntity.attemptToUnSap(player);
		}
		
		return false;
	}
	
	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		if (this.tileEntity.isBeingSapped())
		{
			if (!((IHMSapper)this.tileEntity.sapper.getItem()).isSapperSilent(this.tileEntity.sapper))
			{
				world.spawnParticle("largesmoke", x + 0.5, y + 2, z + 0.5, 0, 0, 0);
				world.spawnParticle("largesmoke", x + 0.5, y + 2, z + 0.5, 0, 0, 0);
				world.spawnParticle("largesmoke", x + 0.5, y + 2, z + 0.5, 0, 0, 0);
				
			}
			
		}
		
	}
	
}
