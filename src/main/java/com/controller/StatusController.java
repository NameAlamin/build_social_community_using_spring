package com.controller;

import com.config.Properties;
import com.config.Utils;
import com.dao.AttachmentDao;
import com.dao.LocationDao;
import com.dao.StatusDao;
import com.dto.StatusDto;
import com.entity.Attachment;
import com.entity.Location;
import com.entity.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/status")
public class StatusController {
    @Autowired
    private LocationDao locationDao;
    @Autowired
    private StatusDao statusDao;
    @Autowired
    private AttachmentDao attacmentDao;

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
    public String storeStatus(Model model, @ModelAttribute("statusDto") StatusDto statusDto, @RequestParam("images") MultipartFile[] files){
        Location location = locationDao.getByName(statusDto.getLocation());

        List<Attachment> attachmentList = new ArrayList<>();
        for (MultipartFile file : files) {
            Attachment attachment = Utils.saveFile(file, Properties.STATUS_FOLDER);
            if (attachment != null) {
                attachmentList.add(attachment);
            }
        }
        attacmentDao.insertBulk(attachmentList);

        Status status = new Status();
        status.setTitle(statusDto.getTitle());
        status.setDescription(statusDto.getDescription());
        status.setPrivacy(statusDto.getPrivacy());
        status.setLocation(location);
        status.setStatusAttachmentList(attachmentList);

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

    @GetMapping(value = "/update/{id}")
    public String update(Model model,  @PathVariable(value = "id") String id){

        Status status = statusDao.getById(Long.parseLong(id));

//        List<Attachment> attachmentList = attachmentDAO.getAttachmentList();

        List<Location> locationList = locationDao.getAll();
        List<String> stringLocationList = new ArrayList<>();

        for(Location location: locationList){
            stringLocationList.add(location.getLocationName());
        }
        List<String> privacyList = new ArrayList<>(List.of("public","private"));
        model.addAttribute("privacyList",privacyList);
        model.addAttribute("stringLocationList", stringLocationList);

        StatusDto statusDto = new StatusDto();
        statusDto.setId(status.getId());
        statusDto.setTitle(status.getTitle());
        statusDto.setDescription(status.getDescription());
        statusDto.setPrivacy(status.getPrivacy());
        statusDto.setLocation(status.getLocation().getLocationName());
//        StatusDto.setAttachmentPath(attachmentList);

        model.addAttribute("statusDto", statusDto);

        return "status/update";
    }

    @PostMapping(value = "/update")
    public String postUpdate(Model model, @ModelAttribute("statusDto") StatusDto statusDto ){
        Location location = locationDao.getByName(statusDto.getLocation());

        Long id = statusDto.getId();
//        It is creating a totally new user
//        Status status = new Status();
        Status status = statusDao.getById(id);
        status.setTitle(statusDto.getTitle());
        status.setDescription(statusDto.getDescription());
        status.setPrivacy(statusDto.getPrivacy());
        status.setLocation(location);
//        status.setUser(userDTO.getUser().getUserName);
        statusDao.update(status);

        location.getStatusList().add(status);
        locationDao.update(location);

        return "redirect:/status/list";
    }

    @GetMapping(value = "/delete/{id}")
    public String update(Model model, @PathVariable("id") Long id) {
        statusDao.delete(id);
        return "redirect:/status/list";
    }
}
