package com.ytudak.malzeme.controller;

import com.ytudak.malzeme.services.RecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecordsController {

    private RecordsService recordsService;

    @Autowired
    public RecordsController(RecordsService recordsService) {
        this.recordsService = recordsService;
    }

    @GetMapping("/kayit")
    public String records(Model model) {
        recordsService.records(model);
        return "kayit";
    }
}
