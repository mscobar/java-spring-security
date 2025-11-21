package com.ennovate.clase6.service.impl;

import com.ennovate.clase6.model.Note;
import com.ennovate.clase6.repository.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class NoteServiceImplTest {
    @Mock
    private NoteRepository repository;

    @InjectMocks
    private NoteServiceImpl service;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTest(){
        Note note = getNote();
        Mockito.when(repository.save(note)).thenReturn(note);

        Note saved = service.create(note);
        assertEquals("hello", saved.getTitle());
        verify(repository, times(1)).save(note);
    }

    private Note getNote(){
        Note note = new Note();
        note.setId(1L);
        note.setOwner("alice");
        note.setTitle("hello");
        return note;
    }

    @Test
    void testCreate() {
        Note note = getNote();
        when(repository.save(ArgumentMatchers.any())).thenReturn(note);
        Note saved = service.create(note);
        assertEquals("hello", saved.getTitle());
        verify(repository, times(1)).save(note);
    }

    @Test
    void testGet() {
        Note note = getNote();
        when(repository.findById(1L)).thenReturn(Optional.of(note));
        Optional<Note> result = service.get(1L);
        assertTrue(result.isPresent());
        assertEquals("alice", result.get().getOwner());
    }

    @Test
    void testListAll() {
        Note note = getNote();
        List<Note> notes = List.of(
                note,
                note
        );
        when(repository.findAll()).thenReturn(notes);
        List<Note> result = service.listAll();
        assertEquals(2, result.size());
    }

    @Test
    void testListByOwner() {
        Note note = getNote();
        List<Note> notes = List.of(note);
        when(repository.findByOwner("alice")).thenReturn(notes);
        List<Note> result = service.listByOwner("alice");
        assertEquals(1, result.size());
        assertEquals("alice", result.get(0).getOwner());
    }

    @Test
    void testDelete() {
        doNothing().when(repository).deleteById(5L);
        service.delete(5L);
        verify(repository, times(1)).deleteById(5L);
    }
}
