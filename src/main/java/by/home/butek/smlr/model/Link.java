package by.home.butek.smlr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "links")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Link {

    private String text = "";

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "links_sequence")
    @SequenceGenerator(name = "links_sequence", sequenceName = "links_seq")
    private Long id;

    public Link(String text) {
        this.text = text;
    }
}
