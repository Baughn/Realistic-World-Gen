package rwg.world;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import rwg.biomes.realistic.RealisticBiomeBase;
import rwg.map.MapGenAncientVillage;
import rwg.util.CanyonColor;
import rwg.util.CellNoise;
import rwg.util.PerlinNoise;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.feature.WorldGenCactus;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;

public class ChunkGeneratorRealistic implements IChunkProvider
{
    private Random rand;
	
    private World worldObj;
    private ChunkManagerRealistic cmr;
    private MapGenCaves caves;
    private MapGenStronghold strongholdGenerator = new MapGenStronghold();
    private MapGenMineshaft mineshaftGenerator = new MapGenMineshaft();
    private MapGenVillage villageGenerator;
    
    private PerlinNoise perlin;
    private CellNoise cell;
    
	private RealisticBiomeBase[] biomesForGeneration;
    
    private final int sampleSize = 8;
    private final int sampleArraySize;
    
    private final int parabolicSize;
    private final int parabolicArraySize;
    private final float[] parabolicField;
    private float parabolicFieldTotal;

    private int[] biomeData;
    private float[][] hugeRender;
    private float[][] smallRender;
    private float[] testHeight;
	
    public ChunkGeneratorRealistic(World world, long l)
    {
    	caves = new MapGenCaves();
        worldObj = world;
        cmr = (ChunkManagerRealistic)worldObj.getWorldChunkManager();
        
        rand = new Random(l);
        perlin = new PerlinNoise(l);
    	cell = new CellNoise(l, (short)0);
    	cell.setUseDistance(true);
        
        //Map m = new HashMap();
        //m.put("size", "0");
        //m.put("distance", "4");
        villageGenerator = new MapGenVillage();//m);
        
        CanyonColor.init(l);

        sampleArraySize = sampleSize * 2 + 5;
        
        parabolicSize = sampleSize;
        parabolicArraySize = parabolicSize * 2 + 1;
        parabolicField = new float[parabolicArraySize * parabolicArraySize];
        for (int j = -parabolicSize; j <= parabolicSize; ++j)
        {
            for (int k = -parabolicSize; k <= parabolicSize; ++k)
            {
                float f = 0.445f / MathHelper.sqrt_float((float)((j * 1) * (j * 1) + (k * 1) * (k * 1)) + 0.3F);
                parabolicField[j + parabolicSize + (k + parabolicSize) * parabolicArraySize] = f;
                parabolicFieldTotal += f;
            }
        }
        
        biomeData = new int[sampleArraySize * sampleArraySize];
    	hugeRender = new float[81][256];
    	smallRender = new float[625][256];
    	testHeight = new float[256];
    }

    public Chunk provideChunk(int cx, int cy)
    {
    	rand.setSeed((long)cx * 0x4f9939f508L + (long)cy * 0x1ef1565bd5L);
        Block[] blocks = new Block[65536];
        byte[] metadata = new byte[65536];
        float[] noise = new float[256];
        biomesForGeneration = new RealisticBiomeBase[256];
        
        generateTerrain(cmr, cx, cy, blocks, metadata, biomesForGeneration, noise);
        replaceBlocksForBiome(cx, cy, blocks, metadata, biomesForGeneration, noise);
        caves.func_151539_a(this, worldObj, cx, cy, blocks);

        mineshaftGenerator.func_151539_a(this, this.worldObj, cx, cy, blocks);
        strongholdGenerator.func_151539_a(this, this.worldObj, cx, cy, blocks);
        villageGenerator.func_151539_a(this, this.worldObj, cx, cy, blocks);
        
        Chunk chunk = new Chunk(this.worldObj, blocks, metadata, cx, cy);
        byte[] abyte1 = chunk.getBiomeArray();
        for (int k = 0; k < abyte1.length; ++k)
        {
            abyte1[k] = (byte)this.biomesForGeneration[parabolicSize * 16 + parabolicSize].baseBiome.biomeID;
        }
        chunk.generateSkylightMap();
        return chunk;
    }
    
