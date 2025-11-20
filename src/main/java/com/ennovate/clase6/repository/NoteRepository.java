package com.ennovate.clase6.repository;

import com.ennovate.clase6.model.Note;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
// todo mover este repositorio a una interfaz para cubrir el consepto de solid
@Repository
public class NoteRepository {

    private final Map<Long, Note> store = new HashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    public Note save(Note note){
        if(note.getId() == null) note.setId(seq.getAndIncrement());
        store.put(note.getId(), note);
        return note;
    }

    public Optional<Note> findById(Long id) { return Optional.ofNullable(store.get(id)); }

    public List<Note> findAll() { return new ArrayList<>(store.values()); }

    public List<Note> findByOwner(String owner) {
        return store.values().stream()
                .filter(n -> n.getOwner().equals(owner))
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) { store.remove(id); }

}
