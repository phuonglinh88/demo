package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.form.ChangePass;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Linh
 * @project demo
 */
@Controller
@RequestMapping("/")
public class HomeController {
    
    private final static Logger logger = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/")
    public String homePage(Model model) {
        List< User > list = userService.findListUser();
        model.addAttribute("list", list);
        return "homePage";
    }
    
    @RequestMapping(value = "/change/{id}", method = RequestMethod.GET)
    public String getRegisterPage(Model model, @PathVariable("id") Long id) throws Exception {
        if (userService.findUserById(id) == null)
            throw new Exception();
        if (!model.containsAttribute("changePass")) {
            ChangePass changePass = new ChangePass();
            changePass.setId(id);
            model.addAttribute("changePass", changePass);
        }
        return "changePage";
    }
    
    @RequestMapping(value = "/change/{id}", method = RequestMethod.POST)
    public String postRegisterPage(@Valid ChangePass changePass, BindingResult result, @PathVariable("id") Long id, RedirectAttributes redirectAttributes) throws Exception {
        User user = userService.findUserById(id);
        if (userService.findUserById(id) == null)
            throw new Exception();
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.changePass", result);
            logger.info(result.toString());
            redirectAttributes.addFlashAttribute("changePass", changePass);
            return "redirect:/change/" + id;
        }
        user.setPassword(changePass.getNewPassword());
        userService.saveUser(user);
        return "redirect:/";
    }
}
