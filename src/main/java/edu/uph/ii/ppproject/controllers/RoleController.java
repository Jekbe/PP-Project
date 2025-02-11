package edu.uph.ii.ppproject.controllers;

import edu.uph.ii.ppproject.domain.Role;
import edu.uph.ii.ppproject.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RoleController {
    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @GetMapping("roles")
    public String roleList(Model model){
        List<Role> roles = roleRepository.findAll();

        model.addAttribute("roles", roles);

        return "roles/roles";
    }

    @GetMapping("roleInfo")
    public String roleInfo(Model model, @RequestParam("Id") Long id){
        Role role = roleRepository.getReferenceById(id);

        model.addAttribute("role", role);

        return "roles/info";
    }
}
