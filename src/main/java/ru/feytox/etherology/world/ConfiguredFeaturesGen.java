package ru.feytox.etherology.world;

import com.google.common.collect.ImmutableList;
import lombok.experimental.UtilityClass;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.PredicatedStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import ru.feytox.etherology.block.beamer.BeamerBlock;
import ru.feytox.etherology.magic.seal.SealType;
import ru.feytox.etherology.mixin.TreeConfiguredFeaturesAccessor;
import ru.feytox.etherology.registry.block.DecoBlocks;
import ru.feytox.etherology.registry.world.WorldGenRegistry;
import ru.feytox.etherology.util.misc.EIdentifier;
import ru.feytox.etherology.world.trees.BirchBranchesDecorator;
import ru.feytox.etherology.world.trees.PeachLanternDecorator;
import ru.feytox.etherology.world.trees.PeachWeepingDecorator;

import java.util.List;

import static net.minecraft.world.gen.feature.ConfiguredFeatures.register;
import static ru.feytox.etherology.registry.world.TreesRegistry.peach;

@UtilityClass
public class ConfiguredFeaturesGen {
    public static final RegistryKey<ConfiguredFeature<?,?>> PEACH_TREE = of("peach_tree");
    public static final RegistryKey<ConfiguredFeature<?,?>> BIRCH_BRANCH_TREE = of("birch_branch_tree");
    public static final RegistryKey<ConfiguredFeature<?,?>> GOLDEN_FOREST_FLOWERS = of("golden_forest_flowers");
    public static final RegistryKey<ConfiguredFeature<?,?>> PATCH_LIGHTELET = of("patch_lightelet");
    public static final RegistryKey<ConfiguredFeature<?,?>> DISK_COARSE_DIRT = of("disk_coarse_dirt");
    public static final RegistryKey<ConfiguredFeature<?,?>> ETHER_ROCK = of("ether_rock");
    public static final RegistryKey<ConfiguredFeature<?,?>> PATCH_BEAMER = of("patch_beamer");
    public static final RegistryKey<ConfiguredFeature<?,?>> PATCH_THUJA = of("patch_thuja");
    public static final RegistryKey<ConfiguredFeature<?,?>> ATTRAHITE = of("attrahite");
    public static final RegistryKey<ConfiguredFeature<?,?>> SINGLE_PIECE_OF_GRASS_LIGHTELET = of("single_piece_of_grass_lightelet");
    public static final RegistryKey<ConfiguredFeature<?,?>> KETA_SEAL = of("keta_seal");
    public static final RegistryKey<ConfiguredFeature<?,?>> RELLA_SEAL = of("rella_seal");
    public static final RegistryKey<ConfiguredFeature<?,?>> CLOS_SEAL = of("clos_seal");
    public static final RegistryKey<ConfiguredFeature<?,?>> VIA_SEAL = of("via_seal");

    public static void registerFeatures(Registerable<ConfiguredFeature<?, ?>> context) {
        register(context, PEACH_TREE, Feature.TREE,
                peach().decorators(ImmutableList.of(PeachWeepingDecorator.INSTANCE, PeachLanternDecorator.INSTANCE)).build());
        register(context, BIRCH_BRANCH_TREE, Feature.TREE,
                TreeConfiguredFeaturesAccessor.callSuperBirch().decorators(ImmutableList.of(BirchBranchesDecorator.INSTANCE, new BeehiveTreeDecorator(0.05f))).build());
        register(context, GOLDEN_FOREST_FLOWERS, Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfig(RegistryEntryList.of(
                PlacedFeatures.createEntry(Feature.RANDOM_PATCH, ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.WHITE_TULIP)))),
                PlacedFeatures.createEntry(Feature.RANDOM_PATCH, ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.LILY_OF_THE_VALLEY)))),
                PlacedFeatures.createEntry(Feature.RANDOM_PATCH, ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.CORNFLOWER)))))));
        register(context, PATCH_LIGHTELET, Feature.RANDOM_PATCH,
                ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(DecoBlocks.LIGHTELET)), List.of(), 48));
        register(context, DISK_COARSE_DIRT, Feature.DISK,
                new DiskFeatureConfig(PredicatedStateProvider.of(Blocks.COARSE_DIRT), BlockPredicate.matchingBlocks(Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.COARSE_DIRT), UniformIntProvider.create(3, 5), 1));
        register(context, ETHER_ROCK, WorldGenRegistry.ETHER_ROCK);
        register(context, PATCH_BEAMER, Feature.RANDOM_PATCH,
                ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(DecoBlocks.BEAMER.getDefaultState().withIfExists(BeamerBlock.AGE, BeamerBlock.MAX_AGE))), List.of(), 48));
        register(context, PATCH_THUJA, Feature.RANDOM_PATCH, ConfiguredFeatures.createRandomPatchFeatureConfig(
                WorldGenRegistry.THUJA, new DefaultFeatureConfig(), List.of(), 32
        ));
        register(context, ATTRAHITE, Feature.ORE, new OreFeatureConfig(new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), DecoBlocks.ATTRAHITE.getDefaultState(), 52, 0));
        register(context, SINGLE_PIECE_OF_GRASS_LIGHTELET, Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(DataPool.<BlockState>builder()
                        .add(Blocks.SHORT_GRASS.getDefaultState(), 1)
                        .add(DecoBlocks.LIGHTELET.getDefaultState(), 2).build())));
        registerSeal(context, KETA_SEAL, SealType.KETA);
        registerSeal(context, RELLA_SEAL, SealType.RELLA);
        registerSeal(context, CLOS_SEAL, SealType.CLOS);
        registerSeal(context, VIA_SEAL, SealType.VIA);

    }

    private static void registerSeal(Registerable<ConfiguredFeature<?, ?>> context, RegistryKey<ConfiguredFeature<?,?>> key, SealType sealType) {
        register(context, key, Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(sealType.getBlock())));
    }

    private static RegistryKey<ConfiguredFeature<?, ?>> of(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, EIdentifier.of(name));
    }
}
