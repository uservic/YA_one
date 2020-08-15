package com.vic.decoder.decoder;

import java.util.List;
import java.util.Map;

public interface Decoder <K, V> {
    List<String> decode(Map<K, V> data);
}
