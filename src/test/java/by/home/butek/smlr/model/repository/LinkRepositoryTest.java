package by.home.butek.smlr.model.repository;

import by.home.butek.smlr.model.AbstractRepositoryTest;
import by.home.butek.smlr.model.Link;
import by.home.butek.smlr.model.repositories.LinkRepository;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

@DatabaseSetup(LinkRepositoryTest.DATASET)
@TestPropertySource(locations = "classpath:repositories-test.properties")
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = LinkRepositoryTest.DATASET)
public class LinkRepositoryTest extends AbstractRepositoryTest {

    static final String DATASET = "classpath:datasets/link-table.xml";
    private static final long LINK_NOT_FOUND = 1L;
    private static final long LINK_1_ID = 100500L;
    private static final String LINK_1_TEXT = "http://www.eveonline.com";
    private static final String LINK_TBS_TEXT = "http://wow.ru";

    @Autowired
    private LinkRepository repository;

    @Test
    public void findExisting() {
        Optional<Link> got = repository.findOne(LINK_1_ID);
        assertThat(got.isPresent(), equalTo(true));
        Link link = got.get();
        assertThat(link, equalTo(new Link(LINK_1_TEXT, LINK_1_ID)));
    }

    @Test
    public void findOneNotExisting() {
        Optional<Link> got = repository.findOne(LINK_NOT_FOUND);
        assertThat(got.isPresent(), equalTo(false));
    }

    @Test
    public void saveNew() {
        Link toBeSaved = new Link(LINK_TBS_TEXT);
        Link got = repository.save(toBeSaved);
        List<Link> list = repository.findAll();
        assertThat(list, hasSize(4));
        assertThat(got.getText(), equalTo(LINK_TBS_TEXT));
    }
}
