package edu.uph.ii.ppproject.controllers;

import edu.uph.ii.ppproject.domain.Issue;
import edu.uph.ii.ppproject.repositories.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IssueController {
    private IssueRepository issueRepository;

    @Autowired
    public void setIssueRepository(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    @GetMapping("issues")
    public String issueList(Model model){
        List<Issue> issues = issueRepository.findAll();

        model.addAttribute("issues", issues);

        return "issues/issues";
    }

    @GetMapping("issueForm")
    public String issueForm(Model model, @RequestParam(value = "Id", required = false) Long id){
        Issue issue = id != null ? issueRepository.findById(id).orElse(new Issue()) : new Issue();

        model.addAttribute("issue", issue);

        return "issues/issueForm";
    }

    @RequestMapping("addIssue")
    public String addIssue(@ModelAttribute("issue") Issue issue){
        issueRepository.save(issue);

        return "redirect:/issues";
    }

    @GetMapping("deleteIssue")
    public String deleteIssue(@RequestParam("Id") Long id){
        issueRepository.deleteById(id);

        return "redirect:/issues";
    }

    @GetMapping("issueInfo")
    public String issueInfo(Model model, @RequestParam("Id") Long id){
        Issue issue = issueRepository.getReferenceById(id);

        model.addAttribute("issue", issue);

        return "issues/info";
    }
}
