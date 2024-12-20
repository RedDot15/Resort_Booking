package t3h.resortbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import t3h.resortbooking.entities.ResortExtensionEntity;

import java.util.List;

public interface ResortExtensionRepository extends JpaRepository<ResortExtensionEntity, Long> {
    @Modifying
    void deleteAllByResortId(Long resortId);

    List<ResortExtensionEntity> findAllByResortId(Long id);
}
