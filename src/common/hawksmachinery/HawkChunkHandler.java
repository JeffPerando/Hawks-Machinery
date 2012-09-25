
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
	public static HawkTileEntityChunkloader[] chunkloaders;
	private static int chunkLimit;
	
	public HawkChunkHandler(HawksMachinery Basemod, int limit)
	{
		BASEMOD = Basemod;
		chunkLimit = limit;
		
	}
	
	@Override
	public void ticketsLoaded(List<Ticket> tickets, World world)
	{
		
	}
	
	public static Ticket requestHMTicket(HawkTileEntityChunkloader chunkloader, World world, Type type)
	{
		chunkloaders[getEarliestChunkloaderNumber()] = chunkloader;
		return ForgeChunkManager.requestTicket(BASEMOD, world, type);
		
	}
	
	public static void releaseTicket(HawkTileEntityChunkloader chunkloader, Ticket ticket)
	{
		for (HawkTileEntityChunkloader otherChunker : chunkloaders)
		{
			if (chunkloader == otherChunker)
			{
				otherChunker = null;
				chunkloader.heldChunk = null;
				ForgeChunkManager.releaseTicket(ticket);
				
			}
			
		}
		
	}
	
	private static int getEarliestChunkloaderNumber()
	{
		for (int counter = chunkLimit; counter > 0; --counter)
		{
			if (chunkloaders[counter] != null)
			{
				return counter;
			}
			
		}
		
		return 0;
	}
	
}
