/**
 * 
 */
package hawksmachinery;

import net.minecraft.src.*;
import net.minecraft.src.forge.*;
import net.minecraft.src.universalelectricity.*;
import net.minecraft.src.universalelectricity.components.*;

/**
 * @author Elusivehawk
 *
 */
public class HawkBlockProcessor extends UEBlockMachine implements ITextureProvider
{

	public HawkBlockProcessor(String name, int id, Material material)
    {
        super(name, id, material);
        this.setBlockName(name);
        this.setHardness(1.0F);
        ModLoader.addName(this, name);
    }

    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);
        this.setDefaultDirection(par1World, par2, par3, par4);
    }

    private void setDefaultDirection(World par1World, int par2, int par3, int par4)
    {
        if (!par1World.isRemote)
        {
            int var5 = par1World.getBlockId(par2, par3, par4 - 1);
            int var6 = par1World.getBlockId(par2, par3, par4 + 1);
            int var7 = par1World.getBlockId(par2 - 1, par3, par4);
            int var8 = par1World.getBlockId(par2 + 1, par3, par4);
            byte var9 = 3;

            if (Block.opaqueCubeLookup[var5] && !Block.opaqueCubeLookup[var6])
            {
                var9 = 3;
            }

            if (Block.opaqueCubeLookup[var6] && !Block.opaqueCubeLookup[var5])
            {
                var9 = 2;
            }

            if (Block.opaqueCubeLookup[var7] && !Block.opaqueCubeLookup[var8])
            {
                var9 = 5;
            }

            if (Block.opaqueCubeLookup[var8] && !Block.opaqueCubeLookup[var7])
            {
                var9 = 4;
            }

            par1World.setBlockMetadataWithNotify(par2, par3, par4, var9);
        }
    }
    
    public boolean machineActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer)
    {
		int metadata = par1World.getBlockMetadata(x, y, z);

        if (!par1World.isRemote)
        {
        	par5EntityPlayer.openGui(IHawkMiscHandler.getModInstance(), 0, par1World, x, y, z); return true;
        }
        
        return true;
    }

    public boolean onUseWrench(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer)
    {
    	UEIRotatable tileEntity = (UEIRotatable)par1World.getBlockTileEntity(x, y, z);

		switch(tileEntity.getDirection())
		{
			case 2: tileEntity.setDirection((byte)5); break;
	    	case 5: tileEntity.setDirection((byte)3); break;
	    	case 3: tileEntity.setDirection((byte)4); break;
	    	case 4: tileEntity.setDirection((byte)2); break;
		}
		
		par1World.notifyBlocksOfNeighborChange(x, y, z, this.blockID);
		
		return true;
	}

    public String getTextureFile()
    {
		return "/hawksmachinery/blocks.png";
    }
    
    public TileEntity getBlockEntity(int metadata)
    {
    	switch(metadata)
    	{
	    	case 0: return new HawkTileEntityGrinder();
    	}
    	
    	return null;
    }
    
}
