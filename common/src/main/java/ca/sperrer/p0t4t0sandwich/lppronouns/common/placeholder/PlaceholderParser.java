package ca.sperrer.p0t4t0sandwich.lppronouns.common.placeholder;

/**
 * The placeholder parser class.
 */
public class PlaceholderParser {
    private String input;

    /**
     * Create a new placeholder parser.
     * @param input The input string.
     */
    public PlaceholderParser(String input) {
        this.input = input;
    }

    /**
     * Parse a placeholder.
     * @param placeholder The placeholder to parse.
     * @param value The value to replace the placeholder with.
     * @return The placeholder parser.
     */
    public PlaceholderParser parseString(String placeholder, String value) {
        this.input = input.replace("%" + placeholder + "%", value);
        return this;
    }

    /**
     * Parse the section sign.
     * @return The placeholder parser.
     */
    public PlaceholderParser parseSectionSign() {
        this.input = input.replace("&", "§");
        return this;
    }

    /**
     * Strip the section sign from the string.
     * @return The string without the section sign.
     */
    public static String stripSectionSign(String s) {
        return s.replaceAll("§.", "");
    }

    public String getResult() {
        return input;
    }
}
