package by.home.butek.smlr.service;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DefaultKeyConverterService implements KeyConverterService {

    private static final char[] CHARS = "qwertyuiopasdfgQWERTYUIOPASDFG1234567890-_".toCharArray();
    private static final Map<Character, Integer> CHARACTER_MAP = new HashMap<>();

    public DefaultKeyConverterService() {
        for (int i = 0; i < CHARS.length; i++) {
            CHARACTER_MAP.put(CHARS[i], i);
        }
    }

    @Override
    public String idToKey(Long id) {
        Long n = id;
        StringBuilder builder = new StringBuilder();
        while (n != 0L) {
            builder.append(CHARS[(int) (n % CHARS.length)]);
            n /= CHARS.length;
        }
        return builder.toString();
    }

    @Override
    public Long keyToId(String key) {
        long result = 0L;
        Long multiplier = 1L;
        for (char aChar : key.toCharArray()) {
            result = result + CHARACTER_MAP.get(aChar) * multiplier;
            multiplier = multiplier * CHARS.length;
        }
        return result;
    }

}
