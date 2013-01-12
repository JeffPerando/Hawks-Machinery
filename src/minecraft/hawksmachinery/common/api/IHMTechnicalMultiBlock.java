
package hawksmachinery.common.api;

import hawksmachinery.common.api.helpers.HMVector;

/**
 * 
 * A much more lightweight version of Calclavia's Multiblock API.
 * 
 * @author Elusivehawk
 */
public interface IHMTechnicalMultiBlock
{
	/**
	 * @param vector The {@link HMVector} leading to the main block.
	 */
	public void setVector(HMVector vec);
	
	/**
	 * @return What setVector() gave your TileEntity.
	 */
	public HMVector getVector();
	
}
