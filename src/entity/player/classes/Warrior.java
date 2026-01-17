package entity.player.classes;

import main.KeyHandler;

import entity.Player;
import entity.player.stats.*;
import entity.Health;

public class Warrior extends Player {
  public Warrior(double x, double y, KeyHandler kh) {
    super(x, y, kh);

    this.hp = new Health(50, 50, 0.5);
    this.mana = new Mana(20, 20, 0.1);
    this.ap = new Dialectics(20);
    this.defence = new Dialectics(10);
  }

  @Override
  protected void performSkill(int num) {
    switch (num) {
      case 0 -> stab();
      case 1 -> dash();
      case 2 -> stomp();
      case 3 -> cleave();
    }
  }

  @Override
  protected void lastItem() {
  }

  private void stab() {
  }

  private void dash() {
  }

  private void stomp() {
  }

  private void cleave() {
  }
}
