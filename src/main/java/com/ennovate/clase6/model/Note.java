package com.ennovate.clase6.model;

import lombok.Data;

@Data
public class Note {
    private Long id;
    private String owner;
    private String title;
    private String content;
}
