package t3h.resortbooking.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "resortimage", schema = "resort_booking" )
public class ResortimageEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "resort_id")
    private Long resortId;


}
