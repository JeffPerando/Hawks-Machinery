
package hawksmachinery.tileentity;

import hawksmachinery.HMInventoryCrafting;
import hawksmachinery.api.HMRecipes;
import hawksmachinery.block.HMBlock;
import hawksmachinery.block.HMBlockStarForge;
import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.Packet;
import net.minecraft.src.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.multiblock.IMultiBlock;
import universalelectricity.prefab.network.PacketManager;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMTileEntityStarForge extends HMTileEntityMachine implements IMultiBlock
{
	/**
	 * The item this Star Forge is going to spit out.
	 */
	public ItemStack output;
	public HMInventoryCrafting matrix;
	
	/**
	 * Used internally to make sure the block drops properly.
	 */
	private boolean canDrop = true;
	
	public HMTileEntityStarForge()
	{
		super();
		ELECTRICITY_REQUIRED = 20;
		TICKS_REQUIRED = 100;
		ELECTRICITY_LIMIT = 2000;
		containingItems = new ItemStack[10];
		voltage = 120;
		isProcessor = true;
		
	}
	
	@Override
	public boolean canReceiveFromSide(ForgeDirection side)
	{
		return side == ForgeDirection.DOWN;
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		
		if (this.canForge())
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
					this.forgeItem();
					this.workTicks = 0;
					
				}
				
			}
			
		}
		else
		{
			this.workTicks = 0;
			
		}
		
	}
	
	public boolean canForge()
	{
		if (this.matrix != null) this.output = HMRecipes.getForgeResult(this.matrix, this.worldObj);
		return this.output != null && (this.electricityStored >= (this.ELECTRICITY_REQUIRED * 2) && !this.isDisabled()) && (this.containingItems[9] == null || (this.output.isItemEqual(this.containingItems[9]) && this.output.stackSize + this.containingItems[9].stackSize <= this.output.getMaxStackSize()));
	}
	
	public void forgeItem()
	{
		if (this.containingItems[9] == null)
		{
			this.containingItems[9] = this.output.copy();
			
		}
		else
		{
			if (this.containingItems[9].isItemEqual(this.output))
			{
				this.containingItems[9].stackSize += this.output.copy().stackSize;
				
			}
			
		}
		
		for (int counter = 0; counter < 9; ++counter)
		{
			if (this.containingItems[counter] != null)
			{
				this.decrStackSize(counter, 1);
				
			}
			
		}
		
		this.randomlyDamageSelf();
		
	}
	
	@Override
	public boolean onActivated(EntityPlayer player)
	{
		if (player.isSneaking()) return false;
		return ((HMBlockStarForge)Block.blocksList[this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord)]).onMachineActivated(this.worldObj, this.xCoord, this.yCoord, this.zCoord, player);
	}
	
	@Override
	public void onCreate(Vector3 placedPosition)
	{
		for (int x = -1; x < 2; ++x)
		{
			for (int z = -1; z < 2; ++z)
			{
				if (x != 0 || z != 0)
				{
					HMBlock.starForgeTechnical.makeFakeBlock(this.worldObj, new Vector3(this.xCoord + x, this.yCoord, this.zCoord + z), placedPosition);
					
				}
				
			}
			
		}
		
	}
	
	public void onDestroy(TileEntity callingBlock, boolean dropItem)
	{
		for (int x = -1; x < 2; ++x)
		{
			for (int z = -1; z < 2; ++z)
			{
				if (x != 0 || z != 0)
				{
					if (dropItem && this.canDrop)
					{
						Block.blocksList[this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord)].dropBlockAsItem(this.worldObj, this.xCoord, this.yCoord, this.zCoord, this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord), this.getBlockMetadata());
						this.canDrop = false;
						
					}
					else if (!dropItem)
					{
						this.canDrop = false;
						
					}
					
					this.worldObj.setBlockWithNotify(this.xCoord + x, this.yCoord, this.zCoord + z, 0);
					
				}
				
			}
			
		}
		
		this.worldObj.setBlockWithNotify(this.xCoord, this.yCoord, this.zCoord, 0);
		this.invalidate();
		
	}
	
	@Override
	public void onDestroy(TileEntity callingBlock)
	{
		this.onDestroy(callingBlock, true);
		
	}
	
	@Override
	public void readFromNBT(NBTTagCompound NBTTag)
	{
		super.readFromNBT(NBTTag);
		if (NBTTag.hasKey("output")) this.output = ItemStack.loadItemStackFromNBT(NBTTag.getCompoundTag("output"));
		
	}
	
	@Override
	public void writeToNBT(NBTTagCompound NBTTag)
	{
		super.writeToNBT(NBTTag);
		if (this.output != null) NBTTag.setCompoundTag("output", this.output.writeToNBT(new NBTTagCompound()));
		
	}
	
	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return (slot > 9) ? super.getStackInSlot(slot) : this.matrix.getStackInSlot(slot);
	}
	
	@Override
	public ItemStack decrStackSize(int slot, int quantity)
	{
		super.decrStackSize(slot, quantity);
		return this.matrix.decrStackSize(slot, quantity);
		
	}
	
	@Override
	public void setInventorySlotContents(int slot, ItemStack item)
	{
		super.setInventorySlotContents(slot, item);
		if (slot < 9) this.matrix.setInventorySlotContents(slot, item);
		
	}
}
