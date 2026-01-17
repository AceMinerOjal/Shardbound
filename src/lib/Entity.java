package lib;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.awt.Graphics2D;

public abstract class Entity {
  protected double x, y;
  protected final double SPEED = 30;

  protected BufferedImage walkSheet;
  protected BufferedImage actionSheet;

  public enum Direction {
    UP, DOWN, LEFT, RIGHT
  }

  protected Direction direction = Direction.DOWN;

  public enum AnimationState {
    IDLE, WALK, ATTACK, DIE
  }

  private AnimationState currentAnimation = AnimationState.IDLE;
  private int currentFrame = 0;
  private float frameTimer = 0f;
  private float frameDuration = 0.2f;

  private final Map<AnimationState, Integer> animationFrames = new HashMap<>();

  public Entity() {
    animationFrames.put(AnimationState.IDLE, 1);
    animationFrames.put(AnimationState.WALK, 4);
    animationFrames.put(AnimationState.ATTACK, 3);
    animationFrames.put(AnimationState.DIE, 5);
  }

  public void updateAnimation(float dt) {
    frameTimer += dt;

    if (frameTimer >= frameDuration) {
      frameTimer = 0f;
      currentFrame++;
      int maxFrames = animationFrames.getOrDefault(currentAnimation, 1);
      if (currentFrame >= maxFrames) {
        currentFrame = 0;
      }
    }
  }

  public int getSpriteRow() {
    int baseRow = switch (getCurrentAnimation()) {
      case IDLE -> 0;
      case WALK -> 3;
      case ATTACK -> 6;
      case DIE -> 9;
    };

    int directionOffset = switch (direction) {
      case DOWN -> 0;
      case UP -> 1;
      case LEFT, RIGHT -> 2;
    };

    return baseRow + directionOffset;
  }

  public void setAnimation(AnimationState state) {
    if (state != currentAnimation) {
      currentAnimation = state;
      currentFrame = 0;
      frameTimer = 0f;
    }
  }

  public AnimationState getCurrentAnimation() {
    return currentAnimation;
  }

  public int getCurrentFrame() {
    return currentFrame;
  }

  public void move(Direction dir, double dt) {
    direction = dir;

    switch (dir) {
      case UP -> y -= SPEED * dt;
      case DOWN -> y += SPEED * dt;
      case LEFT -> x -= SPEED * dt;
      case RIGHT -> x += SPEED * dt;
    }

    setAnimation(AnimationState.WALK);
  }

  public void stop() {
    setAnimation(AnimationState.IDLE);
  }

  public void draw(Graphics2D g) {
    BufferedImage currentSheet;
    int rowOffset;

    switch (getCurrentAnimation()) {
      case IDLE -> {
        currentSheet = walkSheet;
        rowOffset = 0; // Assuming Idle is the first set of rows in walkSheet
      }
      case WALK -> {
        currentSheet = walkSheet;
        rowOffset = 3; // Offset if Walk rows are below Idle
      }
      case ATTACK -> {
        currentSheet = actionSheet;
        rowOffset = 0;
      }
      case DIE -> {
        currentSheet = actionSheet;
        rowOffset = 3;
      }
      default -> {
        currentSheet = walkSheet;
        rowOffset = 0;
      }
    }

    int directionOffset = switch (direction) {
      case DOWN -> 0;
      case UP -> 1;
      case LEFT, RIGHT -> 2;
    };

    int row = rowOffset + directionOffset;

    renderFrame(g, currentSheet, row);
  }

  private void renderFrame(Graphics2D g, BufferedImage sheet, int row) {
    int tw = 32;
    int th = 32;

    int sx = getCurrentFrame() * tw;
    int sy = row * th;

    if (direction == Direction.RIGHT) {
      g.drawImage(sheet, (int) x, (int) y, (int) x + tw, (int) y + th,
          sx + tw, sy, sx, sy + th, null);
    } else {
      g.drawImage(sheet, (int) x, (int) y, (int) x + tw, (int) y + th,
          sx, sy, sx + tw, sy + th, null);
    }
  }
}
