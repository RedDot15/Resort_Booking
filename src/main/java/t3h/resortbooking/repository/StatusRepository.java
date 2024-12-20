package t3h.resortbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import t3h.resortbooking.entities.StatusEntity;

public interface StatusRepository extends JpaRepository<StatusEntity, Long> {
    @Modifying
    void deleteStatusEntityById(Long id);
}
