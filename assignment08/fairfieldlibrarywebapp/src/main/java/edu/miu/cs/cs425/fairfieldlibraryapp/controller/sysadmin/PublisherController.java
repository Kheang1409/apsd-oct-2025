package edu.miu.cs.cs425.fairfieldlibraryapp.controller.sysadmin;

import edu.miu.cs.cs425.fairfieldlibraryapp.model.Address;
import edu.miu.cs.cs425.fairfieldlibraryapp.model.Book;
import edu.miu.cs.cs425.fairfieldlibraryapp.model.Publisher;
import edu.miu.cs.cs425.fairfieldlibraryapp.service.PublisherService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value={"/secured/sysadmin/publisher"})
public class PublisherController {
    private PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping(value = {"/list"})
    public ModelAndView listPublishers(@RequestParam(defaultValue = "1") int pageNo) {
        var modelAndView = new ModelAndView();
        var publishers = publisherService.getAllPublishersPaged(pageNo-1);
        modelAndView.addObject("publishers", publishers);
        modelAndView.addObject("currentPageNo", pageNo-1);
//        modelAndView.addObject("publishersCount", publishers.size());
        modelAndView.setViewName("secured/sysadmin/publisher/list");
        return modelAndView;
    }
//    @GetMapping(value = {"/list"})
//    public ModelAndView listPublishers() {
//        var modelAndView = new ModelAndView();
//        var publishers = publisherService.getAllPublishers();
//        modelAndView.addObject("publishers", publishers);
//        modelAndView.addObject("publishersCount", ((List)publishers).size());
//        modelAndView.setViewName("secured/sysadmin/publisher/list");
//        return modelAndView;
//    }

    @GetMapping(value = {"/new"})
    public String displayNewPublisherForm(Model model) {
        model.addAttribute("publisher", new Publisher(null, null, new Address()));
        return "secured/sysadmin/publisher/new";
    }

    @PostMapping(value = {"/new"})
    public String registerNewPublisher(@Valid @ModelAttribute("publisher") Publisher publisher,
                                       BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("publisher", publisher);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "secured/sysadmin/publisher/new";
        }
        if(publisher.getPrimaryAddress() != null) {
            var primaryAddr = publisher.getPrimaryAddress();
            if(primaryAddr.getStreet().equals("")
                    && primaryAddr.getCity().equals("")
                    && primaryAddr.getState().equals("")
                    && primaryAddr.getZipCode().equals("")
            ) {
                publisher.setPrimaryAddress(null);
            }
        }
        publisherService.savePublisher(publisher);
        return "redirect:/secured/sysadmin/publisher/list";
    }

    @GetMapping(value = {"/edit/{publisherId}"})
    public String editPublisher(@PathVariable Integer publisherId, Model model) {
        var publisher = publisherService.getPublisherById(publisherId);
        if(publisher != null) {
            model.addAttribute("publisher", publisher);
            return "secured/sysadmin/publisher/edit";
        }
        return "redirect:/secured/sysadmin/publisher/list";
    }

    @PostMapping(value = {"/update"})
    public String updatePublisher(@Valid @ModelAttribute("publisher") Publisher publisher,
                                  BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("publisher", publisher);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "secured/sysadmin/publisher/edit";
        }
        publisherService.updatePublisher(publisher);
        return "redirect:/secured/sysadmin/publisher/list";
    }

    @GetMapping(value = {"/delete/{publisherId}"})
    public String deletePublisher(@PathVariable Integer publisherId) {
        publisherService.deletePublisherById(publisherId);
        return "redirect:/secured/sysadmin/publisher/list";
    }

    @GetMapping(value = {"/primaryAddress/delete/{publisherId}"})
    public String deletePrimaryAddressOfPublisher(@PathVariable Integer publisherId) {
        publisherService.deletePrimaryAddressOfPublisher(publisherId);
        return "redirect:/secured/sysadmin/publisher/list";
    }

    @GetMapping(value = {"/search"})
    public ModelAndView searchPublishers(@RequestParam String searchString) {
        ModelAndView modelAndView = new ModelAndView();
        List<Publisher> publishers = publisherService.searchPublishers(searchString);
        modelAndView.addObject("publishers", publishers);
        modelAndView.addObject("searchString", searchString);
        modelAndView.setViewName("secured/sysadmin/publisher/search-result");
        return modelAndView;
    }

}
