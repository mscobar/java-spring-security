package com.ennovate.clase6.service;

import com.ennovate.clase6.model.Note;

import java.util.List;
import java.util.Optional;

public interface NoteService {
    Note create (Note note);
    Optional<Note> get(Long id);
    List<Note> listAll();
    List<Note> listByOwner(String owner);
    void delete(Long id);
}

