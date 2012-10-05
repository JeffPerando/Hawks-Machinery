
package hawksmachinery.items;

import hawksmachinery.interfaces.IHMRepairable;
import hawksmachinery.interfaces.IHMRivet;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumAction;
import net.minecraft.src.EnumMovingObjectType;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMItemRivetGun extends HMItem
{
	private ItemStack rivet;
	
	public HMItemRivetGun(int id, int maxRivets)
	{
		super(id);
		setMaxDamage(maxRivets + 1);
		setMaxStackSize(1);
		setItemName("rivetGun");
		
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
	{
		if (item.getItemDamage() != this.getMaxDamage() - 1 && ((IHMRivet)this.rivet.getItem()).getRepairAmount(this.rivet) > 0 && player.isSneaking())
		{
			player.setItemInUse(item, getMaxItemUseDuration(item));
		}
		
		return item;
	}
	
	@Override
	public ItemStack onFoodEaten(ItemStack item, World world, EntityPlayer player)
	{
		if (item.getItemDamage() != getMaxDamage() - 1)
		{
			MovingObjectPosition locatedBlock = getMovingObjectPositionFromPlayer(world, player, true);
			
			if (locatedBlock == null)
			{
				return item;
			}
			if (locatedBlock.typeOfHit == EnumMovingObjectType.TILE)
			{
				TileEntity hawkTileEntity = world.getBlockTileEntity(locatedBlock.blockX, locatedBlock.blockY, locatedBlock.blockZ);
				
				if (hawkTileEntity instanceof IHMRepairable)
				{
					if (((IHMRepairable)hawkTileEntity).attemptRepair(world, locatedBlock.blockX, locatedBlock.blockY, locatedBlock.blockZ, ((IHMRivet)this.rivet.getItem()).getRepairAmount(this.rivet)))
					{
						//TODO: Add "repair successfull" sound.
						item.damageItem(1, null);
						player.addChatMessage("Machine repair successful.");
						
					}
					else
					{
						//TODO: Add "repair failed" sound.
						player.addChatMessage("Machine repair failed.");
					}
					
					player.swingItem();
					
				}
				
			}
			
		}
		
		return item;
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack item)
	{
		return EnumAction.bow;
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack item)
	{
		return 20;
	}

	public HMItemRivetGun setRivet(ItemStack rivet)
	{
		this.rivet = rivet;
		return this;
	}
	
}
