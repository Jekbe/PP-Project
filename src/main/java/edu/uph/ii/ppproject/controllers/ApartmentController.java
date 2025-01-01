package edu.uph.ii.ppproject.controllers;

import edu.uph.ii.ppproject.domain.Apartment;
import edu.uph.ii.ppproject.repositories.ApartmentRepository;
import edu.uph.ii.ppproject.repositories.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ApartmentController {
    private ApartmentRepository apartmentRepository;
    private BuildingRepository buildingRepository;

    @Autowired
    public void setApartmentRepository(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    @Autowired
    public void setBuildingRepository(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    @GetMapping("apartments")
    public String apartments(Model model, @RequestParam(value = "Id", required = false) Long id) {
        List<Apartment> apartments;
        if (id == null) apartments = apartmentRepository.findAll();
        else apartments = apartmentRepository.findApartmentByBuilding_BuildingId(id);

        model.addAttribute("apartments", apartments);

        return "apartments/apartments";
    }

    @GetMapping("apartmentForm")
    public String apartmentForm(Model model, @RequestParam(value = "Id", required = false) Long id) {
        Apartment apartment = id != null ? apartmentRepository.findById(id).orElse(new Apartment()) : new Apartment();

        model.addAttribute("apartment", apartment);
        model.addAttribute("buildings", buildingRepository.findAll());

        return "apartments/apartmentForm";
    }

    @RequestMapping("addApartment")
    public String addApartment(@ModelAttribute("apartment") Apartment apartment, @RequestParam("building") Long buildingId) {
        apartment.setBuilding(buildingRepository.findById(buildingId).orElse(null));
        if (apartment.getApartmentId() == null) apartment.setApartmentId(apartmentRepository.count() + 1);

        apartmentRepository.save(apartment);

        return "redirect:/apartments";
    }

    @GetMapping("deleteApartment")
    public String deleteApartment(@RequestParam("Id") Long id) {
        apartmentRepository.deleteById(id);

        return "redirect:/apartments";
    }

    @GetMapping("apartmentInfo")
    public String apartmentInfo(@RequestParam("Id") Long id){
        Apartment apartment = apartmentRepository.getReferenceById(id);

        return "apartments/info";
    }
}
