import javax.swing.*;
import java.awt.*;

class BarPanel extends JPanel {
    private int[] array;

    public BarPanel(int[] array) {
        this.array = array;
    }

    public void updateArray(int[] array) {
        this.array = array;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);

        int width = getWidth();
        int height = getHeight();
        int barWidth = width / array.length;

        for (int i = 0; i < array.length; i++) {
            int barHeight = (int)(((double)array[i] / getMax()) * height);
            g.setColor(Color.BLUE);
            g.fillRect(i * barWidth, height - barHeight, barWidth, barHeight);
        }
    }

    private int getMax() {
        int max = Integer.MIN_VALUE;
        for (int value : array) {
            if (value > max) max = value;
        }
        return max;
    }
}
