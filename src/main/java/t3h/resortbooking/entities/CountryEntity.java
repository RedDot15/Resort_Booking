package t3h.resortbooking.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "country", schema = "resort_booking")
public class CountryEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    @Column(name = "name")
    private String name;


}
