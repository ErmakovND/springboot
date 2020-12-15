package nd.ermakov.springboot.controller;

import nd.ermakov.springboot.model.Audit;
import nd.ermakov.springboot.service.AuditService;
import nd.ermakov.springboot.service.AuthService;
import nd.ermakov.springboot.service.AuthStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;

@Controller
public class MainController {

    private AuthService authService;
    private AuditService auditService;

    @Autowired
    public void setAuditService(AuditService auditService) {
        this.auditService = auditService;
    }

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/")
    public ModelAndView getLoginPage(
            ModelMap model,
            @ModelAttribute("message") String message
    ) {
        model.addAttribute("message", message);
        return new ModelAndView("login", model);
    }

    @PostMapping("/login")
    public ModelAndView logIn(
            @RequestParam("name") String name,
            @RequestParam("password") String password,
            ModelMap model,
            RedirectAttributes attributes) {
        AuthStatus authStatus = authService.authorize(name, password);
        auditService.log(name, password, authStatus);
        String message = "";
        if (authStatus == AuthStatus.NOT_FOUND) {
            message = "User " + name + " doesn't exist";
        }
        if (authStatus == AuthStatus.WRONG_PASSWORD) {
            message = "Wrong password";
        }
        attributes.addFlashAttribute("message", message);
        attributes.addFlashAttribute("name", name);
        return new ModelAndView(authStatus == AuthStatus.OK ? "redirect:/welcome" : "redirect:/", model);
    }

    @GetMapping("/welcome")
    public ModelAndView getWelcomePage(
            ModelMap model,
            @ModelAttribute("name") String name
    ) {
        model.addAttribute("name", name);
        return new ModelAndView("welcome", model);
    }

    @GetMapping("/signup")
    public ModelAndView getSignupPage(
            ModelMap model,
            @ModelAttribute("message") String message
    ) {
        model.addAttribute("message", message);
        return new ModelAndView("signup", model);
    }

    @PostMapping("/signup")
    public ModelAndView signUp(
            @RequestParam("name") String name,
            @RequestParam("password") String password,
            ModelMap model,
            RedirectAttributes attributes
    ) {
        AuthStatus authStatus = authService.register(name, password);
        auditService.log(name, password, authStatus);
        String message = "User " + name + (authStatus == AuthStatus.CREATED ? " successfully created" : " already exists");
        attributes.addFlashAttribute("message", message);
        return new ModelAndView(authStatus == AuthStatus.CREATED ? "redirect:/" : "redirect:/signup", model);
    }

    @GetMapping("/audit")
    public ModelAndView getAuditPage(ModelMap model) {
        Collection<Audit> auditList = auditService.get();
        model.addAttribute("audit", auditList);
        return new ModelAndView("audit", model);
    }
}