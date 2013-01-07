
package hawksmachinery.common.item;

import hawksmachinery.common.api.HMRepairInterfaces.IHMRepairable;
import hawksmachinery.common.api.HMRepairInterfaces.IHMSappable;
import hawksmachinery.common.api.IHMMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import universalelectricity.core.electricity.ElectricInfo;
import universalelectricity.core.electricity.ElectricInfo.ElectricUnit;
import universalelectricity.core.implement.IDisableable;
import universalelectricity.core.implement.IJouleStorage;
import universalelectricity.core.implement.IVoltage;
import universalelectricity.prefab.multiblock.TileEntityMulti;
import universalelectricity.prefab.repair.IRepairable;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMItemMeter extends HMItemElectric
{
	private static String[] stateNames = new String[]{"Voltage", "Electricity Cached", "Disabled", "Can work", "Repairable", "Machine Health"};
	
	public HMItemMeter(int id)
	{
		super(id);
		setItemName("HMMachineMeter");
		setIconIndex(139);
		
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
	{
		if (!item.isItemEqual(this.getUncharged()) && (item.getItemDamage() + 250) <= this.getMaxJoules(item))
		{
			byte meterState = item.stackTagCompound.getByte("meterState");
			
			if (player.isSneaking())
			{
				MovingObjectPosition pos = this.getMovingObjectPositionFromPlayer(world, player, true);
				
				if (pos != null)
				{
					if (pos.typeOfHit == EnumMovingObjectType.TILE)
					{
						TileEntity tile = world.getBlockTileEntity(pos.blockX, pos.blockY, pos.blockZ);
						
						if (tile != null)
						{
							if (tile instanceof TileEntityMulti)
							{
								tile = ((TileEntityMulti)tile).mainBlockPosition.getTileEntity(world);
								
							}
							
							String meterMessage = stateNames[meterState] + ": ";
							
							switch (meterState)
							{
								case 0: if (tile instanceof IVoltage) meterMessage += ((IVoltage)tile).getVoltage(); break;
								case 1: if (tile instanceof IHMMachine) meterMessage += ((IHMMachine)tile).getElectricity() + "/" + ElectricInfo.getDisplay(((IHMMachine)tile).getMaxElectricity(), ElectricUnit.JOULES); else if (tile instanceof IJouleStorage) meterMessage += ((IJouleStorage)tile).getJoules(tile) + "/" + ElectricInfo.getDisplay(((IJouleStorage)tile).getMaxJoules(tile), ElectricUnit.JOULES); break;
								case 2: if (tile instanceof IDisableable) meterMessage += (((IDisableable)tile).isDisabled()) ? "Yes" : "No"; break;
								case 3: if (tile instanceof IHMMachine) meterMessage += ((IHMMachine)tile).canWork() ? "Yes" : "No"; break;
								case 4: meterMessage += (((tile instanceof IHMRepairable) ? (((IHMRepairable)tile).getMaxHP() > 0 ? "Yes" : "No") : (tile instanceof IRepairable) ? (((IRepairable)tile).getMaxDamage() > 0 ? "Yes" : "No") : "")); break;
								case 5: if (tile instanceof IHMRepairable) meterMessage += ((IHMRepairable)tile).getHP() + "/" + ((IHMRepairable)tile).getMaxHP(); else if (tile instanceof IRepairable) meterMessage += ((IRepairable)tile).getDamage() + "/" + ((IRepairable)tile).getMaxDamage(); break;
								
							}
							
							boolean mchnBeingSapped = false;
							
							if (tile instanceof IHMRepairable)
							{
								if (((IHMSappable)tile).isBeingSapped()) mchnBeingSapped = true;
							}
							
							if (meterMessage.equals(stateNames[meterState] + ": ") || mchnBeingSapped) meterMessage = "Current State: Data unavailable";
							if (!world.isRemote) player.addChatMessage(meterMessage);
							this.onUse(250, item);
							player.swingItem();
							
						}
						
					}
					
				}
				
			}
			else
			{
				item.stackTagCompound.setByte("meterState", (byte)(meterState + 1));
				if (item.stackTagCompound.getByte("meterState") == stateNames.length) item.stackTagCompound.setByte("meterState", (byte)0);
				if (!world.isRemote) player.addChatMessage("Current State: " + stateNames[item.stackTagCompound.getByte("meterState")]);
				
			}
			
		}
		
		return item;
	}
	
	@Override
	public void onCreated(ItemStack item, World world, EntityPlayer player)
	{
		super.onCreated(item, world, player);
		item.stackTagCompound.setByte("meterState", (byte)0);
		
	}
	
	@Override
	public double getMaxJoules(Object... data)
	{
		return 10000;
	}
	
}
