package ca.sperrer.p0t4t0sandwich.lppronouns.common.api;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.LPPronouns;

public class LPPronounsProvider {
    private static LPPronouns instance = null;

    /**
     * Get the instance of AMPServerManager
     * @return The instance of AMPServerManager
     */
    public static LPPronouns get() {
        if (instance == null) {
            throw new NotLoadedException();
        }
        return instance;
    }

    /**
     * DO NOT USE THIS METHOD, IT IS FOR INTERNAL USE ONLY
     * @param instance: The instance of LPPronouns
     */
    public static void register(LPPronouns instance) {
        LPPronounsProvider.instance = instance;
    }

    /**
     * Throw this exception when the API hasn't loaded yet, or you don't have the LPPronouns plugin installed.
     */
    private static final class NotLoadedException extends IllegalStateException {
        private static final String MESSAGE = "The API hasn't loaded yet, or you don't have the AMPServerManager plugin installed.";

        NotLoadedException() {
            super(MESSAGE);
        }
    }
}
