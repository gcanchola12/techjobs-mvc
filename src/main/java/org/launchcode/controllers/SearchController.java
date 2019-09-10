package org.launchcode.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

import static org.launchcode.models.JobData.findByColumnAndValue;
import static org.launchcode.models.JobData.findByValue;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    @RequestMapping(value ="results", method = RequestMethod.POST)
    public String search(@RequestParam String searchType, @RequestParam String searchTerm, Model model){
        ArrayList<HashMap<String, String>> searchResults = new ArrayList<>();

        if (searchType.equals("all")) {
            searchResults = findByValue(searchTerm);
        } else {
            searchResults = findByColumnAndValue(searchType, searchTerm);
        }

        Integer total = searchResults.size();

        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("results", total + " Result(s)");
        model.addAttribute("searchResults", searchResults);

        return "search";
    }


}
