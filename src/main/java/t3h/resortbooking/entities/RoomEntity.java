package t3h.resortbooking.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "room", schema = "resort_booking" )
public class RoomEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "image_name")
    private String imageName;
    @Basic
    @Column(name = "price")
    private Integer price;
    @Basic
    @Column(name = "resort_id")
    private Long resortId;
    @Basic
    @Column(name = "status")
    private String status;


}
