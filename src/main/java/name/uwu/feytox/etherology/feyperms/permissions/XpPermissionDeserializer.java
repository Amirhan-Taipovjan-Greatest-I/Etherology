package name.uwu.feytox.etherology.feyperms.permissions;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import name.uwu.feytox.etherology.feyperms.PermissionDeserializer;
import net.minecraft.util.Identifier;

public class XpPermissionDeserializer extends PermissionDeserializer<XpPermission> {
    public XpPermissionDeserializer() {
        super("xp_perm_deser");
    }

    @Override
    public XpPermission deserialize(Identifier id, JsonElement json) {
        JsonObject jsonObject = json.getAsJsonObject();

//        String name = jsonObject.get("name").getAsString();
        int xp_level = jsonObject.get("xp_level").getAsInt();

        return new XpPermission(id, xp_level);
    }
}