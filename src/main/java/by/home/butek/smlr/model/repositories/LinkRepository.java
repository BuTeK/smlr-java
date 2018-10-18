package by.home.butek.smlr.model.repositories;

import by.home.butek.smlr.model.Link;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface LinkRepository extends Repository<Link, Long> {

    Optional<Link> findOne(Long id);
    Link save(Link link);
    List<Link> findAll();

}
