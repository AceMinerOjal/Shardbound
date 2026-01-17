package entity.player.classes;

import main.KeyHandler;

import entity.Player;
import entity.player.stats.*;
import entity.Health;

public class Priest extends Player {
  public Priest(double x, double y, KeyHandler kh) {
    super(x, y, kh);

    this.hp = new Health(50, 50, 0.5);
    this.mana = new Mana(20, 20, 0.2);
    this.ap = new Dialectics(10);
    this.defence = new Dialectics(10);
  }

  @Override
  protected void performSkill(int num) {
    switch (num) {
      case 0 -> clense();
      case 1 -> levitate();
      case 2 -> holyNova();
      case 3 -> divineIntervention();
    }
  }

  @Override
  protected void lastItem() {
  }

  private void clense() {
  }

  private void levitate() {
  }

  private void holyNova() {
  }

  private void divineIntervention() {
  }
}
