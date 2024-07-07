package ru.feytox.etherology.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.*;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.util.TriConsumer;
import ru.feytox.etherology.recipes.staff.StaffCarpetCuttingRecipe;
import ru.feytox.etherology.recipes.staff.StaffCarpetingRecipe;
import ru.feytox.etherology.registry.block.EBlockFamilies;
import ru.feytox.etherology.registry.block.EBlocks;
import ru.feytox.etherology.registry.item.ToolItems;
import ru.feytox.etherology.registry.misc.TagsRegistry;
import ru.feytox.etherology.util.misc.EIdentifier;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static net.minecraft.block.Blocks.*;
import static net.minecraft.recipe.book.RecipeCategory.*;
import static ru.feytox.etherology.registry.block.DecoBlocks.*;
import static ru.feytox.etherology.registry.block.EBlocks.*;
import static ru.feytox.etherology.registry.item.DecoBlockItems.*;
import static ru.feytox.etherology.registry.item.EItems.*;
import static ru.feytox.etherology.registry.item.ToolItems.*;

public class RecipeGeneration extends FabricRecipeProvider {

    public RecipeGeneration(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        // azel
        ShapelessRecipeJsonBuilder.create(MISC, AZEL_NUGGET, 9).input(AZEL_INGOT).criterion(has(AZEL_INGOT), from(AZEL_INGOT)).offerTo(exporter, getFromPath(AZEL_NUGGET, "ingot"));
        ShapedRecipeJsonBuilder.create(MISC, AZEL_INGOT).pattern("AAA").pattern("AAA").pattern("AAA").input('A', AZEL_NUGGET).criterion(has(AZEL_NUGGET), from(AZEL_NUGGET)).offerTo(exporter, getFromPath(AZEL_INGOT, "nugget"));
        ShapelessRecipeJsonBuilder.create(MISC, AZEL_INGOT, 9).input(AZEL_BLOCK).criterion(has(AZEL_BLOCK), from(AZEL_BLOCK)).offerTo(exporter, getFromPath(AZEL_INGOT, "block"));
        ShapedRecipeJsonBuilder.create(MISC, AZEL_BLOCK.asItem()).pattern("AAA").pattern("AAA").pattern("AAA").input('A', AZEL_INGOT).criterion(has(AZEL_INGOT), from(AZEL_INGOT)).offerTo(exporter);
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ATTRAHITE), MISC, ATTRAHITE_BRICK, 0.1F, 200).criterion(has(ATTRAHITE), from(ATTRAHITE)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(BUILDING_BLOCKS, ATTRAHITE_BRICKS).input('#', ATTRAHITE_BRICK).pattern("##").pattern("##").criterion(has(ATTRAHITE), from(ATTRAHITE)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(MISC, RAW_AZEL).input('#', ENRICHED_ATTRAHITE).input('C', CALCITE).pattern("#C").pattern("C#").criterion(has(ATTRAHITE), from(ATTRAHITE)).offerTo(exporter);
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(RAW_AZEL), MISC, AZEL_INGOT, 0.3F, 200).criterion(has(ATTRAHITE), from(ATTRAHITE)).offerTo(exporter);
        CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(RAW_AZEL), MISC, AZEL_INGOT, 0.3F, 100).criterion(has(ATTRAHITE), from(ATTRAHITE)).offerTo(exporter, getBlastingItemPath(AZEL_INGOT));
        offerStonecuttingRecipe(exporter, EBlockFamilies.ATTRAHITE_BRICKS);

        // ethril
        ShapelessRecipeJsonBuilder.create(MISC, ETHRIL_NUGGET, 9).input(ETHRIL_INGOT).criterion(has(ETHRIL_INGOT), from(ETHRIL_INGOT)).offerTo(exporter, getFromPath(ETHRIL_NUGGET, "ingot"));
        ShapedRecipeJsonBuilder.create(MISC, ETHRIL_INGOT).pattern("AAA").pattern("AAA").pattern("AAA").input('A', ETHRIL_NUGGET).criterion(has(ETHRIL_NUGGET), from(ETHRIL_NUGGET)).offerTo(exporter, getFromPath(ETHRIL_INGOT, "nugget"));
        ShapelessRecipeJsonBuilder.create(MISC, ETHRIL_INGOT, 9).input(ETHRIL_BLOCK).criterion(has(ETHRIL_BLOCK), from(ETHRIL_BLOCK)).offerTo(exporter, getFromPath(ETHRIL_INGOT, "block"));
        ShapedRecipeJsonBuilder.create(MISC, ETHRIL_BLOCK.asItem()).pattern("AAA").pattern("AAA").pattern("AAA").input('A', ETHRIL_INGOT).criterion(has(ETHRIL_INGOT), from(ETHRIL_INGOT)).offerTo(exporter);

        // ebony
        ShapelessRecipeJsonBuilder.create(MISC, EBONY_NUGGET, 9).input(EBONY_INGOT).criterion(has(EBONY_INGOT), from(EBONY_INGOT)).offerTo(exporter, getFromPath(EBONY_NUGGET, "ingot"));
        ShapedRecipeJsonBuilder.create(MISC, EBONY_INGOT).pattern("AAA").pattern("AAA").pattern("AAA").input('A', EBONY_NUGGET).criterion(has(EBONY_NUGGET), from(EBONY_NUGGET)).offerTo(exporter, getFromPath(EBONY_INGOT, "nugget"));
        ShapelessRecipeJsonBuilder.create(MISC, EBONY_INGOT, 9).input(EBONY_BLOCK).criterion(has(EBONY_BLOCK), from(EBONY_BLOCK)).offerTo(exporter, getFromPath(EBONY_INGOT, "block"));
        ShapedRecipeJsonBuilder.create(MISC, EBONY_BLOCK.asItem()).pattern("AAA").pattern("AAA").pattern("AAA").input('A', EBONY_INGOT).criterion(has(EBONY_INGOT), from(EBONY_INGOT)).offerTo(exporter);

        // forest lantern
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(FOREST_LANTERN), FOOD, FOREST_LANTERN_CRUMB, 0.35f, 200).criterion(has(FOREST_LANTERN), from(FOREST_LANTERN)).offerTo(exporter);
        CookingRecipeJsonBuilder.createSmoking(Ingredient.ofItems(FOREST_LANTERN), FOOD, FOREST_LANTERN_CRUMB, 0.35f, 100).criterion(has(FOREST_LANTERN), from(FOREST_LANTERN)).offerTo(exporter, getFromPath(FOREST_LANTERN_CRUMB, "smoking"));
        CookingRecipeJsonBuilder.createCampfireCooking(Ingredient.ofItems(FOREST_LANTERN), FOOD, FOREST_LANTERN_CRUMB, 0.35f, 600).criterion(has(FOREST_LANTERN), from(FOREST_LANTERN)).offerTo(exporter, getFromPath(FOREST_LANTERN_CRUMB, "campfire"));

        // special recipes
        ComplexRecipeJsonBuilder.create(StaffCarpetingRecipe::new).offerTo(exporter, "staff_carpeting");
        ComplexRecipeJsonBuilder.create(StaffCarpetCuttingRecipe::new).offerTo(exporter, "staff_carpet_cutting");

        // block families recipes
        registerFamilies(EBlockFamilies.FAMILIES, exporter, FeatureSet.of(FeatureFlags.VANILLA));

        // peach
        offerPlanksRecipe(exporter, PEACH_PLANKS, TagsRegistry.PEACH_LOGS, 4);
        offerBarkBlockRecipe(exporter, PEACH_WOOD, PEACH_LOG);
        offerBarkBlockRecipe(exporter, STRIPPED_PEACH_WOOD, STRIPPED_PEACH_LOG);

        // ethereal stones
        offerStonecuttingRecipe(exporter, EBlockFamilies.SLITHERITE, EBlockFamilies.CRACKED_SLITHERITE_BRICKS, EBlockFamilies.CHISELED_SLITHERITE_BRICKS, EBlockFamilies.SLITHERITE_BRICKS, EBlockFamilies.POLISHED_SLITHERITE);

        // stone -> ethereal stone
        ShapedRecipeJsonBuilder.create(REDSTONE, COMPARATOR).input('#', REDSTONE_TORCH).input('X', Items.QUARTZ).input('I', SLITHERITE).pattern(" # ").pattern("#X#").pattern("III").criterion(has(Items.QUARTZ), from(Items.QUARTZ)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(REDSTONE, REPEATER).input('#', REDSTONE_TORCH).input('X', Items.REDSTONE).input('I', SLITHERITE).pattern("#X#").pattern("III").criterion(has(REDSTONE_TORCH), from(REDSTONE_TORCH)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(BUILDING_BLOCKS, SLITHERITE_BRICKS, 4).input('#', SLITHERITE).pattern("##").pattern("##").criterion(has(SLITHERITE), from(SLITHERITE)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(DECORATIONS, STONECUTTER).input('I', Items.IRON_INGOT).input('#', SLITHERITE).pattern(" I ").pattern("###").criterion(has(SLITHERITE), from(SLITHERITE)).offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(MISC, THUJA_OIL, 2).input(THUJA_SEEDS).criterion(has(THUJA_SEEDS), from(THUJA_SEEDS)).offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(MISC, BEAMER_SEEDS, 3).input(BEAM_FRUIT).criterion(has(BEAM_FRUIT), from(BEAM_FRUIT)).offerTo(exporter);

        // NOTE: complicated crafts = more than 1 line for recipe

        // tools
        ShapedRecipeJsonBuilder.create(TOOLS, ToolItems.IRON_SHIELD).input('#', Items.IRON_INGOT).input('P', ItemTags.PLANKS).pattern("###").pattern(" P ").criterion(has(Items.IRON_INGOT), from(Items.IRON_INGOT)).offerTo(exporter);
        // TODO: 28.02.2024 try replace to c:stick etc
        // TODO: 28.02.2024 criterion
        registerPicks(exporter);
        ShapedRecipeJsonBuilder.create(TOOLS, STREAM_KEY).input('N', Items.IRON_NUGGET).input('T', EBONY_INGOT).input('I', Items.STICK)
                .pattern("N")
                .pattern("T")
                .pattern("I").criterion(has(EBONY_INGOT), from(EBONY_INGOT)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(TOOLS, TUNING_MACE).input('W', RESONATING_WAND).input('I', Items.STICK).input('S', Items.IRON_INGOT)
                .pattern("W W")
                .pattern("WSW")
                .pattern(" I ").criterion(has(RESONATING_WAND), from(RESONATING_WAND)).offerTo(exporter);

        // furniture
        ShapelessRecipeJsonBuilder.create(MISC, SHELF_SLAB).input(Items.ITEM_FRAME).input(FURNITURE_SLAB).criterion(has(FURNITURE_SLAB), from(FURNITURE_SLAB)).offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(MISC, CLOSET_SLAB).input(Items.CHEST).input(FURNITURE_SLAB).criterion(has(FURNITURE_SLAB), from(FURNITURE_SLAB)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(MISC, FURNITURE_SLAB, 2).input('#', ItemTags.PLANKS).input('I', Items.STICK)
                .pattern("#I#")
                .pattern("#I#").criterion("has_planks", conditionsFromTag(ItemTags.PLANKS)).offerTo(exporter);

        // "simple" recipe
        ShapedRecipeJsonBuilder.create(MISC, EBlocks.SPILL_BARREL).input('S', ItemTags.WOODEN_SLABS).input('#', ItemTags.PLANKS)
                .pattern("S#S")
                .pattern("# #")
                .pattern("S#S").criterion("has_planks", conditionsFromTag(ItemTags.PLANKS)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(REDSTONE, ESSENCE_DETECTOR_BLOCK).input('G', GLASS).input('A', Items.AMETHYST_SHARD).input('P', ItemTags.WOODEN_SLABS)
                .pattern("GGG")
                .pattern("AAA")
                .pattern("PPP").criterion(has(Items.AMETHYST_SHARD), from(Items.AMETHYST_SHARD)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(MISC, BREWING_CAULDRON).input('#', Items.IRON_INGOT).input('I', Items.STICK).input('C', AZEL_NUGGET)
                .pattern("#I#")
                .pattern("#C#")
                .pattern(" # ").criterion(has(AZEL_INGOT), from(AZEL_INGOT)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(MISC, CRATE).input('C', Items.IRON_NUGGET).input('S', ItemTags.WOODEN_SLABS).input('#', ItemTags.PLANKS)
                .pattern("CSC")
                .pattern("#S#").criterion("has_planks", conditionsFromTag(ItemTags.PLANKS)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(MISC, INVENTOR_TABLE).input('#', Items.IRON_INGOT).input('S', ItemTags.WOODEN_SLABS)
                .pattern("##")
                .pattern("SS")
                .pattern("SS").criterion("has_wooden_slab", conditionsFromTag(ItemTags.WOODEN_SLABS)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(DECORATIONS, PEDESTAL_BLOCK, 2).input('S', POLISHED_SLITHERITE_SLAB).input('#', POLISHED_SLITHERITE)
                .pattern("S")
                .pattern("#")
                .pattern("S").criterion(has(SLITHERITE), from(SLITHERITE)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(TOOLS, WARP_COUNTER).input('R', Items.REDSTONE).input('#', EBONY_INGOT)
                .pattern(" # ")
                .pattern("#R#")
                .pattern(" # ").criterion(has(EBONY_INGOT), from(EBONY_INGOT)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(MISC, ETHEREAL_CHANNEL_CASE, 4).input('I', Items.IRON_INGOT).input('W', ItemTags.PLANKS).input('T', THUJA_OIL)
                .pattern("IWI")
                .pattern("WTW")
                .pattern("IWI").criterion(has(THUJA_OIL), from(THUJA_OIL)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(MISC, Items.LEATHER).input('S', Items.STRING).input('F', FOREST_LANTERN)
                .pattern("SFS")
                .pattern(" F ")
                .pattern("SFS").criterion(has(FOREST_LANTERN), from(FOREST_LANTERN)).offerTo(exporter);

        // "hard" recipes
        ShapedRecipeJsonBuilder.create(MISC, ETHEREAL_SPINNER).input('C', Items.IRON_NUGGET).input('I', Items.IRON_INGOT).input('S', SEDIMENTARY_BLOCK).input('#', SMOOTH_STONE).input('E', ETHEROSCOPE)
                .pattern("CIC")
                .pattern("ISI")
                .pattern("#E#").criterion(has(ETHEROSCOPE), from(ETHEROSCOPE)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(MISC, ETHEREAL_CHANNEL, 2).input('O', THUJA_OIL).input('T', EBONY_INGOT).input('E', ETHEROSCOPE).input('C', Items.IRON_NUGGET)
                .pattern(" O ")
                .pattern("TET")
                .pattern(" C ").criterion(has(ETHEROSCOPE), from(ETHEROSCOPE)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(MISC, ETHEREAL_FORK).input('C', ETHEREAL_CHANNEL).input('I', Items.IRON_INGOT).input('E', ETHEROSCOPE)
                .pattern(" C ")
                .pattern("IEI")
                .pattern(" C ").criterion(has(ETHEROSCOPE), from(ETHEROSCOPE)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(MISC, ETHEREAL_FURNACE).input('I', Items.IRON_INGOT).input('A', AZEL_INGOT).input('B', Items.BLAZE_POWDER).input('C', AZEL_NUGGET).input('E', ETHEROSCOPE)
                .pattern("IAI")
                .pattern("IBI")
                .pattern("CEC").criterion(has(ETHEROSCOPE), from(ETHEROSCOPE)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(MISC, ETHEREAL_SOCKET).input('#', IRON_BARS).input('S', STONE).input('E', ETHEROSCOPE)
                .pattern(" # ")
                .pattern("SES").criterion(has(ETHEROSCOPE), from(ETHEROSCOPE)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(MISC, SEDIMENTARY_BLOCK).input('S', STONE).input('A', AZEL_INGOT).input('R', Items.REDSTONE)
                .pattern("SSS")
                .pattern("SAS")
                .pattern("SRS").criterion(has(AZEL_INGOT), from(AZEL_INGOT)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(MISC, ETHEREAL_STORAGE).input('T', EBONY_INGOT).input('N', Items.IRON_NUGGET).input('G', GLINT).input('C', ItemTags.STONE_CRAFTING_MATERIALS).input('E', ETHEROSCOPE)
                .pattern("TTT")
                .pattern("NGN")
                .pattern("CEC").criterion(has(ETHEROSCOPE), from(ETHEROSCOPE)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(MISC, JEWELRY_TABLE).input('C', ItemTags.STONE_CRAFTING_MATERIALS).input('I', Items.IRON_INGOT).input('D', DROPPER).input('E', ETHEREAL_CHANNEL)
                .pattern("CIC")
                .pattern("CDC")
                .pattern("CEC").criterion(has(ETHEREAL_CHANNEL), from(ETHEREAL_CHANNEL)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(MISC, LEVITATOR).input('#', ItemTags.LOGS).input('N', Items.IRON_NUGGET).input('F', Items.RABBIT_HIDE).input('L', REDSTONE_LENS).input('E', ETHEROSCOPE)
                .pattern("#N#")
                .pattern("FLF")
                .pattern("#E#").criterion(has(ETHEROSCOPE), from(ETHEROSCOPE)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(REDSTONE, TUNING_FORK, 2).input('#', ItemTags.PLANKS).input('R', Items.REDSTONE).input('I', RESONATING_WAND)
                .pattern("IRI")
                .pattern(" # ").criterion(has(RESONATING_WAND), from(RESONATING_WAND)).offerTo(exporter);
    }

    private void registerPicks(RecipeExporter exporter) {
        registerTools(ToolItems.BATTLE_PICKAXES, (tool, material, criterionPredicate) ->
                ShapedRecipeJsonBuilder.create(TOOLS, tool).input('I', Items.STICK).input('M', material)
                .pattern("MM ")
                .pattern(" IM")
                .pattern(" I ")
                .criterion("has_material", conditionsFromItemPredicates(criterionPredicate)).offerTo(exporter));
    }

    private void registerTools(Item[] toolsArr, TriConsumer<ToolItem, Ingredient, ItemPredicate> registrar) {
        Arrays.stream(toolsArr).forEach(item -> {
            ToolItem tool = (ToolItem) item;
            Ingredient material = tool.getMaterial().getRepairIngredient();
            Item[] materialItems = Arrays.stream(material.getMatchingStacks()).map(ItemStack::getItem).toArray(Item[]::new);
            ItemPredicate criterionPredicate = ItemPredicate.Builder.create().items(materialItems).build();
            registrar.accept(tool, material, criterionPredicate);
        });
    }

    private void registerFamilies(List<BlockFamily> blockFamilies, RecipeExporter exporter, FeatureSet enabledFeatures) {
        blockFamilies.stream()
                .filter(BlockFamily::shouldGenerateRecipes)
                .forEach(family -> RecipeProvider.generateFamily(exporter, family, enabledFeatures));
    }

    private void offerStonecuttingRecipe(RecipeExporter exporter, BlockFamily... blockFamilies) {
        Arrays.stream(blockFamilies).forEach(family -> family.getVariants().forEach((variant, block) -> {
            int count = 1;
            RecipeCategory category = BUILDING_BLOCKS;
            boolean exclude = false;

            switch (variant) {
                case SLAB -> count = 2;
                case WALL -> category = DECORATIONS;
                case BUTTON, PRESSURE_PLATE -> exclude = true;
            }

            if (exclude) return;
            offerStonecuttingRecipe(exporter, category, block, family.getBaseBlock(), count);
        }));
    }

    private String has(ItemConvertible itemConvertible) {
        return hasItem(itemConvertible);
    }

    private AdvancementCriterion<InventoryChangedCriterion.Conditions> from(ItemConvertible itemConvertible) {
        return conditionsFromItem(itemConvertible);
    }
    
    private Identifier getFromPath(ItemConvertible itemConvertible, String suffix) {
        return EIdentifier.of(getItemPath(itemConvertible) + "_from_" + suffix);
    }
}
