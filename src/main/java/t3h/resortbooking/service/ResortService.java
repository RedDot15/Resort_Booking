package t3h.resortbooking.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import t3h.resortbooking.dto.ResortDto;
import t3h.resortbooking.entities.*;
import t3h.resortbooking.repository.ResortExtensionRepository;
import t3h.resortbooking.repository.ResortImageRepository;
import t3h.resortbooking.repository.ResortRepository;
import t3h.resortbooking.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class ResortService {
    @Autowired
    ResortRepository resortRepository;

    @Autowired
    ResortExtensionRepository resortExtensionRepository;

    @Autowired
    ResortImageRepository resortImageRepository;

    @Autowired
    FileUtils fileUtils;

    public List<ResortDto> getAll(String name) {
        List<ResortDto> resortDtoList = new ArrayList<>();
        List<ResortEntity> resortEntityList;
        if (StringUtils.isEmpty(name)) {
            resortEntityList = resortRepository.findAll();
        } else {
            resortEntityList = resortRepository.findAllByName(name);
        }
        for (ResortEntity resortEntity : resortEntityList) {
            ResortDto resortDto = new ResortDto();
            BeanUtils.copyProperties(resortEntity, resortDto);
            resortDtoList.add(resortDto);
        }
        return resortDtoList;
    }

    public List<ResortDto> getAllByCityIdAndNameContaining(Long id, String keyword) {
        List<ResortDto> resortDtoList = new ArrayList<>();
        List<ResortEntity> resortEntityList;
        if (StringUtils.isEmpty(keyword)) {
            resortEntityList = resortRepository.findAllByCityId(id);
        } else {
            resortEntityList = resortRepository.findAllByCityIdAndNameContaining(id, keyword);
        }
        Collections.sort(resortEntityList, new Comparator<ResortEntity>() {
            @Override
            public int compare(ResortEntity resort1, ResortEntity resort2) {
                return resort2.getPoint() - resort1.getPoint();
            }
        });
        for (ResortEntity resortEntity : resortEntityList) {
            ResortDto resortDto = new ResortDto();
            BeanUtils.copyProperties(resortEntity, resortDto);
            resortDtoList.add(resortDto);
        }
        return resortDtoList;
    }

    public ResortDto getDetailById(Long id) {
        ResortDto resortDto = new ResortDto();
        ResortEntity resortEntity = resortRepository.findFirstById(id);
        if (resortEntity != null)
            BeanUtils.copyProperties(resortEntity, resortDto);
        else
            return null;
        return resortDto;
    }

    public List<ResortDto> getAllByIdIsNot(Long id, Long cityId) {
        List<ResortDto> resortDtoList = new ArrayList<>();
        List<ResortEntity> resortEntityList = resortRepository.findAllByIdIsNot(id);
        org.springframework.cglib.core.CollectionUtils.filter(resortEntityList,
                o -> ((ResortEntity) o).getCityId().equals(cityId));
        Collections.sort(resortEntityList, new Comparator<ResortEntity>() {
            @Override
            public int compare(ResortEntity resort1, ResortEntity resort2) {
                return resort2.getPoint() - resort1.getPoint();
            }
        });
        for (ResortEntity resortEntity : resortEntityList) {
            ResortDto resortDto = new ResortDto();
            BeanUtils.copyProperties(resortEntity, resortDto);
            resortDtoList.add(resortDto);
        }
        return resortDtoList;
    }

    public ResortDto add(ResortDto resortDto) throws IOException {
        ResortEntity resortEntity = new ResortEntity();
        BeanUtils.copyProperties(resortDto, resortEntity);
        // Lưu vào bảng product để Lấy thông tin primarykey (ID)
        resortRepository.save(resortEntity);
        resortDto.setId(resortEntity.getId());

        // LƯU EXTENSION
        for (Long x : resortDto.getExtensionListId()) {
            ResortExtensionEntity resortExtensionEntity = new ResortExtensionEntity();
            resortExtensionEntity.setResortId(resortDto.getId());
            resortExtensionEntity.setExtensionEntityId(x);
            resortExtensionRepository.save(resortExtensionEntity);
        }

        // LƯU TÊN ẢNH
        // Lưu mhiều ảnh
        if (!CollectionUtils.isEmpty(resortDto.getMultipartFileList())) {
            for (MultipartFile multipartFile : resortDto.getMultipartFileList()) {
                ResortimageEntity resortimageEntity = new ResortimageEntity();
                resortimageEntity.setName(
                        fileUtils.saveFile(multipartFile, "resort\\" + resortDto.getId() + "\\detail\\"));
                resortimageEntity.setResortId(resortDto.getId());
                resortImageRepository.save(resortimageEntity);
            }
        }
        return resortDto;
    }

    public ResortDto update(ResortDto resortDto) throws IOException {
        ResortEntity resortEntity = resortRepository.findById(resortDto.getId()).get();
        resortDto.setResortimageEntityList(resortEntity.getResortimageEntityList());
        BeanUtils.copyProperties(resortDto, resortEntity);
        resortRepository.save(resortEntity);

        resortExtensionRepository.deleteAllByResortId(resortDto.getId());
        // LƯU EXTENSION
        for (Long x : resortDto.getExtensionListId()) {
            ResortExtensionEntity resortExtensionEntity = new ResortExtensionEntity();
            resortExtensionEntity.setResortId(resortDto.getId());
            resortExtensionEntity.setExtensionEntityId(x);
            resortExtensionRepository.save(resortExtensionEntity);
        }

        fileUtils.cleanDir("resort\\" + resortDto.getId() + "\\detail\\");// xóa ảnh trong thư mục
        resortImageRepository.deleteAllByResortId(resortDto.getId());// xóa nhiều ảnh trong database

        // Lưu nhiều ảnh
        if (!CollectionUtils.isEmpty(resortDto.getMultipartFileList())) {
            for (MultipartFile multipartFile : resortDto.getMultipartFileList()) {
                ResortimageEntity resortimageEntity = new ResortimageEntity();
                resortimageEntity.setName(
                        fileUtils.saveFile(multipartFile, "resort\\" + resortDto.getId() + "\\detail\\"));
                resortimageEntity.setResortId(resortDto.getId());
                resortImageRepository.save(resortimageEntity);
            }
        }
        return resortDto;
    }

    public void delete(Long id) {
        resortRepository.deleteResortEntityById(id);
    }
}
