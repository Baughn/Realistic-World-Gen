package rwg.support;

import java.util.ArrayList;

import net.minecraft.world.biome.BiomeGenBase;
import rwg.biomes.base.BaseBiomes;
import rwg.biomes.realistic.RealisticBiomeBase;

public class BiomeSupport 
{
	public static ArrayList<RealisticBiomeBase> biomes;
	public static int biomesLength = 0;
	
	public static int skipWidth = 2;
	public static int supportListLength = 0;
	public static String[] supportList = new String[]{
		"Fungi Forest",				"HOT&PEAKS",
		"Jade Cliffs",				"HOT&PEAKS"
		/*"Alps",						"HOT&PEAKS",
		"Arctic",					"HOT&PEAKS",
		"Bamboo Forest",			"HOT&PEAKS",
		"Bayou",					"HOT&PEAKS",
		"Bog",						"HOT&PEAKS",
		"Boreal Forest",			"HOT&PEAKS",
		"Brushland",				"HOT&PEAKS",
		"Canyon",					"HOT&PEAKS",
		"Chaparral",				"HOT&PEAKS",
		"Cherry Blossom Grove",		"HOT&PEAKS",
		"Coniferous Forest",		"HOT&PEAKS",
		"Snowy Coniferous Forest",	"HOT&PEAKS",
		"Crag",						"HOT&PEAKS",
		"Dead Forest",				"HOT&PEAKS",
		"Dead Swamp",				"HOT&PEAKS",
		"Deciduous Forest",			"HOT&PEAKS",
		"Fen",						"HOT&PEAKS",
		"Flower Field",				"HOT&PEAKS",
		"Frost Forest",				"HOT&PEAKS",
		"Fungi Forest",				"HOT&PEAKS",
		"Garden",					"HOT&PEAKS",
		"Grassland",				"HOT&PEAKS",
		"Grove",					"HOT&PEAKS",
		"Heathland",				"HOT&PEAKS",
		"Highland",					"HOT&PEAKS",
		"Jade Cliffs",				"HOT&PEAKS",
		"Lavender Fields",			"HOT&PEAKS",
		"Lush Desert",				"HOT&PEAKS",
		"Lush Swamp",				"HOT&PEAKS",
		"Maple Woods",				"HOT&PEAKS",
		"Marsh",					"HOT&PEAKS",
		"Meadow",					"HOT&PEAKS",
		"Moor",						"HOT&PEAKS",
		"Mountain",					"HOT&PEAKS",
		"Mystic Grove",				"HOT&PEAKS",
		"Ominous Woods",			"HOT&PEAKS",
		"Origin Valley",			"HOT&PEAKS",
		"Outback",					"HOT&PEAKS",
		"Prairie",					"HOT&PEAKS",
		"Rainforest",				"HOT&PEAKS",
		"Redwood Forest",			"HOT&PEAKS",
		"Sacred Springs",			"HOT&PEAKS",
		"Seasonal Forest",			"HOT&PEAKS",
		"Shield",					"HOT&PEAKS",
		"Shrubland",				"HOT&PEAKS",
		"Sludgepit",				"HOT&PEAKS",
		"Steppe",					"HOT&PEAKS",
		"Temperate Rainforest",		"HOT&PEAKS",
		"Thicket",					"HOT&PEAKS",
		"Tropical Rainforest",		"HOT&PEAKS",
		"Tundra",					"HOT&PEAKS",
		"Wasteland",				"HOT&PEAKS",
		"Wetland",					"HOT&PEAKS",
		"Woodland",					"HOT&PEAKS",
		"Coral Reef",				"HOT&PEAKS",
		"Kelp Forest",				"HOT&PEAKS",
		"Tropics",					"HOT&PEAKS",
		"Volcano",					"HOT&PEAKS",
		"Mangrove",					"HOT&PEAKS",
		"Alps Forest",				"HOT&PEAKS",
		"Canyon Ravine",			"HOT&PEAKS",
		"Glacier",					"HOT&PEAKS",
		"Meadow Forest",			"HOT&PEAKS",
		"Oasis",					"HOT&PEAKS",
		"Orchard",					"HOT&PEAKS",
		"Quagmire",					"HOT&PEAKS",
		"Scrubland",				"HOT&PEAKS",
		"Silkglades",				"HOT&PEAKS",
		"Spruce Woods",				"HOT&PEAKS"*/
	};
	
	public static void init()
	{
		biomes = new ArrayList<RealisticBiomeBase>();
		supportListLength = supportList.length;
		
		BiomeGenBase[] biomes = BiomeGenBase.getBiomeGenArray();
		int l = biomes.length;
		for(int i = 0; i < l; i++)
		{
			if(biomes[i] != null && biomes[i].biomeID > 39 && !(biomes[i].biomeID > 128 && biomes[i].biomeID < 168))
			{
				addBiome(biomes[i]);
			}
		}
	}
	
	public static void addBiome(BiomeGenBase b)
	{
		for(int i = 0; i < supportListLength; i += skipWidth)
		{
			if(supportList[i] == b.biomeName)
			{
				biomesLength++;
				biomes.add(new RealisticBiomeSupport(b));
			}
		}
	}
}
