
package hawksmachinery.interfaces;

/**
 * 
 * Used in order to store Endium Teleporter coordinates.
 * 
 * @author Elusivehawk
 */
public class HMEndiumTeleporterCoords
{
	private int xCoord;
	private int yCoord;
	private int zCoord;
	private int dimensionID;
	private int symbolA;
	private int symbolB;
	private int symbolC;
	
	public HMEndiumTeleporterCoords(int x, int y, int z, int dimension, int symbol1, int symbol2, int symbol3)
	{
		this.xCoord = x;
		this.yCoord = y;
		this.zCoord = z;
		this.dimensionID = dimension;
		this.symbolA = symbol1;
		this.symbolB = symbol2;
		this.symbolC = symbol3;
		
	}
	
	public boolean isEqual(int dimension, int symbol1, int symbol2, int symbol3)
	{
		return this.dimensionID == dimension && this.symbolA == symbol1 && this.symbolB == symbol2 && this.symbolC == symbol3;
	}
	
	public boolean isEqualXYZ(int x, int y, int z)
	{
		return this.xCoord == x && this.yCoord == y && this.zCoord == z;
	}
	
	public int x()
	{
		return this.xCoord;
	}
	
	public int y()
	{
		return this.yCoord;
	}
	
	public int z()
	{
		return this.zCoord;
	}
	
	public void setSymbolA(int symbol)
	{
		this.symbolA = symbol;
	}
	
	public void setSymbolB(int symbol)
	{
		this.symbolB = symbol;
	}
	
	public void setSymbolC(int symbol)
	{
		this.symbolC = symbol;
	}
	
}
