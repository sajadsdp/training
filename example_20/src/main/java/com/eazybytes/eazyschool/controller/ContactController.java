package com.eazybytes.eazyschool.controller;

import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class ContactController {

    private static Logger log = LoggerFactory.getLogger(ContactService.class);

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping("/contact")
    public String displayContactPage(Model model){
        model.addAttribute("contact",new Contact());
        return "contact.html";
    }

//    @RequestMapping(value = "/saveMsg",method = POST)
//    public ModelAndView saveMessage(Contact contact){
//        log.info("Name=" + contact.getName());
//        log.info("Mobile number=" + contact.getMobileNum());
//        log.info("Email=" + contact.getEmail());
//        log.info("Subject=" + contact.getSubject());
//        log.info("Message=" + contact.getMessage());
//        return new ModelAndView("redirect:/contact");
//    }

    @RequestMapping(value = "/saveMsg",method = POST)
    public String saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors){
        if (errors.hasErrors()){
            log.error("Contact form validation failed to due to :" + errors.toString());
            return "contact.html";
        }
        contactService.saveMessageDetails(contact); 
        return "redirect:/contact";
    }


    @RequestMapping("/displayMessages")
    public ModelAndView displayMessages(Model model){
        List<Contact> contactsMsgs = contactService.findMsgsWithOpenStatus();
        ModelAndView modelAndView = new ModelAndView("messages.html");
        modelAndView.addObject("contactMsgs",contactsMsgs);
        return modelAndView;
    }

    @RequestMapping(value = "/closeMsg",method = GET)
    public String closeMsg(@RequestParam int id, Authentication authentication){
        contactService.updateMsgStatus(id,authentication.getName());
        return "redirect:/displayMessages";
    }
}
