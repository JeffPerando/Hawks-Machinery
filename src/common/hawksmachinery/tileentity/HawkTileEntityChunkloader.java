
package hawksmachinery.tileentity;

import hawksmachinery.HawksMachinery;
import net.minecraft.src.Chunk;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.ForgeChunkManager.Type;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkTileEntityChunkloader extends TileEntity
{
	public static HawksMachinery BASEMOD;
	public String ownerUsername;
	public Ticket heldChunk;
	
	@Override
	public void writeToNBT(NBTTagCompound NBTTag)
	{
		super.writeToNBT(NBTTag);
		
		NBTTag.setString("owner", this.ownerUsername);
		
	}
	
	@Override
	public void readFromNBT(NBTTagCompound NBTTag)
	{
		super.readFromNBT(NBTTag);
		
		this.ownerUsername = NBTTag.getString("owner");
	}
	
	@Override
	public void invalidate()
	{
		this.heldChunk = ForgeChunkManager.requestTicket(BASEMOD, this.worldObj, Type.NORMAL);
	}
	
	@Override
	public void validate()
	{
		ForgeChunkManager.releaseTicket(this.heldChunk);
	}
	
}
