package server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import server.model.Project;
import server.util.FieldUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProjectController {

    @Autowired
    ProjectService projectService;
    @Autowired
    protected ObjectMapper mapper;

    @RequestMapping("/projects")
    public String projects(Model model) throws IOException {
        Map<String, List<Project>> resultMap = new HashMap<String, List<Project>>();

        for (Project project : projectService.projects()) {
            if (resultMap.get(project.getCategory()) == null) {
                List<Project> projects = new ArrayList<Project>();
                projects.add(project);
                resultMap.put(project.getCategory(), projects);
            } else {
                List<Project> projects = resultMap.get(project.getCategory());
                projects.add(project);
            }
        }
        model.addAttribute("projects", resultMap);
        return "projects";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/projectnew")
    public String projectnew(Model model)  {
        model.addAttribute("project", new Project());
        return "projectnew";
    }

    @RequestMapping(value = "/project/{id}", method = RequestMethod.GET)
    public String projectview(@PathVariable Integer id, Model model) throws IOException {
        Project project = projectService.projectDetails(id);
        model.addAttribute("project", project);
        return "projectview";
    }

    @RequestMapping(value = "/projectmodify/{id}", method = RequestMethod.GET)
    public String projectmodify(@PathVariable Integer id, Model model) throws IOException{
        Project project = projectService.projectDetails(id);
        model.addAttribute("project", project);
        return "projectnew";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/projectsave")
    public String saveProject(@ModelAttribute Project t, Model model) throws Exception{
        String jsonStr = mapper.writeValueAsString(t);
        Project project = mapper.readValue(jsonStr, Project.class);
        project.setDetails(mapper.writeValueAsString(FieldUtil.filterFields(project.getFields())));
        projectService.saveProject(project);
        model.addAttribute("project", project);
        return "projectview";
    }

    @RequestMapping(value = "/projectdelete/{id}", method = RequestMethod.GET)
    public String projectDelete(@PathVariable Integer id, Model model) {
        projectService.deleteProject(id);
        model.addAttribute("project", new Project());
        return "projectnew";
    }

}
