package k4unl.minecraft.Hydraulicraft.multipart;

import java.util.List;

import k4unl.minecraft.Hydraulicraft.api.IHydraulicTransporter;
import k4unl.minecraft.Hydraulicraft.lib.config.Names;
import k4unl.minecraft.Hydraulicraft.lib.helperClasses.Location;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import codechicken.lib.vec.BlockCoord;
import codechicken.multipart.MultiPartRegistry;
import codechicken.multipart.MultiPartRegistry.IPartConverter;
import codechicken.multipart.MultiPartRegistry.IPartFactory;
import codechicken.multipart.TMultiPart;
import codechicken.multipart.TileMultipart;
import cpw.mods.fml.common.registry.GameRegistry;


public class Multipart implements IPartFactory, IPartConverter{
	public static ItemPartHose itemPartHose;
	public static ItemPartValve itemPartValve;
	
	public static void init(){
		MultiPartRegistry.registerParts(new Multipart(), new String[] { 
                "tile." + Names.partHose[0].unlocalized,
                "tile." + Names.partHose[1].unlocalized,
                "tile." + Names.partHose[2].unlocalized, 
                "tile." + Names.partValve[0].unlocalized,
                "tile." + Names.partValve[1].unlocalized,
                "tile." + Names.partValve[2].unlocalized});
		
		
		itemPartHose = new ItemPartHose();
		itemPartValve= new ItemPartValve();
		
		GameRegistry.registerItem(itemPartHose, Names.partHose[0].unlocalized);
		GameRegistry.registerItem(itemPartValve, Names.partValve[0].unlocalized);
	}

	@Override
	public TMultiPart createPart(String id, boolean client) {
		if(id.equals("tile." + Names.partHose[0].unlocalized) || id.equals("tile." + Names.partHose[1].unlocalized) || id.equals("tile." + Names.partHose[2].unlocalized)){
			return new PartHose();
		}else if(id.equals("tile." + Names.partValve[0].unlocalized) || id.equals("tile." + Names.partValve[1].unlocalized) || id.equals("tile." + Names.partValve[2].unlocalized)){
			return new PartValve();
		}
		return null;
	}
	
	
	
	public static TileMultipart getMultipartTile(IBlockAccess access, Location pos){
        TileEntity te = access.getTileEntity(pos.getX(), pos.getY(), pos.getZ());
        return te instanceof TileMultipart ? (TileMultipart) te : null;
    }

    public static TMultiPart getMultiPart(IBlockAccess w, Location bc, int part){
        TileMultipart t = getMultipartTile(w, bc);
        if (t != null)
            return t.partMap(part);

        return null;
    }
    
    public static boolean hasTransporter(TileMultipart mp){
    	boolean ret = false;
    	List<TMultiPart> t = mp.jPartList();
		for (TMultiPart p: t) {
			if(ret == false){
				if(p instanceof IHydraulicTransporter){
					ret = true;
				}
			}
		}
		return ret;
    }
    
    public static boolean hasPartHose(TileMultipart mp){
    	boolean ret = false;
    	List<TMultiPart> t = mp.jPartList();
		for (TMultiPart p: t) {
			if(ret == false){
				if(p instanceof PartHose){
					ret = true;
				}
			}
		}
		return ret;
    }
    
    public static PartHose getHose(TileMultipart mp){
    	boolean ret = false;
    	List<TMultiPart> t = mp.jPartList();
		for (TMultiPart p: t) {
			if(ret == false){
				if(p instanceof PartHose){
					return (PartHose)p;
				}
			}
		}
		return null;
    }
    
    public static boolean hasPartValve(TileMultipart mp){
    	boolean ret = false;
    	List<TMultiPart> t = mp.jPartList();
		for (TMultiPart p: t) {
			if(ret == false){
				if(p instanceof PartValve){
					ret = true;
				}
			}
		}
		return ret;
    }
    
    public static PartValve getValve(TileMultipart mp){
    	boolean ret = false;
    	List<TMultiPart> t = mp.jPartList();
		for (TMultiPart p: t) {
			if(ret == false){
				if(p instanceof PartValve){
					return (PartValve)p;
				}
			}
		}
		return null;
    }
    
    
    public static IHydraulicTransporter getTransporter(TileMultipart mp){
    	boolean ret = false;
    	List<TMultiPart> t = mp.jPartList();
		for (TMultiPart p: t) {
			if(ret == false){
				if(p instanceof IHydraulicTransporter){
					return (IHydraulicTransporter)p;
				}
			}
		}
		return null;
    }

    
	@Override
	public Iterable<Block> blockTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TMultiPart convert(World arg0, BlockCoord arg1) {
		// TODO Auto-generated method stub
		return null;
	}
}
