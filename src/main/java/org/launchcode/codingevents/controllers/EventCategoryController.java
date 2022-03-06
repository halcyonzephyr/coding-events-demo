package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import javax.validation.Valid;


@Controller
@RequestMapping("eventCategories")
public class EventCategoryController {

    @Autowired
    private EventRepository eventCategoryRepository;

    @GetMapping
    public String displayAllEvents(Model model) {
        model.addAttribute("title", "All Categories");
        model.addAttribute("categories", eventCategoryRepository.findAll());

        return "eventCategories/index";
    }

    @GetMapping
    public String renderCreateEventCategoryForm(Model model) {
        model.addAttribute("title", "Create Category");
        model.addAttribute(new EventCategory());

        return "eventCategories/create";
    }

    @PostMapping
    public String processCreateEventCategoryForm(@ModelAttribute @Valid Event newEvent,
                                                 Errors errors, Model model) {
        if(errors.hasErrors()) {
            return "eventCategories/create";
        }

        model.addAttribute("title", "Create Category");
        model.addAttribute(new EventCategory());

        eventCategoryRepository.save(newEvent);
        return "redirect:";
    }
}
