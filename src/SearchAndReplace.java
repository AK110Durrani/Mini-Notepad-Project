import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchAndReplace extends JFrame {
    JTextArea text;

    JLabel searchlabel = new JLabel("Search");
    JTextField searchField = new JTextField(20);

    JLabel replacelabel = new JLabel("Replace");
    JTextField replaceField = new JTextField(20);

    JButton replaceButton = new JButton("Replace");

    GridLayout layout = new GridLayout(3, 2, 5, 5);

    public SearchAndReplace(JTextArea textAre) {
        this.text = textAre;
        setLayout(layout);
        add(searchlabel);
        add(searchField);
        add(replacelabel);
        add(replaceField);
        add(new JLabel(""));
        add(replaceButton);
        setSize(200, 100);

        replaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text.setText((text.getText().replaceAll(searchField.getText(), replaceField.getText())));
            }
        });

        setTitle("Search and Replace");
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);


    }


}
