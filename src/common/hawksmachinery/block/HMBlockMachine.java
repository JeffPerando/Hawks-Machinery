
package hawksmachinery.block;

import java.util.ArrayList;
import java.util.Random;
import hawksmachinery.HawksMachinery;
import hawksmachinery.api.HMRepairInterfaces.IHMRepairable;
import hawksmachinery.api.HMRepairInterfaces.IHMSapper;
import hawksmachinery.tileentity.HMTileEntityMachine;
import net.minecraft.src.Block;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;
import universalelectricity.prefab.BlockMachine;
import universalelectricity.prefab.UETab;

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
		setStepSound(Block.soundMetalFootstep);
		setTextureFile(BASEMOD.BLOCK_TEXTURE_FILE);
		setCreativeTab(UETab.INSTANCE);
		
	}
	
	@Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entity)
	{
		if (entity != null)
		{
			((HMTileEntityMachine)world.getBlockTileEntity(x, y, z)).machineHP = entity.getHeldItem().getItemDamage();
			
		}
		
	}
	
	@Override
	public boolean onMachineActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		ItemStack playerItem = player.getCurrentEquippedItem();
		
		if (playerItem != null && !player.isSneaking())
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
	public boolean onSneakUseWrench(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
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
	public boolean isBlockSolid(IBlockAccess world, int x, int y, int z, int side)
	{
		return side != world.getBlockMetadata(x, y, z) && side != 1;
	}
	
	@Override
	public int getDamageValue(World world, int x, int y, int z)
	{
		return ((HMTileEntityMachine)world.getBlockTileEntity(x, y, z)).getHP();
	}
	
	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
	{
		ArrayList<ItemStack> itemList = new ArrayList<ItemStack>();
		itemList.add(new ItemStack(this.idDropped(metadata, new Random(), fortune), 1, this.getDamageValue(world, x, y, z)));
		return itemList;
	}
	
}
