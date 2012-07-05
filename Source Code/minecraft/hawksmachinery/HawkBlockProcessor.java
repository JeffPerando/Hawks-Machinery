/**
 * 
 */
package hawksmachinery;

import net.minecraft.src.Block;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModLoader;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraft.src.forge.ITextureProvider;
import net.minecraft.src.universalelectricity.extend.BlockMachine;
import net.minecraft.src.universalelectricity.extend.IRotatable;

/**
 * @author Elusivehawk
 *
 */
public class HawkBlockProcessor extends BlockMachine implements ITextureProvider
{
	public HawkBlockProcessor(String name, int id, Material material)
    {
        super(name, id, material);
        this.setBlockName(name);
        this.setHardness(0.5F);
        this.setResistance(1.0F);
        ModLoader.addName(this, name);
    }

	@Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);
    }
    
    @Override
    public boolean machineActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer)
    {
		int metadata = par1World.getBlockMetadata(x, y, z);

        if (!par1World.isRemote)
        {
        	par5EntityPlayer.openGui(HawkManager.getModInstance(), 0, par1World, x, y, z); return true;
        }
        
        return true;
    }

    @Override
    public boolean onUseWrench(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer)
    {
    	IRotatable tileEntity = (IRotatable)par1World.getBlockTileEntity(x, y, z);

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

    @Override
    public String getTextureFile()
    {
		return HawkManager.blockTextureFile;
    }
    
    @Override
    public TileEntity getBlockEntity(int metadata)
    {
    	switch(metadata)
    	{
	    	case 0: return new HawkTileEntityGrinder();
    	}
    	
    	return null;
    }
    
    public int getBlockTextureFromSide(int side)
    {
    	switch (side)
    	{
    		case 0: return 1;
    		case 1: return 0;
    		case 2: return 3;
    		default: return 2;
    	}
    }
}
