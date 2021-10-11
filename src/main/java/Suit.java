import java.util.ArrayList;
import java.util.List;

public enum Suit {
  方片,
  红桃,
  梅花,
  黑桃;

  public static List<Suit> getAllSuits() {
    List<Suit> suits = new ArrayList<>();
    suits.add(方片);
    suits.add(红桃);
    suits.add(梅花);
    suits.add(黑桃);
    return suits;
  }
}
