package net.danh.storage.Hook;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.danh.storage.Manager.Data;
import net.danh.storage.Manager.Files;
import net.danh.storage.Manager.Items;
import net.danh.storage.Storage;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlaceholderAPI extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "Storage";
    }

    @Override
    public @NotNull String getAuthor() {
        return Storage.get().getDescription().getAuthors().toString();
    }

    @Override
    public @NotNull String getVersion() {
        return Storage.get().getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player p, @NotNull String identifier) {
        if (p == null) {
            return "Player not online";
        }
        if (identifier.startsWith("storage_")) {
            String item = identifier.substring(8);
            return String.valueOf(Data.getStorage(p, item));
        }
        if (identifier.startsWith("max_storage_")) {
            String item = identifier.substring(12);
            return String.valueOf(Data.getMaxStorage(p, item));
        }
        if (identifier.startsWith("price_")) {
            String material = identifier.substring(6);
            return String.valueOf(Items.getPrice(material));
        }
        if (identifier.startsWith("auto_")) {
            String name = identifier.substring(5);
            if (name.equalsIgnoreCase("_smelt")) {
                if (Data.autoSmelt(p)) {
                    return Files.getconfigfile().getString("Boolean.True");
                }
                if (!Data.autoSmelt(p)) {
                    return Files.getconfigfile().getString("Boolean.False");
                }
            }
            if (name.equalsIgnoreCase("_pickup")) {
                if (Data.autoPick(p)) {
                    return Files.getconfigfile().getString("Boolean.True");
                }
                if (!Data.autoPick(p)) {
                    return Files.getconfigfile().getString("Boolean.False");
                }
            }
            return "";
        }
        return null;
    }
}
