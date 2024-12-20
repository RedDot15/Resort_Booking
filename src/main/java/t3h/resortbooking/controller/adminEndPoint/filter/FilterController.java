package t3h.resortbooking.controller.adminEndPoint.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import t3h.resortbooking.params.ExtensionCityParam;
import t3h.resortbooking.service.CityService;
import t3h.resortbooking.service.ResortExtensionService;
import t3h.resortbooking.utils.FileUtils;

@RestController
public class FilterController {
    @Autowired
    CityService cityService;

    @Autowired
    FileUtils fileUtils;

    @Autowired
    ResortExtensionService resortExtensionService;

    @PostMapping("/resort/filter")
    public ResponseEntity<?> getAllResortByEntensionIdAndCityId(
            @RequestBody ExtensionCityParam extensionCity) {
        Object res = resortExtensionService.getAllResortByEntensionIdAndCityId(extensionCity);
        return ResponseEntity.ok(res);
    }
}
