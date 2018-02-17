package com.jedamenko.giltenGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jedamenko.gilten.RecipeDAO;

import java.awt.Font;

import javax.swing.JOptionPane;

import java.awt.FlowLayout;

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
		this.dao = new RecipeDAO(dburl,user,password);
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
		
		JButton btnPatients = new JButton("Patients");
		panel.add(btnPatients);
		btnPatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try
				{
					List<String[]> list = new ArrayList<>();
					list = dao.selectData("patients", "select * from patients");						
					if(!list.isEmpty())
					{
						RecipeDAOTableModel model = new RecipeDAOTableModel(list, dao.getColumn_names());
						table.setModel(model);
					}
				}catch (Exception ex)
				{
					JOptionPane.showMessageDialog(MedicalManagementSystem.this, "Error: "+ex,"Error",JOptionPane.CANCEL_OPTION);
				}
			}
		});
		
		JButton btnRecipes = new JButton("Recipes");
		btnRecipes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try
				{
					List<String[]> list = new ArrayList<>();
					list = dao.selectData("recipes", "select * from recipes");						
					if(!list.isEmpty())
					{
						RecipeDAOTableModel model = new RecipeDAOTableModel(list, dao.getColumn_names());
						table.setModel(model);
					}
				}catch (Exception ex)
				{
					JOptionPane.showMessageDialog(MedicalManagementSystem.this, "Error: "+ex,"Error",JOptionPane.CANCEL_OPTION);
				}
			}
		});
		panel.add(btnRecipes);
		
		JButton btnDoctors = new JButton("Doctors");
		btnDoctors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				{
					try
					{
						List<String[]> list = new ArrayList<>();
						list = dao.selectData("doctors", "select * from doctors");						
						if(!list.isEmpty())
						{
							RecipeDAOTableModel model = new RecipeDAOTableModel(list, dao.getColumn_names());
							table.setModel(model);
						}
					}catch (Exception ex)
					{
						JOptionPane.showMessageDialog(MedicalManagementSystem.this, "Error: "+ex,"Error",JOptionPane.CANCEL_OPTION);
					}
				}
			}
		});
		panel.add(btnDoctors);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
	}

}
