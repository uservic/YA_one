package com.vic.decoder.decoder;

import com.vic.decoder.LineType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StringDecoder implements Decoder<LineType, String> {

    public static final int ALPHABET_LENGTH = 26;
    public static final int FIRST_LETTER_ASCII = (int)'a'; // 97
    public static final int LAST_LETTER_ASCII = (int)'z'; // 122

    private static final List<String> result = new ArrayList<>();

    @Override
    public List<String> decode(Map<LineType, String> data) {
        // a abb bab abc
        // q bcc aza abc z def
        String[] allBase = data.get(LineType.BOOK_TEXT).split(" ");
        String[] allEncoded = data.get(LineType.ENCODED).split(",");

        for (String encoded : allEncoded) {
            for (String base : allBase) {
                if (hasSameLength(base, encoded) && baseFound(base, encoded)) {
                    break;
                }
            }
        }

        return result;
    }

    private boolean hasSameLength(String base, String encoded) {
        return base.length() == encoded.length();
    }

    private boolean baseFound(String base, String encoded) {
        if (base.length() == 1) {
            return result.add(base);
        }

        int shift = getShift(encoded, base);
        if (isEqualsIfShifted(encoded, base, shift)) {
            return result.add(base);
        }

        return false;
    }

    private int getShift(String encoded, String base) {
        return encoded.charAt(0) - base.charAt(0);
    }

    private boolean isEqualsIfShifted(String encoded, String base, int shift) {
        for (int i = 0; i < encoded.length(); i++) {
            if (normalize(base.charAt(i), shift) != encoded.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    private char normalize(char ch, int shift) {
        if (shift >= 0) {
            return (char) (((ch - FIRST_LETTER_ASCII + shift) % ALPHABET_LENGTH) + FIRST_LETTER_ASCII);
        } else {
            return (char) (LAST_LETTER_ASCII - ((LAST_LETTER_ASCII - ch - shift) % ALPHABET_LENGTH));
        }
    }
}
