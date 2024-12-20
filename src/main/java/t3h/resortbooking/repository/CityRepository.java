package t3h.resortbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import t3h.resortbooking.entities.CityEntity;

import java.util.List;

public interface CityRepository extends JpaRepository<CityEntity, Long> {
    List<CityEntity> findAllByName(String name);

    List<CityEntity> findAllByCountryId(Long countryId);

    List<CityEntity> findAllByCountryIdIsNot(Long countryId);

    CityEntity findFirstById(Long id);

    @Modifying
    void deleteCityEntityById(Long id);
}
