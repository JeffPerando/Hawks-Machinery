
package hawksmachinery;

import com.google.common.io.ByteArrayDataInput;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet250CustomPayload;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ISidedInventory;
import universalelectricity.electricity.TileEntityElectricUnit;
import universalelectricity.extend.IRedstoneReceptor;
import universalelectricity.extend.IRotatable;
import universalelectricity.network.IPacketReceiver;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkTileEntityWasher extends TileEntityElectricUnit implements IRedstoneReceptor, IInventory, ISidedInventory, IRotatable, IPacketReceiver
{
	
	@Override
	public void handlePacketData(NetworkManager network, Packet250CustomPayload packet, EntityPlayer player, ByteArrayDataInput dataStream)
	{
		
	}

	@Override
	public ForgeDirection getDirection()
	{
		return null;
	}

	@Override
	public void setDirection(ForgeDirection facingDirection)
	{
		
	}

	@Override
	public int getStartInventorySide(ForgeDirection side)
	{
		return 0;
	}

	@Override
	public int getSizeInventorySide(ForgeDirection side)
	{
		return 0;
	}

	@Override
	public int getSizeInventory()
	{
		return 0;
	}

	@Override
	public ItemStack getStackInSlot(int var1)
	{
		return null;
	}

	@Override
	public ItemStack decrStackSize(int var1, int var2)
	{
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int var1)
	{
		return null;
	}

	@Override
	public void setInventorySlotContents(int var1, ItemStack var2)
	{
		
	}

	@Override
	public String getInvName()
	{
		return "Washer";
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1)
	{
		return false;
	}

	@Override
	public void openChest()
	{
		
	}

	@Override
	public void closeChest()
	{
		
	}

	@Override
	public void onPowerOn()
	{
		
	}

	@Override
	public void onPowerOff()
	{
		
	}
	
	@Override
	public float electricityRequest()
	{
		return 0;
	}
	
	@Override
	public boolean canReceiveFromSide(ForgeDirection side)
	{
		return false;
	}
}
