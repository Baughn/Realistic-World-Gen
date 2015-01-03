package rwg.support.bop;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import rwg.support.RealisticBiomeSupport;
import rwg.support.Support;
import rwg.support.edit.*;
import rwg.surface.*;
import rwg.terrain.*;
import biomesoplenty.api.content.BOPCBiomes;

public class SupportBOP 
{
	public static void init()
	{
		//ALPS
		/*Support.biomes_snow.add(
			new RealisticBiomeSupport(
				BOPCBiomes.alps,
				new TerrainMountainRiver(),
				new SurfaceMountainSnow(Blocks.grass, Blocks.dirt, false, null, 0.45f)
			)
		);*/
		
		//ARCTIC
		/*Support.biomes_snow.add(
			new RealisticBiomeSupport(
				BOPCBiomes.arctic,
				new TerrainMountainRiver(),
				new SurfaceMountainSnow(Blocks.grass, Blocks.dirt, false, null, 0.45f)
			)
		);*/
		
		//BAMBOO FOREST
		Support.biomes_wet.add(
			new RealisticBiomeSupport(
				BOPCBiomes.bambooForest,
				new TerrainSwampMountain(135f, 300f),
				new SurfaceMountainStone(BOPCBiomes.bambooForest.topBlock, BOPCBiomes.bambooForest.fillerBlock, false, null, 0.95f)
			)
		);
		
		//BAYOU
		Support.biomes_wet.add(
			new RealisticBiomeSupport(
				BOPCBiomes.bayou,
				new TerrainSwampRiver(),
				new SurfaceGrassland(BOPCBiomes.bayou.topBlock, BOPCBiomes.bayou.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);
		
		//BOG
		Support.biomes_hot.add(
			new RealisticBiomeSupport(
				BOPCBiomes.bog,
				new TerrainMarsh(),
				new SurfaceGrassland(BOPCBiomes.bog.topBlock, BOPCBiomes.bog.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);
		
		//BOREAL FOREST
		Support.biomes_cold.add(
			new RealisticBiomeSupport(
				BOPCBiomes.borealForest,
				new TerrainMountainSpikes(),
				new SurfaceMountainSnow(BOPCBiomes.borealForest.topBlock, BOPCBiomes.borealForest.fillerBlock, true, Blocks.sand, 0.45f, 1.5f, 60f, 65f, 0.4f, 130f, 50f, 1.5f)
			)
		);
		
		//BRUSHLAND
		Support.biomes_hot.add(
			new RealisticBiomeSupport(
				BOPCBiomes.brushland,
				new TerrainGrasslandHills(90f, 180f, 13f, 100f, 38f, 260f, 71f),
				new SurfaceGrasslandMix1(BOPCBiomes.brushland.topBlock, BOPCBiomes.brushland.fillerBlock, Blocks.sand, Blocks.stone, Blocks.cobblestone, 13f, 0.27f)
			)
		);
		Support.biomes_hot.add(
			new RealisticBiomeSupport(
				BOPCBiomes.brushland,
				new TerrainDuneValley(300f),
				new SurfaceBase[]{
					new SurfaceDuneValley(BOPCBiomes.brushland.topBlock, BOPCBiomes.brushland.fillerBlock, 300f, false, true),
					new SurfaceRiverOasis(),
				},
				new EditBase[]{
					new EditRiverOasis()
				}
			)
		);
		
		//CANYON
		Support.biomes_hot.add(
			new RealisticBiomeSupport(
				BOPCBiomes.canyon, 
				new TerrainCanyon(true, new float[]{7.0f, 0.5f, 10.5f, 0.5f, 17.0f, 0.5f}, 35f, 160f, 60f, 40f, 69f),
				new SurfaceCanyon(BOPCBiomes.canyon.topBlock, BOPCBiomes.canyon.fillerBlock, (byte)0)
			)
		);
		
		//CHAPARRAL
		Support.biomes_hot.add(
			new RealisticBiomeSupport(
				BOPCBiomes.chaparral,
				new TerrainGrasslandHills(90f, 180f, 13f, 100f, 38f, 260f, 71f),
				new SurfaceGrasslandMix1(BOPCBiomes.chaparral.topBlock, BOPCBiomes.chaparral.fillerBlock, Blocks.sand, Blocks.stone, Blocks.cobblestone, 26f, 0.35f)
			)
		);
		
		//CHERRYBLOSSOM GROVE
		Support.biomes_cold.add(
			new RealisticBiomeSupport(
				BOPCBiomes.cherryBlossomGrove,
				new TerrainHighland(6f, 120f, 65f, 200f),
				new SurfaceMountainStone(BOPCBiomes.borealForest.topBlock, BOPCBiomes.borealForest.fillerBlock, true, Blocks.sand, 0.45f, 1.5f, 60f, 65f, 1.5f)
			)
		);
		
		//CONIFEROUS FOREST
		Support.biomes_cold.add(
			new RealisticBiomeSupport(
				BOPCBiomes.coniferousForest,
				new TerrainMountainRiver(),
				new SurfaceMountainSnow(Blocks.grass, Blocks.dirt, false, null, 0.45f)
			)
		);
		Support.biomes_snow.add(
			new RealisticBiomeSupport(
				BOPCBiomes.snowyConiferousForest,
				new TerrainMountainRiver(),
				new SurfaceMountainSnow(Blocks.grass, Blocks.dirt, false, null, 0.45f, 1.5f, 50f, 60f, 0.4f, 100f, 50f, 1.5f)
			)
		);
		
		//CRAG
		/*Support.biomes_cold.add(
			new RealisticBiomeSupport(
				BOPCBiomes.crag,
				new TerrainCanyon(false, new float[]{2.0f, 0.5f, 6.5f, 0.5f, 14.0f, 0.5f, 19.0f, 0.5f}, 35f, 80f, 60f, 40f, 69f),
				new SurfaceGrassland(BOPCBiomes.crag.topBlock, BOPCBiomes.crag.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);*/
		
		//DEAD FOREST
		Support.biomes_cold.add(
			new RealisticBiomeSupport(
				BOPCBiomes.deadForest,
				new TerrainGrasslandHills(50f, 180f, 13f, 100f, 28f, 260f, 70f),
				new SurfaceGrassland(BOPCBiomes.deadForest.topBlock, BOPCBiomes.deadForest.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);
		
		//DEAD SWAMP
		Support.biomes_wet.add(
			new RealisticBiomeSupport(
				BOPCBiomes.deadSwamp,
				new TerrainMarsh(),
				new SurfaceGrassland(BOPCBiomes.deadSwamp.topBlock, BOPCBiomes.deadSwamp.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);
		
		//DECIDUOUS FOREST
		Support.biomes_hot.add(
			new RealisticBiomeSupport(
				BOPCBiomes.deciduousForest,
				new TerrainHighland(0f, 140f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.deciduousForest.topBlock, BOPCBiomes.deciduousForest.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);
		
		//FEN
		Support.biomes_hot.add(
			new RealisticBiomeSupport(
				BOPCBiomes.fen,
				new TerrainHighland(0f, 140f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.fen.topBlock, BOPCBiomes.fen.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);
		
		//FLOWER FIELD
		/*Support.biomes_hot.add(
			new RealisticBiomeSupport(
				BOPCBiomes.flowerField,
				new TerrainGrasslandHills(40f, 180f, 13f, 100f, 28f, 260f, 70f),
				new SurfaceGrassland(BOPCBiomes.flowerField.topBlock, BOPCBiomes.flowerField.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);*/
		
		//FROST FOREST
		Support.biomes_snow.add(
			new RealisticBiomeSupport(
				BOPCBiomes.frostForest,
				new TerrainHighland(0f, 140f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.frostForest.topBlock, BOPCBiomes.frostForest.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);
		
		//FUNGI FOREST
		Support.biomes_wet.add(
			new RealisticBiomeSupport(
				BOPCBiomes.fungiForest,
				new TerrainSwampMountain(135f, 300f),
				new SurfaceMountainStone(BOPCBiomes.fungiForest.topBlock, BOPCBiomes.fungiForest.fillerBlock, false, null, 0.95f)
			)
		);
		
		//GARDEN
		Support.biomes_cold.add(
			new RealisticBiomeSupport(
				BOPCBiomes.garden,
				new TerrainMountainSpikes(),
				new SurfaceMountainSnow(BOPCBiomes.garden.topBlock, BOPCBiomes.garden.fillerBlock, true, Blocks.sand, 0.45f, 1.5f, 60f, 65f, 0.4f, 130f, 50f, 1.5f)
			)
		);
		
		//GRASSLAND
		/*Support.biomes_cold.add(
			new RealisticBiomeSupport(
				BOPCBiomes.grassland,
				new TerrainGrasslandHills(47f, 180f, 13f, 100f, 28f, 260f, 70f),
				new SurfaceGrassland(BOPCBiomes.grassland.topBlock, BOPCBiomes.grassland.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);*/
		
		//GROVE
		Support.biomes_cold.add(
			new RealisticBiomeSupport(
				BOPCBiomes.grove,
				new TerrainHighland(0f, 140f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.grove.topBlock, BOPCBiomes.grove.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);
		
		//HEATHLAND
		Support.biomes_hot.add(
			new RealisticBiomeSupport(
				BOPCBiomes.heathland,
				new TerrainDuneValley(300f),
				new SurfaceBase[]{
					new SurfaceDuneValley(BOPCBiomes.brushland.topBlock, BOPCBiomes.brushland.fillerBlock, 300f, false, true) ,
					new SurfaceRiverOasis(),
				},
				new EditBase[]{
					new EditRiverOasis()
				}
			)
		);
		
		//HIGHLAND
		Support.biomes_cold.add(
			new RealisticBiomeSupport(
				BOPCBiomes.highland,
				new TerrainHighland(0f, 140f, 68f, 150f),
				new SurfaceMountainStone(BOPCBiomes.highland.topBlock, BOPCBiomes.highland.fillerBlock, false, null, 1f, 1.5f, 85f, 20f, 4f)
			)
		);
		
		//JADE CLIFFS
		Support.biomes_hot.add(
			new RealisticBiomeSupport(
				BOPCBiomes.jadeCliffs,
				new TerrainHilly(230f, 120f, 0f),
				new SurfaceMountainStone(BOPCBiomes.jadeCliffs.topBlock, BOPCBiomes.jadeCliffs.fillerBlock, false, null, 0.95f)
			)
		);
		
		//LAVENDER FIELDS
		Support.biomes_cold.add(
			new RealisticBiomeSupport(
				BOPCBiomes.lavenderFields,
				new TerrainMountainSpikes(),
				new SurfaceMountainStone(BOPCBiomes.lavenderFields.topBlock, BOPCBiomes.lavenderFields.fillerBlock, false, null, 1.2f)
			)
		);
		
		//LUSH DESERT
		Support.biomes_hot.add(
			new RealisticBiomeSupport(
				BOPCBiomes.lushDesert,
				new TerrainGrasslandHills(90f, 180f, 13f, 100f, 38f, 260f, 71f),
				new SurfaceGrassland(BOPCBiomes.lushDesert.topBlock, BOPCBiomes.lushDesert.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);
		
		//LUSH SWAMP
		Support.biomes_wet.add(
			new RealisticBiomeSupport(
				BOPCBiomes.lushSwamp,
				new TerrainSwampRiver(),
				new SurfaceGrassland(BOPCBiomes.lushSwamp.topBlock, BOPCBiomes.lushSwamp.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);
		
		//MAPLE WOODS
		Support.biomes_cold.add(
			new RealisticBiomeSupport(
				BOPCBiomes.mapleWoods,
				new TerrainHighland(0f, 140f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.mapleWoods.topBlock, BOPCBiomes.mapleWoods.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);
		
		//MARSH
		/*Support.biomes_cold.add(
			new RealisticBiomeSupport(
				BOPCBiomes.marsh,
				new TerrainMarsh(),
				new SurfaceGrassland(BOPCBiomes.marsh.topBlock, BOPCBiomes.marsh.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);*/
		
		//MEADOW
		Support.biomes_cold.add(
			new RealisticBiomeSupport(
				BOPCBiomes.meadow,
				new TerrainMountainSpikes(),
				new SurfaceMountainStone(BOPCBiomes.meadow.topBlock, BOPCBiomes.meadow.fillerBlock, false, null, 1.2f)
			)
		);
		
		//MOOR
		/*Support.biomes_wet.add(
			new RealisticBiomeSupport(
				BOPCBiomes.moor,
				new TerrainHighland(0f, 70f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.moor.topBlock, BOPCBiomes.moor.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);*/
		
		//MOUNTAIN
		Support.biomes_hot.add(
			new RealisticBiomeSupport(
				BOPCBiomes.mountain,
				new TerrainMountainRiver(),
				new SurfaceMountainStone(BOPCBiomes.mountain.topBlock, BOPCBiomes.mountain.fillerBlock, true, Blocks.sand, 0.75f)
			)
		);
		
		//MYSTIC GROVE
		Support.biomes_wet.add(
			new RealisticBiomeSupport(
				BOPCBiomes.mysticGrove,
				new TerrainHighland(0f, 140f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.mysticGrove.topBlock, BOPCBiomes.mysticGrove.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);
		
		//OMINOUS WOODS
		/*Support.biomes_cold.add(
			new RealisticBiomeSupport(
				BOPCBiomes.ominousWoods,
				new TerrainHighland(0f, 140f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.ominousWoods.topBlock, BOPCBiomes.ominousWoods.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);*/
		
		//ORIGIN VALLEY
		/*Support.biomes_cold.add(
			new RealisticBiomeSupport(
				BOPCBiomes.originValley,
				new TerrainHighland(10f, 80f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.originValley.topBlock, BOPCBiomes.originValley.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);*/
		
		//OUTBACK
		Support.biomes_hot.add(
			new RealisticBiomeSupport(
				BOPCBiomes.outback,
				new TerrainDuneValley(300f),
				new SurfaceBase[]{
					new SurfaceDuneValley(BOPCBiomes.outback.topBlock, BOPCBiomes.outback.fillerBlock, 300f, false, false),
					new SurfaceRiverOasis(),
				},
				new EditBase[]{
					new EditRiverOasis()
				}
			)
		);
		
		//PRAIRIE
		Support.biomes_hot.add(
			new RealisticBiomeSupport(
				BOPCBiomes.prairie,
				new TerrainGrasslandHills(90f, 180f, 13f, 100f, 38f, 260f, 71f),
				new SurfaceGrassland(BOPCBiomes.prairie.topBlock, BOPCBiomes.prairie.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);
		
		//RAINFOREST
		Support.biomes_wet.add(
			new RealisticBiomeSupport(
				BOPCBiomes.rainforest,
				new TerrainSwampMountain(120f, 300f),
				new SurfaceMountainStone(BOPCBiomes.rainforest.topBlock, BOPCBiomes.rainforest.fillerBlock, false, null, 1.3f)
			)
		);
		
		//REDWOOD FOREST
		Support.biomes_cold.add(
			new RealisticBiomeSupport(
				BOPCBiomes.redwoodForest,
				new TerrainGrasslandHills(80f, 180f, 13f, 100f, 38f, 260f, 71f),
				new SurfaceMountainStone(BOPCBiomes.redwoodForest.topBlock, BOPCBiomes.redwoodForest.fillerBlock, false, null, 0.4f)
			)
		);
		
		//SACRED SPRINGS
		Support.biomes_wet.add(
			new RealisticBiomeSupport(
				BOPCBiomes.sacredSprings,
				new TerrainHighland(0f, 120f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.sacredSprings.topBlock, BOPCBiomes.sacredSprings.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);
		
		//SEASONAL FOREST
		Support.biomes_cold.add(
			new RealisticBiomeSupport(
				BOPCBiomes.seasonalForest,
				new TerrainHighland(0f, 140f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.seasonalForest.topBlock, BOPCBiomes.seasonalForest.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);
		
		//SHIELD
		Support.biomes_cold.add(
			new RealisticBiomeSupport(
				BOPCBiomes.shield,
				new TerrainGrasslandHills(90f, 180f, 13f, 100f, 38f, 260f, 71f),
				new SurfaceGrassland(BOPCBiomes.shield.topBlock, BOPCBiomes.shield.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);
		
		//SHRUBLAND
		Support.biomes_hot.add(
			new RealisticBiomeSupport(
				BOPCBiomes.shrubland,
				new TerrainGrasslandHills(90f, 180f, 13f, 100f, 38f, 260f, 71f),
				new SurfaceGrassland(BOPCBiomes.shrubland.topBlock, BOPCBiomes.shrubland.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);
		
		//SLUDGEPIT
		Support.biomes_wet.add(
			new RealisticBiomeSupport(
				BOPCBiomes.sludgepit,
				new TerrainMarsh(),
				new SurfaceGrassland(BOPCBiomes.sludgepit.topBlock, BOPCBiomes.sludgepit.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);
		
		//STEPPE
		/*Support.biomes_hot.add(
			new RealisticBiomeSupport(
				BOPCBiomes.steppe,
				new TerrainGrasslandHills(70f, 180f, 13f, 100f, 38f, 260f, 71f),
				new SurfaceGrassland(BOPCBiomes.steppe.topBlock, BOPCBiomes.steppe.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);*/
		
		//TEMPERATE RAINFOREST
		Support.biomes_wet.add(
			new RealisticBiomeSupport(
				BOPCBiomes.temperateRainforest,
				new TerrainMountainRiver(),
				new SurfaceMountainStone(BOPCBiomes.temperateRainforest.topBlock, BOPCBiomes.temperateRainforest.fillerBlock, false, null, 0.45f)
			)
		);
		
		//THICKET
		/*Support.biomes_hot.add(
			new RealisticBiomeSupport(
				BOPCBiomes.thicket,
				new TerrainGrasslandHills(70f, 180f, 13f, 100f, 38f, 260f, 71f),
				new SurfaceGrassland(BOPCBiomes.thicket.topBlock, BOPCBiomes.thicket.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);*/
		
		//TROPICAL RAINFOREST
		Support.biomes_wet.add(
			new RealisticBiomeSupport(
				BOPCBiomes.tropicalRainforest,
				new TerrainHighland(0f, 140f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.tropicalRainforest.topBlock, BOPCBiomes.tropicalRainforest.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);
		
		//TUNDRA
		Support.biomes_snow.add(
			new RealisticBiomeSupport(
				BOPCBiomes.tundra,
				new TerrainGrasslandHills(90f, 180f, 13f, 100f, 38f, 260f, 71f),
				new SurfaceGrassland(BOPCBiomes.tundra.topBlock, BOPCBiomes.tundra.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);
		
		//WASTELAND
		/*Support.biomes_hot.add(
			new RealisticBiomeSupport(
				BOPCBiomes.wasteland,
				new TerrainHighland(10f, 80f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.wasteland.topBlock, BOPCBiomes.wasteland.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);*/
		
		//WETLAND
		Support.biomes_wet.add(
			new RealisticBiomeSupport(
				BOPCBiomes.wetland,
				new TerrainMarsh(),
				new SurfaceGrassland(BOPCBiomes.wetland.topBlock, BOPCBiomes.wetland.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);
		
		//WOODLAND
		Support.biomes_cold.add(
			new RealisticBiomeSupport(
				BOPCBiomes.woodland,
				new TerrainHighland(0f, 140f, 68f, 200f),
				new SurfaceGrassland(BOPCBiomes.woodland.topBlock, BOPCBiomes.woodland.fillerBlock, Blocks.stone, Blocks.cobblestone)
			)
		);
	}
}
