package rwg.biomes.realistic.old;

import java.util.Random;

import rwg.api.RWGBiomes;
import rwg.biomes.base.BaseBiomes;
import rwg.biomes.realistic.RealisticBiomeBase;
import rwg.util.CellNoise;
import rwg.util.PerlinNoise;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class OldRealisticBiomeMesa extends RealisticBiomeBase
{
	public OldRealisticBiomeMesa() 
	{
		super(0, RWGBiomes.baseHotPlains);
	}

    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength)
    {
    }
    
    public float rNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean)
    {		
    	float b = perlin.noise2(x / 200f, y / 200f) * 40f;
    	b = b < 0f ? 0f : b;
    	
    	return 70f;
    }
    
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise)
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
}
