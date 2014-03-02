package test;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

public class MIPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea textArea = new JTextArea();
	private JButton clearButton = new JButton("Clear Log");
	private JButton speciesButton = new JButton("Show Species");
	private JButton actionsButton = new JButton("Show Actions");
	private JButton tankButton = new JButton("Show Tank Stock");
	
	public MIPanel()
	{
		//textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setFont(new Font("monospaced", Font.PLAIN, 12));
		DefaultCaret caret = (DefaultCaret)textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		JScrollPane areaScrollPane = new JScrollPane(textArea);
		areaScrollPane.setVerticalScrollBarPolicy(
		                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane.setPreferredSize(new Dimension(780, 330));
		
		JPanel miSubPanelA = new JPanel();
		JPanel miSubPanelB = new JPanel();
		
		miSubPanelA.add(speciesButton); miSubPanelA.add(actionsButton); miSubPanelA.add(tankButton); miSubPanelA.add(clearButton);
		miSubPanelB.add(areaScrollPane);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createTitledBorder("Management Interface"));
		add(miSubPanelA);
		add(miSubPanelB);
		
		setPreferredSize(new Dimension(100, 100));
		setFocusable(true);
		
		OptionListener ol = new OptionListener();
		speciesButton.addActionListener(ol);
		actionsButton.addActionListener(ol);
		tankButton.addActionListener(ol);
		clearButton.addActionListener(ol);
		
	}
	
	private class OptionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            Object source = event.getSource();
            if (source.equals(speciesButton))
            {
            	getSpecies();
            }
            if (source.equals(actionsButton))
            {
            	getActions();
            }
            if (source.equals(tankButton))
            {
            	getTankStock();
            }
            if (source.equals(clearButton))
            {
            	textArea.setText("");
            }
        }
    }
	
	private void getSpecies()
	{
		try
		{
			Connection connection = null;
			textArea.append("Connecting to species_database...\n");
			connection = DriverManager.getConnection(TemperatureRegulator.url, TemperatureRegulator.username, TemperatureRegulator.password);
			Statement stmt = connection.createStatement();
			String query = "select * from species_database";
			ResultSet rs = stmt.executeQuery(query);
			textArea.append(String.format("%-4s %-26s %8s %8s %8s %8s %5s %8s %8s %8s", 
					"ID", "Name","Ammonia", "Nitrate","Nitrite", "Oxygen", "pH", "Salinity", "Temperature", "Water Hardness") + "\n");
			
			while(rs.next())
			{

				textArea.append(String.format("%-4s %-26s %8s %8s %8s %8s %5s %8s %8s %11s", 
						rs.getString("id"), rs.getString("name"), rs.getString("ammonia"),
						rs.getDouble("nitrate"),rs.getDouble("nitrite"),
						rs.getDouble("oxygen"), rs.getDouble("pH"),
						rs.getDouble("salinity"), rs.getDouble("temperature"),
						rs.getDouble("water_hardness")) + "\n"); 
			}
			
			connection.close();
			textArea.append("Species Information Acquired...Connection closed.\n");
		}
		catch(SQLException s)
		{
			textArea.append("\nMySQL error.\n");
			textArea.append(s.getLocalizedMessage());
		}
	}
	private void getActions()
	{
		try
		{
			Connection connection = null;
			textArea.append("Connecting to action_log...\n");
			connection = DriverManager.getConnection(TemperatureRegulator.url, TemperatureRegulator.username, TemperatureRegulator.password);
			Statement stmt = connection.createStatement();
			String query = "select * from action_log";
			ResultSet rs = stmt.executeQuery(query);
			
			textArea.append(String.format("%-8s %7s %16s %20s", "ID", "Module ID", "Timestamp", "Action") + "\n");
			while(rs.next())
			{
				textArea.append(String.format("%-2s %10s %27s %20s",rs.getString("id"), 
						rs.getString("module_id"), rs.getString("time_stamp"), rs.getString("action_taken")) + "\n");
			}
			connection.close();
			textArea.append("Action Log Acquired...Connection closed.\n");
		}
		catch(SQLException s)
		{
			textArea.append("\nMySQL error.\n");
			textArea.append(s.getLocalizedMessage());
		}
	}
	private void getTankStock()
	{
		try
		{
			Connection connection = null;
			textArea.append("Connecting to tank_stock database...\n");
			connection = DriverManager.getConnection(TemperatureRegulator.url, TemperatureRegulator.username, TemperatureRegulator.password);
			Statement stmt = connection.createStatement();
			String query = "select * from tank_stock";
			ResultSet rs = stmt.executeQuery(query);
			textArea.append("Species ID   Tank ID   Amount\n");
			while(rs.next())
			{
				textArea.append(String.format("%5s %11s %8s",rs.getString("species_id"), rs.getString("tank_id"), rs.getString("amount")) + "\n");
			}
			connection.close();
			textArea.append("Tank Stock Acquired...Connection closed.\n");
		}
		catch(SQLException s)
		{
			textArea.append("\nMySQL error.\n");
			textArea.append(s.getLocalizedMessage());
		}
	}

}
