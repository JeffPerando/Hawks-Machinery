
package hawksmachinery.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import hawksmachinery.HawksMachinery;
import hawksmachinery.interfaces.HMRepairInterfaces.IHMRepairable;
import hawksmachinery.interfaces.HMRepairInterfaces.IHMSapper;
import hawksmachinery.items.HMItemBlockMachine;
import hawksmachinery.tileentity.HMTileEntityMachine;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;
import universalelectricity.prefab.BlockMachine;

/**
 * 
 * Just a wrapper for {@link BlockMachine}.
 * 
 * @author Elusivehawk
 */
public abstract class HMBlockMachine extends BlockMachine
{
	public static HawksMachinery BASEMOD;
	
	public HMBlockMachine(String name, int id, Material mat)
	{
		super(name, id, mat);
		setHardness(1.0F);
		setResistance(5.0F);
		setTextureFile(BASEMOD.BLOCK_TEXTURE_FILE);
		GameRegistry.registerBlock(this, HMItemBlockMachine.class);
		
	}
	
	@Override
	public boolean onMachineActivated(World world, int x, int y, int z, EntityPlayer player)
	{
		ItemStack playerItem = player.getCurrentEquippedItem();
		
		if (playerItem != null)
		{
			if (playerItem.getItem() instanceof IHMSapper)
			{
				if (((IHMSapper)playerItem.getItem()).sappersRequired(playerItem) > 0)
				{
					IHMSapper sapper = ((IHMSapper)playerItem.getItem());
					
					if (world.getBlockTileEntity(x, y, z) instanceof IHMRepairable)
					{
						return ((IHMRepairable)world.getBlockTileEntity(x, y, z)).setSapper(new ItemStack(playerItem.getItem(), sapper.sappersRequired(playerItem), playerItem.getItemDamage()));
					}
					
				}
				
			}
			
		}
		
		return false;
	}
	
	@Override
	public boolean onSneakUseWrench(World world, int x, int y, int z, EntityPlayer player)
	{
		if (world.getBlockTileEntity(x, y, z) instanceof IHMRepairable)
		{
			if (((IHMRepairable)world.getBlockTileEntity(x, y, z)).isBeingSapped())
			{
				((IHMRepairable)world.getBlockTileEntity(x, y, z)).attemptToUnSap(player);
				return true;
			}
			
		}
		
		return false;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entity)
	{
		if (entity != null)
		{
			if (entity instanceof EntityPlayer)
			{
				((HMTileEntityMachine)world.getBlockTileEntity(x, y, z)).machineHP = (((EntityPlayer)entity).getCurrentEquippedItem().getItemDamage());
				
			}
			
		}
		
	}
	
	@Override
	public boolean isBlockSolid(IBlockAccess world, int x, int y, int z, int side)
	{
		return side != world.getBlockMetadata(x, y, z) && side != 1;
	}
	
}
