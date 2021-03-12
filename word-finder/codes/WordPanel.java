package findingwordsedited;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.JTextField;
import java.awt.Color;

import java.awt.EventQueue;

@SuppressWarnings("serial")
public class WordPanel extends JFrame {

	String inputWords;
	IOFile file;
	JTextArea textArea;
	JFrame frame;
	JTextField textField;
	JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WordPanel window = new WordPanel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WordPanel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(new Color(255, 255, 255));
		frame.getContentPane().setBackground(new Color(153, 102, 102));
		frame.getContentPane().setForeground(new Color(153, 102, 102));
		frame.getContentPane().setFont(new Font("Yu Gothic UI", Font.BOLD, 22));
		frame.setBounds(100, 100, 901, 540);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("Submit And Action");
		btnNewButton.setForeground(new Color(102, 102, 0));
		btnNewButton.setBackground(new Color(204, 204, 153));
		btnNewButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 22));
		btnNewButton.setBounds(612, 406, 252, 49);
		frame.getContentPane().add(btnNewButton);

		JLabel lblText = new JLabel("Text:");
		lblText.setFont(new Font("Yu Gothic UI", Font.PLAIN, 22));
		lblText.setBounds(15, 16, 113, 49);
		frame.getContentPane().add(lblText);

		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		textArea.setBackground(new Color(255, 204, 255));
		textArea.setBounds(15, 73, 849, 138);

		fileReader fr = new fileReader();
		try {
			textArea.setText(fr.fileRead());
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		frame.getContentPane().add(textArea);

		JLabel lblEnterTheWords = new JLabel("Enter The Words You Are Looking For: ");
		lblEnterTheWords.setFont(new Font("Yu Gothic UI", Font.PLAIN, 22));
		lblEnterTheWords.setBounds(15, 242, 377, 36);
		frame.getContentPane().add(lblEnterTheWords);

		textField = new JTextField();
		textField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		textField.setBackground(new Color(255, 204, 255));
		textField.setBounds(15, 286, 849, 104);

		frame.getContentPane().add(textField);
		textField.setColumns(10);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new IOFile();
				IOFile inputWords = new IOFile();
				String  text = textField.getText();
				textArea.setText(inputWords.IOfile(text));
			}

		});
	}

	public void setTextArea(String line) {

		textArea.setText(line);
	}

	public String setTextField(String text) {
		textField.setText(text);
		return text;
	}

}
