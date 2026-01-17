package entity.statusEffects;

import java.util.List;
import lib.Entity;

public class LightningConductive extends StatusEffect {

  private final double damagePerTick;
  private final double chainDamageMultiplier;
  private final double chainRadius;

  public LightningConductive(double durationSeconds, double damagePerTick, double tickInterval,
      double chainDamageMultiplier, double chainRadius) {
    super("Conductive", durationSeconds, tickInterval);
    this.damagePerTick = damagePerTick;
    this.chainDamageMultiplier = chainDamageMultiplier;
    this.chainRadius = chainRadius;
  }

  @Override
  protected void onTick() {
    if (target == null)
      return;
    target.applyDamage(damagePerTick);

    try {
      List<Entity> nearby = target.getNearbyEntities(chainRadius);
      if (nearby != null && !nearby.isEmpty()) {
        for (Entity e : nearby) {
          if (e == null)
            continue;
          if (e == target)
            continue;
          if (e instanceof EffectTarget et) {
            double chained = damagePerTick * chainDamageMultiplier;
            et.applyDamage(chained);
          }
        }
      }
    } catch (Throwable t) {
      t.printStackTrace();
    }
  }

  @Override
  public void onFinish() {
  }
}
