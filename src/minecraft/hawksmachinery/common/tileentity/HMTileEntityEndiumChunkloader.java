
package hawksmachinery.common.tileentity;

import hawksmachinery.common.HawksMachinery;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.ForgeChunkManager.Type;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMTileEntityEndiumChunkloader extends TileEntity
{
	public static HawksMachinery BASEMOD;
	public Ticket heldChunk;
	
	@Override
	public void invalidate()
	{
		this.forceChunkLoading(null);
	}
	
	@Override
	public void validate()
	{
		this.forceChunkLoading(null);
	}
	
	public void forceChunkLoading(Ticket ticket)
	{
		if (ticket != null)
		{
			this.heldChunk = ticket;
			ForgeChunkManager.forceChunk(this.heldChunk, new ChunkCoordIntPair(this.xCoord >> 4, this.zCoord >> 4));
			
		}
		else
		{
			if (this.heldChunk == null)
			{
				Ticket newTicket = ForgeChunkManager.requestTicket(BASEMOD.instance(), this.worldObj, Type.NORMAL);
				newTicket.getModData().setInteger("xCoord", this.xCoord);
				newTicket.getModData().setInteger("yCoord", this.yCoord);
				newTicket.getModData().setInteger("zCoord", this.zCoord);
				newTicket.setChunkListDepth(BASEMOD.instance().MANAGER.maxChunksLoaded);
				this.heldChunk = newTicket;
				ForgeChunkManager.forceChunk(this.heldChunk, new ChunkCoordIntPair(this.xCoord >> 4, this.zCoord >> 4));
				
			}
			else
			{
				ForgeChunkManager.releaseTicket(this.heldChunk);
				this.heldChunk = null;
				
			}
			
		}
		
	}
	
}
