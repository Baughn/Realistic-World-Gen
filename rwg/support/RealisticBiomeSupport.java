package rwg.support;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import rwg.biomes.base.BaseBiomes;
import rwg.biomes.realistic.RealisticBiomeBase;
import rwg.util.CellNoise;
import rwg.util.CliffCalculator;
import rwg.util.PerlinNoise;

public class RealisticBiomeSupport extends RealisticBiomeBase
{
	public BiomeGenBase customBiome;
	
	public RealisticBiomeSupport(BiomeGenBase b)
	{
		super(0, b);
		customBiome = b;
	}

    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength)
    {
    	customBiome.decorate(world, rand, chunkX, chunkY);
    }

    @Override
    public float rNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border)
    {
		float b = (12f + (perlin.noise2(x / 300f, y / 300f) * 6f));
		float h = cell.noise(x / 200D, y / 200D, 1D) * b;
		h *= h * 1.5f;// / 2f;
		h = h > 155f ? 155f : h;
		
		if(h > 2f)
		{
			float d = (h - 2f) / 2f > 8f ? 8f : (h - 2f) / 2f;
			h += perlin.noise2(x / 30f, y / 30f) * d;
			h += perlin.noise2(x / 50f, y / 50f) * d * 0.5f;

			if(h > 35f)
			{
				float d2 = (h - 35f) / 1.5f > 30f ? 30f : (h - 35f) / 1.5f;
				h += cell.noise(x / 25D, y / 25D, 1D) * d2;
			}
		}
		
		h += perlin.noise2(x / 18f, y / 18f) * 3;
		h += perlin.noise2(x / 8f, y / 8f) * 2;
				
		return 45f + h + (b * 2);
    }
    
    @Override
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise)
    {
		float p = perlin.noise2(i / 8f, j / 8f) * 0.5f;
		float c = CliffCalculator.calc(x, y, noise);
		int cliff = 0;
		
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
        			if(c > 0.45f && c > 1.5f - ((k - 70f) / 95f) + p)
        			{
        				cliff = 1;
        			}
            		if(c > 1.5f)
        			{
        				cliff = 2;
        			}
        			if(k > 140 + (p * 4) && c < 0.3f + ((k - 140f) / 70f) + p)
        			{
        				cliff = 3;
        			}
            		
            		if(cliff == 1)
            		{
            			blocks[(y * 16 + x) * 256 + k] = rand.nextInt(3) == 0 ? Blocks.cobblestone : Blocks.stone; 
            		}
            		else if(cliff == 2)
            		{
        				blocks[(y * 16 + x) * 256 + k] = Blocks.stained_hardened_clay; 
        				metadata[(y * 16 + x) * 256 + k] = 9; 
            		}
            		else if(cliff == 3)
            		{
	        			blocks[(y * 16 + x) * 256 + k] = Blocks.snow;
            		}
            		else if(k < 64)
            		{
            			blocks[(y * 16 + x) * 256 + k] = customBiome.fillerBlock;
            		}
            		else
            		{
            			blocks[(y * 16 + x) * 256 + k] = customBiome.topBlock;
            		}
            	}
            	else if(depth < 6)
        		{
            		if(cliff == 1)
            		{
            			blocks[(y * 16 + x) * 256 + k] = Blocks.stone; 
            		}
            		else if(cliff == 2)
            		{
        				blocks[(y * 16 + x) * 256 + k] = Blocks.stained_hardened_clay; 
        				metadata[(y * 16 + x) * 256 + k] = 9; 
            		}
            		else if(cliff == 3)
            		{
	        			blocks[(y * 16 + x) * 256 + k] = Blocks.snow;
            		}
            		else
            		{
            			blocks[(y * 16 + x) * 256 + k] = customBiome.fillerBlock;
            		}
        		}
            }
		}
    }
}