    public void generateTerrain(ChunkManagerRealistic cmr, int cx, int cy, Block[] blocks, byte[] metadata, RealisticBiomeBase biomes[], float[] n)
    {	
    	int p, h;
    	float[] noise = getNewNoise(cmr, cx * 16, cy * 16, biomes);
    	float noise3;
    	for(int i = 0; i < 16; i++)
    	{
    		for(int j = 0; j < 16; j++)
    		{
    			h = (int)noise[j * 16 + i];
    			
    			for(int k = 0; k < 256; k++)
    			{
    				p = (j * 16 + i) * 256 + k;
    				if(k > h)
    				{
    					if(k < 63)
        				{
        					blocks[p] = Blocks.water;
        				}
        				else
        				{
        					blocks[p] = Blocks.air;
        				}
    				}
    				/*else if(k <= noise[0] + noise[1] * 6 && k >= noise[0] - noise[1] * 6)
    				{
        				noise3 = perlin.noise3((cx * 16 + j) / 20f, (cy * 16 + i) / 20f, k / 20f) + (noise[0] - k) / (noise[1] * 2);
        				if(noise3 > 0f)
        				{
        					blocks[p] = Blocks.stone;
        				}
        				else if(k < 63)
        				{
        					blocks[p] = Blocks.water;
        				}
        				else
        				{
        					blocks[p] = Blocks.air;
        				}
    				}
    				else if(k < noise[0] - noise[1])*/
    				else 
    				{
    					blocks[p] = Blocks.stone;
    				}
    			}
    			n[j * 16 + i] = noise[j * 16 + i];
    		}
    	}
    }
    
