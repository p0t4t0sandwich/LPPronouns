package ca.sperrer.p0t4t0sandwich.lppronouns.common.storage;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.LPPronouns;
import dev.dejvokep.boostedyaml.YamlDocument;

import java.io.File;

public class Filesystem extends Database<String> {
    /**
     * Constructor for the Filesystem class
     * @param config The configuration for the Filesystem data source.
     */
    public Filesystem(YamlDocument config) {
        super("filesystem", null, null);
        String path = "." + File.separator + LPPronouns.configPath + File.separator + "LPPronouns";
        String directory = config.getString("storage.config.directory");

        setConnection(path);
        setDatabase(directory);
    }
}
