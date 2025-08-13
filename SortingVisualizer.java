 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class SortingVisualizer extends JFrame {
    private int[] numbers;
    private int[] originalNumbers;
    private BarPanel barPanel;
    private JComboBox<String> algorithmSelector;
    private JButton sortButton;
    private JButton saveButton;
    private JButton resetButton;

    public SortingVisualizer() {
        setTitle("Sorting Visualizer");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        numbers = readNumbersFromFile("worstCase.txt");
        originalNumbers = Arrays.copyOf(numbers, numbers.length);
        barPanel = new BarPanel(numbers);
        add(barPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        algorithmSelector = new JComboBox<>(new String[] {
            "Bubble Sort", "Selection Sort", "Insertion Sort", "Merge Sort", "Quick Sort"
        });
        sortButton = new JButton("Sort");
        saveButton = new JButton("Save Output");
        resetButton = new JButton("Reset");

        controlPanel.add(new JLabel("Choose Algorithm: "));
        controlPanel.add(algorithmSelector);
        controlPanel.add(sortButton);
        controlPanel.add(saveButton);
        controlPanel.add(resetButton);
        add(controlPanel, BorderLayout.SOUTH);

        sortButton.addActionListener(e -> startSorting());
        saveButton.addActionListener(e -> saveSortedOutput());
        resetButton.addActionListener(e -> resetArray());

        setVisible(true);
    }

 
    private void startSorting() {
        String algo = (String) algorithmSelector.getSelectedItem();
        int[] arr = Arrays.copyOf(numbers, numbers.length);

        new Thread(() -> {
            try {
                switch (algo) {
                    case "Bubble Sort":
                        SortAlgorithms.bubbleSort(arr, barPanel);
                        break;
                    case "Selection Sort":
                        SortAlgorithms.selectionSort(arr, barPanel);
                        break;
                    case "Insertion Sort":
                        SortAlgorithms.insertionSort(arr, barPanel);
                        break;
                    case "Merge Sort":
                        SortAlgorithms.mergeSort(arr, barPanel);
                        break;
                    case "Quick Sort":
                        SortAlgorithms.quickSort(arr, barPanel);
                        break;
                }
                numbers = Arrays.copyOf(arr, arr.length);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }).start();
    }

  private void saveSortedOutput() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("sorted_output.txt"))) {
            for (int i = 0; i < numbers.length; i++) {
                writer.write(String.valueOf(numbers[i]));
                if (i < numbers.length - 1) writer.write(",");
            }
            JOptionPane.showMessageDialog(this, "Sorted output saved to sorted_output.txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving file!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void resetArray() {
        numbers = Arrays.copyOf(originalNumbers, originalNumbers.length);
        barPanel.updateArray(numbers);
    }

    private int[] readNumbersFromFile(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            if (line == null || line.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "num.txt is empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return new int[0];
            }
            String[] tokens = line.split(",");
            int[] nums = new int[tokens.length];
            for (int i = 0; i < tokens.length; i++) {
                nums[i] = Integer.parseInt(tokens[i].trim());
            }
            reader.close();
            return nums;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "num.txt not found!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return new int[0];
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SortingVisualizer::new);
    }
 

   
} 
 
