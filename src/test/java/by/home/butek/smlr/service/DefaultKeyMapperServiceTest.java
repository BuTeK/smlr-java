package by.home.butek.smlr.service;

import by.home.butek.smlr.model.Link;
import by.home.butek.smlr.model.repositories.LinkRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;

public class DefaultKeyMapperServiceTest {

    private static final String KEY = "aAbBcCbD";
    private static final String LINK_A = "http://google.com";
    private static final String LINK_B = "http://yahoo.com";
    private static final String KEY_A = "abc";
    private static final String KEY_B = "cde";

    private static final Long ID_A = 100000000L;
    private static final Long ID_B = 100000001L;

    private Link LINK_OBJ_A = new Link(LINK_A, ID_A);
    private Link LINK_OBJ_B = new Link(LINK_B, ID_B);

    @InjectMocks
    private KeyMapperService service = new DefaultKeyMapperService();

    @Mock
    private KeyConverterService converter;

    @Mock
    private LinkRepository repo;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        Mockito.when(converter.keyToId(KEY_A)).thenReturn(ID_A);
        Mockito.when(converter.idToKey(ID_A)).thenReturn(KEY_A);
        Mockito.when(converter.keyToId(KEY_B)).thenReturn(ID_B);
        Mockito.when(converter.idToKey(ID_B)).thenReturn(KEY_B);

        Mockito.when(repo.findById(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(repo.save(new Link(LINK_A))).thenReturn(LINK_OBJ_A);
        Mockito.when(repo.save(new Link(LINK_B))).thenReturn(LINK_OBJ_B);
        Mockito.when(repo.findById(ID_A)).thenReturn(Optional.of(LINK_OBJ_A));
        Mockito.when(repo.findById(ID_B)).thenReturn(Optional.of(LINK_OBJ_B));
    }

    @Test
    public void  clientCanAddLink() {
        final String keyA = service.add(LINK_A);
        assertEquals(LINK_A, service.getLink(keyA));
        final String keyB = service.add(LINK_B);
        assertEquals(LINK_B, service.getLink(keyB));
        assertNotEquals(keyA, keyB);
    }

    @Test
    public void  clientCanNotTakeLinkIfNotFoundIsService() {
        assertNull(service.getLink(KEY));
    }

}
