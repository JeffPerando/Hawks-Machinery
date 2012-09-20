
package hawksmachinery.tileentity;

import net.minecraft.src.Chunk;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkTileEntityChunkloader extends TileEntity
{
	public String ownerUsername;
	
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
	
	//TODO: Wait for chunkloading hooks.
	
}
