import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Vector;

public class MainWindow extends JFrame {
    String title = "Mini NotePad";
    private final int WIDTH = 400;
    private final int HEIGHT = 400;
    JTextArea textArea = new JTextArea(200, 200);
    JPanel undoredoPanel = new JPanel();
    JButton undoButton = new JButton("Undo");
    JButton redoButton = new JButton("Redo");
    char lastChar;

    Stack undoKeys = new Stack();
    FlowLayout undolayout = new FlowLayout(FlowLayout.LEFT);
    JPanel pridictionPanel = new JPanel(new FlowLayout());
    JLabel labels[] = new JLabel[5];
    int lastspaceIndex = 0;
    LinkedList wordList = new LinkedList();

    public MainWindow() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu("File");
        JMenuItem item1 = new JMenuItem("New");
        JMenuItem save = new JMenuItem("Save");
        undoredoPanel.setLayout(undolayout);
        undoredoPanel.add(undoButton);
        undoredoPanel.add(redoButton);
        add(undoredoPanel, BorderLayout.NORTH);
        for (int i = 0, labelsLength = labels.length; i < labelsLength; i++) {
            labels[i] = new JLabel("");
            JLabel label = labels[i];
            pridictionPanel.add(label);
            labels[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    replaceWord(((JLabel) e.getSource()).getText());
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
        }

        menu1.add(item1);
        menu1.add(save);

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    File file = new File("file" + LocalDate.now() +
                            " " + LocalTime.now().getHour() + " - " +
                            LocalTime.now().getMinute() +
                            " - " + LocalTime.now().getSecond() + ".txt");

                    FileWriter fileWriter = new FileWriter(file);
                    fileWriter.write(textArea.getText());
                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        item1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
            }
        });
        menuBar.add(menu1);
        JMenu menu2 = new JMenu("Search and Replace");
        JMenuItem item2 = new JMenuItem("Seach and Replace");
        menu2.add(item2);
        item2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchAndReplace replacewindow = new SearchAndReplace(textArea);
                replacewindow.setVisible(true);

            }
        });
        textArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                String text = textArea.getText();

                if (text.length() != 0) {
                    lastChar = text.charAt(text.length() - 1);
                }
                if (text.length() <= 1) {
                    return;
                } else {
                    String word = "";
                    if (text.contains(" ")) {
                        String[] wordsS = text.split(" ");
                        word = wordsS[wordsS.length - 1];
                    } else {
                        word = text;
                    }
                    if (word.length() > 0) {
                        Vector<String> words = wordList.PredictWord(word);
                        for (int i = 0; i < words.size(); i++) {
                            labels[i].setText(words.elementAt(i));
                        }
                    }
                }


            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String txt = textArea.getText();
                int length = txt.length();
                undoKeys.push(txt.charAt(length - 1));
                textArea.setText(txt.substring(0, length - 1));
            }
        });
        redoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText(textArea.getText() + ((char) undoKeys.pop()));
            }
        });

        menuBar.add(menu2);

        add(textArea, BorderLayout.CENTER);
        add(pridictionPanel, BorderLayout.SOUTH);
        setJMenuBar(menuBar);
        setTitle(title);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    void replaceWord(String word) {
        String text = textArea.getText();
        if (text.contains(" ")) {
            String strings[] = text.split(" ");
            String w = strings[strings.length - 1];
            int x = text.lastIndexOf(w);

            text = text.substring(0, x);
            text += word;
        } else {
            text = word;
        }
        textArea.setText(text);

    }


}
