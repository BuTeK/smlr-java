package by.home.butek.smlr.service;

import by.home.butek.smlr.model.Link;
import by.home.butek.smlr.model.repositories.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class DefaultKeyMapperService implements KeyMapperService {

    @Autowired
    private KeyConverterService converter;

    @Autowired
    private LinkRepository repo;

    @Transactional
    public String add(String link) {
        return converter.idToKey(repo.save(new Link(link)).getId());
    }

    public String getLink(String key) {
        Optional<Link> result = repo.findById(converter.keyToId(key));
        return result.map(Link::getText).orElse(null);
    }
}
