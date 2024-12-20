package t3h.resortbooking.controller.adminEndPoint.search;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import t3h.resortbooking.service.CityService;
import t3h.resortbooking.utils.FileUtils;

@RestController
public class SearchController {
    @Autowired
    CityService cityService;

    @Autowired
    FileUtils fileUtils;

    @GetMapping("/city/search")
    @ResponseBody
    public ResponseEntity<?> getAllCityInVietnam(Principal principal) {
        Object p = cityService.getAllCityByKeyword("");
        return ResponseEntity.ok(p);
    }

    @GetMapping("/city/search/{keyword}")
    @ResponseBody
    public ResponseEntity<?> getAllCityByKeyword(@PathVariable("keyword") String keyword, Principal principal) {
        Object p = cityService.getAllCityByKeyword(keyword);
        return ResponseEntity.ok(p);
    }
}
