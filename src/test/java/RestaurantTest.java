import org.junit.*;
import static org.junit.Assert.*;

public class RestaurantTest {

  @Test
  public void Restaurant_instantiatesCorrectly_true() {
    Restaurant testRestaurant = new Restaurant("LBB");
    assertEquals(true, testRestaurant instanceof Restaurant);
  }


}
