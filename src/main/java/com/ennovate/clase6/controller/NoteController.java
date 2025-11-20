package com.ennovate.clase6.controller;

import com.ennovate.clase6.model.Note;
import com.ennovate.clase6.model.NoteDto;
import com.ennovate.clase6.service.NoteService;
import com.ennovate.clase6.util.NoteMapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;


    @GetMapping
    public List<Note> getNotes(){
        return noteService.listAll();
    }

    @PostMapping
    public ResponseEntity<Note> create(@Valid @RequestBody NoteDto dto, @RequestParam String owner){
        return ResponseEntity.created(URI.create("/api/notes"))
                .body(noteService.create(NoteMapperUtil.transformNoteDto(dto,owner))
                );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        noteService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
