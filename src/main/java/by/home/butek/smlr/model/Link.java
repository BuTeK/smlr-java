package by.home.butek.smlr.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "links")
@Data
@AllArgsConstructor
public class Link {

    private String text = "";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "links_sequence")
    @SequenceGenerator(name = "links_sequence", sequenceName = "links_seq", allocationSize = 5)
    private Long id;

    public Link(String text) {
        this.text = text;
    }
}
