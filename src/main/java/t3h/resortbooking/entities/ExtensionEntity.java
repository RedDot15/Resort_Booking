package t3h.resortbooking.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "extension", schema = "resort_booking")
public class ExtensionEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    @Column(name = "name")
    private String name;


}
