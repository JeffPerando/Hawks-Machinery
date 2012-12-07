
package hawksmachinery.item;

import hawksmachinery.HawksMachinery;
import hawksmachinery.api.HMRepairInterfaces.IHMRepairable;
import hawksmachinery.api.HMRepairInterfaces.IHMRivet;
import universalelectricity.prefab.ItemElectric;
import universalelectricity.prefab.UETab;
import universalelectricity.prefab.multiblock.TileEntityMulti;
import universalelectricity.prefab.repair.IRepairable;
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
public class HMItemRivetGun extends ItemElectric
{
	public static HawksMachinery BASEMOD;
	
	public HMItemRivetGun(int id)
	{
		super(id);
		setTextureFile(BASEMOD.ITEM_TEXTURE_FILE);
		setItemName("HMRivetGun");
		setIconIndex(40);
		setCreativeTab(UETab.INSTANCE);
		
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
	{
		if (player.isSneaking() && (item.getItemDamage() < (this.getMaxDamage() - 1) || !item.isItemDamaged()))
		{
			player.setItemInUse(item, getMaxItemUseDuration(item));
		}
		
		return item;
	}
	
	@Override
	public ItemStack onFoodEaten(ItemStack item, World world, EntityPlayer player)
	{
		MovingObjectPosition locatedBlock = this.getMovingObjectPositionFromPlayer(world, player, true);
		
		if (locatedBlock != null)
		{
			if (locatedBlock.typeOfHit == EnumMovingObjectType.TILE);
			{
				TileEntity foundBlock = world.getBlockTileEntity(locatedBlock.blockX, locatedBlock.blockY, locatedBlock.blockZ);
				
				if (foundBlock != null)
				{
					if (foundBlock instanceof TileEntityMulti) foundBlock = ((TileEntityMulti)foundBlock).mainBlockPosition.getTileEntity(world);
					
					if (foundBlock instanceof IHMRepairable)
					{
						if (((IHMRepairable)foundBlock).getMaxHP() > 0)
						{
							return this.tryRepairTile(foundBlock, player, item);
						}
						
					}
					
				}
				
			}
			
		}
		
		return item;
	}
	
	public ItemStack tryRepairTile(TileEntity machine, EntityPlayer player, ItemStack item)
	{
		for (int counter = 0; counter < player.inventory.getSizeInventory(); ++counter)
		{
			ItemStack invItem = player.inventory.getStackInSlot(counter);
			
			if (invItem != null)
			{
				if (invItem.getItem() instanceof IHMRivet)
				{
					int potentialRepairAmount = ((IHMRivet)invItem.getItem()).getRepairAmount(invItem);
					
					if (potentialRepairAmount > 0)
					{
						if (machine instanceof IHMRepairable)
						{
							if (((IHMRepairable)machine).attemptToRepair(potentialRepairAmount))
							{
								player.inventory.decrStackSize(counter, 1);
								player.swingItem();
								this.onUse(10, item);
								return item;
							}
							
						}
						
					}
					
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
		return 25;
	}
	
	@Override
	public double getMaxJoules(Object... data)
	{
		return 3000;
	}
	
	@Override
	public double getVoltage()
	{
		return 120;
	}
	
	@Override
	public boolean isFull3D()
	{
		return true;
	}
	
}
