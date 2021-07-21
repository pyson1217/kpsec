package com.kpsec.test.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kpsec.test.contoller.config.AbstractControllerTest;
import com.kpsec.test.vo.RequestJson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class AssignmentControllerTest extends AbstractControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AssignmentController assignmentController;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getUserAmountSum() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/assignment/a1/user/amount/sum"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getUserDealNo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/assignment/a2/user/deal/no"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getBranchAmtSum() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/assignment/a3/branch/amount/sum"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getBranchNameAmtSum() throws Exception {
        String content = objectMapper.writeValueAsString(new RequestJson("판교점"));
        mockMvc.perform(MockMvcRequestBuilders.post("/assignment/a4/branch/name/amount/sum").content(content).contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Override
    protected Object controller() {
        return assignmentController;
    }
}