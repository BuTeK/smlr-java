package by.home.butek.smlr;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:repositories-test.properties")
@WebAppConfiguration
class SmlrApplicationTests {

    @Test
    void contextLoads() {
    }
}
