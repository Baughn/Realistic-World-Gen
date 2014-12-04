package rwg.biomes.realistic;

import java.util.Random;

import rwg.biomes.base.BaseBiomes;
import rwg.deco.DecoBlob;
import rwg.deco.DecoCacti;
import rwg.deco.DecoFlowers;
import rwg.deco.DecoGrass;
import rwg.deco.DecoJungleCane;
import rwg.deco.DecoLog;
import rwg.deco.ruins.DecoRuinsAncient;
import rwg.deco.trees.DecoEuroPine;
import rwg.deco.trees.DecoLargePine;
import rwg.deco.trees.DecoSmallCocoa;
import rwg.deco.trees.DecoSmallJungle;
import rwg.deco.trees.DecoSmallPine;
import rwg.deco.trees.DecoSmallSpruce;
import rwg.deco.trees.DecoPineTree;
import rwg.deco.trees.DecoShrub;
import rwg.util.CellNoise;
import rwg.util.CliffCalculator;
import rwg.util.PerlinNoise;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RealisticBiomeTestHeight extends RealisticBiomeBase
{
    private CellNoise celltest;
    
	public RealisticBiomeTestHeight(int i) 
	{
		super(i, BaseBiomes.baseColdForest);

		celltest = new CellNoise(0, (short)0);
		//celltest.setUseDistance(true);
	}
	
	@Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength)
    {
		if(rand.nextInt((int)(2f / strength)) == 0)
		{
			int i1 = chunkX + rand.nextInt(16) + 8;
			int j1 = chunkY + rand.nextInt(16) + 8;
		    int k1 = world.getHeightValue(i1, j1);
			if(k1 < 85)
			{
				(new DecoBlob(Blocks.cobblestone, 0)).generate(world, rand, i1, k1, j1);
			}
		}

		for(int k18 = 0; k18 < 12f * strength; k18++)
		{
			int k21 = chunkX + rand.nextInt(16) + 8;
			int j23 = rand.nextInt(160);
			int k24 = chunkY + rand.nextInt(16) + 8;
			(new DecoCacti(false)).generate(world, rand, k21, j23, k24);
		}
		
		for(int i15 = 0; i15 < 3f * strength; i15++)
		{
			int i17 = chunkX + rand.nextInt(16) + 8;
			int i20 = rand.nextInt(160);
			int l22 = chunkY + rand.nextInt(16) + 8;
			(new WorldGenDeadBush(Blocks.deadbush)).generate(world, rand, i17, i20, l22);
		}
    }

	@Override
    public float rNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border)
    {
		/*float pX = x + (perlin.noise1(y / 40f) * 50f);
		float pY = y + (perlin.noise1(x / 40f) * 50f);
		float p = cell.border(pX / 800D, pY / 800D, 50D / 600D, 30f);

		return 100f + p;*/
		
		float pX = x + (perlin.noise1(y / 40f) * 50f);
		float pY = y + (perlin.noise1(x / 40f) * 50f);
		float p = 1f + cell.border(pX / 1500D, pY / 1500D, 50D / 800D, 1f);
		p = p < 0f ? 0f : p;
		
		float st = (perlin.noise2(x / 160f, y / 160f) + 0.38f) * 35f;
		st = st < 0.2f ? 0.2f : st;
		
		float h = perlin.noise2(x / 60f, y / 60f) * st * 2f;
		h = h > 0f ? -h : h;
		h += st;
		h *= h / 50f;
		h += st;
		
		float eh = 0f;
		if(h < 10f)
		{
			float d = (h - 10f) / 2f;
			d = d > 4f ? 4f : d;
			eh += cell.noise(x / 25D, y / 25D, 1D) * d * p;
			eh += perlin.noise2(x / 30f, y / 30f) * d;
			eh += perlin.noise2(x / 14f, y / 14f) * d * 0.5f;
		}
		
		return 70f + (h * p) + eh + ((p - 1f) * 10f);
    }
	
    @Override
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise)
    {
		float pX = i + (perlin.noise1(j / 40f) * 50f);
		float pY = j + (perlin.noise1(i / 40f) * 50f);
		float p = 1f + cell.border(pX / 1500D, pY / 1500D, 50D / 800D, 1f);
		p = p < 0f ? 0f : p;
		
		if(p < 1f)
		{
			p += perlin.noise2(i / 10f, j / 10f) * 0.2f;
		}
    	
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

            	if(p < 0.25f)
            	{
            		if(depth == 0)
            		{
            			blocks[(y * 16 + x) * 256 + k] = Blocks.grass;
            		}
            		else if(depth < 5)
            		{
            			blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
            		}
            	}
            	else if(depth < 7)
        		{
        			blocks[(y * 16 + x) * 256 + k] = Blocks.sand;
        		}
        		else if(depth < 10)
        		{
        			blocks[(y * 16 + x) * 256 + k] = Blocks.sandstone;
        		}
            }
		}
    }
}