    public float[] getNewNoise(ChunkManagerRealistic cmr, int x, int y, RealisticBiomeBase biomes[])
    {
    	int i, j, k, l, m, n, p;
    	
    	for(i = -sampleSize; i < sampleSize + 5; i++)
    	{
    		for(j = -sampleSize; j < sampleSize + 5; j++)
    		{
    			biomeData[(i + sampleSize) * sampleArraySize + (j + sampleSize)] = cmr.getBiomeDataAt(x + ((i * 8) - 8), y + ((j * 8) - 8)).biomeID;
    		}
    	}

    	for(i = -1; i < 4; i++)
    	{
        	for(j = -1; j < 4; j++)
        	{
        		hugeRender[(i * 2 + 2) * 9 + (j * 2 + 2)] = new float[256];
        		for(k = -parabolicSize; k <= parabolicSize; k++)
        		{
        			for(l = -parabolicSize; l <= parabolicSize; l++)
        			{
        				hugeRender[(i * 2 + 2) * 9 + (j * 2 + 2)][biomeData[(i + k + sampleSize + 1) * sampleArraySize + (j + l + sampleSize + 1)]] += parabolicField[k + parabolicSize + (l + parabolicSize) * parabolicArraySize] / parabolicFieldTotal;
        			}
        		}
        		
        	}
    	}
    	
    	//MAIN BIOME CHECK
    	RealisticBiomeBase b = null;
    	for(i = 0; i < 256; i++)
    	{
    		if(hugeRender[4 * 9 + 4][i] > 0.95f)
    		{
    			b = RealisticBiomeBase.getBiome(i);
    		}
    	}
    	
    	//RENDER HUGE 1
    	for(i = 0; i < 4; i++)
    	{
    		for(j = 0; j < 4; j++)
    		{
    			hugeRender[(i * 2 + 1) * 9 + (j * 2 + 1)] = mix4(new float[][]{
					hugeRender[(i * 2) * 9 + (j * 2)], 
					hugeRender[(i * 2 + 2) * 9 + (j * 2)], 
					hugeRender[(i * 2) * 9 + (j * 2 + 2)], 
					hugeRender[(i * 2 + 2) * 9 + (j * 2 + 2)]});
    		} 
    	}
    	
    	//RENDER HUGE 2
    	for(i = 0; i < 7; i++)
    	{
    		for(j = 0; j < 7; j++)
    		{
    			if(!(i % 2 == 0 && j % 2 == 0) && !(i % 2 != 0 && j % 2 != 0))
    			{
    				smallRender[(i * 4) * 25 + (j * 4)] = mix4(new float[][]{
						hugeRender[(i) * 9 + (j + 1)], 
						hugeRender[(i + 1) * 9 + (j)], 
						hugeRender[(i + 1) * 9 + (j + 2)], 
						hugeRender[(i + 2) * 9 + (j + 1)]});
    			}
    			else
    			{
    				smallRender[(i * 4) * 25 + (j * 4)] = hugeRender[(i + 1) * 9 + (j + 1)];
    			}
    		} 
    	}
    	
    	//RENDER SMALL 1
    	for(i = 0; i < 6; i++)
    	{
    		for(j = 0; j < 6; j++)
    		{
    			smallRender[(i * 4 + 2) * 25 + (j * 4 + 2)] = mix4(new float[][]{
    				smallRender[(i * 4) * 25 + (j * 4)], 
    				smallRender[(i * 4 + 4) * 25 + (j * 4)], 
    				smallRender[(i * 4) * 25 + (j * 4 + 4)], 
    				smallRender[(i * 4 + 4) * 25 + (j * 4 + 4)]});
    		} 
    	}
    	
    	//RENDER SMALL 2
    	for(i = 0; i < 11; i++)
    	{
    		for(j = 0; j < 11; j++)
    		{
    			if(!(i % 2 == 0 && j % 2 == 0) && !(i % 2 != 0 && j % 2 != 0))
    			{
    				smallRender[(i * 2 + 2) * 25 + (j * 2 + 2)] = mix4(new float[][]{
    					smallRender[(i * 2) * 25 + (j * 2 + 2)], 
    					smallRender[(i * 2 + 2) * 25 + (j * 2)], 
    					smallRender[(i * 2 + 2) * 25 + (j * 2 + 4)], 
    					smallRender[(i * 2 + 4) * 25 + (j * 2 + 2)]});
    			}
    		} 
    	}
    	
    	//RENDER SMALL 3
    	for(i = 0; i < 9; i++)
    	{
    		for(j = 0; j < 9; j++)
    		{
    			smallRender[(i * 2 + 3) * 25 + (j * 2 + 3)] = mix4(new float[][]{
        				smallRender[(i * 2 + 2) * 25 + (j * 2 + 2)], 
        				smallRender[(i * 2 + 4) * 25 + (j * 2 + 2)], 
        				smallRender[(i * 2 + 2) * 25 + (j * 2 + 4)], 
        				smallRender[(i * 2 + 4) * 25 + (j * 2 + 4)]});
    		} 
    	}
    	
    	//RENDER SMALL 4
    	for(i = 0; i < 16; i++)
    	{
    		for(j = 0; j < 16; j++)
    		{
    			if(!(i % 2 == 0 && j % 2 == 0) && !(i % 2 != 0 && j % 2 != 0))
    			{
    				smallRender[(i + 4) * 25 + (j + 4)] = mix4(new float[][]{
    					smallRender[(i + 3) * 25 + (j + 4)], 
    					smallRender[(i + 4) * 25 + (j + 3)], 
    					smallRender[(i + 4) * 25 + (j + 5)], 
    					smallRender[(i + 5) * 25 + (j + 4)]});
    			}
    		}
    	}
    	
    	//CREATE BIOMES ARRAY
    	boolean randBiome = true;
    	float bCount = 0f, bRand = 0f;
    	if(b != null)
    	{
    		randBiome = false;
    		for(i = 0; i < 256; i++)
    		{
    			biomes[i] = b;
    		}
    	}
    	
    	for(i = 0; i < 16; i++)
    	{
    		for(j = 0; j < 16; j++)
    		{
    			if(randBiome)
    			{
    				bCount = 0f;
    				bRand = 0.5f + perlin.noise2((float)(x + i) / 5f, (float)(y + j) / 5f); //1f + rand.nextFloat();
    				bRand = bRand < 0f ? 0f : bRand > 0.99999f ? 0.99999f : bRand;
    			}
    			
    			float ocean = cmr.getOceanValue(x, y);
    			l = ((int)(i + 4) * 25 + (j + 4));
    			
    			testHeight[i * 16 + j] = 0f;
    			
    			for(k = 0; k < 256; k++)
    			{
    				if(smallRender[l][k] > 0f)
    				{
    	    			if(randBiome && bCount <= 1f) //3f)
    	    			{
	    					bCount += smallRender[l][k];// * 3f;
    	    				if(bCount > bRand)
    	    				{
    	    					biomes[j * 16 + i] = RealisticBiomeBase.getBiome(k);
    	    					bCount = 2f; //20f;
    	    				}
    	    			}
    					
    					testHeight[i * 16 + j] += RealisticBiomeBase.getBiome(k).rNoise(perlin, cell, x + i, y + j, ocean, smallRender[l][k]) * smallRender[l][k];
    				}
    			}
    		}
    	}
    	
    	return testHeight;
    }
    
