
package hawksmachinery;

import hawksmachinery.padAPI.HawkPadAPICore;
import hawksmachinery.padAPI.IHawkPadTexture;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;
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
	
	private boolean isBeingRedstoned;
	
	private int electricityStored;
	
	public static HawksMachinery BASEMOD;
	
	private static HawkPadAPICore apiCore = new HawkPadAPICore();
	
	public HawkBlockPad(int id)
	{
		super("Pad", id, Material.iron);
		GameRegistry.registerBlock(this, HawkItemBlockPad.class);
		setRequiresSelfNotify();
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int metadata)
	{
		return apiCore.getPadTextureLocation(this.padItem, this.isBeingRedstoned, this.electricityStored);
	}
	
	@Override
	public boolean onMachineActivated(World world, int x, int y, int z, EntityPlayer player)
	{
		
		
		return true;
	}
	
	@Override
	public boolean onSneakMachineActivated(World world, int x, int y, int z, EntityPlayer player)
	{
		return false;
	}
	
	@Override
	public boolean onUseWrench(World world, int x, int y, int z, EntityPlayer player)
	{
		return false;
	}
	
	@Override
	public boolean onSneakUseWrench(World world, int x, int y, int z, EntityPlayer player)
	{
		return false;
	}
	
}
