package rwg.biomes.realistic.land;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import rwg.api.RWGBiomes;
import rwg.biomes.realistic.RealisticBiomeBase;
import rwg.deco.DecoBlob;
import rwg.deco.DecoCacti;
import rwg.surface.SurfaceBase;
import rwg.surface.SurfaceMountainStone;
import rwg.terrain.TerrainBase;
import rwg.terrain.TerrainHilly;
import rwg.util.CellNoise;
import rwg.util.PerlinNoise;

public class RealisticBiomeDesertMountains extends RealisticBiomeBase
{
	private TerrainBase terrain;
	private SurfaceBase surface;

	public RealisticBiomeDesertMountains() 
	{
		super(0, RWGBiomes.baseHotDesert);
		terrain = new TerrainHilly(230f, 120f, 50f);
		surface = new SurfaceMountainStone(Blocks.sand, Blocks.sandstone, false, null, 0f, 1.5f, 60f, 65f, 1.5f);
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
		for(int k18 = 0; k18 < 12; k18++)
		{
			int k21 = chunkX + rand.nextInt(16) + 8;
			int j23 = rand.nextInt(160);
			int k24 = chunkY + rand.nextInt(16) + 8;
			if(j23 < 120f)
			{
				(new DecoCacti(false)).generate(world, rand, k21, j23, k24);
			}
		}
		
		for(int i15 = 0; i15 < 3f * strength; i15++)
		{
			int i17 = chunkX + rand.nextInt(16) + 8;
			int i20 = rand.nextInt(160);
			int l22 = chunkY + rand.nextInt(16) + 8;
			(new WorldGenDeadBush(Blocks.deadbush)).generate(world, rand, i17, i20, l22);
		}
    }
    
    public float rNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border)
    {
    	return terrain.generateNoise(perlin, cell, x, y, ocean, border);
    }
    
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise)
    {
    	surface.paintTerrain(blocks, metadata, i, j, x, y, depth, world, rand, perlin, cell, noise);
    }
}
