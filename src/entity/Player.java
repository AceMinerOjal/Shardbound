package entity;

import lib.Entity;
import main.KeyHandler;
import java.awt.event.KeyEvent;

import entity.player.stats.*;

public abstract class Player extends Entity {

  protected Health hp;
  protected Mana mana;
  protected Dialectics ap;
  protected Dialectics defence;
  protected Level level;

  private KeyHandler kh;

  public Player(double x, double y, KeyHandler kh) {
    this.x = x;
    this.y = y;
    this.kh = kh;
    this.level = new Level();
  }

  public void update(double dt) {
    handleMovement(dt);
    handleInputs();
    hp.update(dt);
    mana.update(dt);
    ap.update(dt);
    defence.update(dt);
  }

  private void handleInputs() {
    int[] skillKeys = { KeyEvent.VK_1, KeyEvent.VK_2, KeyEvent.VK_3, KeyEvent.VK_4 };

    boolean isShifting = kh.isDown(KeyEvent.VK_SHIFT);

    for (int i = 0; i < skillKeys.length; i++) {
      if (kh.isTriggered(skillKeys[i])) {
        if (isShifting) {
          activateItem(i);
        } else {
          performSkill(i);
          setAnimation(AnimationState.ATTACK);
        }
        return;
      }
    }
  }

  private void handleMovement(double dt) {
    int dy = ((kh.isDown(KeyEvent.VK_S)) ? 1 : 0)
        - ((kh.isDown(KeyEvent.VK_W)) ? 1 : 0);
    int dx = ((kh.isDown(KeyEvent.VK_D)) ? 1 : 0)
        - ((kh.isDown(KeyEvent.VK_A)) ? 1 : 0);

    if (dy != 0) {
      direction = (dy > 0) ? Direction.DOWN : Direction.UP;
    } else if (dx != 0) {
      direction = (dx > 0) ? Direction.RIGHT : Direction.LEFT;
    }

    double mag = Math.hypot(dx, dy);
    double vx, vy;

    if (mag > 0) {
      vx = dx / mag;
      vy = dy / mag;
      setAnimation(AnimationState.WALK);
    } else {
      vx = vy = 0.0;
      setAnimation(AnimationState.IDLE);
    }

    x += vx * SPEED * dt;
    y += vy * SPEED * dt;
  }

  protected abstract void performSkill(int skillNum);

  private void activateItem(int slot) {
    switch (slot) {
      case 0 -> {
      }
      case 1 -> {
      }
      case 2 -> {
      }
      case 3 -> lastItem();
    }
  }

  public void gainExp(int amount) {
    int oldLevel = level.getLevel();
    level.addExp(amount);
    int newLevel = level.getLevel();

    if (newLevel > oldLevel) {
      onLevelUp(newLevel);
    }
  }

  private void onLevelUp(int level) {
    hp.scale(level);
    mana.scale(level);
    ap.scale(level);
    defence.scale(level);
  }

  protected abstract void lastItem();
}
