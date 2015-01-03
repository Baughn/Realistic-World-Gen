package rwg.biomes.realistic.land;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenCactus;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenerator;
import rwg.api.RWGBiomes;
import rwg.biomes.realistic.RealisticBiomeBase;
import rwg.surface.SurfaceBase;
import rwg.surface.SurfaceMesa;
import rwg.terrain.TerrainBase;
import rwg.terrain.TerrainMesa;
import rwg.util.CellNoise;
import rwg.util.PerlinNoise;

public class RealisticBiomeMesa extends RealisticBiomeBase
{
	private TerrainBase terrain;
	private SurfaceBase surface;
	
	public RealisticBiomeMesa() 
	{
		super(0, RWGBiomes.baseHotDesert);
		
		terrain = new TerrainMesa();
		surface = new SurfaceMesa(Blocks.sand, Blocks.sand, (byte)1);
	}

	@Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength, float river)
    {
		for (int l = 0; l < 1; ++l)
		{
			int i1 = chunkX + rand.nextInt(16) + 8;
			int j1 = chunkY + rand.nextInt(16) + 8;
		    int k1 = world.getHeightValue(i1, j1);
		    if(k1 < 83)
			{
				(new WorldGenBlockBlob(Blocks.cobblestone, 0)).generate(world, rand, i1, k1, j1);
			}
		}
		
		for (int b1 = 0; b1 < 3; b1++)
		{
			int j6 = chunkX + rand.nextInt(16) + 8;
			int k10 = chunkY + rand.nextInt(16) + 8;
			int z52 = world.getHeightValue(j6, k10);

			if(z52 < 90)
			{
				WorldGenerator worldgenerator;
				worldgenerator = new WorldGenShrub(0, 0);
				worldgenerator.setScale(1.0D, 1.0D, 1.0D);
				worldgenerator.generate(world, rand, j6, z52, k10);
			}
		}
		
		for(int i15 = 0; i15 < 3; i15++)
		{
			int i17 = chunkX + rand.nextInt(16) + 8;
			int i20 = 60 + rand.nextInt(40);
			int l22 = chunkY + rand.nextInt(16) + 8;
			(new WorldGenDeadBush(Blocks.deadbush)).generate(world, rand, i17, i20, l22);
		}
		
		for(int k18 = 0; k18 < 18; k18++)
		{
			int k21 = chunkX + rand.nextInt(16) + 8;
			int j23 = 60 + rand.nextInt(40);
			int k24 = chunkY + rand.nextInt(16) + 8;
			(new WorldGenCactus()).generate(world, rand, k21, j23, k24);
		}
    }
    
    public float rNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border, float river)
    {
    	return terrain.generateNoise(perlin, cell, x, y, ocean, border, river);
    }
    
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
    {
    	surface.paintTerrain(blocks, metadata, i, j, x, y, depth, world, rand, perlin, cell, noise, river, base);
    }
}
