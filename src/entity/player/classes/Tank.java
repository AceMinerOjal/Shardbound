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
    double cost = 5;
    if (!mana.canSpend(cost)) {
      return;
    }
    mana.spend(cost);
    // Grants few seconds of immunity: TODO
    setAnimation(AnimationState.ATTACK);
  }

  private void bash() {
    double cost = 6;
    if (!mana.canSpend(cost)) {
      return;
    }
    mana.spend(cost);
    // Small distance dash with damage : TODO
    setAnimation(AnimationState.ATTACK);
  }

  private void wall() {
    double cost = 10;
    if (!mana.canSpend(cost)) {
      return;
    }
    mana.spend(cost);
    // Create a defensive nova to grant immunity: TODO
    setAnimation(AnimationState.ATTACK);
  }

  private void taunt() {
    double cost = 4;
    if (!mana.canSpend(cost)) {
      return;
    }
    mana.spend(cost);
    // Forces enemies to target the tank: TODO
    setAnimation(AnimationState.ATTACK);
  }
}
