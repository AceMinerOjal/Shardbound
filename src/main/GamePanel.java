package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import javax.swing.JPanel;

import entity.Player;
import entity.player.classes.Mage;

public class GamePanel extends JPanel implements Runnable {

  private static final int TILE_SIZE = 32;
  private static final int BASE_WIDTH = 640;
  private static final int BASE_HEIGHT = 360;
  private static final int UPS = 30;

  final int actualTileSize;
  final int screenWidth;
  final int screenHeight;

  private Thread gameThread;
  private KeyHandler kh = new KeyHandler();
  Player player = new Mage(100, 100, kh);

  public GamePanel() {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    int scale = Math.max(1, Math.min(screenSize.width / BASE_WIDTH, screenSize.height / BASE_HEIGHT));

    actualTileSize = TILE_SIZE * scale;
    screenWidth = BASE_WIDTH * scale;
    screenHeight = BASE_HEIGHT * scale;

    setPreferredSize(new Dimension(screenWidth, screenHeight));
    setBackground(Color.BLACK);
    setDoubleBuffered(true);
    addKeyListener(kh);
    setFocusable(true);
  }

  public void startGameThread() {
    gameThread = new Thread(this, "GameThread");
    gameThread.start();
  }

  @Override

  public void run() {
    // The mystical interval that separates “frames” from “eternal suffering”
    final double drawInterval = 1_000_000_000.0 / UPS; // nanoseconds per frame, aka Goku’s power level per second

    long tLastTimeOnDBZ = System.nanoTime(); // time of last frame, measured in Super Saiyan units
    double deltaPowerUp = 0.0; // how much time we owe the universe
    double dtPerFrame = 1.0 / UPS; // each frame is a tiny slice of eternity

    // The Infinite Game Loop: like One Piece, it never ends
    while (gameThread != null) {
      long tCurrentTime = System.nanoTime(); // current moment in the anime timeline
      long elapsedTimeSinceLastSaga = tCurrentTime - tLastTimeOnDBZ; // how long since last dramatic cliffhanger
      tLastTimeOnDBZ = tCurrentTime;

      // deltaPowerUp accumulates like your waifu points or regrets from skipping
      // sleep
      deltaPowerUp += elapsedTimeSinceLastSaga / drawInterval;

      // Level up the game state until deltaPowerUp runs out of energy
      while (deltaPowerUp >= 1) {
        update(dtPerFrame); // transform the frame into reality
        deltaPowerUp--;
      }

      repaint(); // give your pixels a makeover, like a new OP animation

      // Let the thread nap like it’s recovering from a final boss fight
      long napTimeForThread = (long) ((drawInterval - elapsedTimeSinceLastSaga) / 1_000_000);
      if (napTimeForThread > 0) {
        try {
          Thread.sleep(napTimeForThread); // thread hits the Z-sleep button
        } catch (InterruptedException e) {
          e.printStackTrace(); // RIP thread, blame the anime plot twist
        }
      }
    }

    // If you survived this loop, consider yourself a Legendary Super Programmer
  }

  public void update(double dt) {
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(Color.WHITE);
    g2.fillRect(20, 20, actualTileSize, actualTileSize);
    g2.dispose();
  }
}
