package ru.feytox.etherology;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import ru.feytox.etherology.items.*;
import ru.feytox.etherology.items.MatrixRing.EthrilRing;
import ru.feytox.etherology.items.MatrixRing.NetheriteRing;
import ru.feytox.etherology.items.MatrixRing.TelderSteelRing;
import ru.feytox.etherology.items.glints.GlintItem;
import ru.feytox.etherology.util.feyapi.EIdentifier;


public class ItemsRegistry {

    public static final Teldecore TELDECORE = (Teldecore) new Teldecore().register();

    // TODO: 04/04/2023 удалить шарды
    public static final DeepShard DEEP_SHARD = (DeepShard) new DeepShard().register();
    public static final TerrestrialShard TERRESTRIAL_SHARD = (TerrestrialShard) new TerrestrialShard().register();
    public static final HeavenlyShard HEAVENLY_SHARD = (HeavenlyShard) new HeavenlyShard().register();
    public static final AquaticShard AQUATIC_SHARD = (AquaticShard) new AquaticShard().register();
    public static final EthrilRing ETHRIL_RING = (EthrilRing) new EthrilRing().register();
    public static final TelderSteelRing TELDER_STEEL_RING = (TelderSteelRing) new TelderSteelRing().register();
    public static final NetheriteRing NETHERITE_RING = (NetheriteRing) new NetheriteRing().register();
    public static final PrimoShard.KetaPrimoShard PRIMOSHARD_KETA = (PrimoShard.KetaPrimoShard) new PrimoShard.KetaPrimoShard().register();
    public static final PrimoShard.RelaPrimoShard PRIMOSHARD_RELA = (PrimoShard.RelaPrimoShard) new PrimoShard.RelaPrimoShard().register();
    public static final PrimoShard.ClosPrimoShard PRIMOSHARD_CLOS = (PrimoShard.ClosPrimoShard) new PrimoShard.ClosPrimoShard().register();
    public static final PrimoShard.ViaPrimoShard PRIMOSHARD_VIA = (PrimoShard.ViaPrimoShard) new PrimoShard.ViaPrimoShard().register();
    public static final GlintItem GLINT = (GlintItem) new GlintItem().register();
    public static final EtherShard ETHER_SHARD = (EtherShard) new EtherShard().register();
    public static final EtherOil ETHEREAL_OIL = (EtherOil) new EtherOil().register();
    public static final SpillBarrelItem SPILL_BARREL = (SpillBarrelItem) registerItem("spill_barrel", new SpillBarrelItem());

    private static Item registerItem(String itemId, Item item) {
        return Registry.register(Registries.ITEM, new EIdentifier(itemId), item);
    }

    public static void registerItems() {
    }
}
