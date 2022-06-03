package com.controller;

import com.dao.LocationDao;
import com.dto.LocationDto;
import com.entity.Location;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private LocationDao locationDao;

    @GetMapping("/create")
    public String locationForm(Model model){
        LocationDto locationDto = new LocationDto();
        model.addAttribute("locationDto",locationDto);
        return "/location/create";
    }

    @PostMapping("/store")
    public String saveLocation(Model model, @ModelAttribute("locationDto") LocationDto locationDto){
        Location location = new Location();
        BeanUtils.copyProperties(locationDto,location);
        locationDao.store(location);
        model.addAttribute("location",location);
        return "location/show";
    }
    @GetMapping("/list")
    public String showLocationList(Model model){
        List<Location> locationList = locationDao.getAll();
        model.addAttribute("locationList",locationList);
        return "location/list";
    }
}
