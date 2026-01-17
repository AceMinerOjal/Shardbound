package entity.player.stats;

public class Level {
  private int level;
  private int exp;

  private static final int BASE_XP = 20;

  public Level() {
    this.level = 0;
    this.exp = 0;
  }

  public Level(int level, int exp) {
    this.level = level;
    this.exp = exp;
  }

  public void addExp(int amount) {
    if (amount <= 0)
      return;
    exp += amount;

    int required = requiredExp();
    while (exp >= required) {
      exp -= required;
      level++;
      required = requiredExp();
    }
  }

  public int requiredExp() {
    return (int) Math
        .floor(BASE_XP * (Math.pow((double) level, Math.log(level + 1) / Math.log(4))
            + (1.5 * Math.min(level, 6))));
  }

  public int getLevel() {
    return level;
  }

  public int getExp() {
    return exp;
  }
}
