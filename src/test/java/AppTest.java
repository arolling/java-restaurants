import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import static org.fluentlenium.core.filter.FilterConstructor.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();
  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  //Integration testing
  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Restaurant");
  }

  @Test
  public void staysOnIndexPage() {
    goTo("http://localhost:4567/");
    fill("#restaurantName").with("LBB");
    submit(".btn");
    assertThat(pageSource()).contains("Restaurant");
  }

  @Test
  public void createsARestaurantObject() {
    goTo("http://localhost:4567/");
    fill("#restaurantName").with("LBB");
    submit(".btn");
    //click("a", withText("Go Back"));
    assertThat(pageSource()).contains("LBB");
  }

  @Test
  public void createsARestaurantObjectWithManyAttributes() {
    goTo("http://localhost:4567/");
    fill("#restaurantName").with("LBB");
    fill("#restaurantType").with("sushi");
    fill("#restaurantNotes").with("The restaurant was always busy.");
    Select select = new Select(webDriver.findElement(By.id("restaurantCost")));
    select.selectByValue("deluxe");
    submit(".btn");
    //click("a", withText("Go Back"));
    assertThat(pageSource()).contains("The sushi restaurant LBB has deluxe pricing. I had this to say about it: The restaurant was always busy.");
  }

  @Test
  public void createsMultipleRestaurantObjectsWithManyAttributes() {
    goTo("http://localhost:4567/");
    fill("#restaurantName").with("LBB");
    fill("#restaurantType").with("sushi");
    fill("#restaurantNotes").with("The restaurant was always busy.");
    Select select = new Select(webDriver.findElement(By.id("restaurantCost")));
    select.selectByValue("deluxe");
    submit(".btn");
    //click("a", withText("Go Back"));
    fill("#restaurantName").with("sushi land");
    fill("#restaurantType").with("burger");
    fill("#restaurantNotes").with("The restaurant was always empty.");
    Select newSelect = new Select(webDriver.findElement(By.id("restaurantCost")));
    newSelect.selectByValue("good value");
    submit(".btn");
    //click("a", withText("Go Back"));
    assertThat(pageSource()).contains("The sushi restaurant LBB has deluxe pricing. I had this to say about it: The restaurant was always busy.");
    assertThat(pageSource()).contains("The burger restaurant sushi land has good value pricing. I had this to say about it: The restaurant was always empty.");
  }
}
