package lib;

public abstract class Timer {
  private double duration;
  private double timeLeft;
  private boolean active;

  public Timer(double duration) {
    this.duration = duration;
    this.timeLeft = 0;
    this.active = false;
  }

  public void start() {
    this.timeLeft = duration;
    this.active = true;
  }

  public void update(double dt) {
    if (!active)
      return;

    timeLeft -= dt;
    if (timeLeft <= 0) {
      timeLeft = 0;
      active = false;
      onFinish();
    } else
      onActive();
  }

  public abstract void onFinish();

  public abstract void onActive();

  public void reset() {
    active = false;
    timeLeft = 0;
  }

  public boolean isReady() {
    return !active && timeLeft <= 0;
  }

  public boolean isActive() {
    return active;
  }

  public double getProgress() {
    return duration <= 0 ? 1.0 : 1.0 - (timeLeft / duration);
  }
}
