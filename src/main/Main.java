package main;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      JFrame frame = new JFrame("Legend");

      GamePanel gamePanel = new GamePanel();
      frame.add(gamePanel);
      frame.pack();

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setResizable(false);
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
    });
  }
}
