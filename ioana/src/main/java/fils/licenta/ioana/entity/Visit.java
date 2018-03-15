package fils.licenta.ioana.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "visits")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_visits_gen")
    @SequenceGenerator(name = "seq_visits_gen", sequenceName = "seq_visits", allocationSize = 1)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private City city;
}
