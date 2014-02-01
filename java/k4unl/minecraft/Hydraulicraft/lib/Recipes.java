package k4unl.minecraft.Hydraulicraft.lib;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.LanguageRegistry;
import k4unl.minecraft.Hydraulicraft.blocks.Blocks;
import k4unl.minecraft.Hydraulicraft.items.Items;
import k4unl.minecraft.Hydraulicraft.lib.config.Config;
import k4unl.minecraft.Hydraulicraft.lib.config.Ids;
import k4unl.minecraft.Hydraulicraft.lib.config.Names;
import k4unl.minecraft.Hydraulicraft.multipart.Multipart;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Recipes {
	public static void init(){
		GameRegistry.registerCraftingHandler(new CraftingHandler());

		initializeBlockRecipes();
		initializeItemRecipes();
		
		initializeSmeltingRecipes();

        initializeCrushingRecipes();
	}

    private static void initializeCrushingRecipes() {
    	//Yeah, just put them in, right there, yeahhhhhh
    	OreDictionary.registerOre("oreIron", Block.oreIron);
    	OreDictionary.registerOre("ingotIron", Item.ingotIron);
    	OreDictionary.registerOre("oreGold", Block.oreGold);
    	OreDictionary.registerOre("ingotGold", Item.ingotGold);
        //Get items from ore dictionary:
        List<String> crushableItems = new ArrayList<String>();
        crushableItems.add("Gold");
        crushableItems.add("Iron");
        //MODDED:
        crushableItems.add("Copper");
        crushableItems.add("Lead");
        crushableItems.add("FzDarkIron");
        crushableItems.add("Tin");
        crushableItems.add("Cobalt");
        crushableItems.add("Silver");
        crushableItems.add("Nickel");
        

        for(String item : crushableItems){
            String oreName = "ore" + item;
            ArrayList<ItemStack> oreStack = OreDictionary.getOres(oreName);

            String ingotName = "ingot" + item;
            ArrayList<ItemStack> ingotStack = OreDictionary.getOres(ingotName);
            
            Log.info("Found " + oreStack.size() + " ores and " + ingotStack.size() + " ingots for " + item);
            
            if(oreStack.size() > 0 && ingotStack.size() > 0){
            	int metaId = Items.itemChunk.addChunk(item);
                Items.itemDust.addDust(item, metaId);
                
		        CrushingRecipes.addCrushingRecipe(new CrushingRecipes
		                .CrushingRecipe
		                (oreName, 100F, new ItemStack(Items.itemChunk
		                .itemID, 2, metaId)));
		        
		        
		        CrushingRecipes.addCrushingRecipe(new CrushingRecipes.CrushingRecipe
		                (ingotName, 10F,
		                        new ItemStack(Items.itemDust.itemID, 1, metaId)));
		
		        
		        WashingRecipes.addWashingRecipe(new WashingRecipes.WashingRecipe(
		               new ItemStack(Items.itemChunk.itemID, 1, metaId), 100F,
		                new ItemStack(Items.itemDust.itemID, 1, metaId)));
            }
        }
        
        //Other mods. Stuff that doesn't follow the ingot stuff.
        if(Loader.isModLoaded("AppliedEnergistics")){
        	registerNonStandardCrushRecipe("oreCertusQuartz", "crystalCertusQuartz", 2);
        }
        if(Loader.isModLoaded("IC2")){
        	registerNonStandardCrushRecipe("oreUranium", "crushedUranium", 2);
        }
        
        CrushingRecipes.addCrushingRecipe(new CrushingRecipes.CrushingRecipe("oreNetherQuartz", 10F, new ItemStack(Item.netherQuartz, 3)));
        CrushingRecipes.addCrushingRecipe(new CrushingRecipes.CrushingRecipe(new ItemStack(Block.cobblestone, 1), 10F, new ItemStack(Block.sand, 2)));
        CrushingRecipes.addCrushingRecipe(new CrushingRecipes.CrushingRecipe("cobblestone", 10F, new ItemStack(Block.sand, 2)));
    }
    
    private static void registerNonStandardCrushRecipe(String sourceName, String targetName, int number){
    	ArrayList<ItemStack> oreStackL = OreDictionary.getOres(sourceName);
        ArrayList<ItemStack> targetStackL = OreDictionary.getOres(targetName);
        if(oreStackL.size() == 0 || targetStackL.size() == 0)
        	return;
        
        ItemStack targetStack = targetStackL.get(0);
        CrushingRecipes.addCrushingRecipe(new CrushingRecipes.CrushingRecipe(sourceName, 10F, targetStack));
    }

    private static void initializeSmeltingRecipes(){
		GameRegistry.addSmelting(Ids.oreCopper.act, new ItemStack(Items.ingotCopper), 0);
		GameRegistry.addSmelting(Ids.oreLead.act, new ItemStack(Items.ingotLead), 0);
	}
	
	private static void initializeBlockRecipes(){
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.hydraulicPiston, 1, 0), true ,
				new Object[] {
					"WRW",
					"WCW",
					"WKW",
					'W', Blocks.hydraulicPressureWall,
					'C', new ItemStack(Blocks.blockCore, 1, 1),
					'K', Items.gasket,
					'R', Item.redstone
			})
		);
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.hydraulicHarvesterSource, 18, 1), true ,
				new Object[] {
					"SSS",
					"-S-",
					"SSS",
					'S', Item.stick
			})
		);
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.hydraulicHarvesterSource, 1, 0), true ,
				new Object[] {
					"WWW",
					"WCK",
					"WWW",
					'C', new ItemStack(Blocks.blockCore, 1, 1),
					'W', Blocks.hydraulicPressureWall,
					'K', Items.gasket
			})
		);
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.harvesterTrolley, 4, 0), true ,
				new Object[] {
					"-P-",
					"WCW",
					"-H-",
					'C', new ItemStack(Blocks.blockCore, 1, 1),
					'W', Blocks.hydraulicPressureWall,
					'H', Item.hoeGold,
					'P', Blocks.hydraulicPiston
			})
		);
		
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.blockCore, 2, 0), true ,
				new Object[] {
					"LSL",
					"SWS",
					"LSL",
					'S' , Block.stone,
					'W', Blocks.hydraulicPressureWall,
					'L', "ingotLead"
			})
		);
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.blockCore, 2, 1), true ,
				new Object[] {
					"CWC",
					"WBW",
					"CWC",
					'W', Blocks.hydraulicPressureWall,
					'C', "ingotCopper",
					'B', new ItemStack(Blocks.blockCore, 1, 0)
			})
		);
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.blockCore, 2, 2), true ,
				new Object[] {
					"EWE",
					"WBW",
					"EWE",
					'W', Blocks.hydraulicPressureWall,
					'E', "ingotEnrichedCopper",
					'B', new ItemStack(Blocks.blockCore, 1, 1)
			})
		);
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.hydraulicPressureWall, 6),true,
				new Object [] {
					"SSS",
					"SLS",
					"SSS",
					'S', Block.stone,
					'L', "ingotLead"
				}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.hydraulicPump,1,0), true,
			new Object [] {
				"PKP",
				"GCG",
				"PWP",
				'P', Block.pistonBase,
				'K', Items.gasket,
				'G', Block.glass,
				'W', Blocks.hydraulicPressureWall,
				'C', new ItemStack(Blocks.blockCore, 1, 0)
			})
		);

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.hydraulicPump,1,1), true,
				new Object [] {
					"PKP",
					"GCG",
					"PWP",
					'P', Block.pistonBase,
					'K', Items.gasket,
					'G', Block.glass,
					'W', Blocks.hydraulicPressureWall,
					'C', new ItemStack(Blocks.blockCore, 1, 1)
				})
			);
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.hydraulicPump, 1, 2), true,
                new Object[]{
                        "PKP",
                        "GCG",
                        "PWP",
                        'P', Block.pistonBase,
                        'K', Items.gasket,
                        'G', Block.glass,
                        'W', Blocks.hydraulicPressureWall,
                        'C', new ItemStack(Blocks.blockCore, 1, 2)
                })
        );

		
		GameRegistry.addRecipe(new ItemStack(Blocks.hydraulicMixer, 1),
                new Object[]{
                        "GKG",
                        "KCK",
                        "WWW",
                        'K', Items.gasket,
                        'G', Block.glass,
                        'W', Blocks.hydraulicPressureWall,
                        'C', new ItemStack(Blocks.blockCore, 1, 2)
                });
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Multipart.itemPartHose, 4, 0), true,
                new Object[]{
                        "LLL",
                        "K-K",
                        "LLL",
                        'K', Items.gasket,
                        'L', "ingotLead"
                }));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Multipart.itemPartHose, 6, 1), true,
                new Object[]{
                        "CCC",
                        "KHK",
                        "CCC",
                        'K', Items.gasket,
                        'C', "ingotCopper",
                        'H', new ItemStack(Multipart.itemPartHose, 1, 0)
                }));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Multipart.itemPartHose, 12, 2), true,
				new Object [] {
					"C-C",
					"KHK",
					"C-C",
					'K', Items.gasket,
					'C', "ingotEnrichedCopper",
					'H', new ItemStack(Multipart.itemPartHose,1,1)
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.hydraulicPressurevat, 1, 0), true,
				new Object [] {
					"LWL",
					"KCK",
					"LWL",
					'W', Blocks.hydraulicPressureWall,
					'K', Items.gasket,
					'L', "ingotLead",
					'C', new ItemStack(Blocks.blockCore, 1, 0)
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.hydraulicPressurevat, 1, 1), true,
				new Object [] {
					"CWC",
					"KBK",
					"CVC",
					'W', Blocks.hydraulicPressureWall,
					'K', Items.gasket,
					'C', "ingotCopper",
					'V', new ItemStack(Blocks.hydraulicPressurevat, 1,0),
					'B', new ItemStack(Blocks.blockCore, 1, 1)
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.hydraulicPressurevat, 1, 2), true,
				new Object [] {
					"WWW",
					"KBK",
					"WVW",
					'W', Blocks.hydraulicPressureWall,
					'K', Items.gasket,
					'C', "ingotEnrichedCopper",
					'V', new ItemStack(Blocks.hydraulicPressurevat, 1, 1),
					'B', new ItemStack(Blocks.blockCore, 1, 2)
			}));
		
		GameRegistry.addRecipe(new ItemStack(Blocks.hydraulicFrictionIncinerator, 1),
			new Object [] {
				"GKG",
				"FCF",
				"WWW",
				'W', Blocks.hydraulicPressureWall,
				'G', Block.glass,
				'F', Items.itemFrictionPlate,
				'K', Items.gasket,
				'C', new ItemStack(Blocks.blockCore, 1, 1)
				
			});
		
		
		/*GameRegistry.addRecipe(new ItemStack(Blocks.hydraulicPressureValve, 1),
				new Object [] {
					"---",
					"HLH",
					"---",
					'H', Blocks.hydraulicHose,
					'L', Block.lever,
				});*/
		
		GameRegistry.addRecipe(new ItemStack(Blocks.hydraulicCrusher, 1),
				new Object [] {
					"-K-",
					"PCP",
					"WWW",
					'K', Items.gasket,
					'P', Block.pistonBase,
					'W', Blocks.hydraulicPressureWall,
					'C', new ItemStack(Blocks.blockCore, 1, 1)
				});
		
		GameRegistry.addRecipe(new ItemStack(Blocks.hydraulicWasher, 1),
				new Object [] {
					"GKG",
					"KCG",
					"WWW",
					'K', Items.gasket,
					'G', Block.glass,
					'W', Blocks.hydraulicPressureWall,
					'C', new ItemStack(Blocks.blockCore, 1, 1)
				});
	}
	
	private static void initializeItemRecipes(){
		GameRegistry.addRecipe(new ItemStack(Items.itemFrictionPlate, 1),
		new Object [] {
			"-SS",
			"S-S",
			"SS-",
			'S', Block.stone
			
		});
	
		//TODO: Change me!
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.ingotEnrichedCopper, 4), true,
			new Object [] {
				"---",
				"CDC",
				"---",
				'D', Item.diamond,
				'C', "ingotCopper"
		}));

		
		GameRegistry.addRecipe(new ItemStack(Items.gasket, 4),
			new Object [] {
				"P-P",
				"-B-",
				"P-P",
				'P', Item.paper,
				'B', Block.fenceIron
			}
		);
		
		
	}
	
}
