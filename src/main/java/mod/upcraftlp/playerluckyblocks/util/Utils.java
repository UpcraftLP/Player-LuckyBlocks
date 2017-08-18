package mod.upcraftlp.playerluckyblocks.util;

import static net.minecraft.util.ChatAllowedCharacters.ILLEGAL_FILE_CHARACTERS;

import java.util.Random;

/**
 * (c)2017 UpcraftLP
 */
public class Utils {

    public static final Random RANDOM = new Random();

    /**
     * filters illegal file characters out of a String.
     */
    public static String filterChars(String input) {
        for (char c : ILLEGAL_FILE_CHARACTERS) {
            if(c == ILLEGAL_FILE_CHARACTERS[0]) continue; //don't compute the '/'
            input = input.replace(c, '_');
        }
        return input;
    }
}
