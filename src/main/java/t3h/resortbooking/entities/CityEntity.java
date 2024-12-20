package t3h.resortbooking.entities;

import lombok.Data;

import java.util.List;

import javax.persistence.*;

@Entity
@Data
@Table(name = "city", schema = "resort_booking")
public class CityEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    @Column(name = "country_id")
    private Long countryId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "image_name")
    private String imageName;

    @OneToMany()
    @JoinColumn(name = "city_id")
    List<ResortEntity> resortEntityList;
}
