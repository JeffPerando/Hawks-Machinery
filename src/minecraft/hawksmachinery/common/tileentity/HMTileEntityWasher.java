
package hawksmachinery.common.tileentity;

import hawksmachinery.common.HawksMachinery;
import hawksmachinery.common.api.HMRecipes;
import hawksmachinery.common.api.HMRecipes.HMEnumProcessing;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidTank;
import universalelectricity.core.electricity.ElectricInfo;
import universalelectricity.core.implement.IItemElectric;
import universalelectricity.prefab.network.PacketManager;
import com.google.common.io.ByteArrayDataInput;
import cpw.mods.fml.common.FMLCommonHandler;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMTileEntityWasher extends HMTileEntityMachine implements ITankContainer
{
	public int WATER_LIMIT = 9;
	public int waterUnits;
	private LiquidTank waterTank;
	
	public HMTileEntityWasher()
	{
		super();
		ELECTRICITY_REQUIRED = 5;
		TICKS_REQUIRED = FMLCommonHandler.instance().getSide().isServer() ? HawksMachinery.instance().MANAGER.crusherTicks : 100;
		ELECTRICITY_LIMIT = 1200;
		containingItems = new ItemStack[6];
		machineEnum = HMEnumProcessing.WASHING;
		VOLTAGE = 120;
		isProcessor = true;
		canRotate = true;
		WATER_LIMIT = 9;
		waterTank = new LiquidTank(LiquidContainerRegistry.BUCKET_VOLUME * WATER_LIMIT);
		
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		
		if (HawksMachinery.instance().MANAGER.enableWasherSourceBlockConsump)
		{
			if (this.worldObj.getTotalWorldTime() % 3L == 0L && this.worldObj.getBlockId(this.xCoord, this.yCoord + 1, this.zCoord) == Block.waterStill.blockID && this.waterUnits + 1.0F <= this.WATER_LIMIT)
			{
				this.waterTank.fill(new LiquidStack(Block.waterStill, LiquidContainerRegistry.BUCKET_VOLUME), true);
				this.worldObj.setBlockAndMetadataWithUpdate(this.xCoord, this.yCoord + 1, this.zCoord, 0, 0, true);
				this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord + 1, this.zCoord, 0);
				
			}
			
		}
		
		if (!this.isDisabled() && !this.worldObj.isRemote)
		{
			this.waterUnits = Math.min(Math.max(this.waterUnits,0), this.WATER_LIMIT);
			if (this.waterTank.getLiquid() != null) this.waterUnits = (this.waterTank.getLiquid().amount / LiquidContainerRegistry.BUCKET_VOLUME);
			
			if (this.containingItems[0] != null)
			{
				if (this.containingItems[0].getItem() instanceof IItemElectric)
				{
					IItemElectric electricItem = (IItemElectric) this.containingItems[0].getItem();
					
					if (electricItem.canProduceElectricity() && this.electricityStored > this.ELECTRICITY_LIMIT)
					{
						double receivedElectricity = electricItem.onUse(Math.min(electricItem.getMaxJoules() * 0.01, ElectricInfo.getWattHours(this.ELECTRICITY_REQUIRED)), this.containingItems[0]);
						this.electricityStored += ElectricInfo.getWatts(receivedElectricity);
						
					}
					
				}
				
			}
			
			if (this.containingItems[1] != null)
			{
				if (this.containingItems[1].getItem() == Item.bucketWater && this.waterUnits + 1 <= this.WATER_LIMIT)
				{
					int filled = this.waterTank.fill(new LiquidStack(Block.waterStill, LiquidContainerRegistry.BUCKET_VOLUME), true);
					if (filled > 0) this.containingItems[1] = new ItemStack(Item.bucketEmpty, 1);
					
				}
				
			}
			
			if (this.canWork())
			{
				this.electricityStored -= this.ELECTRICITY_REQUIRED;
				
				if (this.workTicks == 0)
				{
					this.workTicks = this.TICKS_REQUIRED;
					
				}
				else
				{
					--this.workTicks;
					
					if (this.workTicks == 1)
					{
						this.washItem();
						this.workTicks = 0;
						this.waterTank.drain(LiquidContainerRegistry.BUCKET_VOLUME, true);
					}
					
				}
				
			}
			else
			{
				this.workTicks = 0;
				
			}
			
		}
		
	}
	
	public boolean canWork()
	{
		ItemStack output = HMRecipes.getResult(this.containingItems[2], this.machineEnum);
		return output != null && (this.electricityStored >= (this.ELECTRICITY_REQUIRED * 2) && this.waterUnits >= 1) && (this.containingItems[3] == null || (output.isItemEqual(this.containingItems[3]) && output.stackSize + this.containingItems[3].stackSize <= output.getMaxStackSize()));
	}
	
	private void washItem()
	{
		if (this.canWork())
		{
			ItemStack newItem = HMRecipes.getResult(this.containingItems[2], this.machineEnum);
			
			if (this.containingItems[3] == null)
			{
				this.containingItems[3] = newItem.copy();
			}
			else if (this.containingItems[3].isItemEqual(newItem))
			{
				this.containingItems[3].stackSize += newItem.stackSize;
			}
			
			this.decrStackSize(2, HMRecipes.getQuantity(this.containingItems[2], this.machineEnum));
			
			this.randomlyDamageSelf();
			
		}
		
	}
	
	@Override
	public Packet getDescriptionPacket()
	{
		if (this.isOpen > 0) return PacketManager.getPacket("HawksMachinery", this, this.workTicks, this.electricityStored, this.machineHP, this.waterUnits);
		return PacketManager.getPacket("HawksMachinery", this, this.electricityStored, this.machineHP, this.waterUnits);
	}
	
	@Override
	public void handlePacketData(INetworkManager network, int type, Packet250CustomPayload packet, EntityPlayer player, ByteArrayDataInput dataStream)
	{
		try
		{
			if (this.isOpen > 0)
			{
				this.workTicks = dataStream.readInt();
				
			}
			if (this.worldObj.isRemote)
			{
				this.electricityStored = dataStream.readDouble();
				this.machineHP = dataStream.readInt();
				this.waterUnits = dataStream.readInt();
				
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public int getWashingStatus(int par1)
	{
		return this.workTicks * par1 / 200;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound NBTTag)
	{
		super.readFromNBT(NBTTag);
		int water = 0;
		if (NBTTag.getTag("waterUnits") instanceof NBTTagFloat) water = (int)NBTTag.getFloat("waterunits");
		else water = NBTTag.getInteger("waterUnits");
		this.waterTank.setLiquid(new LiquidStack(Block.waterStill, water));
		
	}
	
	@Override
	public void writeToNBT(NBTTagCompound NBTTag)
	{
		super.writeToNBT(NBTTag);
		NBTTag.setInteger("waterUnits", this.waterTank.getLiquid() != null ? this.waterTank.getLiquid().amount : 0);
		
	}
	
	@Override
	public int getSizeInventorySide(ForgeDirection side)
	{
		return this.getStartInventorySide(side) == 3 ? 3 : 1;
	}
	
	@Override
	public int getStartInventorySide(ForgeDirection side)
	{
		if (side == ForgeDirection.UP) return 1;
		
		if (side == ForgeDirection.DOWN) return 0;
		
		if (side == this.getDirection()) return 2;
		
		return 3;
	}
	
	@Override
	public String getInvName()
	{
		return "HMWasher";
	}
	
	@Override
	public int fill(ForgeDirection from, LiquidStack resource, boolean doFill)
	{
		if (resource.itemID == Block.waterStill.blockID) return this.fill(0, resource, doFill);
		return 0;
	}
	
	@Override
	public int fill(int tankIndex, LiquidStack resource, boolean doFill)
	{
		if (tankIndex != 0 || resource == null) return 0;
		return this.waterTank.fill(resource, doFill);
	}
	
	@Override
	public LiquidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
	{
		return null;
	}
	
	@Override
	public LiquidStack drain(int tankIndex, int maxDrain, boolean doDrain)
	{
		return null;
	}
	
	@Override
	public ILiquidTank[] getTanks(ForgeDirection direction)
	{
		return new ILiquidTank[] { this.waterTank };
	}
	
	@Override
	public ILiquidTank getTank(ForgeDirection direction, LiquidStack type)
	{
		return null;
	}
	
}
