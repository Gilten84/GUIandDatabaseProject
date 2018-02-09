package giltenGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jedamenko.gilten.DBCommonObject;
import com.jedamenko.gilten.Doctor;
import com.jedamenko.gilten.RecipeDAO;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.awt.event.ActionEvent;

public class MedicalManagementSystem extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private RecipeDAO dao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MedicalManagementSystem frame = new MedicalManagementSystem();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MedicalManagementSystem() {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream("sql\\demo.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File file = new File("sql\\schema.properties");
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");
		this.dao = new RecipeDAO(file, dburl,user,password);
		setFont(new Font("Arial", Font.PLAIN, 12));
		setTitle("Medical management system");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Enter last name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		panel.add(separator);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try
				{
					List<DBCommonObject> list = new ArrayList<>();
					String lastName = textField.getText();
					if (lastName!=null && lastName.trim().length()>0)
					{
						list = dao.selectData("doctors", "select * from doctors where doctor_last_name like \""+lastName+"\"");
					}else
					{
						list = dao.selectData("doctors","select * from doctors");
					}
					for (DBCommonObject obj : list)
					{
						Doctor d = (Doctor) obj;
						System.out.println(d);
					}
						
				}catch (Exception ex)
				{
					JOptionPane.showMessageDialog(MedicalManagementSystem.this, "Error: "+ex,"Error",JOptionPane.CANCEL_OPTION);
				}
			}
		});
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}

}
