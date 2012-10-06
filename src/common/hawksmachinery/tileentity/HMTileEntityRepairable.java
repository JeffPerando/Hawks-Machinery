
package hawksmachinery.tileentity;

import java.util.Random;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;
import hawksmachinery.interfaces.HMRepairCore.IHMRepairable;
import hawksmachinery.interfaces.HMRepairCore.IHMSapper;

/**
 * 
 * Extend this instead of {@link HMTileEntityMachine} if you're going to also implement {@link IHMRepairable}.
 * 
 * @author Elusivehawk
 */
public abstract class HMTileEntityRepairable extends HMTileEntityMachine implements IHMRepairable
{
	public int machineHealth;
	public ItemStack sapper;
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		
		if (this.isBeingSapped() || this.machineHealth <= 0)
		{
			++this.disabledTicks;
			if (this.isBeingSapped())
			{
				this.machineHealth -= ((IHMSapper)this.sapper.getItem()).getSapRate();
				((IHMSapper)this.sapper.getItem()).sapperTick(this);
			}
			
		}
		
		if (this.machineHealth > this.getMaxHP())
		{
			this.machineHealth = this.getMaxHP();
		}
		
		if (this.machineHealth < 0)
		{
			this.machineHealth = 0;
		}
		
	}
	
	public boolean attemptToUnSap(EntityPlayer player)
	{
		if (((IHMSapper)this.sapper.getItem()).getSapperRemovalChance() <= 0)
		{
			player.addChatMessage("The Sapper cannot be removed.");
		}
		else
		{
			int removeRate = ((IHMSapper)this.sapper.getItem()).getSapperRemovalChance();
			int randomInt = new Random().nextInt(removeRate);
			
			if (randomInt == removeRate / 2)
			{
				if (!((IHMSapper)this.sapper.getItem()).isSapperSingleUse(this.sapper))
				{
					this.worldObj.spawnEntityInWorld(new EntityItem(this.worldObj, this.xCoord + 0.5, this.yCoord + 1.5, this.zCoord + 0.5, this.sapper));
				}
				
				this.sapper = null;
			}
			
		}
		
		return !this.isBeingSapped();
	}
	
	public boolean randomlyDamageSelf()
	{
		int randomDigit = new Random().nextInt(10);
		
		if (randomDigit == 6)
		{
			--this.machineHealth;
		}
		
		return false;
	}
	
	public boolean attemptRepair(World world, int x, int y, int z, int repairAmount)
	{
		if (this.machineHealth != this.getMaxHP() && !this.isBeingSapped())
		{
			this.machineHealth += repairAmount;
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean isDisabled()
	{
		return this.isBeingSapped() || this.machineHealth <= 0 || this.worldObj.isBlockGettingPowered(this.xCoord, this.yCoord, this.zCoord);
	}
	
	public boolean isBeingSapped()
	{
		return this.sapper != null;
	}
	
	public int getMaxHP()
	{
		return 20;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound NBTTag)
	{
		super.writeToNBT(NBTTag);
		
		if (!NBTTag.hasKey("MachineHP"))
		{
			this.machineHealth = 20;
		}
		
		NBTTag.setInteger("MachineHP", this.machineHealth);
		
		if (this.sapper != null)
		{
			NBTTag.setCompoundTag("Sapper", this.sapper.writeToNBT(new NBTTagCompound()));
			
		}
		
	}
	
	@Override
	public void readFromNBT(NBTTagCompound NBTTag)
	{
		super.readFromNBT(NBTTag);
		
		if (NBTTag.hasKey("MachineHP"))
		{
			this.machineHealth = NBTTag.getInteger("MachineHP");
		}
		else
		{
			this.machineHealth = this.getMaxHP();
		}
		
		this.sapper = ItemStack.loadItemStackFromNBT(NBTTag.getCompoundTag("Sapper"));
		
	}
	
}
