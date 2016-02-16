import java.util.HashMap;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      // model.put("restaurant")
      model.put("restaurant", request.session().attribute("restaurant"));
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/success", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      String restaurantNameInput = request.queryParams("restaurantName");
      String restaurantTypeInput = request.queryParams("restaurantType");
      String restaurantCostInput = request.queryParams("restaurantCost");
      String restaurantNotesInput = request.queryParams("restaurantNotes");

      Restaurant newRestaurant = new Restaurant(restaurantNameInput);
      request.session().attribute("restaurant", newRestaurant);

      model.put("template", "templates/success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
