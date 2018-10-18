package by.home.butek.smlr.service;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class DefaultKeyConverterServiceTest {

    private KeyConverterService service = new DefaultKeyConverterService();

    @Test
    public void givenIdMustBeConvertableBothWays() {
        final Random rand = new Random();
        for(int i = 0; i < 10000; i++) {
            Long initialId = Math.abs(rand.nextLong());
            String key = service.idToKey(initialId);
            Long id = service.keyToId(key);
            Assert.assertEquals(initialId, id);
        }
    }
}
