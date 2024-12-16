package edu.uph.ii.ppproject.controllers;

import edu.uph.ii.ppproject.domain.Building;
import edu.uph.ii.ppproject.repositories.BuildingRepository;
import edu.uph.ii.ppproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BuildingController {
    private BuildingRepository buildingRepository;
    private UserRepository userRepository;

    @Autowired
    public void setBuildingRepository(BuildingRepository buildingRepository){
        this.buildingRepository = buildingRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/buildings")
    public String buildingList (Model model){
        List<Building> buildings = buildingRepository.findAll();
        model.addAttribute("buildings", buildings);

        return "buildings/buildings";
    }

    @GetMapping("/buildingForm")
    public String buildingForm(Model model, @RequestParam(value = "Id", required = false) Long id){
        Building building = id != null ? buildingRepository.findById(id).orElse(new Building()) : new Building();

        model.addAttribute("building", building);
        model.addAttribute("managers", userRepository.findAll());

        return "buildings/buildingForm";
    }

    @RequestMapping(value = "/addBuilding", method = RequestMethod.POST)
    public String addBuilding(@ModelAttribute("building") Building building, @RequestParam("manager") Long managerId){
        building.setManager(userRepository.findById(managerId).orElse(null));
        if (building.getBuildingId() == null) building.setBuildingId(buildingRepository.count() + 1);

        buildingRepository.save(building);

        return "redirect:/buildings";
    }

    @GetMapping("deleteBuilding")
    public String deleteBuilding(@RequestParam("Id") Long id){
        buildingRepository.deleteById(id);

        return "redirect:/buildings";
    }

    @GetMapping("buildingInfo")
    public String buildingInfo(Model model, @RequestParam("Id") Long id){
        Building building = buildingRepository.findById(id).orElse(null);

        model.addAttribute("building", building);

        return "buildings/info";
    }
}
