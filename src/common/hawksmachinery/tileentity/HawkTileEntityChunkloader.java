
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
	public Chunk chunkInside = this.worldObj.getChunkFromBlockCoords(this.xCoord, this.zCoord);
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
		this.ownerUsername = NBTTag.getString("owner");
	}
	
	//TODO: Wait for chunkloading hooks.
	
}
