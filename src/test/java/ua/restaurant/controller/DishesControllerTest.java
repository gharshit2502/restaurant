package ua.restaurant.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ua.restaurant.dto.ItemDTO;
import ua.restaurant.entity.Categories;
import ua.restaurant.entity.Dishes;
import ua.restaurant.repository.DishesRepository;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.containsString;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@WithUserDetails("manager")
public class DishesControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private DishesRepository dishesRepository;

    private static final MediaType APPLICATION_JSON_UTF8 =
            new MediaType(MediaType.APPLICATION_JSON.getType(),
                    MediaType.APPLICATION_JSON.getSubtype(),
                    StandardCharsets.UTF_8);

    @Test
    public void testGetAllDishes() throws Exception {
        this.mockMvc.perform(get("/api/manager/dishes/get_all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Pasta")));
    }

    private String jsonMapper(Object o) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(o);
    }

    @Test
    public void testCreate() throws Exception {
        Dishes dish = Dishes.builder()
                .nameEn("test")
                .nameUa("тест")
                .price(new BigDecimal(123))
                .categories(Categories.builder().id(1L).build())
                .build();
        String requestJson = jsonMapper(dish);

        mockMvc.perform(post("/api/manager/dishes/create")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("test")));
    }
    @Test
    public void testCreate_InvalidInput_nameEn() throws Exception {
        Dishes dish = Dishes.builder()
                .nameEn("тест")
                .nameUa("тест")
                .price(new BigDecimal(123))
                .categories(Categories.builder().id(1L).build())
                .build();
        String requestJson = jsonMapper(dish);

        mockMvc.perform(post("/api/manager/dishes/create")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString("Validation failed")));
    }
    @Test
    public void testCreate_InvalidInput_price() throws Exception {
        Dishes dish = Dishes.builder()
                .nameEn("test")
                .nameUa("тест")
                .price(new BigDecimal(-123))
                .categories(Categories.builder().id(1L).build())
                .build();
        String requestJson = jsonMapper(dish);

        mockMvc.perform(post("/api/manager/dishes/create")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString("Validation failed")));
    }
    @Test
    public void testCreate_InvalidInput_categories() throws Exception {
        Dishes dish = Dishes.builder()
                .nameEn("test")
                .nameUa("тест")
                .price(new BigDecimal(123))
                .categories(Categories.builder().build())
                .build();
        String requestJson = jsonMapper(dish);

        mockMvc.perform(post("/api/manager/dishes/create")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testGetDish() throws Exception {
        mockMvc.perform(get("/api/manager/dishes/update?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("nameEn")));
    }
    @Test
    public void testGetDish_InvalidInput_1() throws Exception {
        mockMvc.perform(get("/api/manager/dishes/update?id=fhdfh"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString("Failed to convert")));
    }
    @Test
    public void testGetDish_InvalidInput_2() throws Exception {
        mockMvc.perform(get("/api/manager/dishes/update?id="))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void testGetDish_InvalidInput_3() throws Exception {
        mockMvc.perform(get("/api/manager/dishes/update"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void testGetDish_InvalidInput_4() throws Exception {
        mockMvc.perform(get("/api/manager/dishes/update?id=-123"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void testGetDish_InvalidInput_5() throws Exception {
        mockMvc.perform(get("/api/manager/dishes/update?id=9999999"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testUpdate() throws Exception {
        Dishes dishId = dishesRepository.findByNameEn("test").orElse(null);
        if (dishId == null) return;

        Dishes dish = Dishes.builder()
                .id(dishId.getId())
                .nameEn("testNew")
                .nameUa("тест123")
                .price(new BigDecimal(321))
                .categories(Categories.builder().id(1L).build())
                .build();
        String requestJson = jsonMapper(dish);

        mockMvc.perform(put("/api/manager/dishes/update")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdate_InvalidInput_1() throws Exception {
        ItemDTO dish = ItemDTO.builder().build();
        String requestJson = jsonMapper(dish);

        mockMvc.perform(put("/api/manager/dishes/update")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
//    @Transactional
    public void testDelete() throws Exception {
        Dishes dishId = dishesRepository.findByNameEn("testNew").orElse(null);
        if (dishId == null) return;

        ItemDTO dish = ItemDTO.builder().itemId(dishId.getId()).build();
        String requestJson = jsonMapper(dish);

        mockMvc.perform(delete("/api/manager/dishes/delete")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testDelete_InvalidInput_1() throws Exception {
        ItemDTO dish = ItemDTO.builder().build();
        String requestJson = jsonMapper(dish);

        mockMvc.perform(delete("/api/manager/dishes/delete")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}