
package hawksmachinery;

import java.util.ArrayList;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.src.*;
import net.minecraftforge.*;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkBlock extends Block
{
	public static HawksMachinery BASEMOD;
	private int[] sides = new int[5];
	
	public HawkBlock(int id, Material material, int side0, int side1, int side2, int side3, int side4, int side5)
    {
	    super(id, material);
	    GameRegistry.registerBlock(this);
	    this.sides[0] = side0;
	    this.sides[1] = side1;
	    this.sides[2] = side2;
	    this.sides[3] = side3;
	    this.sides[4] = side4;
	    this.sides[5]= side5;
    }
	
	@Override
	public String getTextureFile()
	{
		return BASEMOD.BLOCK_TEXTURE_FILE;
	}
	
	@Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);
    }
	
    @Override
	public int getBlockTextureFromSide(int side)
	{
		return this.sides[side];
	}
	
    @Override
    public void addCreativeItems(ArrayList itemList)
    {
    	itemList.add(new ItemStack(this, 1, 0));
    }
}
