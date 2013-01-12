
package hawksmachinery.common.tileentity;

import hawksmachinery.common.api.IHMTechnicalMultiBlock;
import hawksmachinery.common.api.helpers.HMVector;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMTileEntityStarForgeTechnical extends TileEntity implements IHMTechnicalMultiBlock
{
	private HMVector vector;
	
	@Override
	public void readFromNBT(NBTTagCompound NBTTag)
	{
		super.readFromNBT(NBTTag);
		this.vector = new HMVector(NBTTag.getCompoundTag("starForge"));
		
	}
	
	@Override
	public void writeToNBT(NBTTagCompound NBTTag)
	{
		super.writeToNBT(NBTTag);
		NBTTag.setCompoundTag("starForge", this.vector.writeToNBTTag(new NBTTagCompound()));
		
	}
	
	@Override
	public void setVector(HMVector vec)
	{
		this.vector = vec;
		
	}

	@Override
	public HMVector getVector()
	{
		return this.vector;
	}
	
}
