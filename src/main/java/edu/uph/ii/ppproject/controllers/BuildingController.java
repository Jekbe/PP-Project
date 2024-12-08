package edu.uph.ii.ppproject.controllers;

import edu.uph.ii.ppproject.domain.Building;
import edu.uph.ii.ppproject.repositories.BuildingRepository;
import edu.uph.ii.ppproject.repositories.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BuildingController {
    private BuildingRepository buildingRepository;
    private ManagerRepository managerRepository;

    @Autowired
    public void setBuildingRepository(BuildingRepository buildingRepository){
        this.buildingRepository = buildingRepository;
    }

    @Autowired
    public void setManagerRepository(ManagerRepository managerRepository){
        this.managerRepository = managerRepository;
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
        model.addAttribute("managers", managerRepository.findAll());

        return "buildings/buildingForm";
    }

    @RequestMapping(value = "/addBuilding", method = RequestMethod.POST)
    public String addBuilding(@ModelAttribute("building") Building building){
        building.setNumberOfApartments(0);
        buildingRepository.save(building);

        return "redirect:/buildings/buildings";
    }

    @GetMapping("deleteBuilding")
    public String deleteBuilding(Model model, @RequestParam(value = "Id") Long id){
        buildingRepository.deleteById(id);
        return "redirect:/buildings/buildings";
    }
}
