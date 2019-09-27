package movierental;

import movierental.priceclass.Adult;
import movierental.priceclass.Childrens;
import movierental.priceclass.NewRelease;
import movierental.priceclass.Regular;

public class MovieFactory {

  public static Movie regular(String title) {
    return new Movie(title, new Regular());
  }

  public static Movie newRelease(String title) {
    return new Movie(title, new NewRelease());
  }

  public static Movie childrens(String title) {
    return new Movie(title, new Childrens());
  }

  public static Movie adult(String title) {
    return new Movie(title, new Adult());
  }

}
