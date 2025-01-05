package edu.uph.ii.ppproject.controllers;

import edu.uph.ii.ppproject.domain.User;
import edu.uph.ii.ppproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    private UserRepository userRepository;
    private JavaMailSender mailSender;

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Autowired
    public void setMailSender(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    @GetMapping("/users")
    public String userList (Model model){
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);

        return "users/users";
    }

    @GetMapping("/userForm")
    public String userForm(Model model, @RequestParam(value = "Id", required = false) Long id){
        User user = id != null ? userRepository.findById(id).orElse(new User()) : new User();

        model.addAttribute("user", user);

        return "users/userForm";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user){
        userRepository.save(user);
        sendSimpleMessage(user.getEmail(), "aktywuj konto", "Kliknij w link, aby aktywować konto: " + "http://localhost:8080/activate?code=" + user.getActivationCode());

        return "redirect:/users";
    }

    @GetMapping("deleteUser")
    public String deleteUser(@RequestParam("Id") Long id){
        userRepository.deleteById(id);

        return "redirect:/users";
    }

    @GetMapping("/userInfo")
    public String userInfo(Model model, @RequestParam("Id") Long id){
        User user = userRepository.getReferenceById(id);

        model.addAttribute("user", user);

        return "users/info";
    }

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("kari68@ethereal.email");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    @GetMapping("/activate")
    public String activateAccount(@RequestParam("code") String code) {
        User user = userRepository.findByActivationCode(code);

        if (user != null && !user.isEnabled()) {
            user.setEnabled(true);
            userRepository.save(user);
            return "users/activationSuccess";
        }
        return "users/activationError";
    }
}
