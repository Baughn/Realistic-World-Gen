package rwg.biomes.realistic.land;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import rwg.api.RWGBiomes;
import rwg.biomes.realistic.RealisticBiomeBase;
import rwg.util.CellNoise;
import rwg.util.PerlinNoise;

public class RealisticBiomeTest extends RealisticBiomeBase
{
	public RealisticBiomeTest() 
	{
		super(0, RWGBiomes.baseColdForest);
	}
	
	@Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength, float river)
    {
    }

	@Override
    public float rNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border, float river)
    {
    	return 64.1f + (50f * river);
    }

	@Override
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
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
