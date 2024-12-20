package t3h.resortbooking.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "resort_extension", schema = "resort_booking" )
public class ResortExtensionEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    @Column(name = "resort_id")
    private Long resortId;
//    @Basic
//    @Column(name = "extension_id")
//    private Long extensionId;

    @ManyToOne
    @JoinColumn(name = "extension_id")
    ExtensionEntity extensionEntity;

    public void setExtensionEntityId(Long id){
        extensionEntity = new ExtensionEntity();
        extensionEntity.setId(id);
    }
}
