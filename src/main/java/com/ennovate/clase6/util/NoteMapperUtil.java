package com.ennovate.clase6.util;

import com.ennovate.clase6.model.Note;
import com.ennovate.clase6.model.NoteDto;

public class NoteMapperUtil {

    public static Note transformNoteDto(NoteDto dto, String owner){
        Note note = new Note();
        note.setOwner(owner);
        note.setContent(dto.getContent());
        note.setTitle(dto.getTitle());
        return note;
    }
}
