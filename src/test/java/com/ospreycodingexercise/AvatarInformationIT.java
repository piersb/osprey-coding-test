package com.ospreycodingexercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {"spring.config.name=myapp-test-h2"})
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AvatarInformationIT {


    @Autowired
    private MockMvc mockMVC;

    @Autowired
    private AvatarRepository avatarRepository;

    @BeforeEach
    public void setup() {
        Avatar avatar = new Avatar();
        avatar.setX(5);
        avatar.setY(5);
        avatar.setDirection("NORTH");
        avatarRepository.save(avatar);
    }

    @Test
    public void getAvatarLocationIT() throws Exception {
        this.mockMVC.perform(get("/api/board")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.location").value("5x5"))
                .andExpect(jsonPath("$.direction").value("NORTH"));
    }


    @Test
    public void OnlyTheMostRecentAvatarInformationShouldBeReturned() throws Exception {
        Avatar secondAvatar = new Avatar();
        secondAvatar.setX(3);
        secondAvatar.setY(3);
        secondAvatar.setDirection("SOUTH");
        avatarRepository.save(secondAvatar);

        this.mockMVC.perform(get("/api/board")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.location").value("3x3"))
                .andExpect(jsonPath("$.direction").value("SOUTH"));
        
    }
    
    @Test
    public void ResetShouldClearAvatarHistoryAndInstantiateNewAvatar() throws Exception {
        this.mockMVC.perform(post("/api/board/NORTH"));
        assertThat(avatarRepository.count()).isEqualTo(2);
        this.mockMVC.perform(post("/api/reset")).andDo(print())
                .andExpect(status().isOk());
        assertThat(avatarRepository.count()).isEqualTo(1);
    }
    

    @Test 
    public void ValidMoveShouldReturn201() throws Exception {
        this.mockMVC.perform(post("/api/board/NORTH"))
                .andExpect(status().isCreated());
    }
    
    

}
