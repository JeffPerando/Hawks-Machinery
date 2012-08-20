
package hawksmachinery;

import hawksmachinery.padAPI.HawkPadAPICore;
import hawksmachinery.padAPI.IHawkPadTexture;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.extend.BlockMachine;
import universalelectricity.extend.IRedstoneReceptor;

/**
 * 
 * The actual block for the Pad!
 * 
 * @author Elusivehawk
 */
public class HawkBlockPad extends BlockMachine
{
	private ItemStack padItem;
	
	public static HawksMachinery BASEMOD;
	
	private HawkTileEntityPad tileEntity = new HawkTileEntityPad();
	
	private static HawkPadAPICore apiCore = new HawkPadAPICore();
	
	public HawkBlockPad(int id)
	{
		super("Pad", id, Material.iron);
		GameRegistry.registerBlock(this, HawkItemBlockPad.class);
		GameRegistry.registerTileEntity(HawkTileEntityPad.class, "Pad");
		setRequiresSelfNotify();
		setHardness(1.0F);
		setResistance(25.0F);
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int metadata)
	{
		return apiCore.getPadTextureLocation(this.padItem, this.tileEntity.isBeingRedstoned, this.tileEntity.electricityStored);
	}
	
	@Override
	public boolean onMachineActivated(World world, int x, int y, int z, EntityPlayer player)
	{
		ItemStack item = player.getCurrentEquippedItem();
		
		if (!apiCore.onPadActivated(this.padItem, world, x, y, z, player))
		{
			if (this.padItem == null && apiCore.isItemValidForPad(new ItemStack(item.itemID, 1, item.getItemDamage()), world, x, y, z, player))
			{
				this.updatePadItem(new ItemStack(item.itemID, 1, item.getItemDamage()));
				--item.stackSize;
				return true;
			}
			
		}
		
		return false;
	}
	
	@Override
	public boolean onSneakMachineActivated(World world, int x, int y, int z, EntityPlayer player)
	{
		if (this.padItem != null && player.getCurrentEquippedItem() == null)
		{
			world.spawnEntityInWorld(new EntityItem(world, x, y, z, this.padItem));
			this.updatePadItem(null);
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean onUseWrench(World world, int x, int y, int z, EntityPlayer player)
	{
		ItemStack item = player.getCurrentEquippedItem();
		
		if (!apiCore.onPadWrenched(this.padItem, world, x, y, z, player))
		{
			if (this.padItem == null && apiCore.isItemValidForPad(new ItemStack(item.itemID, 1, item.getItemDamage()), world, x, y, z, player))
			{
				this.updatePadItem(new ItemStack(item.itemID, 1, item.getItemDamage()));
				--item.stackSize;
				return true;
			}
			else if (this.padItem != null)
			{
				world.spawnEntityInWorld(new EntityItem(world, x, y, z, this.padItem));
				this.updatePadItem(null);
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public boolean onSneakUseWrench(World world, int x, int y, int z, EntityPlayer player)
	{
		if (this.padItem != null)
		{
			world.spawnEntityInWorld(new EntityItem(world, x, y, z, this.padItem));
			this.updatePadItem(null);
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean hasTileEntity(int metadata)
	{
		return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata)
	{
		return this.tileEntity;
	}
	
	private void updatePadItem(ItemStack item)
	{
		this.padItem = item;
		this.tileEntity.padItem = item;
	}
	
	@Override
	public String getTextureFile()
	{
		return apiCore.getPadTextureFile(this.padItem, this.tileEntity.isBeingRedstoned, this.tileEntity.electricityStored);
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		apiCore.getPadEffect(this.padItem, world, x, y, z, entity, this.tileEntity.isBeingRedstoned, this.tileEntity.electricityStored);
	}
	
}
