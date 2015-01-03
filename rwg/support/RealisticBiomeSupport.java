package rwg.support;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import rwg.biomes.base.BaseBiomes;
import rwg.biomes.realistic.RealisticBiomeBase;
import rwg.surface.SurfaceBase;
import rwg.terrain.TerrainBase;
import rwg.util.CellNoise;
import rwg.util.CliffCalculator;
import rwg.util.PerlinNoise;

public class RealisticBiomeSupport extends RealisticBiomeBase
{
	public BiomeGenBase customBiome;
	public TerrainBase terrain;
	public SurfaceBase surface;
	
	public RealisticBiomeSupport(BiomeGenBase b, TerrainBase t, SurfaceBase s)
	{
		super(0, b);
		customBiome = b;
		terrain = t;
		surface = s;
	}

    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength, float river)
    {
    	if(strength > 0.3f)
    	{
        	customBiome.decorate(world, rand, chunkX, chunkY);
    	}
    }

    @Override
    public float rNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border, float river)
    {
		return terrain.generateNoise(perlin, cell, x, y, ocean, border, river);
    }
    
    @Override
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
    {
    	surface.paintTerrain(blocks, metadata, i, j, x, y, depth, world, rand, perlin, cell, noise, river, base);
    }
}
