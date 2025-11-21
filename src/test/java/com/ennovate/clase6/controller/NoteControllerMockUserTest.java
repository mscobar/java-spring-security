package com.ennovate.clase6.controller;

import com.ennovate.clase6.service.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@WebMvcTest(NoteController.class)
class NoteControllerMockUserTest {

    @Autowired MockMvc mvc;
    @MockitoBean
    NoteService svc;

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminCanDelete() throws Exception {
        mvc.perform(delete("/api/notes/1")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "USER")
    void userCannotDelete() throws Exception {
        mvc.perform(get("/api/notes")).andExpect(status().isOk());
    }

    @Test
    void getWithJwt() throws Exception {
        mvc.perform(get("/api/notes")
                        .with(jwt().jwt(jwt -> jwt.claim("sub", "alice")
                                .claim("scope","openid")
                                .claim("roles", List.of("USER")))))
                .andExpect(status().isOk());
    }

}
