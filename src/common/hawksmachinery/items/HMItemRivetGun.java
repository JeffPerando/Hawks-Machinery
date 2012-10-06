
package hawksmachinery.items;

import hawksmachinery.interfaces.HMRepairCore.HMEnumRivet;
import hawksmachinery.interfaces.HMRepairCore.IHMRepairable;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Entity;
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
	private HMEnumRivet rivet;
	
	public HMItemRivetGun(int id, int maxRivets)
	{
		super(id);
		setMaxDamage(maxRivets);
		setMaxStackSize(1);
		setIconIndex(17);
		setNoRepair();
		setItemName("rivetGun");
		setCreativeTab(CreativeTabs.tabTools);
		
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
	{
		if (this.rivet.getRepairAmount() > 0 && player.isSneaking())
		{
			player.setItemInUse(item, getMaxItemUseDuration(item));
		}
		
		return item;
	}
	
	@Override
	public ItemStack onFoodEaten(ItemStack item, World world, EntityPlayer player)
	{
		MovingObjectPosition locatedBlock = getMovingObjectPositionFromPlayer(world, player, true);
		
		if (locatedBlock == null)
		{
			return item;
		}
		else if (locatedBlock.typeOfHit == EnumMovingObjectType.TILE)
		{
			TileEntity hawkTileEntity = world.getBlockTileEntity(locatedBlock.blockX, locatedBlock.blockY, locatedBlock.blockZ);
			
			if (hawkTileEntity instanceof IHMRepairable)
			{
				for (int counter = 0; counter <= 8; ++counter)
				{
					ItemStack hotbarItem = player.inventory.mainInventory[counter];
					
					if (hotbarItem.isItemEqual(this.rivet.getEndResult()) || this.rivet.healsMoreThan(hotbarItem))
					{
						if (((IHMRepairable)hawkTileEntity).attemptRepair(world, locatedBlock.blockX, locatedBlock.blockY, locatedBlock.blockZ, this.rivet.getRepairAmount()))
						{
							//TODO: Add "repair successful" sound.
							item.damageItem(1, null);
							--hotbarItem.stackSize;
							player.addChatMessage("Machine repair successful.");
							
						}
						else
						{
							//TODO: Add "repair failed" sound.
							player.addChatMessage("Machine repair failed.");
							
						}
						
						player.swingItem();
						break;
						
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
		return 20;
	}
	
	@Override
	public void onCreated(ItemStack item, World world, EntityPlayer player)
	{
		if (this.rivet != null)
		{
			item.stackTagCompound.setInteger("Rivet", this.rivet.ordinal());
			
		}
		
	}
	
	public HMItemRivetGun setRivet(HMEnumRivet rivet)
	{
		this.rivet = rivet;
		return this;
	}
	
	@Override
	public void onUpdate(ItemStack item, World world, Entity entity, int par4, boolean par5)
	{
		if (this.rivet == null)
		{
			this.rivet = HMEnumRivet.getRivetFromNumber(item.stackTagCompound.getInteger("Rivet"));
		}
		
	}
	
}
