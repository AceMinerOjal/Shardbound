package entity.player.stats;

import lib.Stat;

public class Dialectics extends Stat {

  public Dialectics(double base) {
    super(base, -1, 0);
  }

  @Override
  public void scale(int level) {
    this.value = this.base + (this.base * 0.08 * level);
  }
}
