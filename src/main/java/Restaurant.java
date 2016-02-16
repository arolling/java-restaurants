public class Restaurant {

  private String mName;
  private String mType;
  private String mPriceRange;
  private String mNotes;

  public Restaurant(String name) {
    mName = name;
  }

  public String setPriceRange(String priceRange) {
    mPriceRange = priceRange;
    return mPriceRange;
  }

}
