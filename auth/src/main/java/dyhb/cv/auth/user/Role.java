package dyhb.cv.auth.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;
    private String name;


    public enum Values {
        ADMIN(1L),
        BASIC(2L);

        long role_id;

        Values(long role_id) {
            this.role_id = role_id;
        }

        public long getRoleId() {
            return role_id;
        }
    }
}
