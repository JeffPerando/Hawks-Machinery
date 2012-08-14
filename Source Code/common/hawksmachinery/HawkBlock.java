
package hawksmachinery;

import java.util.ArrayList;

import net.minecraft.src.*;
import net.minecraftforge.*;

/**
 * @author Elusivehawk
 *
 */
public class HawkBlock extends Block
{
	private static int side0;
	private static int side1;
	private static int side2;
	private static int side3;
	private static int side4;
	private static int side5;
	
	public HawkBlock(String name, int id, Material material, int side0, int side1, int side2, int side3, int side4, int side5)
    {
	    super(id, material);
	    this.setBlockName(name);
	    ModLoader.addName(this, name);
	    ModLoader.registerBlock(this);
	    this.side0 = side0;
	    this.side1 = side1;
	    this.side2 = side2;
	    this.side3 = side3;
	    this.side4 = side4;
	    this.side5 = side5;
    }

	public String getTextureFile()
	{
		return HawkManager.BLOCK_TEXTURE_FILE;
	}
	
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);
    }
	
	public int getBlockTextureFromSide(int side)
	{
		switch (side)
		{
			case 0: return this.side0;
			case 1: return this.side1;
			case 2: return this.side2;
			case 3: return this.side3;
			case 4: return this.side4;
			case 5: return this.side5;
			default: return 0;
		}
	}
	
    public void addCreativeItems(ArrayList itemList)
    {
    	itemList.add(new ItemStack(this, 1, 0));
    }
}
