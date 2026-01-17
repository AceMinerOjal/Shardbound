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

  // Damaging small have a chance for a character's element's status effect
  private void stab() {
    double cost = 5;
    if (!mana.canSpend(cost)) {
      return;
    }
    mana.spend(cost);
    // Primary attack: TODO
    setAnimation(AnimationState.ATTACK);
  }

  private void dash() {
    double cost = 8;
    if (!mana.canSpend(cost)) {
      return;
    }
    mana.spend(cost);
    double dist = 32; // WIP
    switch (direction) {
      case UP -> y -= dist;
      case DOWN -> y += dist;
      case LEFT -> x -= dist;
      case RIGHT -> x += dist;
    }
    setAnimation(AnimationState.ATTACK);
  }

  private void stomp() {
    double cost = 10;
    if (!mana.canSpend(cost)) {
      return;
    }
    mana.spend(cost);
    // AOE damage: TODO
    setAnimation(AnimationState.ATTACK);
  }

  private void cleave() {
    double cost = 12;
    if (!mana.canSpend(cost)) {
      return;
    }
    mana.spend(cost);
    // Big damage: TODO
    setAnimation(AnimationState.ATTACK);
  }
}
