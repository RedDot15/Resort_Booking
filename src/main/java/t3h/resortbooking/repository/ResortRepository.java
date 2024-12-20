package t3h.resortbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import t3h.resortbooking.entities.ResortEntity;

import java.util.List;

public interface ResortRepository extends JpaRepository<ResortEntity, Long> {
    List<ResortEntity> findAllByName(String name);

    List<ResortEntity> findAllByCityIdAndNameContaining(Long id, String keyword);

    List<ResortEntity> findAllByCityId(Long id);

    ResortEntity findFirstById(Long id);

    List<ResortEntity> findAllByIdIsNot(Long id);

    @Modifying
    void deleteResortEntityById(Long id);

}
