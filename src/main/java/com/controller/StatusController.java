package com.controller;

import com.dao.LocationDao;
import com.dao.StatusDao;
import com.dto.StatusDto;
import com.entity.Location;
import com.entity.Status;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/status")
public class StatusController {
    @Autowired
    private LocationDao locationDao;
    @Autowired
    private StatusDao statusDao;

    @GetMapping("/create")
    public String createStatus(Model model){
        StatusDto statusDto = new StatusDto();
        model.addAttribute("statusDto",statusDto);

        //set privacy list
        List<String> privacyList = new ArrayList<>(List.of("public","private"));
        model.addAttribute("privacyList",privacyList);

        // set location list dynamic way
        List<Location> locations = locationDao.getAll();
        List<String> locationList = new ArrayList<>();
        for (Location location:locations) {
            locationList.add(location.getLocationName());
        }
        model.addAttribute("locationList",locationList);

        return "status/create";
    }
    @PostMapping("/store")
    public String storeStatus(Model model, @ModelAttribute("statusDto") StatusDto statusDto){
        Location location = locationDao.getByName(statusDto.getLocation());
        Status status = new Status();
        status.setTitle(statusDto.getTitle());
        status.setDescription(statusDto.getDescription());
        status.setPrivacy(statusDto.getPrivacy());
        status.setLocation(location);
        statusDao.store(status);
        location.getStatusList().add(status);
        locationDao.update(location);
        model.addAttribute("status",status);

        return "status/show";
    }
    @GetMapping("/list")
    public String showStatusTableInBrowser(Model model){
        List<Status> statusList = statusDao.getAll();
        model.addAttribute("statusList",statusList);
        return "status/list";
    }
}
