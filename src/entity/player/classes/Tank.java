package entity.player.classes;

import main.KeyHandler;

import entity.Player;
import entity.player.stats.*;
import entity.Health;

public class Tank extends Player {
  public Tank(double x, double y, KeyHandler kh) {
    super(x, y, kh);

    this.hp = new Health(100, 100, 0.5);
    this.mana = new Mana(10, 10, 0.25);
    this.ap = new Dialectics(10);
    this.defence = new Dialectics(20);
  }

  @Override
  protected void performSkill(int num) {
    switch (num) {
      case 0 -> shield();
      case 1 -> bash();
      case 2 -> wall();
      case 3 -> taunt();
    }
  }

  @Override
  protected void lastItem() {
  }

  private void shield() {
  }

  private void bash() {
  }

  private void wall() {
  }

  private void taunt() {
  }
}
