package ua.restaurant.utils;

public interface Constants {

    String NULL = "null";
    String NAME = "name";

    //Controllers (for logging)
    String REGISTRATION = "Registration new user: ";
    String GET_PAGE_DISHES = "Get page of dishes #";

    String GET_ALL_BASKET_DISHES = "Get all dishes in the basket for user: ";
    String ADD_NEW_DISH = "Add to basket new item: ";
    String DELETE_ONE = "Delete from basket one item, id: ";
    String DELETE_ALL = "Delete all from basket for user: ";

    String GET_ALL_ORDER_USER = "Get all orders for user: ";
    String GET_ALL_ORDER_MANAGER = "Get all orders for manager: ";
    String ADD_NEW_ORDER = "Add new order for user: ";
    String CONFIRM = "Confirm action for order #: ";
    String PAYMENT = "Accept payment for order #: ";

    String GET_ALL_DISHES = "Get all dishes";
    String GET_DISH = "Get dish, id: ";
    String CREATE_NEW_DISH = "Create new dish";
    String UPDATE_DISH = "Update dish, id: ";
    String DELETE_DISH = "Delete dish, id: ";
//    String DELETE_ALL = "Delete all from basket for user: ";

    String GET_ALL_CATEGORIES = "Get all categories";

    // Services (for logging and response)
    String DELETE_ALL_BASKET = "Delete all from basket success";
    String EMPTY_LIST = "List of items is empty";
    String DISH_NOT_FOUND = "Dish not found, id: ";
    String CATEGORY_NOT_FOUND = "Category not found, id: ";
    String ORDER_NOT_FOUND = "Order not found, id: ";
    String ERROR_DELETE_ALL = "Cannot delete all items from basket.";
    String ERROR_DELETE = "Cannot delete item.";
    String LOGIN_EXISTS = "Login already exists: ";
}
