package fils.licenta.ioana.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cities")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cities_gen")
    @SequenceGenerator(name = "seq_cities_gen", sequenceName = "seq_cities", allocationSize = 1)
    private Long id;

    private String name;

    private String description;
}
