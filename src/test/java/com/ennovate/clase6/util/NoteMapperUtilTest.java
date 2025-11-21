package com.ennovate.clase6.util;

import com.ennovate.clase6.model.Note;
import com.ennovate.clase6.model.NoteDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class NoteMapperUtilTest {
    @Test
    void testTransformNoteDto() {
        // Arrange
        NoteDto dto = new NoteDto("Test Title","Test Content");


        String owner = "user123";

        // Act
        Note result = NoteMapperUtil.transformNoteDto(dto, owner);

        // Assert
        assertNotNull(result);
        assertEquals(owner, result.getOwner());
        assertEquals(dto.getTitle(), result.getTitle());
        assertEquals(dto.getContent(), result.getContent());
    }
}
