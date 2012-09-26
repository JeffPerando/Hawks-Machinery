
package hawksmachinery;

import hawksmachinery.tileentity.HawkTileEntityChunkloader;
import java.util.List;
import net.minecraft.src.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.LoadingCallback;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.ForgeChunkManager.Type;

/**
 * 
 * Handles all of the Chunkloading-related code.
 * 
 * @author Elusivehawk
 */
public class HawkChunkHandler implements LoadingCallback
{
	public static HawksMachinery BASEMOD;
	private static int chunkLimit;
	
	public HawkChunkHandler(HawksMachinery Basemod, int limit)
	{
		BASEMOD = Basemod;
		chunkLimit = limit;
		
	}
	
	@Override
	public void ticketsLoaded(List<Ticket> tickets, World world)
	{
		for (Ticket ticket : tickets)
		{
			int xPos = ticket.getModData().getInteger("xCoord");
			int yPos = ticket.getModData().getInteger("yCoord");
			int zPos = ticket.getModData().getInteger("zCoord");
			
			if (world.getBlockTileEntity(xPos, yPos, zPos) instanceof HawkTileEntityChunkloader)
			{
				((HawkTileEntityChunkloader)world.getBlockTileEntity(xPos, yPos, zPos)).forceChunkLoading(ticket);
				
			}
			
		}
		
	}
	
}
