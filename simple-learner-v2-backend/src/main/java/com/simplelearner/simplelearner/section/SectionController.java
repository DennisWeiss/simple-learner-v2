package com.simplelearner.simplelearner.section;

import com.simplelearner.simplelearner.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("section")
public class SectionController {
    private SectionService sectionService;

    @Autowired
    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public ResponseEntity<Section> newSection(@RequestParam String name) {
        Section section = new Section(name);
        sectionService.save(section);
        return new ResponseEntity<>(section, new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Section> getAllSections() {
        return sectionService.getAllSections();
    }

    @RequestMapping(value = "{sectionName}/addtasks", method = RequestMethod.POST)
    public ResponseEntity<Section> addTasks(@PathVariable String sectionName,
                                            @RequestBody List<Task> tasks) {
        Section section = sectionService.addTasks(sectionName, tasks);
        return new ResponseEntity<>(section, new HttpHeaders(), section == null ? HttpStatus.CREATED : HttpStatus.NOT_FOUND);
    }
}
