import java.util.HashMap;
import java.util.ArrayList;
import java.util.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      model.put("restaurants", request.session().attribute("restaurants"));
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/success", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      ArrayList<Restaurant> restaurants = request.session().attribute("restaurants");

      if(restaurants == null) {
        restaurants = new ArrayList<Restaurant>();
        request.session().attribute("restaurants", restaurants);
      }

      String restaurantNameInput = request.queryParams("restaurantName");
      String restaurantTypeInput = request.queryParams("restaurantType");
      String restaurantCostInput = request.queryParams("restaurantCost");
      String restaurantNotesInput = request.queryParams("restaurantNotes");

      Restaurant newRestaurant = new Restaurant(restaurantNameInput);
      request.session().attribute("restaurant", newRestaurant);

      newRestaurant.setPriceRange(restaurantCostInput);
      newRestaurant.setType(restaurantTypeInput);
      newRestaurant.setNotes(restaurantNotesInput);

      restaurants.add(newRestaurant);
      Collections.sort((ArrayList<Restaurant>) request.session().attribute("restaurants"));
      model.put("restaurants", request.session().attribute("restaurants"));
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
