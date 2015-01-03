package rwg.biomes.realistic.land;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import rwg.api.RWGBiomes;
import rwg.biomes.realistic.RealisticBiomeBase;
import rwg.surface.SurfaceBase;
import rwg.surface.SurfacePolar;
import rwg.terrain.TerrainBase;
import rwg.terrain.TerrainPolar;
import rwg.util.CellNoise;
import rwg.util.PerlinNoise;

public class RealisticBiomePolar extends RealisticBiomeBase
{	
	private TerrainBase terrain;
	private SurfaceBase surface;
	
	public RealisticBiomePolar() 
	{
		super(0, RWGBiomes.baseSnowDesert);
		terrain = new TerrainPolar();
		surface = new SurfacePolar(Blocks.snow, Blocks.snow);
	}
	
	@Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength, float river)
    {
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
