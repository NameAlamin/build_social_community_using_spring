package com.controller;

import com.Role;
import com.config.Properties;
import com.config.Utils;
import com.dao.LocationDao;
import com.dao.StatusDao;
import com.dao.UserDao;
import com.dto.UserDto;
import com.entity.Attachment;
import com.entity.Location;
import com.entity.Status;
import com.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private LocationDao locationDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/create")
    public String showUserForm(Model model){
        UserDto userDto = new UserDto();
        model.addAttribute(userDto);
        List<Location> locations = locationDao.getAll();
        List<String> locationList = new ArrayList<>();
        for (Location location:locations) {
            locationList.add(location.getLocationName());
        }

        List<String> roleList = Stream.of(Role.values())
                .map(Enum::name)
                .collect(Collectors.toList());

        model.addAttribute("roleList", roleList);

        model.addAttribute("locationList",locationList);
        return "user/create";
    }
    @PostMapping("/store")
    public String storeUser(Model model, @ModelAttribute("userDto")UserDto userDto, @RequestParam("image") MultipartFile file){
      /*
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        userDao.store(user);
        model.addAttribute("user",user);
        return "user/show";
      */

        Attachment attachment = Utils.saveFile(file, Properties.USER_FOLDER);

        Location location = locationDao.getByName(userDto.getLocation());
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(Role.valueOf(userDto.getRole()));

        user.setLocation(location);
        user.setAttachment(attachment);
        userDao.store(user);
//        location.getUserList().add(user);
//        locationDao.update(location);
        model.addAttribute("user",user);
        return "user/show";
    }
    @GetMapping("/list")
    public String showUserTableInBrowser(Model model){
        List<User> userList = userDao.getAll();
        model.addAttribute("userList",userList);
        return "user/list";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") String id){

        User user = userDao.getById(Long.parseLong(id));

        List<Location> locationList = locationDao.getAll();
        List<String> stringLocationList = new ArrayList<>();
        for(Location location: locationList){
            stringLocationList.add(location.getLocationName());
        }
        model.addAttribute("stringLocationList",stringLocationList);

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setLocation(user.getLocation().getLocationName());
        model.addAttribute("userDto",userDto);

        return "user/update";
    }

    @PostMapping("/update")
    public String updateNow(Model model, @ModelAttribute("userDto") UserDto userDto){
        Location location = locationDao.getByName(userDto.getLocation());

        Long id = userDto.getId();
//        It is creating a totally new user
//        Status status = new Status();
        User user = userDao.getById(id);
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setLocation(location);
        userDao.update(user);

        return "redirect:/user/list";
    }
    @GetMapping(value = "/delete/{id}")
    public String update(Model model, @PathVariable("id") Long id) {
        userDao.delete(id);
        return "redirect:/user/list";
    }



}
