package t3h.resortbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import t3h.resortbooking.entities.GenderEntity;

public interface GenderRepository extends JpaRepository<GenderEntity, Long> {
    @Modifying
    void deleteGenderEntityById(Long id);
}