    public float[] mix4(float[][] ingredients)
    {
    	float[] result = new float[256];
    	int i, j;
    	for(i = 0; i < 256; i++)
    	{
    		for(j = 0; j < 4; j++)
    		{
    			if(ingredients[j][i] > 0f)
    			{
    				result[i] += ingredients[j][i] / 4f;
    			}
    		}
    	}
    	
    	return result;
    }

    public void replaceBlocksForBiome(int cx, int cy, Block[] blocks, byte[] metadata, RealisticBiomeBase biomes[], float[] n)
    {
    	for(int i = 0; i < 16; i++)
    	{
    		for(int j = 0; j < 16; j++)
    		{
    			RealisticBiomeBase biome = biomes[i * 16 + j];
    			int depth = -1;
                
    			biome.rReplace(blocks, metadata, cx * 16 + j, cy * 16 + i, i, j, depth, rand, perlin, cell, n);
    			
    			blocks[(j * 16 + i) * 256] = Blocks.bedrock;
    		}
    	}
    }

    public Chunk loadChunk(int par1, int par2)
    {
        return provideChunk(par1, par2);
    }

    private double[] func_4061_a(double ad[], int i, int j, int k, int l, int i1, int j1)
    {
    	return null;
    }

    public boolean chunkExists(int par1, int par2)
    {
        return true;
    }

    public void populate(IChunkProvider ichunkprovider, int i, int j)
    {
        BlockFalling.fallInstantly = true;
        int x = i * 16;
        int y = j * 16;
        RealisticBiomeBase biome = cmr.getBiomeDataAt(x + 16, y + 16);
        this.rand.setSeed(this.worldObj.getSeed());
        long i1 = this.rand.nextLong() / 2L * 2L + 1L;
        long j1 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed((long)i * i1 + (long)j * j1 ^ this.worldObj.getSeed());
        boolean flag = false;

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(ichunkprovider, worldObj, rand, i, j, flag));

        mineshaftGenerator.generateStructuresInChunk(worldObj, rand, i, j);
        strongholdGenerator.generateStructuresInChunk(worldObj, rand, i, j);
        villageGenerator.generateStructuresInChunk(worldObj, rand, i, j);

        if(rand.nextInt(10) == 0)
		{
			int i2 = x + rand.nextInt(16) + 8;
			int l4 = rand.nextInt(50);
			int i8 = y + rand.nextInt(16) + 8;
			(new WorldGenLakes(Blocks.water)).generate(worldObj, rand, i2, l4, i8);
		}
		
		if(rand.nextInt(18) == 0) 
		{
			int j2 = x + rand.nextInt(16) + 8;
			int i5 = rand.nextInt(rand.nextInt(45) + 8);
			int j8 = y + rand.nextInt(16) + 8;
			if(i5 < 64 || rand.nextInt(10) == 0)
			{
				(new WorldGenLakes(Blocks.lava)).generate(worldObj, rand, j2, i5, j8);
			}
		} 
		
		for(int k1 = 0; k1 < 8; k1++)
		{
			int j5 = x + rand.nextInt(16) + 8;
			int k8 = rand.nextInt(128);
			int j11 = y + rand.nextInt(16) + 8;
			(new WorldGenDungeons()).generate(worldObj, rand, j5, k8, j11);
		}
		
		for(int j2 = 0; j2 < 10; j2++)
		{
			int l5 = x + rand.nextInt(16);
			int i9 = rand.nextInt(64);
			int l11 = y + rand.nextInt(16);
			(new WorldGenMinable(Blocks.dirt, 32)).generate(worldObj, rand, l5, i9, l11);
		}

