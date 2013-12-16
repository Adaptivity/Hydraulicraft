package pet.minecraft.Hydraulicraft.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import pet.minecraft.Hydraulicraft.lib.CustomTabs;
import pet.minecraft.Hydraulicraft.lib.config.Ids;
import pet.minecraft.Hydraulicraft.lib.config.ModInfo;
import pet.minecraft.Hydraulicraft.lib.config.Names;

public class ItemChunks extends Item {
	class chunk{
		private String _name;
		private Icon _icon;
		
		public chunk(String n){
			_name = n;
		}
		
		public void setName(String n){
			_name = n;
		}
		public void setIcon(Icon i){
			_icon = i;
		}
		public String getName(){
			return _name;
		}
		public Icon getIcon(){
			return _icon;
		}
	}
	
	
	private List<chunk> chunks = new ArrayList<chunk>();
	
	public ItemChunks() {
		super(Ids.itemChunks.act);
		
		setMaxStackSize(64);
		setUnlocalizedName(Names.itemChunk.unlocalized);
		
		setCreativeTab(CustomTabs.tabHydraulicraft);
		
		setHasSubtypes(true);
	}
	
	public void addChunk(String oreDictName){
		chunks.add(new chunk(oreDictName));
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack){
		return chunks.get(itemstack.getItemDamage()).getName();
	}
	
	@Override
	public void registerIcons(IconRegister icon){
		for (chunk c : chunks) {
			c.setIcon(icon.registerIcon(ModInfo.LID + ":" + "chunk" + c.getName()));
		}
	}
	
	@Override
	public Icon getIconFromDamage(int damage){
		if(chunks.get(damage) != null){
			return chunks.get(damage).getIcon();
		}
		return null;
	}
	
	@Override
	public void getSubItems(int id, CreativeTabs tab, List list){
		for(chunk c : chunks){
			
		}
	}
	
}
