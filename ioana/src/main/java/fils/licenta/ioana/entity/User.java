package fils.licenta.ioana.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_users_gen")
    @SequenceGenerator(name = "seq_users_gen", sequenceName = "seq_users", allocationSize = 1)
    private Long id;

    private String name;

    private String username;

    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    private Type type;

    public enum Type {
        TOURIST, COMPANY
    }
}
