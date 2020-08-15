package com.vic.decoder.utils;

import com.vic.decoder.LineType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class DecoderUtils {
    private static final String DELIMITER = "/";
    public static final String ENCODED_DELIMITER = ",";

    private DecoderUtils() {
    }

    public static String readAndPrepareData(String source) {
        return readFrom(source);
    }

    private static String readFrom(String file) {
        StringBuilder sb = new StringBuilder();
        try (InputStream is = DecoderUtils.class.getClassLoader().getResourceAsStream(file);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append(DELIMITER);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();

    }

    public static void parse(String combinedString, Map<LineType, String> data) {
        String[] split = combinedString.split(DELIMITER);

        for (int i = 0; i < split.length; i++) {
            switch (i) {
                case 0:
                    data.put(LineType.BOOK_TEXT, split[i]);
                    break;
                case 1:
                    data.put(LineType.WORD_NUM, split[i]);
                    break;
                default:
                    compute(split[i], data);
            }
        }
    }

    private static void compute(String string, Map<LineType, String> data) {
        data.compute(LineType.ENCODED, (k, v) -> (v == null) ? string : v + ENCODED_DELIMITER + string);
    }
}
