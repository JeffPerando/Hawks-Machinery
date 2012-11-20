
package hawksmachinery.tileentity;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMTileEntityFireBlock extends HMTileEntityMachine
{
	public HMTileEntityFireBlock()
	{
		super();
		ELECTRICITY_REQUIRED = 5;
		ELECTRICITY_LIMIT = 20;
		voltage = 120;
		
	}
	
}
