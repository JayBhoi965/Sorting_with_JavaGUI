 
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

        numbers = readNumbersFromFile("BestCase.txt");
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

} 
 
