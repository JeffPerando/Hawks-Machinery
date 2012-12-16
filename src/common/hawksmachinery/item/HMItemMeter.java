
package hawksmachinery.item;

import hawksmachinery.HawksMachinery;
import hawksmachinery.api.HMRepairInterfaces.IHMRepairable;
import hawksmachinery.tileentity.HMTileEntityMachine;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumMovingObjectType;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import universalelectricity.core.implement.IDisableable;
import universalelectricity.core.implement.IVoltage;
import universalelectricity.prefab.ItemElectric;
import universalelectricity.prefab.UETab;
import universalelectricity.prefab.multiblock.TileEntityMulti;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMItemMeter extends ItemElectric
{
	public static HawksMachinery BASEMOD;
	private static String[] stateNames = new String[]{"Voltage", "Electricity Cached", "Disabled", "Can work", "Repairable", "Machine Health"};
	
	public HMItemMeter(int id)
	{
		super(id);
		setTextureFile(BASEMOD.ITEM_TEXTURE_FILE);
		setItemName("HMMachineMeter");
		setIconIndex(139);
		setCreativeTab(UETab.INSTANCE);
		
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
	{
		byte meterState = item.stackTagCompound.getByte("meterState");
		
		if (player.isSneaking())
		{
			if (!world.isRemote)
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
							
							String mchnState = stateNames[meterState] + ": ";
							
							switch (meterState)
							{
								case 0: if (tile instanceof IVoltage) mchnState += ((IVoltage)tile).getVoltage(); break;
								case 1: if (tile instanceof HMTileEntityMachine) mchnState += ((HMTileEntityMachine)tile).electricityStored + "/" +  ((HMTileEntityMachine)tile).ELECTRICITY_LIMIT; break;
								case 2: if (tile instanceof IDisableable) mchnState += (((IDisableable)tile).isDisabled()) ? "Yes" : "No"; break;
								case 3: if (tile instanceof HMTileEntityMachine) mchnState += ((HMTileEntityMachine)tile).canWork() ? "Yes" : "No"; break;
								case 4: if (tile instanceof IHMRepairable) mchnState += ((IHMRepairable)tile).getMaxHP() > 0 ? "Yes" : "No"; break;
								case 5: if (tile instanceof IHMRepairable) mchnState += ((IHMRepairable)tile).getHP() + "/" + ((IHMRepairable)tile).getMaxHP(); break;
								
							}
							
							if (mchnState.equals(stateNames[meterState] + ": ")) mchnState += "Data unavailable";
							player.addChatMessage(mchnState);
							this.onUse(250, item);
							
						}
						
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
		
		return item;
	}
	
	@Override
	public void onCreated(ItemStack item, World world, EntityPlayer player)
	{
		super.onCreated(item, world, player);
		item.stackTagCompound = new NBTTagCompound();
		item.stackTagCompound.setByte("meterState", (byte)0);
		
	}
	
	@Override
	public double getMaxJoules(Object... data)
	{
		return 10000;
	}
	
	@Override
	public double getVoltage()
	{
		return 120;
	}
	
}
