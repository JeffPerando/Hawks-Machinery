
package hawksmachinery.tools.common.item;

import hawksmachinery.core.common.HMCore;
import hawksmachinery.core.common.item.HMItem;
import java.util.List;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMItemEnderMirrorPlayer extends HMItem
{
	public HMItemEnderMirrorPlayer(int id)
	{
		super(id);
		setItemName("HMEnderMirrorPlayer");
		setMaxDamage(8);
		setIconIndex(140);
		setMaxStackSize(1);
		
	}
	
	@Override
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean f3)
	{
		if (item.stackTagCompound != null)
		{
			if (item.stackTagCompound.hasKey("tetheredPlayer"))
			{
				list.add(StatCollector.translateToLocal("hm.endermirror.tetheredTo") + ": " + item.stackTagCompound.getString("tetheredPlayer"));
				
			}
			
		}
		
	}
	
	@Override
	public boolean itemInteractionForEntity(ItemStack item, EntityLiving entity)
	{
		if (item.stackTagCompound == null) item.stackTagCompound = new NBTTagCompound();
		
		if (entity instanceof EntityPlayer)
		{
			item.stackTagCompound.setString("tetheredPlayer", ((EntityPlayer)entity).username);
			
			return true;
		}
		
		return false;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
	{
		if (player != null)
		{
			if (item.stackTagCompound == null) item.stackTagCompound = new NBTTagCompound();
			
			if (player.isSneaking())
			{
				item.stackTagCompound.setString("tetheredPlayer", player.username);
				return item;
			}
			else if (item.stackTagCompound.hasKey("tetheredPlayer"))
			{
				String playerUsername = item.stackTagCompound.getString("tetheredPlayer");
				
				if (!playerUsername.toLowerCase().equals(player.username.toLowerCase()))
				{
					ServerConfigurationManager server = MinecraftServer.getServer().getServerConfigurationManager(MinecraftServer.getServer());
					EntityPlayerMP sentPlayer = server.getPlayerForUsername(player.username);
					EntityPlayerMP targetPlayer = server.getPlayerForUsername(playerUsername);
					
					if (targetPlayer != null)
					{
						if (targetPlayer.dimension == player.dimension)
						{
							sentPlayer.fallDistance = 0;
							sentPlayer.playerNetServerHandler.setPlayerLocation(targetPlayer.posX, targetPlayer.posY, targetPlayer.posZ, targetPlayer.rotationPitch, targetPlayer.rotationYaw);
							item.damageItem(1, player);
							world.playSoundEffect(sentPlayer.posX, sentPlayer.posY, sentPlayer.posZ, "mob.endermen.portal", 1.0F, 1.0F);
							
						}
						
					}
					
				}
				
				
			}
			
		}
		
		return item;
	}
	
}
