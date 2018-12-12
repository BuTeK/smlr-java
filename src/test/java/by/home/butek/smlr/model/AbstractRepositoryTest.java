package by.home.butek.smlr.model;

import by.home.butek.smlr.SmlrApplication;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:repositories-test.properties")
@TestExecutionListeners({DbUnitTestExecutionListener.class, DependencyInjectionTestExecutionListener.class})
@SpringBootTest(classes = SmlrApplication.class)
@DirtiesContext
@Transactional
abstract public class AbstractRepositoryTest {
}
