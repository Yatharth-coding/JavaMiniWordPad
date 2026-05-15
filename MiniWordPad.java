import java.awt.*;
import java.io.*;
import javax.swing.*;

public class MiniWordPad {

    JFrame frame;
    JTextArea textArea;

    MiniWordPad() {
        frame = new JFrame("Mini WordPad - Yatharth Yadav (23BCE10354)");
        textArea = new JTextArea();

        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane);

        JMenuBar menuBar = new JMenuBar();

        // FILE MENU
        JMenu fileMenu = new JMenu("File");
        JMenuItem newFile = new JMenuItem("New");
        JMenuItem openFile = new JMenuItem("Open");
        JMenuItem saveFile = new JMenuItem("Save");

        fileMenu.add(newFile);
        fileMenu.add(openFile);
        fileMenu.add(saveFile);

        // EDIT MENU
        JMenu editMenu = new JMenu("Edit");
        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem paste = new JMenuItem("Paste");

        editMenu.add(cut);
        editMenu.add(copy);
        editMenu.add(paste);

        // FORMAT MENU
        JMenu formatMenu = new JMenu("Format");
        JMenuItem fontSize = new JMenuItem("Font Size");
        JMenuItem textColor = new JMenuItem("Text Color");

        formatMenu.add(fontSize);
        formatMenu.add(textColor);

        // TOOLS MENU (CUSTOM FEATURE)
        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem wordCount = new JMenuItem("Word Count");

        toolsMenu.add(wordCount);

        // ADD MENUS
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(formatMenu);
        menuBar.add(toolsMenu);

        frame.setJMenuBar(menuBar);

        // ===== ACTIONS =====

        // New File
        newFile.addActionListener(e -> textArea.setText(""));

        // Open File
        openFile.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(frame);
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = fileChooser.getSelectedFile();
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    textArea.read(br, null);
                    br.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Save File
        saveFile.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showSaveDialog(frame);
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = fileChooser.getSelectedFile();
                    BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                    textArea.write(bw);
                    bw.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Cut, Copy, Paste
        cut.addActionListener(e -> textArea.cut());
        copy.addActionListener(e -> textArea.copy());
        paste.addActionListener(e -> textArea.paste());

        // Change Font Size
        fontSize.addActionListener(e -> {
            String size = JOptionPane.showInputDialog(frame, "Enter Font Size:");
            if (size != null) {
                try {
                    int fontSizeValue = Integer.parseInt(size);
                    textArea.setFont(new Font("Arial", Font.PLAIN, fontSizeValue));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid size!");
                }
            }
        });

        // Change Text Color
        textColor.addActionListener(e -> {
            Color color = JColorChooser.showDialog(frame, "Choose Text Color", Color.BLACK);
            if (color != null) {
                textArea.setForeground(color);
            }
        });


        wordCount.addActionListener(e -> {
            String text = textArea.getText().trim();

            int wordCountValue = 0;
            if (!text.isEmpty()) {
                wordCountValue = text.split("\\s+").length;
            }

            int charCount = text.length();

            JOptionPane.showMessageDialog(frame,
                    "Words: " + wordCountValue + "\nCharacters: " + charCount,
                    "Word Count",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        // FRAME SETTINGS
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MiniWordPad();
    }
}
