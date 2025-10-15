package edu.miu.cs.cs425.fairfieldlibraryapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"", "/"})
public class PublicPagesController {

    @GetMapping(value = {""})
    public String displayHomepage() {
        return "public/index";
    }

    @GetMapping(value = {"/about"})
    public String displayAboutPage() {
        return "public/about";
    }

    @GetMapping(value = {"/virtual-tour"})
    public String displayVirtualTourPage() {
        return "public/virtual-tour";
    }

    @GetMapping(value = {"/services"})
    public String displayServicesPage() {
        return "public/services";
    }

    @GetMapping(value = {"/signin"})
    public String displaySignInPage() {
        return "public/signin";
    }

}
