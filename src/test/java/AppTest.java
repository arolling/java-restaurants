import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
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
  public void generatesSuccessPage() {
    goTo("http://localhost:4567/");
    fill("#restaurantName").with("LBB");
    submit(".btn");
    assertThat(pageSource()).contains("Go Back");
  }

  @Test
  public void createsARestaurantObject() {
    goTo("http://localhost:4567/");
    fill("#restaurantName").with("LBB");
    submit(".btn");
    click("a", withText("Go Back"));
    assertThat(pageSource()).contains("LBB");
  }
}
