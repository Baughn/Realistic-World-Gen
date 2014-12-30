package rwg.support;

import java.util.ArrayList;

import cpw.mods.fml.common.Loader;
import rwg.biomes.realistic.RealisticBiomeBase;
import rwg.support.bop.SupportBOP;

public class Support 
{
	public static ArrayList<RealisticBiomeBase> biomes_snow;
	public static ArrayList<RealisticBiomeBase> biomes_cold;
	public static ArrayList<RealisticBiomeBase> biomes_hot;
	public static ArrayList<RealisticBiomeBase> biomes_wet;
	public static ArrayList<RealisticBiomeBase> biomes_test;
	
	public static void init()
	{
		biomes_snow = new ArrayList<RealisticBiomeBase>();
		biomes_cold = new ArrayList<RealisticBiomeBase>();
		biomes_hot = new ArrayList<RealisticBiomeBase>();
		biomes_wet = new ArrayList<RealisticBiomeBase>();
		biomes_test = new ArrayList<RealisticBiomeBase>();
		
		if (Loader.isModLoaded("BiomesOPlenty"))
		{
			SupportBOP.init();
		}
	}
}
