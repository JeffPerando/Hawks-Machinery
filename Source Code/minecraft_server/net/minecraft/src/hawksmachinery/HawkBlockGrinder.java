
package net.minecraft.src.hawksmachinery;

import net.minecraft.src.*;
import net.minecraft.src.forge.ITextureProvider;
import net.minecraft.src.universalelectricity.*;
import net.minecraft.src.universalelectricity.extend.*;

/**
 * 
 * Just the block for the Grinder.
 * 
 * @author Elusivehawk
 */
public class HawkBlockGrinder extends BlockMachine implements ITextureProvider
{
	public HawkBlockGrinder(int id, Material material)
    {
        super("Grinder", id, material);
        this.setBlockName("Grinder");
        this.setHardness(2.0F);
        this.setResistance(20.0F);
        ModLoader.registerBlock(this, HawkItemBlockGrinder.class);
    	this.setRequiresSelfNotify();
    }
	
	@Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);
    }
    
    @Override
    public boolean onMachineActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer)
    {
		int metadata = par1World.getBlockMetadata(x, y, z);

        if (!par1World.isRemote)
        {
        	par5EntityPlayer.openGui(HawkManager.getModInstance(), 0, par1World, x, y, z); return true;
        }
        
        return true;
    }

    @Override
    public boolean onUseWrench(World world, int x, int y, int z, EntityPlayer par5EntityPlayer)
    {
		switch(world.getBlockMetadata(x, y, z))
		{
			case 2: world.setBlockMetadataWithNotify(x, y, z, 4); break;
	    	case 5: world.setBlockMetadataWithNotify(x, y, z, 2); break;
	    	case 0: world.setBlockMetadataWithNotify(x, y, z, 5); break;
	    	case 3: world.setBlockMetadataWithNotify(x, y, z, 5); break;
	    	case 4: world.setBlockMetadataWithNotify(x, y, z, 3); break;
		}
		
		return true;
	}
    
    @Override
    public void onBlockPlacedBy(World par1World, int x, int y, int z, EntityLiving entityLiving)
    {
        int direction = MathHelper.floor_double((entityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        int newMetadata = 3;
        
    	switch (direction)
        {
        	case 0: newMetadata = 3; break;
        	case 1: newMetadata = 4; break;
        	case 2: newMetadata = 2; break;
        	case 3: newMetadata = 5; break;
        }
    	
    	par1World.setBlockMetadataWithNotify(x, y, z, newMetadata);
    }

    @Override
    public String getTextureFile()
    {
		return HawkManager.BLOCK_TEXTURE_FILE;
    }
    
    @Override
    public TileEntity getBlockEntity(int metadata)
    {    	
    	return new HawkTileEntityGrinder();
    }
    
    @Override
    public int getBlockTextureFromSideAndMetadata(int side, int metadata)
    {
    	switch (metadata)
		{
    		case 2: switch (side)
    				{
    					case 1: return 5;
    					case 3: return 6;
    					default: return 7;
    				}
    		case 3: switch (side)
    				{
    					case 1: return 5;
    					case 2: return 6;
    					default: return 7;
    				}
    		case 4: switch (side)
    				{
    					case 1: return 23;
    					case 5: return 6;
    					default: return 7;
    				}
    		case 5: switch (side)
    				{
    					case 1: return 23;
    					case 4: return 6;
    					default: return 7;
    				}
    		default: switch (side)
    				{
    					case 1: return 5;
    					case 3: return 6;
    					default: return 7;
    				}
		}
    }
	
	@Override
	public boolean isBlockSolidOnSide(World world, int x, int y, int z, int side)
	{
		if (side != 0 || side == world.getBlockMetadata(x, y, z))
		{
			return false;
		}
		
		return true;
	}

}
