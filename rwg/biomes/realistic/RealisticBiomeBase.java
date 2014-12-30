package rwg.biomes.realistic;

import java.util.Random;

import rwg.biomes.realistic.land.*;
import rwg.biomes.realistic.old.*;
import rwg.surface.SurfaceBase;
import rwg.terrain.TerrainBase;
import rwg.util.CellNoise;
import rwg.util.PerlinNoise;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBase
{
	private static final RealisticBiomeBase[] biomeList = new RealisticBiomeBase[256];
	private static int nextBiomeID = 0;
	
	//SNOW 6 =========================================================================================
	public static RealisticBiomeBase polar = new RealisticBiomePolar();
	
	public static RealisticBiomeBase tundraHills = new RealisticBiomeTundraHills();
	public static RealisticBiomeBase tundraPlains = new RealisticBiomeTundraPlains();
	
	public static RealisticBiomeBase snowHills = new RealisticBiomeSnowHills();
	public static RealisticBiomeBase snowRivers = new RealisticBiomeSnowRivers();
	public static RealisticBiomeBase snowLakes = new RealisticBiomeSnowLakes();
	
	public static RealisticBiomeBase redwoodSnow = new RealisticBiomeRedwoodSnow();
	
	//COLD 3 =========================================================================================
	public static RealisticBiomeBase taigaHills = new RealisticBiomeTaigaHills();
	public static RealisticBiomeBase taigaPlains = new RealisticBiomeTaigaPlains();
	
	public static RealisticBiomeBase redwood = new RealisticBiomeRedwood();
	
	//WARM 8 =========================================================================================
	public static RealisticBiomeBase desert;
	public static RealisticBiomeBase desertMountains = new RealisticBiomeDesertMountains();
	public static RealisticBiomeBase dunes = new RealisticBiomeDunes();
	
	public static RealisticBiomeBase stoneMountains = new RealisticBiomeStoneMountains();
	
	public static RealisticBiomeBase savanna = new RealisticBiomeSavanna();
	public static RealisticBiomeBase savannaForest = new RealisticBiomeSavannaForest();
	public static RealisticBiomeBase savannaDunes = new RealisticBiomeSavannaDunes();
	
	public static RealisticBiomeBase redDesert;
	
	public static RealisticBiomeBase canyon = new RealisticBiomeCanyon();
	public static RealisticBiomeBase canyonForest;
	public static RealisticBiomeBase mesa = new RealisticBiomeMesa();
	
	//WET 0 =========================================================================================
	public static RealisticBiomeBase rainForest;
	public static RealisticBiomeBase jungle;
	public static RealisticBiomeBase jungleFields;
	public static RealisticBiomeBase jungleHills = new RealisticBiomeJungleHills();
	
	public static RealisticBiomeBase redwoodJungle; //dutch note to self: minder stam meer bladeren en varens
	
	
	
	
	
	
	
	
	
	
	
	//============================= OLD BIOMES ===============================================
	
	//POLAR BIOMES
	//public static RealisticBiomeBase oldlandPolarPlains = new OldRealisticBiomePolar(0);
	//public static RealisticBiomeBase oldlandPolarLakes = new OldRealisticBiomePolar(1);
	
	//TUNDRA BIOMES
	//public static RealisticBiomeBase oldlandTundraHillsHigh = new OldRealisticBiomeTundraHills(0);
	//public static RealisticBiomeBase oldlandTundraHillsValley = new OldRealisticBiomeTundraHills(1);
	//public static RealisticBiomeBase oldlandTundraHillsSpikes = new OldRealisticBiomeTundraHills(2);
	//public static RealisticBiomeBase oldlandTundraPlainsPolar = new OldRealisticBiomeTundraPlains(0);
	//public static RealisticBiomeBase oldlandTundraPlainsShield = new OldRealisticBiomeTundraPlains(1);
	//public static RealisticBiomeBase oldlandTundraPlainsMix = new OldRealisticBiomeTundraPlains(2);
	//public static RealisticBiomeBase oldlandTundraLakesIslands = new OldRealisticBiomeTundraLakes(0);
	//public static RealisticBiomeBase oldlandTundraLakesShield = new OldRealisticBiomeTundraLakes(1);

	//SNOW BIOMES
	//public static RealisticBiomeBase oldlandSnowHillsHigh = new OldRealisticBiomeSnowHills(0);
	//public static RealisticBiomeBase oldlandSnowHillsRivers = new OldRealisticBiomeSnowHills(1);
	//public static RealisticBiomeBase oldlandSnowHillsSpikes = new OldRealisticBiomeSnowHills(2);
	//public static RealisticBiomeBase oldlandSnowPlainsField = new OldRealisticBiomeSnowPlains(0);
	//public static RealisticBiomeBase oldlandSnowLakesIslands = new OldRealisticBiomeSnowLakes(0);
	
	//TAIGA BIOMES
	//public static RealisticBiomeBase oldlandTaigaHillsShield = new OldRealisticBiomeTaigaHills(0);
	//public static RealisticBiomeBase oldlandTaigaHillsRivers = new OldRealisticBiomeTaigaHills(1);
	//public static RealisticBiomeBase oldlandTaigaHillsSpikes = new OldRealisticBiomeTaigaHills(2);
	//public static RealisticBiomeBase oldlandTaigaHillsMix = new OldRealisticBiomeTaigaHills(3);
	//public static RealisticBiomeBase oldlandTaigaPlainsShield = new OldRealisticBiomeTaigaPlains(0);
	//public static RealisticBiomeBase oldlandTaigaPlainsMix = new OldRealisticBiomeTaigaPlains(1);
	//public static RealisticBiomeBase oldlandTaigaLakesIslands = new OldRealisticBiomeTaigaLakes(0);
	//public static RealisticBiomeBase oldlandTaigaLakesSwamp = new OldRealisticBiomeTaigaLakes(1);
	//public static RealisticBiomeBase oldlandTaigaLakesMix = new OldRealisticBiomeTaigaLakes(2);
	
	//REDWOOD BIOMES
	//public static RealisticBiomeBase landRedwoodHillsRivers;
	//public static RealisticBiomeBase landRedwoodHillsSpikes;
	//public static RealisticBiomeBase landRedwoodPlainsField;
	//public static RealisticBiomeBase landRedwoodPlainsLakes;
	
	//WIP ===========================================================================================
	//public static RealisticBiomeBase oldtest = new OldRealisticBiomeTest();
	//public static RealisticBiomeBase oldlow = new OldRealisticBiomeTestHeight(0);
	//public static RealisticBiomeBase oldhigh = new OldRealisticBiomeTestHeight(1);
	
	//public static RealisticBiomeBase oldlandRedwoodSpikes = new OldRealisticBiomeRedwood(0);
	
	//public static RealisticBiomeBase landGlacierHigh = new RealisticBiomeGlacier(0);
	//public static RealisticBiomeBase landGlacierLow = new RealisticBiomeGlacier(1);
	//public static RealisticBiomeBase oldlandPlainsPrairie = new OldRealisticBiomePlainsHot(0);
	//public static RealisticBiomeBase oldlandPlainsSavanna = new OldRealisticBiomePlainsHot(1);
	//public static RealisticBiomeBase oldlandPlainsPlains = new OldRealisticBiomePlains(0);
	//public static RealisticBiomeBase oldlandPlainsGrassland = new OldRealisticBiomePlains(1);
	//public static RealisticBiomeBase oldlandDesertHills = new OldRealisticBiomeDesert(0);
	//public static RealisticBiomeBase oldlandDesertMountains = new OldRealisticBiomeDesert(1);
	//public static RealisticBiomeBase oldlandDesertDunes = new OldRealisticBiomeDesert(2);
	
	//public static RealisticBiomeBase landTaigaHills = new RealisticBiomeTaiga(0);
	//public static RealisticBiomeBase landTaigaFields = new RealisticBiomeTaiga(1);
	
	//public static RealisticBiomeBase landSnowHills = new RealisticBiomeSnow(0);
	//public static RealisticBiomeBase landSnowFields = new RealisticBiomeSnow(1);
	//public static RealisticBiomeBase landSnowLakes = new RealisticBiomeSnow(2);
	//public static RealisticBiomeBase oldlandSaltLakes = new OldRealisticBiomeSaltLakes();
	
	//public static RealisticBiomeBase landRedwoodLakes
	//public static RealisticBiomeBase landRedwoodFields
	//public static RealisticBiomeBase landRedwoodHills
	
	//public static RealisticBiomeBase oldlandRockDesert = new OldRealisticBiomeRockDesert();
	//public static RealisticBiomeBase oldlandCanyonMesa = new OldRealisticBiomeMesa();
	//public static RealisticBiomeBase landCanyonCanyon
	//public static RealisticBiomeBase landCanyonForest
	//public static RealisticBiomeBase landCanyonGreen
	//public static RealisticBiomeBase landCanyonJungle
	
	//public static RealisticBiomeBase landSavannaFields
	//public static RealisticBiomeBase landSavannaForest
	//public static RealisticBiomeBase landSavannaHills
	
	//public static RealisticBiomeBase oceanTropicalAtoll = new OldRealisticBiomeAtoll();
	
	public final int biomeID;
	public final int subID;
	public final BiomeGenBase baseBiome;
	
	public RealisticBiomeBase(int sub, BiomeGenBase biome)
	{
		biomeID = nextBiomeID;
		biomeList[biomeID] = this;
		nextBiomeID++;
		
		subID = sub;
		baseBiome = biome;
	}
	
	public static RealisticBiomeBase getBiome(int id)
	{
		return biomeList[id];
	}
	
	
	//======================================================================================================================================
	
	
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength)
    {
    }
    
    public float rNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border)
    {
    	return 63f;
    }
    
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise)
    {
    	Block b;
		for(int k = 255; k > -1; k--)
		{
			b = blocks[(y * 16 + x) * 256 + k];
            if(b == Blocks.air)
            {
            	depth = -1;
            }
            else if(b == Blocks.stone)
            {
            	depth++;

        		if(depth == 0)
        		{
    				if(k < 62)
    				{
    					blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
    				}
    				else
    				{
    					blocks[(y * 16 + x) * 256 + k] = Blocks.grass;
    				}
        		}
        		else if(depth < 6)
        		{
        			blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
        		}
            }
		}
    }
    
    public float r3Dnoise(float z)
    {
    	return 0f;
    }
}
