package by.home.butek.smlr.model.repository;

import by.home.butek.smlr.AbstractIntegrationTest;
import by.home.butek.smlr.model.Link;
import by.home.butek.smlr.model.repositories.LinkRepository;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DatabaseSetup(LinkIntegrationTest.DATASET)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = LinkIntegrationTest.DATASET)
class LinkIntegrationTest extends AbstractIntegrationTest {

    static final String DATASET = "classpath:datasets/link-table.xml";
    private static final long LINK_NOT_FOUND = 1L;
    private static final long LINK_1_ID = 100500L;
    private static final String LINK_1_TEXT = "http://www.eveonline.com";
    private static final String LINK_TBS_TEXT = "http://wow.ru";

    @Autowired
    private LinkRepository repository;

    @Test
    void findExisting() {
        Optional<Link> got = repository.findById(LINK_1_ID);
        assertThat(got.isPresent()).isTrue();
        Link link = got.get();
        assertThat(link).isEqualTo(new Link(LINK_1_TEXT, LINK_1_ID));
    }

    @Test
    void findOneNotExisting() {
        Optional<Link> got = repository.findById(LINK_NOT_FOUND);
        assertThat(got.isPresent()).isFalse();
    }

    @Test
    void saveNew() {
        Link toBeSaved = new Link(LINK_TBS_TEXT);
        Link got = repository.save(toBeSaved);
        List<Link> list = repository.findAll();
        assertThat(list).hasSize(4);
        assertThat(got.getText()).isEqualTo(LINK_TBS_TEXT);
    }
}
