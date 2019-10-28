package server.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import server.model.Field;
import server.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.persistance.repository.ProjectRepository;
import server.util.FieldUtil;

import java.io.IOException;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    protected ObjectMapper mapper;

    public Iterable<Project> projects() throws IOException {
        Iterable<Project> projects = projectRepository.findAll();
        for(Project project : projects){
            List<Field> fields = mapper.readValue(project.getDetails(), new TypeReference<List<Field>>() {});
            project.setFields(fields);
        }
        return projects;
    }

    public Project projectDetails(@PathVariable Integer id) throws IOException {
        Project project = projectRepository.findById(id).orElse(new Project());
        List<Field> fields = mapper.readValue(project.getDetails(), new TypeReference<List<Field>>() {});
        project.setFields(fields);
        return project;
    }

    public Project saveProject(@RequestBody Project arg) throws Exception{
        String jsonStr = mapper.writeValueAsString(arg);
        Project project = mapper.readValue(jsonStr, Project.class);
        project.setDetails(mapper.writeValueAsString(FieldUtil.filterFields(project.getFields())));
        projectRepository.save(project);
        return project;
    }

    public Integer deleteProject(@PathVariable Integer id) {
        projectRepository.deleteById(id);
        return id;
    }

}