		for(int k2 = 0; k2 < 5; k2++)
		{
			int i6 = x + rand.nextInt(16);
			int j9 = rand.nextInt(64);
			int i12 = y + rand.nextInt(16);
			(new WorldGenMinable(Blocks.gravel, 32)).generate(worldObj, rand, i6, j9, i12);
		}

		for(int i3 = 0; i3 < 20; i3++)
		{
			int j6 = x + rand.nextInt(16);
			int k9 = rand.nextInt(128);
			int j12 = y + rand.nextInt(16);
			(new WorldGenMinable(Blocks.coal_ore, 16)).generate(worldObj, rand, j6, k9, j12);
		}

		for(int j3 = 0; j3 < 20; j3++)
		{
			int k6 = x + rand.nextInt(16);
			int l9 = rand.nextInt(64);
			int k12 = y + rand.nextInt(16);
			(new WorldGenMinable(Blocks.iron_ore, 8)).generate(worldObj, rand, k6, l9, k12);
		}

		for(int k3 = 0; k3 < 2; k3++)
		{
			int l6 = x + rand.nextInt(16);
			int i10 = rand.nextInt(32);
			int l12 = y + rand.nextInt(16);
			(new WorldGenMinable(Blocks.gold_ore, 8)).generate(worldObj, rand, l6, i10, l12);
		}

		for(int l3 = 0; l3 < 8; l3++)
		{
			int i7 = x + rand.nextInt(16);
			int j10 = rand.nextInt(16);
			int i13 = y + rand.nextInt(16);
			(new WorldGenMinable(Blocks.redstone_ore, 7)).generate(worldObj, rand, i7, j10, i13);
		}

		for(int i4 = 0; i4 < 1; i4++)
		{
			int j7 = x + rand.nextInt(16);
			int k10 = rand.nextInt(16);
			int j13 = y + rand.nextInt(16);
			(new WorldGenMinable(Blocks.diamond_ore, 7)).generate(worldObj, rand, j7, k10, j13);
		}

		for(int j4 = 0; j4 < 1; j4++)
		{
			int k7 = x + rand.nextInt(16);
			int l10 = rand.nextInt(16) + rand.nextInt(16);
			int k13 = y + rand.nextInt(16);
			(new WorldGenMinable(Blocks.lapis_ore, 6)).generate(worldObj, rand, k7, l10, k13);
		}
		
        for (int g12 = 0; g12 < 4; ++g12)
        {
            int n1 = x + rand.nextInt(16);
            int m1 = rand.nextInt(28) + 4;
            int p1 = y + rand.nextInt(16);

            if (worldObj.getBlock(n1, m1, p1).isReplaceableOreGen(worldObj, n1, m1, p1, Blocks.stone))
            {
            	worldObj.setBlock(n1, m1, p1, Blocks.emerald_ore, 0, 2);
            }
        }
        
		if(rand.nextInt(5) == 0)
		{
			int k15 = x + rand.nextInt(16) + 8;
			int k17 = rand.nextInt(64);
			int k20 = y + rand.nextInt(16) + 8;
			
			if(rand.nextBoolean())
			{
				(new WorldGenFlowers(Blocks.brown_mushroom)).generate(worldObj, rand, k15, k17, k20);
			}
			else
			{
				(new WorldGenFlowers(Blocks.red_mushroom)).generate(worldObj, rand, k15, k17, k20);
			}
		}
        
        float[] biomeNoise = new float[256];
        for(int bx = -4; bx <= 4; bx++)
        {
        	for(int by = -4; by <= 4; by++)
        	{
        		biomeNoise[cmr.getBiomeDataAt(x + 24 + bx * 16, y + 24 + by * 16).biomeID] += 0.01234569f;
        	}
        }
        
        RealisticBiomeBase b;
        float snow = 0f;
        for(int bn = 0; bn < 256; bn++)
        {
        	if(biomeNoise[bn] > 0f)
        	{
        		if(biomeNoise[bn] >= 1f)
        		{
        			biomeNoise[bn] = 1f;
        		}
        		b = RealisticBiomeBase.getBiome(bn);
                b.rDecorate(this.worldObj, this.rand, x, y, perlin, cell, biomeNoise[bn]);
                
                if(b.baseBiome.temperature < 0.15f)
                {
                	snow -= 0.6f * biomeNoise[bn];
                }
                else
                {
                	snow += 0.6f * biomeNoise[bn];
                }
        	}
        }
        
