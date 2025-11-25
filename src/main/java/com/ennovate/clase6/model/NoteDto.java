package com.ennovate.clase6.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class NoteDto {

    @NonNull
    private String title;
    @NonNull
    private String content;

}
