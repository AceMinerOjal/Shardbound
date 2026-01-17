package entity.player.classes;

import main.KeyHandler;

import entity.Player;
import entity.player.stats.*;
import entity.Health;

public class Mage extends Player {
  public Mage(double x, double y, KeyHandler kh) {
    super(x, y, kh);

    this.hp = new Health(50, 50, 0.25);
    this.mana = new Mana(20, 20, 0.5);
    this.ap = new Dialectics(20);
    this.defence = new Dialectics(10);
  }

  @Override
  protected void performSkill(int num) {
    switch (num) {
      case 0 -> ball();
      case 1 -> teleport();
      case 2 -> nova();
      case 3 -> meteor();
    }
  }

  @Override
  protected void lastItem() {
  }

  private void ball() {
  }

  private void teleport() {
  }

  private void nova() {
  }

  private void meteor() {
  }
}
