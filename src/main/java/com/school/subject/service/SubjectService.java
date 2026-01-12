package com.school.subject.service;

import com.school.subject.domain.Subject;
import com.school.subject.persistence.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {


    private final SubjectRepository repo;

    public SubjectService(SubjectRepository repo) {
        this.repo = repo;
    }

    public List<Subject> findAll() {
        return repo.findAll();
    }

    public Subject create(String name) {
        Subject subject = new Subject(name);
        return repo.save(subject);
    }
    public Subject findById(Long id) {
        return repo.findById(id).orElse(null);
    }
    public void delete(Long id) {
        repo.deleteById(id);
    }


}
