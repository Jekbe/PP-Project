package edu.uph.ii.ppproject.controllers;

import edu.uph.ii.ppproject.domain.Apartment;
import edu.uph.ii.ppproject.domain.Fee;
import edu.uph.ii.ppproject.repositories.ApartmentRepository;
import edu.uph.ii.ppproject.repositories.FeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FeeController {
    private FeeRepository feeRepository;
    private ApartmentRepository apartmentRepository;

    @Autowired
    public void setFeeRepository(FeeRepository feeRepository) {
        this.feeRepository = feeRepository;
    }
    @Autowired
    public void setApartmentRepository(ApartmentRepository apartmentRepository){
        this.apartmentRepository = apartmentRepository;
    }

    @GetMapping("fees")
    public String feeList(Model model) {
        List<Fee> fees = feeRepository.findAll();

        model.addAttribute("fees", fees);

        return "fees/fees";
    }

    @GetMapping("feeForm")
    public String feeForm(Model model, @RequestParam(value = "Id", required = false) Long id){
        Fee fee = id != null ? feeRepository.findById(id).orElse(new Fee()) :new Fee();
        List<Apartment> apartments = apartmentRepository.findAll();

        model.addAttribute("fee", fee);
        model.addAttribute("apartments", apartments);

        return "fees/feeForm";
    }

    @RequestMapping("addFee")
    public String addFee(@ModelAttribute("fee") Fee fee){
        feeRepository.save(fee);

        return "redirect:/fees";
    }

    @GetMapping("deleteFee")
    public String deleteFee(@RequestParam("Id") Long id){
        feeRepository.deleteById(id);

        return "redirect:/fees";
    }

    @GetMapping("feeInfo")
    public String feeInfo(Model model, @RequestParam("Id") Long id){
        Fee fee = feeRepository.getReferenceById(id);

        model.addAttribute("fee", fee);

        return "fees/info";
    }
}
