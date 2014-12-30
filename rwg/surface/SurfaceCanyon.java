package rwg.surface;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import rwg.util.CellNoise;
import rwg.util.CliffCalculator;
import rwg.util.PerlinNoise;

public class SurfaceCanyon extends SurfaceBase
{
	private int[] claycolor = new int[100];
	private byte blockByte = 0;
	
	public SurfaceCanyon(Block top, Block fill, byte b)
	{
		super(top, fill);
		blockByte = b;
		
		int[] c = new int[]{1, 8, 0};
		PerlinNoise perlin = new PerlinNoise(2L);
		
		float n;
		for(int i = 0; i < 100; i++)
		{
			n = perlin.noise1(i / 3f) * 3f + perlin.noise1(i / 1f) * 0.3f + 1.5f;
			n = n >= 3f ? 2.9f : n < 0f ? 0f : n;
			claycolor[i] = c[(int)n];
		}
	}
	
	public byte getClayColorForHeight(int k)
	{
		k -= 60;
		k = k < 0 ? 0 : k > 99 ? 99 : k;
		return (byte)claycolor[k];
	}
	
	@Override
	public void paintTerrain(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise)
	{
		float c = CliffCalculator.calc(x, y, noise);
		boolean cliff = c > 1.3f ? true : false;
		
		for(int k = 255; k > -1; k--)
		{
			Block b = blocks[(y * 16 + x) * 256 + k];
            if(b == Blocks.air)
            {
            	depth = -1;
            }
            else if(b == Blocks.stone)
            {
            	depth++;

        		if(depth > -1 && depth < 12)
	        	{
	            	if(cliff)
	            	{
	        			blocks[(y * 16 + x) * 256 + k] = Blocks.stained_hardened_clay;
	        			metadata[(y * 16 + x) * 256 + k] = getClayColorForHeight(k);
	            	}
	            	else
	            	{
	        			if(depth > 4)
	        			{
		        			blocks[(y * 16 + x) * 256 + k] = Blocks.stained_hardened_clay;
		        			metadata[(y * 16 + x) * 256 + k] = getClayColorForHeight(k);
	        			}
	        			else if(k > 77)
	        			{
	        				if(rand.nextInt(5) == 0)
	        				{
		        				blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
	        				}
	        				else
	        				{
		        				if(depth == 0)
		        				{
			        				blocks[(y * 16 + x) * 256 + k] = topBlock;
			        				metadata[(y * 16 + x) * 256 + k] = blockByte;
		        				}
		        				else
		        				{
			        				blocks[(y * 16 + x) * 256 + k] = fillerBlock;
			        				metadata[(y * 16 + x) * 256 + k] = blockByte;
		        				}
	        				}
	        			}
	        			else if(k < 62)
	        			{
	        				blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
	        			}
	        			else if(k < 70)
	        			{
	        				if(depth == 0)
	        				{
		        				int r = k - 62;
		        				if(rand.nextInt(r + 1) == 0)
		        				{
			        				blocks[(y * 16 + x) * 256 + k] = Blocks.grass;
		        				}
		        				else if(rand.nextInt((int)(r / 2f) + 1) == 0)
		        				{
			        				blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
		        				}
		        				else
		        				{
			        				blocks[(y * 16 + x) * 256 + k] = topBlock;
			        				metadata[(y * 16 + x) * 256 + k] = blockByte;
		        				}
	        				}
	        				else
	        				{
		        				blocks[(y * 16 + x) * 256 + k] = fillerBlock;
		        				metadata[(y * 16 + x) * 256 + k] = blockByte;
	        				}
	        			}
	        			else
	        			{
	        				if(depth == 0)
	        				{
		        				blocks[(y * 16 + x) * 256 + k] = topBlock;
		        				metadata[(y * 16 + x) * 256 + k] = blockByte;
	        				}
	        				else
	        				{
		        				blocks[(y * 16 + x) * 256 + k] = fillerBlock;
		        				metadata[(y * 16 + x) * 256 + k] = blockByte;
	        				}
	        			}
	            	}
        		}
        		else if(k > 63)
        		{
        			blocks[(y * 16 + x) * 256 + k] = Blocks.stained_hardened_clay;
        			metadata[(y * 16 + x) * 256 + k] = getClayColorForHeight(k);
        		}
            }
		}
	}
}