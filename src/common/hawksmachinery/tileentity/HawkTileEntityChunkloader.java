
package hawksmachinery.tileentity;

import hawksmachinery.HawksMachinery;
import net.minecraft.src.Chunk;
import net.minecraft.src.ChunkCoordIntPair;
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
		this.forceChunkLoading(this.heldChunk);
		super.invalidate();
		
	}
	
	@Override
	public void validate()
	{
		ForgeChunkManager.releaseTicket(this.heldChunk);
		super.validate();
	}
	
	public void forceChunkLoading(Ticket ticket)
	{
		if (this.heldChunk == null)
		{
			if (ticket == null)
			{
				Ticket newTicket = ForgeChunkManager.requestTicket(BASEMOD.instance(), this.worldObj, Type.NORMAL);
				newTicket.getModData().setInteger("xCoord", this.xCoord);
				newTicket.getModData().setInteger("yCoord", this.yCoord);
				newTicket.getModData().setInteger("zCoord", this.zCoord);
				newTicket.setChunkListDepth(BASEMOD.maxChunksLoaded);
				this.heldChunk = newTicket;
				
			}
			else
			{
				this.heldChunk = ticket;
			}
			
		}
		
		ForgeChunkManager.forceChunk(this.heldChunk, new ChunkCoordIntPair(this.xCoord >> 4, this.zCoord >> 4));
		
	}
	
}