		for(int l18 = 0; l18 < 50; l18++)
		{
			int l21 = x + rand.nextInt(16) + 8;
			int k23 = rand.nextInt(rand.nextInt(120) + 8);
			int l24 = y + rand.nextInt(16) + 8;
			(new WorldGenLiquids(Blocks.flowing_water)).generate(worldObj, rand, l21, k23, l24);
		}

		for(int i19 = 0; i19 < 20; i19++)
		{
			int i22 = x + rand.nextInt(16) + 8;
			int l23 = rand.nextInt(rand.nextInt(rand.nextInt(112) + 8) + 8);
			int i25 = y + rand.nextInt(16) + 8;
			(new WorldGenLiquids(Blocks.flowing_lava)).generate(worldObj, rand, i22, l23, i25);
		}
        
        SpawnerAnimals.performWorldGenSpawning(this.worldObj, biome.baseBiome, x + 8, y + 8, 16, 16, this.rand);

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(ichunkprovider, worldObj, rand, i, j, flag));

        if(snow < 0.59f)
        {
	        x += 8;
	        y += 8;
			float s;
			Block b1, b2;
			
	        for (int sn1 = 0; sn1 < 16; ++sn1)
	        {
	            for (int sn2 = 0; sn2 < 16; ++sn2)
	            {
	            	if(snow < -0.59f)
	            	{
	            		s = -1f;
	            	}
	            	else
	            	{
	            		s = perlin.noise2((sn1 + x) / 3f, (sn2 + y) / 3f) + snow;
	            	}
	            	
	            	if(s < 0f)
	            	{
		                int sn3 = worldObj.getPrecipitationHeight(x + sn1, y + sn2);
		                b1 = worldObj.getBlock(sn1 + x, sn3, sn2 + y);
		                b2 = worldObj.getBlock(sn1 + x, sn3 - 1, sn2 + y);
		
		                if (b2 == Blocks.water || b2 == Blocks.flowing_water)
		                {
		                	worldObj.setBlock(sn1 + x, sn3 - 1, sn2 + y, Blocks.ice, 0, 2);
		                }
		
		                if (Blocks.snow_layer.canPlaceBlockAt(worldObj, sn1 + x, sn3, sn2 + y) && b2 != Blocks.ice && b2 != Blocks.water && sn3 > 62)
		                {
		                	if(b1 != Blocks.snow_layer && b2 != Blocks.packed_ice)
		                	{
		                		worldObj.setBlock(sn1 + x, sn3, sn2 + y, Blocks.snow_layer, 0, 2);
		                	}
		                }
	            	}
	            }
	        }
        }
		

        BlockFalling.fallInstantly = false;
    }

    public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate)
    {
        return true;
    }
	
    public boolean unloadQueuedChunks()
    {
        return false;
    }

    public boolean unload100OldestChunks()
    {
        return false;
    }

    public boolean canSave()
    {
        return true;
    }

    public String makeString()
    {
        return "RandomLevelSource";
    }
	
    public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4)
    {
        BiomeGenBase var5 = this.worldObj.getBiomeGenForCoords(par2, par4);
        return var5 == null ? null : var5.getSpawnableList(par1EnumCreatureType);
    }
	
    public ChunkPosition func_147416_a(World par1World, String par2Str, int par3, int par4, int par5)
    {
        return "Stronghold".equals(par2Str) && this.strongholdGenerator != null ? this.strongholdGenerator.func_151545_a(par1World, par3, par4, par5) : null;
    }

    public int getLoadedChunkCount()
    {
        return 0;
    }
    
    public void saveExtraData() {}

    public void recreateStructures(int par1, int par2)
    {
		strongholdGenerator.func_151539_a(this, worldObj, par1, par2, (Block[])null);
		mineshaftGenerator.func_151539_a(this, worldObj, par1, par2, (Block[])null);
        villageGenerator.func_151539_a(this, this.worldObj, par1, par2, (Block[])null);
	}
}