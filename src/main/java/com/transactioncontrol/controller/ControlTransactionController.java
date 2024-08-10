package com.transactioncontrol.controller;

import com.transactioncontrol.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ControlTransactionController {

    @GetMapping("/control-transaction")
    public String controlTransaction(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || !"admin".equals(user.getRule())) {
            return "redirect:/home";
        }
        model.addAttribute("name", user.getName());
        return "control-transaction";
    }
}
