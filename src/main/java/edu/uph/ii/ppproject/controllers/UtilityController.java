package edu.uph.ii.ppproject.controllers;

import edu.uph.ii.ppproject.domain.Utility;
import edu.uph.ii.ppproject.repositories.UtilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UtilityController {
    private UtilityRepository utilityRepository;

    @Autowired
    public void setUtilityRepository(UtilityRepository utilityRepository) {
        this.utilityRepository = utilityRepository;
    }

    @GetMapping("utilities")
    public String utilities(Model model) {
        List<Utility> utilities = utilityRepository.findAll();

        model.addAttribute("utilities", utilities);

        return "utilities/utilities";
    }

    @GetMapping("utilityForm")
    public String utilityForm(Model model, @RequestParam(value = "Id", required = false) Long id) {
        Utility utility = id != null ? utilityRepository.findById(id).orElse(new Utility()) : new Utility();

        model.addAttribute("utility", utility);

        return "utilities/utilityForm";
    }

    @RequestMapping("addUtility")
    public String addUtility(@ModelAttribute("utility") Utility utility) {
        if (utility.getUtilityId() == null) utility.setUtilityId(utilityRepository.count() + 1);

        utilityRepository.save(utility);

        return "redirect:/utilities";
    }

    @GetMapping("deleteUtility")
    public String deleteUtility(@RequestParam("Id") Long id) {
        utilityRepository.deleteById(id);

        return "redirect:/utilities";
    }
}
