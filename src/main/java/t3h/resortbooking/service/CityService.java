package t3h.resortbooking.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import t3h.resortbooking.dto.CityDto;
import t3h.resortbooking.entities.CityEntity;
import t3h.resortbooking.repository.CityRepository;
import t3h.resortbooking.repository.ResortExtensionRepository;
import t3h.resortbooking.repository.ResortRepository;
import t3h.resortbooking.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CityService {
    @Autowired
    CityRepository cityRepository;

    @Autowired
    FileUtils fileUtils;

    @Autowired
    ResortRepository resortRepository;

    @Autowired
    ResortExtensionRepository resortExtensionRepository;

    public List<CityDto> getAll(String name) {
        List<CityDto> cityDtoList = new ArrayList<>();
        List<CityEntity> cityEntityList;
        if (StringUtils.isEmpty(name)) {
            cityEntityList = cityRepository.findAll();
        } else {
            cityEntityList = cityRepository.findAllByName(name);
        }
        for (CityEntity cityEntity : cityEntityList) {
            CityDto cityDto = new CityDto();
            BeanUtils.copyProperties(cityEntity, cityDto);
            cityDtoList.add(cityDto);
        }
        return cityDtoList;
    }

    public List<CityDto> getAllByCountryId(Long countryId) {
        List<CityDto> cityDtoList = new ArrayList<>();
        List<CityEntity> cityEntityList;
        if (StringUtils.isEmpty(countryId)) {
            cityEntityList = cityRepository.findAllByCountryId(countryId);
        } else {
            cityEntityList = cityRepository.findAllByCountryId(countryId);
        }
        for (CityEntity cityEntity : cityEntityList) {
            CityDto cityDto = new CityDto();
            BeanUtils.copyProperties(cityEntity, cityDto);
            cityDtoList.add(cityDto);
        }
        return cityDtoList;
    }

    public List<CityDto> getAllByCountryIdIsNot(Long countryId) {
        List<CityDto> cityDtoList = new ArrayList<>();
        List<CityEntity> cityEntityList;
        if (StringUtils.isEmpty(countryId)) {
            cityEntityList = cityRepository.findAllByCountryIdIsNot(countryId);
        } else {
            cityEntityList = cityRepository.findAllByCountryIdIsNot(countryId);
        }
        for (CityEntity cityEntity : cityEntityList) {
            CityDto cityDto = new CityDto();
            BeanUtils.copyProperties(cityEntity, cityDto);
            cityDtoList.add(cityDto);
        }
        return cityDtoList;
    }

    public CityDto getDetailById(Long id) {
        CityDto cityDto = new CityDto();
        CityEntity cityEntity = cityRepository.findFirstById(id);
        if (cityEntity != null)
            BeanUtils.copyProperties(cityEntity, cityDto);
        else
            return null;
        return cityDto;
    }

    public List<CityDto> getAllCityByKeyword(String keyword) {
        if (keyword.equals("")) {
            return getAllByCountryId((long) 1);
        }
        List<CityDto> cityDtoList = new ArrayList<>();
        List<CityEntity> cityEntityList = new ArrayList<>();
        List<CityEntity> allCityRepository = cityRepository.findAll();
        for (CityEntity cityEntity : allCityRepository) {
            if (cityEntity.getName().toUpperCase().trim().contains(keyword.toUpperCase().trim())) {
                cityEntityList.add(cityEntity);
            }
        }
        for (CityEntity cityEntity : cityEntityList) {
            CityDto cityDto = new CityDto();
            BeanUtils.copyProperties(cityEntity, cityDto);
            cityDtoList.add(cityDto);
        }
        return cityDtoList;
    }

    public CityDto add(CityDto cityDto) {
        CityEntity cityEntity = new CityEntity();
        BeanUtils.copyProperties(cityDto, cityEntity);
        // Lưu vào bảng product để Lấy thông tin primarykey (ID)
        cityRepository.save(cityEntity);
        cityDto.setId(cityEntity.getId());

        // LƯU ẢNH
        try {
            saveFile(cityDto);
        } catch (IOException e) {
            throw new RuntimeException("Tạo mới lỗi");
        }

        // LƯU TÊN ẢNH
        // lưu ảnh main
        cityEntity.setImageName(cityDto.getImageName());
        return cityDto;
    }

    public CityDto update(CityDto cityDto) {
        CityEntity cityEntity = cityRepository.findById(cityDto.getId()).get();
        BeanUtils.copyProperties(cityDto, cityEntity);
        cityRepository.save(cityEntity);

        fileUtils.cleanDir("city\\" + cityDto.getId());// xóa ảnh trong thư mục
        try {
            saveFile(cityDto);
        } catch (IOException e) {
            throw new RuntimeException("Tạo mới lỗi");
        }
        // lưu ảnh main
        cityEntity.setImageName(cityDto.getImageName());
        return cityDto;
    }

    public void delete(Long id) {
       cityRepository.deleteCityEntityById(id);
    }


    void saveFile(CityDto cityDto) throws IOException {
        if (cityDto.getFileImage() != null && !cityDto.getFileImage().isEmpty()) {// Lưu 1 file ảnh chính
            cityDto.setImageName(
                    fileUtils.saveFile(cityDto.getFileImage(), "city\\" + cityDto.getId() + "\\"));
        }

    }
}
