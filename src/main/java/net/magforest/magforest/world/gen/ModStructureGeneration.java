package net.magforest.magforest.world.gen;

import net.magforest.magforest.world.biome.MagicForest;
import net.magforest.magforest.world.structure.ModStructures;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class ModStructureGeneration {
    public static void generateStructures(final BiomeLoadingEvent event) {
        RegistryKey<Biome> key = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, event.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);

        if(event.getName().equals(MagicForest.MAGIC_FOREST.getLocation())) {
            List<Supplier<StructureFeature<?, ?>>> structures = event.getGeneration().getStructures();

            structures.add(() -> ModStructures.WIZARD_TOWER.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
        }
    }
}
