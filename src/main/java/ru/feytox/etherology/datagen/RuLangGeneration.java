package ru.feytox.etherology.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import ru.feytox.etherology.Etherology;
import ru.feytox.etherology.datagen.util.BlockRuTranslationBuilder;
import ru.feytox.etherology.datagen.util.RuTranslationPart;
import ru.feytox.etherology.registry.block.DecoBlocks;
import ru.feytox.etherology.registry.block.DevBlocks;
import ru.feytox.etherology.registry.block.EBlocks;

import java.nio.file.Path;

import static ru.feytox.etherology.registry.block.DecoBlocks.*;
import static ru.feytox.etherology.registry.block.EBlocks.CRATE;
import static ru.feytox.etherology.registry.block.EBlocks.LEVITATOR;
import static ru.feytox.etherology.registry.item.ArmorItems.*;
import static ru.feytox.etherology.registry.item.DecoBlockItems.*;
import static ru.feytox.etherology.registry.item.EItems.*;
import static ru.feytox.etherology.registry.item.ToolItems.*;

public class RuLangGeneration extends FabricLanguageProvider {
    private final String langCode;

    public RuLangGeneration(FabricDataOutput dataOutput) {
        // TODO: 12/04/2023 rename on release or smth
        super(dataOutput, "en_us");
        langCode = "en_us";
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        BlockRuTranslationBuilder builder = new BlockRuTranslationBuilder(translationBuilder);

        builder.add(DecoBlocks.ETHEREAL_STONE, "Эфирный камень");
        new RuTranslationPart(builder, "эфирного камня")
                .stairs(ETHEREAL_STONE_STAIRS)
                .slab(ETHEREAL_STONE_SLAB)
                .wall(ETHEREAL_STONE_WALL)
                .button(ETHEREAL_STONE_BUTTON)
                .plate(ETHEREAL_STONE_PRESSURE_PLATE);

        builder.add(COBBLED_ETHEREAL_STONE, "Эфирный булыжник");
        new RuTranslationPart(builder, "эфирного булыжника")
                .stairs(COBBLED_ETHEREAL_STONE_STAIRS)
                .slab(COBBLED_ETHEREAL_STONE_SLAB)
                .wall(COBBLED_ETHEREAL_STONE_WALL);

        builder.add(ETHEREAL_STONE_BRICKS, "Эфирные каменные кирпичи");
        new RuTranslationPart(builder, "эфирного каменного кирпича")
                .stairs(ETHEREAL_STONE_BRICK_STAIRS)
                .slab(ETHEREAL_STONE_BRICK_SLAB)
                .wall(ETHEREAL_STONE_BRICK_WALL);

        builder.add(CHISELED_ETHEREAL_STONE_BRICKS, "Резные эфирные каменные кирпичи");
        new RuTranslationPart(builder, "резного эфирного каменного кирпича")
                .stairs(CHISELED_ETHEREAL_STONE_BRICK_STAIRS);

        builder.add(CRACKED_ETHEREAL_STONE_BRICKS, "Потрескавшиеся эфирные каменные кирпичи");
        new RuTranslationPart(builder, "потрескавшегося эфирного каменного кирпича")
                .slab(CRACKED_ETHEREAL_STONE_BRICK_SLAB)
                .stairs(CRACKED_ETHEREAL_STONE_BRICK_STAIRS);

        builder.add(MOSSY_COBBLED_ETHEREAL_STONE, "Замшелый эфирный булыжник");
        new RuTranslationPart(builder, "замшелого эфирного булыжника")
                .slab(MOSSY_COBBLED_ETHEREAL_STONE_SLAB)
                .stairs(MOSSY_COBBLED_ETHEREAL_STONE_STAIRS);

        builder.add(MOSSY_ETHEREAL_STONE_BRICKS, "Замшелые эфирные каменные кирпичи");
        new RuTranslationPart(builder, "замшелого эфирного каменного кирпича")
                .slab(MOSSY_ETHEREAL_STONE_BRICK_SLAB)
                .stairs(MOSSY_ETHEREAL_STONE_BRICK_STAIRS);

        builder.add(POLISHED_ETHEREAL_STONE, "Полированный эфирный камень");
        new RuTranslationPart(builder, "полированного эфирного камня")
                .stairs(POLISHED_ETHEREAL_STONE_STAIRS)
                .slab(POLISHED_ETHEREAL_STONE_SLAB);

        builder.add(CLAY_TILES, "Керамическая плитка");
        new RuTranslationPart(builder, "керамической плитки")
                .stairs(CLAY_TILES_STAIRS)
                .slab(CLAY_TILES_SLAB)
                .wall(CLAY_TILES_WALL);

        builder.add(BLUE_CLAY_TILES, "Синяя керамическая плитка");
        new RuTranslationPart(builder, "синей керамической плитки")
                .stairs(BLUE_CLAY_TILES_STAIRS)
                .slab(BLUE_CLAY_TILES_SLAB)
                .wall(BLUE_CLAY_TILES_WALL);

        builder.add(GREEN_CLAY_TILES, "Зелёная керамическая плитка");
        new RuTranslationPart(builder, "зелёной керамической плитки")
                .stairs(GREEN_CLAY_TILES_STAIRS)
                .slab(GREEN_CLAY_TILES_SLAB)
                .wall(GREEN_CLAY_TILES_WALL);

        builder.add(RED_CLAY_TILES, "Красная керамическая плитка");
        new RuTranslationPart(builder, "красной керамической плитки")
                .stairs(RED_CLAY_TILES_STAIRS)
                .slab(RED_CLAY_TILES_SLAB)
                .wall(RED_CLAY_TILES_WALL);

        builder.add(YELLOW_CLAY_TILES, "Жёлтая керамическая плитка");
        new RuTranslationPart(builder, "жёлтой керамической плитки")
                .stairs(YELLOW_CLAY_TILES_STAIRS)
                .slab(YELLOW_CLAY_TILES_SLAB)
                .wall(YELLOW_CLAY_TILES_WALL);

        // azel
        builder.add(AZEL_BLOCK, "Азельный блок");
        builder.add(AZEL_INGOT, "Азельный слиток");
        builder.add(AZEL_NUGGET, "Азельный самородок");

        // attrahite
        builder.add(ATTRAHITE, "Аттрахит");
        builder.add(ATTRAHITE_BRICKS, "Аттрахитовые кирпичи");
        new RuTranslationPart(builder, "аттрахитовых кирпичей")
                .slab(ATTRAHITE_BRICK_SLAB)
                .stairs(ATTRAHITE_BRICK_STAIRS);
        builder.add(ATTRAHITE_BRICK, "Аттрахитовый кирпич");
        builder.add(RAW_AZEL, "Сырой азель");
        builder.add(ENRICHED_ATTRAHITE, "Обогащённый аттрахит");

        // ethril
        builder.add(ETHRIL_BLOCK, "Эфриловый блок");
        builder.add(ETHRIL_INGOT, "Эфриловый слиток");
        builder.add(ETHRIL_NUGGET, "Эфриловый самородок");

        // ebony
        builder.add(EBONY_BLOCK, "Эбонитовый блок");
        builder.add(EBONY_INGOT, "Эбонитовый слиток");
        builder.add(EBONY_NUGGET, "Эбонитовый самородок");

        // beamer
        builder.add(BEAMER, "Лучевод");
        builder.add(BEAMER_SEEDS, "Семена лучевода");
        builder.add(BEAM_FRUIT, "Лучеплод");

        builder.add(CRATE, "Ящик");

        builder.add(PEACH_SAPLING, "Саженец персикового дерева");
        builder.add(PEACH_LEAVES, "Персиковая листва");

        builder.add(ETHRIL_AXE, "Эфриловый топор");
        builder.add(ETHRIL_HOE, "Эфриловая мотыга");
        builder.add(ETHRIL_PICKAXE, "Эфриловая кирка");
        builder.add(ETHRIL_SHOVEL, "Эфриловая лопата");
        builder.add(ETHRIL_SWORD, "Эфриловый меч");

        builder.add(EBONY_AXE, "Эбонитовый меч");
        builder.add(EBONY_HOE, "Эбонитовая мотыга");
        builder.add(EBONY_PICKAXE, "Эбонитовая кирка");
        builder.add(EBONY_SHOVEL, "Эбонитовая лопата");
        builder.add(EBONY_SWORD, "Эбонитовый меч");

        builder.add(WOODEN_BATTLE_PICKAXE, "Деревянное кайло");
        builder.add(STONE_BATTLE_PICKAXE, "Каменное кайло");
        builder.add(IRON_BATTLE_PICKAXE, "Железное кайло");
        builder.add(GOLDEN_BATTLE_PICKAXE, "Золотое кайло");
        builder.add(DIAMOND_BATTLE_PICKAXE, "Алмазное кайло");
        builder.add(NETHERITE_BATTLE_PICKAXE, "Незеритовое кайло");
        builder.add(ETHRIL_BATTLE_PICKAXE, "Эфриловое кайло");
        builder.add(EBONY_BATTLE_PICKAXE, "Эбонитовое кайло");

        builder.add(TUNING_MACE, "Тональная булава");

        builder.add(WOODEN_GLAIVE, "Деревянная глефа");
        builder.add(STONE_GLAIVE, "Каменная глефа");
        builder.add(IRON_GLAIVE, "Железная глефа");
        builder.add(GOLDEN_GLAIVE, "Золотая глефа");
        builder.add(DIAMOND_GLAIVE, "Алмазная глефа");
        builder.add(NETHERITE_GLAIVE, "Незеритовая глефа");
        builder.add(ETHRIL_GLAIVE, "Эфриловая глефа");
        builder.add(EBONY_GLAIVE, "Эбонитовая глефа");

        builder.add(ETHRIL_HELMET, "Эфриловый шлем");
        builder.add(ETHRIL_CHESTPLATE, "Эфриловый нагрудник");
        builder.add(ETHRIL_LEGGINGS, "Эфриловые поножи");
        builder.add(ETHRIL_BOOTS, "Эфриловые ботинки");
        builder.add(EBONY_HELMET, "Эбонитовый шлем");
        builder.add(EBONY_CHESTPLATE, "Эбонитовый нагрудник");
        builder.add(EBONY_LEGGINGS, "Эбонитовые поножи");
        builder.add(EBONY_BOOTS, "Эбонитовые ботинки");

        builder.add(DevBlocks.UNLIMITED_ETHER_STORAGE_BLOCK, "Творческий бесконечный источник эфира");
        builder.add(LEVITATOR, "Левитатор");

        builder.add(STAFF, "Посох");
        builder.add(EBlocks.INVENTOR_TABLE, "Стол изобретателя");
        builder.add(EBlocks.JEWELRY_TABLE, "Стол ювелира");
        builder.add(ARISTOCRAT_PATTERN_TABLET, "Скрижаль со Знатным Шаблоном");
        builder.add(ASTRONOMY_PATTERN_TABLET, "Скрижаль с Астрономическим Шаблоном");
        builder.add(HEAVENLY_PATTERN_TABLET, "Скрижаль с Вольным Шаблоном");
        builder.add(OCULAR_PATTERN_TABLET, "Скрижаль с Окулярным Шаблоном");
        builder.add(RITUAL_PATTERN_TABLET, "Скрижаль с Ритуальным Шаблоном");
        builder.add(ROYAL_PATTERN_TABLET, "Скрижаль с Королевским Шаблоном");
        builder.add(TRADITIONAL_PATTERN_TABLET, "Скрижаль с Традиционным Шаблоном");

        builder.add(UNADJUSTED_LENS, "Ненастроенная линза");
        builder.add(REDSTONE_LENS, "Линза красного камня");

        builder.add(EBlocks.ESSENCE_DETECTOR_BLOCK, "Детектор эссенции");

        builder.add(ETHEROSCOPE, "Эфироскоп");
        builder.add(THUJA_OIL, "Хвойное масло");
        builder.add(THUJA_SEEDS, "Семена туи");
        builder.add(BINDER, "Связующий элемент");

        builder.add(EBlocks.ETHEREAL_CHANNEL_CASE, "Обшивка эфирного канала");
        builder.add(STREAM_KEY, "Ключ трансляции");
        builder.add(REVELATION_VIEW, "Окуляр");

        try {
            Path existingFilePath = dataOutput.getModContainer().findPath("assets/" + Etherology.MOD_ID + "/lang/" + langCode + ".existing.json").get();
            translationBuilder.add(existingFilePath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add existing language file!", e);
        }
    }
}
