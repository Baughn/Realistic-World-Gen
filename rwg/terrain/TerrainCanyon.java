package rwg.terrain;

import rwg.util.CellNoise;
import rwg.util.PerlinNoise;

public class TerrainCanyon extends TerrainBase
{
	private boolean river;
	private float[] height;
	private int heightLength;
	private float strength;
	private float cWidth;
	private float cHeigth;
	private float cStrength;
	private float base;
	
	/*
	 * Example parameters:
	 * 
	 * allowed to generate rivers?
	 * riverGen = true
	 * 
	 * canyon jump heights
	 * heightArray = new float[]{2.0f, 0.5f, 6.5f, 0.5f, 14.0f, 0.5f, 19.0f, 0.5f}
	 * 
	 * strength of canyon jump heights
	 * heightStrength = 35f
	 * 
	 * canyon width (cliff to cliff)
	 * canyonWidth = 160f
	 * 
	 * canyon heigth (total heigth)
	 * canyonHeight = 60f
	 * 
	 * canyon strength
	 * canyonStrength = 40f
	 * 
	 */
	public TerrainCanyon(boolean riverGen, float[] heightArray, float heightStrength, float canyonWidth, float canyonHeight, float canyonStrength, float baseHeight)
	{
		river = riverGen;
		height = heightArray;
		strength = heightStrength;
		heightLength = height.length;
		cWidth = canyonWidth;
		cHeigth = canyonHeight;
		cStrength = canyonStrength;
		base = baseHeight;
	}

	@Override
	public float generateNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border)
	{
		float b = perlin.noise2(x / cWidth, y / cWidth) * cHeigth;
		b *= b / cStrength;

		float hn = perlin.noise2(x / 12f, y / 12f);
		float sb = 0f;
		if(b > 0f)
		{
			sb = b;
			sb = sb < 0f ? 0f : sb > 7f ? 7f : sb;
			sb = hn * sb;
		}
		b += sb;

		float cTotal = 0f;
		float cTemp = 0f;
		
		for(int i = 0; i < heightLength; i += 2)
		{
			cTemp = 0;
			if(b > height[i] && border > 0.7f + (height[i] * 0.012f) + hn * 0.09f)
			{
				cTemp = b > height[i] + height[i + 1] ? height[i + 1] : b - height[i];
				cTemp *= strength;
			}
			cTotal += cTemp;
		}
		
		
		float bn = 0f;
		if(river)
		{
			if(b < 5f)
			{
				bn = 5f - b;
				for(int i = 0; i < 3; i++)
				{
					bn *= bn / 4.5f;
				}
			}
		}
		else if(b < 5f)
		{
			bn = (perlin.noise2(x / 7f, y / 7f) * 1.3f + perlin.noise2(x / 15f, y / 15f) * 2f) * (5f - b) * 0.2f;
		}
		
		b += cTotal - bn;
		
		return base + b;
	}
}
