package k4unl.minecraft.Hydraulicraft.thirdParty;

import java.util.List;

import k4unl.minecraft.Hydraulicraft.api.IHydraulicGenerator;
import k4unl.minecraft.Hydraulicraft.api.IHydraulicMachine;
import k4unl.minecraft.Hydraulicraft.multipart.Multipart;
import k4unl.minecraft.Hydraulicraft.multipart.PartHose;
import k4unl.minecraft.Hydraulicraft.thirdParty.industrialcraft.tileEntities.TileElectricPump;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import codechicken.multipart.TileMultipart;

public class WailaProvider implements IWailaDataProvider {

	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor,
			IWailaConfigHandler config) {
		return null;
	}

	@Override
	public List<String> getWailaHead(ItemStack itemStack,
			List<String> currenttip, IWailaDataAccessor accessor,
			IWailaConfigHandler config) {
		return currenttip;
	}

	@Override
	public List<String> getWailaBody(ItemStack itemStack,
			List<String> currenttip, IWailaDataAccessor accessor,
			IWailaConfigHandler config) {
		
		TileEntity ent = accessor.getTileEntity();
		if(accessor.getTileEntity() instanceof IHydraulicMachine|| ent instanceof TileMultipart){
			IHydraulicMachine mEnt = null;
			if(ent instanceof TileMultipart){
				if(Multipart.hasPartHose((TileMultipart)ent)){
					mEnt = (IHydraulicMachine) Multipart.getHose((TileMultipart)ent);
				}else{
					return currenttip;
				}
			}else{
				mEnt = (IHydraulicMachine) ent;
			}
			//IHydraulicMachine mEnt = (IHydraulicMachine) accessor.getTileEntity();
			
			int stored = mEnt.getHandler().getStored();
			int max = mEnt.getMaxStorage();
			
			float pressure = mEnt.getHandler().getPressure();
			float maxPressure = mEnt.getMaxPressure(mEnt.getHandler().isOilStored());
	
			currenttip.add("Fl: " + stored + "/" + max + " mBuckets (" + (int)(((float)stored / (float)max) * 100) + "%)");
			currenttip.add("Pr: " + pressure + "/" + maxPressure + " mBar (" + (int)(((float)pressure / (float)maxPressure) * 100) + "%)");
			
			if(mEnt instanceof IHydraulicGenerator){
				float gen = ((IHydraulicGenerator) mEnt).getGenerating();
				int maxGen = ((IHydraulicGenerator) mEnt).getMaxGenerating();
				currenttip.add("Gen: " + gen + "/" + maxGen);
			}
			if(mEnt instanceof TileElectricPump){
				int storedEU = ((TileElectricPump)mEnt).getEUStored();
				int maxEU = ((TileElectricPump)mEnt).getMaxEUStorage();
				currenttip.add("EU: " + storedEU + "/" + maxEU);
			}
		}
		return currenttip;
	}
	
	public static void callbackRegister(IWailaRegistrar registrar){
		registrar.registerHeadProvider(new WailaProvider(), IHydraulicMachine.class);
		registrar.registerBodyProvider(new WailaProvider(), IHydraulicMachine.class);
		registrar.registerTailProvider(new WailaProvider(), IHydraulicMachine.class);
		registrar.registerBodyProvider(new WailaProvider(), TileMultipart.class);
		
		//registrar.registerBodyProvider(new WailaProvider(), Ids.blockHydraulicPump.act);
	}

	@Override
	public List<String> getWailaTail(ItemStack itemStack,
			List<String> currenttip, IWailaDataAccessor accessor,
			IWailaConfigHandler config) {
		return currenttip;
	}

}
