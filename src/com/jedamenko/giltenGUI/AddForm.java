package com.jedamenko.giltenGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jedamenko.gilten.RecipeDAO;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddForm extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private List<JTextField> textFieldList = new ArrayList<>();
	private RecipeDAO dao;
	private MedicalManagementSystem mms;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddForm dialog = new AddForm();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddForm(RecipeDAO dao, MedicalManagementSystem mms)
	{
		this();
		this.dao = dao;
		this.mms = mms;
	}
	public AddForm() {
		setTitle("Patient");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		{
			List<JLabel> labelList = new ArrayList<>();
			for (String name : column_names)
			{
				labelList.add(new JLabel(name));
				textFieldList.add(new JTextField());
			}
			for (int i = 0; i<labelList.size(); i++)
			{
				contentPanel.add(labelList.get(i), "2, 2, right, default");
				JTextField textField = textFieldList.get(i);
				contentPanel.add(textField, "4, 2, fill, default");
				textField.setColumns(25);
			}
				
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Add");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						saveData();
						//to receive a data from a form and return it to invoker 
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	protected void saveData()
	{
		String[] entries = new String[dao.getColumn_names().size()];
		for (int i=0; i<entries.length; i++)
			entries[i]= textFieldList.get(i).getText();
	}

}