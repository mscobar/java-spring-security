package com.ennovate.clase6.service.impl;

import com.ennovate.clase6.model.Note;
import com.ennovate.clase6.repository.NoteRepository;
import com.ennovate.clase6.service.NoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Slf4j
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository repo;

    @Override
    public Note create(Note note) {
        return repo.save(note);
    }

    @Override
    public Optional<Note> get(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<Note> listAll() {
        return repo.findAll();
    }

    @Override
    public List<Note> listByOwner(String owner) {
        return repo.findByOwner(owner);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
