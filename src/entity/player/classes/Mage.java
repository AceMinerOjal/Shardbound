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
      case 3 -> explosion();
    }
  }

  @Override
  protected void lastItem() {
  }

  // Damaging skills have a chance for a character's element's status effect
  private void ball() {
    double cost = 6;
    if (!mana.canSpend(cost)) {
      return;
    }
    mana.spend(cost);
    // Primary attack: TODO
    setAnimation(AnimationState.ATTACK);
  }

  private void teleport() {
    double cost = 12;
    if (!mana.canSpend(cost)) {
      return;
    }
    mana.spend(cost);
    double dist = 96;
    switch (direction) {
      case UP -> y -= dist;
      case DOWN -> y += dist;
      case LEFT -> x -= dist;
      case RIGHT -> x += dist;
    }
    setAnimation(AnimationState.ATTACK);
  }

  private void nova() {
    double cost = 18;
    if (!mana.canSpend(cost)) {
      return;
    }
    mana.spend(cost);
    // AOE damage: TODO
    setAnimation(AnimationState.ATTACK);
  }

  private void explosion() {
    double cost = 20;
    if (!mana.canSpend(cost)) {
      return;
    }
    mana.spend(cost);
    // Big damage: TODO
    setAnimation(AnimationState.ATTACK);
  }
}
