package com.simplelearner.simplelearner.section;

import com.simplelearner.simplelearner.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectionService {
    private SectionRepository sectionRepository;

    @Autowired
    SectionService(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    void save(Section section) {
        sectionRepository.save(section);
    }

    Iterable<Section> getAllSections() {
        return sectionRepository.findAll();
    }

    Section addTasks(String sectionName, List<Task> tasks) {
        Optional<Section> sectionOptional = sectionRepository.findById(sectionName);
        if (sectionOptional.isPresent()) {
            Section section = sectionOptional.get();
            section.addTasks(tasks);
            sectionRepository.save(section);
            return section;
        }
        return null;
    }
}
