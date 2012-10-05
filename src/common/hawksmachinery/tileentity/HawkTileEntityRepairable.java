
package hawksmachinery.tileentity;

import java.util.Random;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;
import hawksmachinery.interfaces.IHawkRepairable;
import hawksmachinery.interfaces.IHawkSapper;

/**
 * 
 * Extend this instead of {@link HawkTileEntityMachine} if you're going to also implement {@link IHawkRepairable}.
 * 
 * @author Elusivehawk
 */
public abstract class HawkTileEntityRepairable extends HawkTileEntityMachine implements IHawkRepairable
{
	public int machineHealth;
	public boolean isBeingSapped;
	public ItemStack sapper;
	
	@Override
	public void updateEntity()
	{
		this.isBeingSapped = this.sapper != null;

		super.updateEntity();
		
		if (this.isBeingSapped || this.machineHealth <= 0)
		{
			++this.disabledTicks;
			if (this.isBeingSapped)
			{
				this.machineHealth -= ((IHawkSapper)this.sapper.getItem()).getSapRate();
				((IHawkSapper)this.sapper.getItem()).sapperTick(this);
			}
			
		}
		
	}
	
	public boolean attemptToUnSap(EntityPlayer player)
	{
		if (((IHawkSapper)this.sapper.getItem()).getSapperRemovalChance() <= 0)
		{
			player.addChatMessage("The Sapper cannot be removed.");
		}
		else
		{
			int removeRate = ((IHawkSapper)this.sapper.getItem()).getSapperRemovalChance();
			int randomInt = new Random().nextInt(removeRate);
			
			if (randomInt == removeRate / 2)
			{
				if (!((IHawkSapper)this.sapper.getItem()).isSapperSingleUse(this.sapper))
				{
					this.worldObj.spawnEntityInWorld(new EntityItem(this.worldObj, this.xCoord + 0.5, this.yCoord + 1.5, this.zCoord + 0.5, this.sapper));
				}
				
				this.sapper = null;
			}
			
		}
		
		return !this.isBeingSapped;
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
		//TODO Wait for Rivet Gun implementation.
		return false;
	}
	
	@Override
	public boolean isDisabled()
	{
		return this.isBeingSapped || this.machineHealth <= 0;
	}
	
	public int getMaxHP()
	{
		return 20;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound NBTTag)
	{
		super.writeToNBT(NBTTag);
		NBTTag.setInteger("MachineHP", this.machineHealth);
		NBTTag.setCompoundTag("Sapper", this.sapper.writeToNBT(new NBTTagCompound()));
		
	}
	
	@Override
	public void readFromNBT(NBTTagCompound NBTTag)
	{
		super.readFromNBT(NBTTag);
		this.machineHealth = NBTTag.getInteger("MachineHP");
		this.sapper = ItemStack.loadItemStackFromNBT(NBTTag.getCompoundTag("Sapper"));
		
	}
	
}
