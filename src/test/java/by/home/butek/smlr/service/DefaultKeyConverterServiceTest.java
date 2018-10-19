package by.home.butek.smlr.service;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultKeyConverterServiceTest {

    private KeyConverterService service = new DefaultKeyConverterService();

    @Test
    public void givenIdMustBeConvertableBothWays() {
        final Random rand = new Random();
        for(int i = 0; i < 10000; i++) {
            Long initialId = Math.abs(rand.nextLong());
            String key = service.idToKey(initialId);
            Long id = service.keyToId(key);
            assertEquals(initialId, id);
        }
    }
}
