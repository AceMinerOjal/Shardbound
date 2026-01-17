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
    double cost = 6;
    if (!mana.canSpend(cost)) {
      System.out.println("Priest: not enough mana for Cleanse.");
      return;
    }
    mana.spend(cost);
    // Remove negative effects and do double ap damage only to undead : TODO
    setAnimation(AnimationState.ATTACK);
  }

  private void levitate() {
    double cost = 8;
    if (!mana.canSpend(cost)) {
      System.out.println("Priest: not enough mana for Levitate.");
      return;
    }
    mana.spend(cost);
    // Immune to AOE damage: TODO
    setAnimation(AnimationState.ATTACK);
  }

  private void holyNova() {
    double cost = 20;
    if (!mana.canSpend(cost)) {
      System.out.println("Priest: not enough mana for Holy Nova.");
      return;
    }
    mana.spend(cost);
    // Heal and increase mana regen of nearby allies : TODO
    setAnimation(AnimationState.ATTACK);
  }

  private void divineIntervention() {
    double cost = 40;
    if (!mana.canSpend(cost)) {
      System.out.println("Priest: not enough mana for Divine Intervention.");
      return;
    }
    mana.spend(cost);
    // Apply multipliers to ally stats : TODO
    setAnimation(AnimationState.ATTACK);
  }
}
