/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpackage;


import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultEditorKit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import net.proteanit.sql.DbUtils;


import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


/**
 *
 * @author Christos
 */
public class AddProject extends javax.swing.JFrame {
    
    
     static Vector headers = new Vector();
    static DefaultTableModel model = null;
    static Vector data = new 
    Vector();
 
    static int tableWidth = 0; // set the tableWidth 
    static int tableHeight = 0;

    
    
public class Project {
    
    private int id;
    private String project_name;
    private String supervisor;
    private String details;
   
    
    public Project(int ID, String ProjectName,String Supervisor,String Details)
    {
        this.id = ID;
        this.project_name = ProjectName;
        this.supervisor = Supervisor;
        this.details = Details;
      
    }

   
    public int getId()
    {
        return id;
    }
    
    public String getProjectName()
    {
        return project_name;
    }
    
    
    public String getSupervisor()
    {
        return supervisor;
    }
    
    public String getDetails()
    {
        return details;
    }
 }


    
    public Connection getConnection()
   {
       Connection con;

       try {
           con = DriverManager.getConnection("jdbc:mysql://localhost/kios", "root","9667");
           return con;
       } 
      catch (Exception e) {
           e.printStackTrace();
           return null;
       }
   }
    
    
    
    
public ArrayList<Project> getProjectList()
   {
       ArrayList<Project> ProjectList = new ArrayList<Project>();
       Connection connection = getConnection();
       
       String query = "SELECT * FROM  `project` ";
       Statement st;
       ResultSet rs;
       
       try {
           st = connection.createStatement();
           rs = st.executeQuery(query);

           Project project;

           while(rs.next())
           {
project = new Project(rs.getInt("Project_ID"),rs.getString("Project_Name"),rs.getString("Project_super"),rs.getString("Details"));
               ProjectList.add(project);
           }

       } 
      catch (Exception e) {
           e.printStackTrace();
       }
       return ProjectList;
   }


   public void Show_Project_In_JTable()
   {
       ArrayList<Project> list = getProjectList();
       DefaultTableModel model = (DefaultTableModel)jTable2.getModel();
       Object[] row = new Object[4];
       for(int i = 0; i < list.size(); i++)
       {
           row[0] = list.get(i).getId();
           row[1] = list.get(i).getProjectName();
           row[2] = list.get(i).getSupervisor();
           row[3] = list.get(i).getDetails();
           
         
           model.addRow(row);
       }
    }
   
public void executeSQlQuery(String query, String message)
   {
       Connection con = getConnection();
       Statement st;
       try{
           st = con.createStatement();
           if((st.executeUpdate(query)) == 1)
           {
               // refresh jtable data
               DefaultTableModel model = (DefaultTableModel)jTable2.getModel();
               model.setRowCount(0);
               Show_Project_In_JTable();
               
               JOptionPane.showMessageDialog(null, "Data "+message+" Succefully");
           }else{
               JOptionPane.showMessageDialog(null, "Data Not "+message);
           }
       }catch(Exception ex){
           ex.printStackTrace();
       }
   }

        
public JMenuBar createMenuBar () {
        JMenuItem menuItem = null;
        JMenuBar menuBar = new JMenuBar();
        JMenu mainMenu = new JMenu("Edit");
        mainMenu.setMnemonic(KeyEvent.VK_E);

        menuItem = new JMenuItem(new DefaultEditorKit.CutAction());
        menuItem.setText("Cut");
        menuItem.setMnemonic(KeyEvent.VK_T);
        mainMenu.add(menuItem);

        menuItem = new JMenuItem(new DefaultEditorKit.CopyAction());
        menuItem.setText("Copy");
        menuItem.setMnemonic(KeyEvent.VK_C);
        mainMenu.add(menuItem);

        menuItem = new JMenuItem(new DefaultEditorKit.PasteAction());
        menuItem.setText("Paste");
        menuItem.setMnemonic(KeyEvent.VK_P);
        mainMenu.add(menuItem);

        menuBar.add(mainMenu);
        return menuBar;
    }

    
    
    
    
