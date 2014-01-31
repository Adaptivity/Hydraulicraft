package k4unl.minecraft.Hydraulicraft.multipart;


import java.util.List;

import k4unl.minecraft.Hydraulicraft.lib.config.Names;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import codechicken.lib.vec.BlockCoord;
import codechicken.lib.vec.Vector3;
import codechicken.multipart.JItemMultiPart;
import codechicken.multipart.MultiPartRegistry;
import codechicken.multipart.TMultiPart;
import codechicken.multipart.TileMultipart;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemPartHose extends JItemMultiPart
{
    public ItemPartHose(int id)
    {
        super(id);
        setHasSubtypes(true);
        setUnlocalizedName(Names.blockHydraulicHose[0].unlocalized);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World w, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        if (super.onItemUse(stack, player, w, x, y, z, side, hitX, hitY, hitZ))
        {
            w.playSoundEffect(x + 0.5, y + 0.5, z + 0.5, Block.soundGlassFootstep.getPlaceSound(), Block.soundGlassFootstep.getVolume() * 5.0F, Block.soundGlassFootstep.getPitch() * .9F);
            return true;
        }
        return false;
    }

    @Override
    public TMultiPart newPart(ItemStack item, EntityPlayer player, World world, BlockCoord pos, int side, Vector3 vhit)
    {
        PartHose w = (PartHose) MultiPartRegistry.createPart(Names.blockHydraulicHose[item.getItemDamage()].unlocalized, false);
        if (w != null)
            w.preparePlacement(item.getItemDamage());
        return w;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(int id, CreativeTabs tab, List list){
    	for(int i = 0; i < 3; i++){
			list.add(new ItemStack(this, 1, i));
		}
    }

    @Override
    public String getUnlocalizedName(ItemStack stack){
        return Names.blockHydraulicHose[stack.getItemDamage()].unlocalized;
    }

    @Override
    public void registerIcons(IconRegister reg){
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getSpriteNumber()
    {
        return 0;
    }
}
