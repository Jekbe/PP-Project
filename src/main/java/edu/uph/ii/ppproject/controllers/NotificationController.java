package edu.uph.ii.ppproject.controllers;

import edu.uph.ii.ppproject.domain.Notification;
import edu.uph.ii.ppproject.domain.User;
import edu.uph.ii.ppproject.repositories.NotificationRepository;
import edu.uph.ii.ppproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class NotificationController {
    private NotificationRepository notificationRepository;
    private UserRepository userRepository;

    @Autowired
    public void setNotificationRepository(NotificationRepository notificationRepository){
        this.notificationRepository = notificationRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("notifications")
    public String notificationList(Model model){
        List<Notification> notifications = notificationRepository.findAll();

        model.addAttribute("notifications", notifications);

        return "notifications/notifications";
    }

    @GetMapping("notifictionForm")
    public String notifictionForm(Model model, @RequestParam(value = "Id", required = false) Long id){
        Notification notification = id != null ? notificationRepository.findById(id).orElse(new Notification()) : new Notification();
        List<User> users = userRepository.findAll();

        model.addAttribute("notification", notification);
        model.addAttribute("users", users);

        return "notifications/notificationForm";
    }

    @RequestMapping("addNotification")
    public String addNotification(@ModelAttribute("notification") Notification notification){
        notificationRepository.save(notification);

        return "redirect:/notifications";
    }

    @GetMapping("deleteNotification")
    public String deleteNotification(@RequestParam("Id") Long id){
        notificationRepository.deleteById(id);

        return "redirect:/notifications";
    }

    @GetMapping("notificationInfo")
    public String notificationInfo(Model model, @RequestParam("Id") Long id){
        Notification notification = notificationRepository.getReferenceById(id);

        model.addAttribute("notification", notification);

        return "notifications/info";
    }
}