    /**
     * Creates new form AddProject
     */
    public AddProject() {
        initComponents();
        FillComboBox();
        Show_Project_In_JTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jFileChooser1 = new javax.swing.JFileChooser();
        jFileChooser2 = new javax.swing.JFileChooser();
        jButton8 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jTextFieldSearch = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton();
        jMenuBar = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Project Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Project ID:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 148, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Project Name:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 195, -1, -1));
        jPanel2.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 192, 417, -1));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 145, 417, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Supervisor:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(579, 147, -1, -1));

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(655, 143, 432, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Details:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(579, 190, 70, -1));

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(655, 187, 432, -1));

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setFont(new java.awt.Font("Helvetica", 1, 24)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 153, 0));
        jButton7.setText("Project");
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel2.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 25, 275, 80));

        jButton1.setBackground(new java.awt.Color(255, 204, 153));
        jButton1.setFont(new java.awt.Font("Helvetica", 1, 16)); // NOI18N
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 580, 190, 60));

        jButton2.setBackground(new java.awt.Color(255, 204, 153));
        jButton2.setFont(new java.awt.Font("Helvetica", 1, 16)); // NOI18N
        jButton2.setText("Add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 50, 190, 50));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Project ID", "Project Name", "Supervisor", "Details"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable2);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 252, 1065, 355));
        jPanel2.add(jFileChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 866, 21, 12));
        jPanel2.add(jFileChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1283, 763, 0, 4));

        jButton8.setBackground(new java.awt.Color(255, 204, 153));
        jButton8.setFont(new java.awt.Font("Helvetica", 1, 16)); // NOI18N
        jButton8.setText("Delete");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 120, 190, 50));

        jButton5.setBackground(new java.awt.Color(255, 204, 153));
        jButton5.setFont(new java.awt.Font("Helvetica", 1, 16)); // NOI18N
        jButton5.setText("Save");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 180, 190, 50));

        jButton9.setBackground(new java.awt.Color(255, 204, 153));
        jButton9.setFont(new java.awt.Font("Helvetica", 1, 16)); // NOI18N
        jButton9.setText("Edit");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 250, 190, 50));

        jButton10.setBackground(new java.awt.Color(255, 204, 153));
        jButton10.setFont(new java.awt.Font("Helvetica", 1, 16)); // NOI18N
        jButton10.setText("Import");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 320, 190, 50));

        jButton11.setBackground(new java.awt.Color(255, 204, 153));
        jButton11.setFont(new java.awt.Font("Helvetica", 1, 16)); // NOI18N
        jButton11.setText("Export");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 390, 190, 50));

        jTextFieldSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldSearchMouseClicked(evt);
            }
        });
        jTextFieldSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSearchActionPerformed(evt);
            }
        });
        jTextFieldSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldSearchKeyTyped(evt);
            }
        });
        jPanel2.add(jTextFieldSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 460, 190, 50));

        jButtonSearch.setBackground(new java.awt.Color(255, 204, 153));
        jButtonSearch.setFont(new java.awt.Font("Helvetica", 1, 16)); // NOI18N
        jButtonSearch.setText("Search");
        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 520, 190, 50));

        jMenu2.setText("File");
        jMenuBar.add(jMenu2);

        jMenu3.setText("Edit");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Cut");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Paste");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuBar.add(jMenu3);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel2.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        dispose();//To close the current window

        Menu closeCurrentWindow = new Menu();
        closeCurrentWindow.setVisible(true);//Open the new window

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
           if (jTextField1.getText().equals("")  || jTextField2.getText().equals("")  || jTextField3.getText().equals("")) {
            //  jButton1.setEnabled(true);
           JOptionPane.showMessageDialog(null, "Add required fields", "InfoBox: " + "Warning!", JOptionPane.INFORMATION_MESSAGE); 
          
                } 
        
        else {
   
         String query = "INSERT INTO `project`(`Project_ID`,`Project_Name`,`Project_super`,`Details`) VALUES ('"+jTextField1.getText()+"','"+jTextField2.getText()+"','"+(jComboBox1.getSelectedIndex()+1)+"','"+jTextField3.getText()+"')";
       
         executeSQlQuery(query, "Inserted");
           }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:

           // Get The Index Of The Slected Row 
        int i = jTable2.getSelectedRow();

        TableModel model = jTable2.getModel();
        
         // Display Slected Row In JTexteFields
        jTextField1.setText(model.getValueAt(i,0).toString());
        jTextField2.setText(model.getValueAt(i,1).toString());
        jComboBox1.setSelectedItem(model.getValueAt(i,2).toString());
        jTextField3.setText(model.getValueAt(i,3).toString());
    
        
    }//GEN-LAST:event_jTable2MouseClicked

    
    
    public void toExcel(JTable table, File file){
		try{
			TableModel model = table.getModel();
			FileWriter excel = new FileWriter(file);

			for(int i = 0; i < model.getColumnCount(); i++){
				excel.write(model.getColumnName(i) + "\t");
			}

			excel.write("\n");

			for(int i=0; i< model.getRowCount(); i++) {
				for(int j=0; j < model.getColumnCount(); j++) {
					excel.write(model.getValueAt(i,j).toString()+"\t");
				}
				excel.write("\n");
			}

			excel.close();
		}catch(IOException e){ System.out.println(e); }
	}
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        TransferHandler.getCopyAction();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:

        TransferHandler.getPasteAction();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu3MouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed

        // removeSelectedFromTable(jTable2);

        String query = "DELETE FROM `project` WHERE ID = ('"+jTextField1.getText()+"')";
        executeSQlQuery(query, "Deleted");

        //  }

        // deleterow();

        //Working deletion

        /*
        String query2 = "DELETE FROM `researchers` WHERE fk_Supervisor = ('"+row+"')";
        executeSQlQuery(query2, "Deleting NOW");

        String query1 = "DELETE FROM `project` WHERE Project_super = ('"+row+"')";
        executeSQlQuery(query1, "Deleting NOW");

        String query = "DELETE FROM `supervisors` WHERE ID = ('"+row+"')";
        executeSQlQuery(query, "Deleted");*/

        /*
        DefaultTableModel from = (DefaultTableModel)jTable2.getModel();
        int row = jTable2.getSelectedRow();

        while (row != -1)
        {

            int modelRow = jTable2.convertRowIndexToModel( row );
            String query2 = "UPDATE `researchers` SET fk_Supervisor = 1 WHERE fk_Supervisor = ('"+row+"') ";

            executeSQlQuery(query2, "Updated from Researchers Table");

            String query1 = "UPDATE `project` SET Project_super = 1 WHERE Project_super = ('"+row+"')";

            executeSQlQuery(query1, "Updated from Project Table");

            String query = "DELETE FROM `supervisors` WHERE ID = ('"+row+"')";
            executeSQlQuery(query, "Deleted Supervisors Table");

            model.removeRow( modelRow );
            //row = jTable2.getSelectedRow();

        }*/
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        /*
        String query = "UPDATE `supervisors` SET `FullName`='"+jTextField2.getText()+"' WHERE `ID` = "+jTextField1.getText();
        executeSQlQuery(query, "Updated");
        */

        int rows = jTable2.getRowCount();

        System.out.println(""+rows);
        for(int row = 0; row<rows ; row++)
        {

            String id = (String) jTable2.getValueAt(row, 0);
            String name = (String) jTable2.getValueAt(row, 1);
            String supervisor = (String) jTable2.getValueAt(row, 2);
            String details = (String) jTable2.getValueAt(row, 3);

            try{
                Class.forName("com.mysql.jdbc.Driver");
                java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost/kios","root","9667");

                
         String query = "INSERT INTO `project`(`Project_ID`,`Project_Name`,`Project_super`,`Details`) VALUES (?,?,?,?)";
       
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setString(1, id); //Invoice No
                stmt.setString(2, name); //Code
                 stmt.setString(3, supervisor); //Invoice No
                stmt.setString(4, details); //Code

                stmt.addBatch();
                stmt.executeBatch();
                con.commit();
            }

            catch(Exception ex)
            {
                //JOptionPane.showMessageDialog(null, "Cannot save. "+ ex);
            }
        }

        set();

    }//GEN-LAST:event_jButton5ActionPerformed

    
    
    
       public void set()
{
      try
        {
                 
     
           Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost/kios","root","9667");
         String sql="select * from project";
         java.sql.PreparedStatement pst=con.prepareStatement(sql);
  
         ResultSet rs = pst.executeQuery(sql);
         jTable2.setModel(DbUtils.resultSetToTableModel(rs));
   

        con.close();
        pst.close();
        }
        catch(Exception e)
        {
          //  JOptionPane.showMessageDialog(null, e);
        }
}
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
         String query = "UPDATE `project`(`Project_ID`,`Project_Name`,`Project_super`,`Details`) VALUES ('"+jTextField1.getText()+"','"+jTextField2.getText()+"','"+(jComboBox1.getSelectedIndex()+1)+"','"+jTextField3.getText()+"')";
       
         executeSQlQuery(query, "Updated");

    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:

        //add to existing data in jTable without replacing the old data

        int dialogButton = JOptionPane.YES_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to export the current data first?","Warning",dialogButton);
        if(dialogResult == JOptionPane.YES_OPTION){

            JFileChooser jf = new JFileChooser();
            jf.setDialogTitle("Please select the .XLS (Excel File) where you want to EXPORT the data:");
            int result =  jf.showOpenDialog(null);
            if(result == JFileChooser.APPROVE_OPTION){
                String filename = jf.getSelectedFile().getName();
                String path = jf.getSelectedFile().getParentFile().getPath();

                int len = filename.length();
                String ext = "";
                String file = "";

                if(len > 4){
                    ext = filename.substring(len-4, len);
                }

                if(ext.equals(".xls")){
                    file = path + "/" + filename;
                }else{
                    file = path + "/" + filename + ".xls";
                }
                toExcel(jTable2, new File(file));
            }

        }

        int dialogButton2 = JOptionPane.YES_NO_OPTION;
        int dialogResult2 = JOptionPane.showConfirmDialog(this, "Would you like to import", "Importing .xls file", dialogButton2);
        if(dialogResult2 == 0) {
            System.out.println("Yes option");

            int dialogButton3 = JOptionPane.YES_NO_OPTION;
            int dialogResult3 = JOptionPane.showConfirmDialog(this, "Would you like to replace the current data", "Importing .xls file", dialogButton3);
            if(dialogResult3 == 0) {
                System.out.println("Yes option");
                //new code

                // String query0 =  "ALTER `supervisors` SET FOREIGN KEY(0) REFERENCES root";
                /*
                String query0 =  "ALTER `supervisors` SET FOREIGN_KEY_CHECKS = 0";

                executeSQlQuery(query0, "All KEYS");

                String query001 = " TRUNCATE `supervisors`";
                executeSQlQuery(query001, "All Data removed from supervisors");

                String query01 =  "ALTER TABLE `supervisors` SET FOREIGN_KEY_CHECKS (1)";
                executeSQlQuery(query01, "All KEYS");
                */

                /*
                String query01 = "`supervisors` SET FOREIGN_KEY_CHECKS = 1;";
                executeSQlQuery(query01, "All KEYS SET BACK");*/

                // jTable2.removeAll();
                //   model.setRowCount(0);

                //correct code
                jFileChooser1.showOpenDialog(null);
                File file = jFileChooser1.getSelectedFile();
                if(!file.getName().endsWith("xls")){
                    JOptionPane.showMessageDialog(null,"Please select only Excel file.",
                        "Error",JOptionPane.ERROR_MESSAGE);

                }
                else
                {
                    fillData(file);
                    model = new DefaultTableModel(data,headers);
                    tableWidth = model.getColumnCount() *150;
                    tableHeight = model.getRowCount() *25;
                    jTable2.setPreferredSize( new Dimension (tableWidth,tableHeight));
                }
                jTable2.setModel(model);
            } else
            {
                System.out.println("Not Replacing! Adding to the existing data");
                //Here the code to add to the existing data
                //Actually you need to append or concatenate to the existing data

                jFileChooser1.showOpenDialog(null);
                File file = jFileChooser1.getSelectedFile();
                if(!file.getName().endsWith("xls")){
                    JOptionPane.showMessageDialog(null,"Please select only Excel file.",
                        "Error",JOptionPane.ERROR_MESSAGE);

                }
                else
                {
                    fillData(file);
                    // fillData(file);
                    //The edited code where the data are going to be appended
                    model = new DefaultTableModel(data,headers);
                    tableWidth = model.getColumnCount() *150;
                    tableHeight = model.getRowCount() *25;
                    jTable2.setPreferredSize( new Dimension (tableWidth,tableHeight));
                }
                jTable2.setModel(model);

            }
        }
        }

        void fillData(File file)
        {
            Workbook workbook = null;
            try {
                try {
                    workbook = Workbook.getWorkbook(file);
                }  catch(IOException ex){
                    Logger.getLogger(AddSupervisor.class.getName()).log(Level.SEVERE,null,ex);
                }
                Sheet sheet = workbook.getSheet(0);

                headers.clear();
                for ( int i=0; i<sheet.getColumns(); i++){
                    Cell cell1 = sheet.getCell(i,0);
                    headers.add(cell1.getContents());
                }
                data.clear();
                for (int j = 1; j < sheet.getRows(); j++) {
                    Vector d = new Vector();

                    for (int i = 0; i < sheet.getColumns(); i++) {
                        Cell cell = sheet.getCell(i, j);

                        d.add(cell.getContents());

                    }
                    d.add("\n");
                    data.add(d);
                }
            }
            catch (BiffException e) {
                e.printStackTrace();
            }

    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:

       
         JFileChooser jf = new JFileChooser();
        jf.setDialogTitle("Please select the .XLS (Excel File) where you want to EXPORT the data:");
       int result =  jf.showOpenDialog(null);
       if(result == JFileChooser.APPROVE_OPTION){
       String filename = jf.getSelectedFile().getName();
        String path = jf.getSelectedFile().getParentFile().getPath();

        int len = filename.length();
        String ext = "";
        String file = "";

        if(len > 4){
            ext = filename.substring(len-4, len);
        }

        if(ext.equals(".xls")){
            file = path + "/" + filename; 
        }else{
            file = path + "/" + filename + ".xls"; 
        }
        toExcel(jTable2, new File(file));
    }

    }//GEN-LAST:event_jButton11ActionPerformed

    private void jTextFieldSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldSearchMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSearchMouseClicked

    private void jTextFieldSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSearchActionPerformed

    private void jTextFieldSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSearchKeyTyped

    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed
        // TODO add your handling code here:

        // Commons.attachJTableFilter(jTable2, jTextFieldSearch);

        // jTable2.removeAll();
        // model.getRowCount();
        for(int i = 0; i < jTable2.getRowCount(); i++){ //For each row
            for(int j = 0; j < jTable2.getColumnCount(); j++){ //For each column in that row
                // if(jTable2.getModel().getValueAt(i, j).equals("Elias Kyriakides")){//Search the model
                    if(jTable2.getModel().getValueAt(i, j).equals(jTextFieldSearch.getText())){  //Search the model

                        JOptionPane.showMessageDialog(null, "Found "+ jTable2.getModel().getValueAt(i, j), "InfoBox: " + "Warning!", JOptionPane.INFORMATION_MESSAGE);
                        //Vector rowVector = (Vector) i;

                        // model.addRow(data);
                        break;
                    }   //For loop inner
                    /*  else
                    {
                        JOptionPane.showMessageDialog(null, "Not Found ","InfoBox: " + "Warning!", JOptionPane.INFORMATION_MESSAGE);

                        break;
                    }*/

                }   //For loop outer

            }

    }//GEN-LAST:event_jButtonSearchActionPerformed

    
    
     private void FillComboBox(){
        
          Connection connection = getConnection();
       
       String query = "SELECT * FROM  `supervisors` ";
       Statement st;
       ResultSet rs;
       
       try {
           st = connection.createStatement();
           rs = st.executeQuery(query);

           
        while(rs.next()){
            String memberType = rs.getString("FullName");
            jComboBox1.addItem(memberType);

        }
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
}
     
     
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddProject().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JFileChooser jFileChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables
}
