package t3h.resortbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import t3h.resortbooking.entities.ExtensionEntity;

import java.util.List;

public interface ExtensionRepository extends JpaRepository<ExtensionEntity, Long> {
    List<ExtensionEntity> findAllByName(String name);

    ExtensionEntity findFirstById(Long id);

    @Modifying
    void deleteExtensionEntityById(Long id);
}
