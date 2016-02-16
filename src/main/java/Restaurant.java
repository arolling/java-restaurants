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

  public String setType(String type) {
    mType = type;
    return mType;
  }

  public String setNotes(String notes) {
    mNotes = notes;
    return mNotes;
  }

  public String getDescription() {
    return "The " + mType + " restaurant " + mName + " has " + mPriceRange + " pricing. I had this to say about it:<br>"  + mNotes;
  }
}
