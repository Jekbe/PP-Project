package edu.uph.ii.ppproject.controllers;

import edu.uph.ii.ppproject.domain.Fee;
import edu.uph.ii.ppproject.repositories.FeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FeeController {
    private FeeRepository feeRepository;

    @Autowired
    public void setFeeRepository(FeeRepository feeRepository) {
        this.feeRepository = feeRepository;
    }

    @GetMapping("/fees")
    public String feeList(Model model) {
        List<Fee> fees = feeRepository.findAll();

        model.addAttribute("fees", fees);

        return "fees/fees";
    }
}
