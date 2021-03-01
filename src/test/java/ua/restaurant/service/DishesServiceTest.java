package ua.restaurant.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.test.context.junit4.SpringRunner;
import ua.restaurant.dto.DishDTO;
import ua.restaurant.dto.PageableDishesDTO;
import ua.restaurant.entity.Categories;
import ua.restaurant.entity.Dishes;
import ua.restaurant.repository.DishesRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DishesServiceTest {
    @Autowired
    private DishesService dishesService;
    @Autowired
    private DishesRepository dishesRepository;

    // todo test on test database not on real one

    private static final int PAGE_NO = 1;
    private static final String SORT = "id";
    private static final String DIRECTION = "asc";
    private static final Long CATEGORY_ID = 0L;

    private static final int ROWS_ON_PAGE = 5;

    @Test
    public void findAllDishesPaginated() {
        PageableDishesDTO page = dishesService.findAllDishesPaginated(
                PAGE_NO, SORT, DIRECTION, CATEGORY_ID);

        assertEquals(ROWS_ON_PAGE, page.getDishes().size());
        assertTrue(page.getCategories().size() > 2);
        assertTrue(page.getTotalPages() >= page.getDishes().size() / 5);
        assertEquals(PAGE_NO, page.getCurrentPage());
        assertEquals(SORT, page.getSortField());
        assertEquals(DIRECTION, page.getSortDirection());
        assertEquals(CATEGORY_ID, page.getCategoryId());
    }

    @Test
    public void findAllDishesPaginated_withFilter() {
        PageableDishesDTO page = dishesService.findAllDishesPaginated(
                PAGE_NO, SORT, DIRECTION, 2L);
        assertTrue(page.getDishes().size() > 0);
    }

    @Test
    public void findAllDishesPaginated_setDefault1() {
        PageableDishesDTO page = dishesService.findAllDishesPaginated(
                PAGE_NO, SORT, DIRECTION, -3L);
        assertEquals(ROWS_ON_PAGE, page.getDishes().size());
    }
    @Test
    public void findAllDishesPaginated_setDefault2() {
        PageableDishesDTO page = dishesService.findAllDishesPaginated(
                PAGE_NO, null, null, null);
        assertEquals(ROWS_ON_PAGE, page.getDishes().size());
    }
    @Test
    public void findAllDishesPaginated_setDefault3() {
        PageableDishesDTO page = dishesService.findAllDishesPaginated(
                PAGE_NO, SORT, "noname", CATEGORY_ID);
        assertEquals(ROWS_ON_PAGE, page.getDishes().size());
    }

    @Test(expected = NullPointerException.class)
    public void findAllDishesPaginated_Exception0() {
        dishesService.findAllDishesPaginated(
                null, SORT, DIRECTION, CATEGORY_ID);
    }
    @Test(expected = IllegalArgumentException.class)
    public void findAllDishesPaginated_Exception1() {
        dishesService.findAllDishesPaginated(
                -1, SORT, DIRECTION, CATEGORY_ID);
    }
    @Test(expected = PropertyReferenceException.class)
    public void findAllDishesPaginated_Exception2() {
        dishesService.findAllDishesPaginated(
                PAGE_NO, "noname", DIRECTION, CATEGORY_ID);
    }

    @Test
    public void findAllDishesTest() {
        List<DishDTO> list = dishesService.findAllDishes();
        assertTrue(list.size() > 10);
    }

    @Test
    public void saveNewDish() {
        Dishes dish = dishesService.saveNewDish(Dishes.builder()
                .nameEn("test")
                .nameUa("тест")
                .price(new BigDecimal(123))
                .categories(Categories.builder().id(1L).build())
                .build());
        assertNotNull(dish);
    }

    @Test
    public void findById() {
        Dishes dish = dishesService.findById(1L);
        assertNotNull(dish);
    }

    @Test(expected = NoSuchElementException.class)
    public void findById_Exception() {
        dishesService.findById(-1L);
    }

    @Test
//    @Transactional
    public void update() {
        Dishes dish = dishesRepository.findByNameEn("test").orElse(null);
        if (dish == null) return;

        dishesService.update(Dishes.builder()
                .id(dish.getId())
                .nameEn("testNew")
                .nameUa("тест123")
                .price(new BigDecimal(321))
                .categories(Categories.builder().id(1L).build())
                .build());
    }

    @Test
    public void delete() {
        Dishes dish = dishesRepository.findByNameEn("testNew").orElse(null);
        if (dish == null) return;

        dishesService.delete(dish.getId());
    }
}