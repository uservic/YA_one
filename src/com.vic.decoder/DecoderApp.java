package com.vic.decoder;

import com.vic.decoder.decoder.Decoder;
import com.vic.decoder.decoder.StringDecoder;
import com.vic.decoder.utils.DecoderUtils;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class DecoderApp {

    private Map<LineType, String> data = new EnumMap<>(LineType.class);


    public void start() {
        init();
        List<String> result = doWork(data);
        printResult(result);
    }

    private void init() {
        prepareData(data);

    }

    private void prepareData(Map<LineType, String> data) {
        String resultString = DecoderUtils.readAndPrepareData(ConfigHandler.getSource());
        DecoderUtils.parse(resultString, data);
    }

    private List<String> doWork(Map<LineType, String> data) {
        return getDecoder().decode(data);
    }

    private Decoder<LineType, String> getDecoder() {
        return new StringDecoder();
    }

    private void printResult(List<String> result) {
        result.forEach(System.out::println);
    }
}
