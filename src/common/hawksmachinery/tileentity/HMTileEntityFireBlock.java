
package hawksmachinery.tileentity;

import java.util.Random;
import net.minecraft.src.Block;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.core.vector.Vector3;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMTileEntityFireBlock extends HMTileEntityMachine
{
	private Vector3 fire;
	
	public HMTileEntityFireBlock()
	{
		super();
		ELECTRICITY_REQUIRED = 5;
		ELECTRICITY_LIMIT = 20;
		voltage = 120;
		canSendPackets = false;
		
	}
	
	@Override
	public void setDirection(ForgeDirection direction)
	{
		super.setDirection(direction);
		this.fire = new Vector3(this.xCoord + this.getDirection().offsetX, this.yCoord + this.getDirection().offsetY, this.zCoord + this.getDirection().offsetZ);
		
	}
	
	@Override
	public void updateEntity()
	{
		if (this.fire != null)
		{
			if (this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord))
			{
				if (!this.worldObj.isBlockOpaqueCube(this.fire.intX(), this.fire.intY(), this.fire.intZ()))
				{
					this.worldObj.playSoundEffect(this.xCoord, this.yCoord, this.zCoord, "fire.ignite", 1.0F, new Random().nextFloat() * 0.4F + 0.8F);
					this.fire.setBlock(this.worldObj, Block.fire.blockID);
					
				}
				
				this.electricityStored -= this.ELECTRICITY_REQUIRED;
				
			}
			else
			{
				if (this.fire.getBlockID(this.worldObj) == Block.portal.blockID || this.fire.getBlockID(this.worldObj) == Block.fire.blockID)
				{
					this.fire.setBlock(this.worldObj, 0);
					
				}
				
			}
			
		}
		
	}
	
}
