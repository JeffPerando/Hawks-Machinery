
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
