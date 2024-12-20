package t3h.resortbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import t3h.resortbooking.entities.ResortimageEntity;

public interface ResortImageRepository extends JpaRepository<ResortimageEntity, Long> {
    @Modifying
    void deleteAllByResortId(Long resortId);

    ResortimageEntity findFirstByResortId(Long id);
}
