import org.junit.*;
import static org.junit.Assert.*;

public class RestaurantTest {

  @Test
  public void Restaurant_instantiatesCorrectly_true() {
    Restaurant testRestaurant = new Restaurant("LBB");
    assertEquals(true, testRestaurant instanceof Restaurant);
  }
  @Test
  public void setPriceRange_setsPriceRangeFromUserInput_Deluxe() {
    Restaurant testRestaurant = new Restaurant("LBB");
    assertEquals("Deluxe", testRestaurant.setPriceRange("Deluxe"));
  }
  @Test
  public void setType_setsTypeOfFoodFromUserInput_Sushi() {
    Restaurant testRestaurant = new Restaurant("LBB");
    assertEquals("Sushi", testRestaurant.setType("Sushi"));
  }
  @Test
  public void setNotes_setsNotesAboutRestaurantFromUserInput_AlwaysBusy() {
    Restaurant testRestaurant = new Restaurant("LBB");
    assertEquals("Always busy", testRestaurant.setNotes("Always busy"));
  }

  @Test
  public void getDescription_getsCompleteSentenceDescribingRestaurant_longString(){
    Restaurant testRestaurant = new Restaurant("LBB");
    testRestaurant.setPriceRange("deluxe");
    testRestaurant.setType("sushi");
    testRestaurant.setNotes("Always busy, must make reservations.");
    assertEquals("The sushi restaurant LBB has deluxe pricing. I had this to say about it: Always busy, must make reservations.", testRestaurant.getDescription());
  }

}
