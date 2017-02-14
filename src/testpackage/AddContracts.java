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
public class AddContracts extends javax.swing.JFrame {
    
    
    
    static Vector headers = new Vector();
    static DefaultTableModel model = null;
    static Vector data = new
            Vector();
    
    static int tableWidth = 0;// set the tableWidth
    static int tableHeight = 0;
    
    
    public class Contract {
      private int id;
      private String researcher;
      private int project;
      private String position;
      private String details;
      private String datefrom;
      private String dateto;
      private int salary;
      private int monthlyhours;
      private int hourlyrate;
      
      
      public Contract( int ID,String Researcher, int Project, String Position, String Details, String DateFrom, String DateTo, int Salary, int MonthlyHours, int HourlyRate)
      {
     this.id = ID;
     this.researcher = Researcher;
     this.project = Project;
     this.position = Position;
     this.details = Details;
     this.datefrom = DateFrom;
     this.dateto = DateTo;
     this.salary = Salary;
     this.monthlyhours = MonthlyHours;
     this.hourlyrate = HourlyRate;
      
      }
      
  public int getId()
   {
       return id;
   }
  
     
  public String getResearcher()
   {
       return researcher;
   }
  
  public int getProject()
  {
      return project;
      
  }
  
  public String getPosition()
          {
              return position;
          }
  public String getDetails()
  
  {
      return details;
  }
  
  public String getDateFrom()
  {
      return datefrom;
  }
  public String getDateTo()
  {
      return dateto;
  }
  
  public int getSalary()
  {
      return salary;
  }
  
  public int getMonthlyhours()
  {
      return monthlyhours;
  }
  
  public int getHourlyRate()
  {
      return hourlyrate;
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

    
    
        
public ArrayList<Contract> getContractList()
   {
       ArrayList<Contract> ContractList = new ArrayList<Contract>();
       Connection connection = getConnection();
       
       String query = "SELECT * FROM  `contract` ";
       Statement st;
       ResultSet rs;
       
       try {
           st = connection.createStatement();
           rs = st.executeQuery(query);

           Contract contract;

           while(rs.next())
           {
contract = new Contract(rs.getInt("ID_contract"),rs.getString("fk_ID_researcher"),rs.getInt("fk_ID_project"),rs.getString("Position"),rs.getString("Details"),rs.getString("Date_From"),rs.getString("Date_To"),rs.getInt("Salary"),rs.getInt("Monthly_Hours"),rs.getInt("Hourly_Rate"));
               ContractList.add(contract);
           }

       } 
      catch (Exception e) {
           e.printStackTrace();
       }
       return ContractList;
   }


   public void Show_Contract_In_JTable()
   {
       ArrayList<Contract> list = getContractList();
       DefaultTableModel model = (DefaultTableModel)jTable2.getModel();
       Object[] row = new Object[10];
       for(int i = 0; i < list.size(); i++)
       {
           row[0] = list.get(i).getId();
           row[1] = list.get(i).getResearcher();
           row[2] = list.get(i).getProject();
           row[3] = list.get(i).getPosition();
           row[4] = list.get(i).getDetails();
           row[5] = list.get(i).getDateFrom();
           row[6] = list.get(i).getDateTo();
           row[7] = list.get(i).getSalary();
           row[8] = list.get(i).getMonthlyhours();
           row[9] = list.get(i).getHourlyRate();
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
               Show_Contract_In_JTable();
               
               JOptionPane.showMessageDialog(null, "Data "+message+" Succefully");
           }else{
               JOptionPane.showMessageDialog(null, "Data Not "+message);
           }
       }catch(Exception ex){
           ex.printStackTrace();
       }
   }
    
    
  
       

    /**
     * Creates new form AddContracts
     */
    public AddContracts() {
        initComponents();
        Show_Contract_In_JTable();
        FillComboBox();
        FillComboBox1();
        FillComboBox2();
        //Show_Contract_In_JTable();
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
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextField2 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jComboBox3 = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jFileChooser1 = new javax.swing.JFileChooser();
        jButton5 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jTextFieldSearch = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jMenuBar = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Personal Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Researcher:*");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 181, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Project:*");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 230, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Position:*");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 277, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Details:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 317, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("From:*");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 132, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("To:*");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(596, 181, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Salary:*");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(579, 230, -1, -1));

        jTextField2.setColumns(20);
        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField2.setLineWrap(true);
        jTextField2.setRows(5);
        jScrollPane1.setViewportView(jTextField2);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, 398, 39));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Contract ID:*");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 132, -1, -1));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(141, 129, 398, -1));

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(141, 177, 398, -1));
        jPanel2.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(141, 226, 398, -1));

        jLabel7.setText("€");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(635, 231, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Monthly Hours:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(551, 277, -1, -1));

        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(664, 274, 424, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Hourly Rate:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(557, 320, -1, -1));

        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 320, 427, -1));

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setFont(new java.awt.Font("Helvetica", 1, 24)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 153, 0));
        jButton7.setText("Contracts");
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(464, 19, 305, 83));

        jButton3.setBackground(new java.awt.Color(255, 204, 153));
        jButton3.setFont(new java.awt.Font("Helvetica", 1, 16)); // NOI18N
        jButton3.setText("Back");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 617, 203, 50));
        jPanel2.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(138, 273, 398, -1));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Contract ID", "Researcher ID", "Project ID", "Position", "Details", "From Date", "To Date", "Salary", "Monthly Hours", "Hourly Rate"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 1040, 190));
        jPanel2.add(jFileChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1101, 19, 0, 0));

        jButton5.setBackground(new java.awt.Color(255, 204, 153));
        jButton5.setFont(new java.awt.Font("Helvetica", 1, 16)); // NOI18N
        jButton5.setText("Add");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 70, 200, 50));

        jTextField4.setText("eg. 2017-12-10");
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(661, 178, 424, -1));

        jTextField3.setText("eg. 2016-10-12");
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(661, 129, 424, -1));

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(661, 227, 424, -1));

        jButton6.setBackground(new java.awt.Color(255, 204, 153));
        jButton6.setFont(new java.awt.Font("Helvetica", 1, 16)); // NOI18N
        jButton6.setText("Export");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 404, 200, 60));

        jButton9.setBackground(new java.awt.Color(255, 204, 153));
        jButton9.setFont(new java.awt.Font("Helvetica", 1, 16)); // NOI18N
        jButton9.setText("Import");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 320, 200, 60));

        jButton8.setBackground(new java.awt.Color(255, 204, 153));
        jButton8.setFont(new java.awt.Font("Helvetica", 1, 16)); // NOI18N
        jButton8.setText("Save");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 190, 200, 50));

        jButton10.setBackground(new java.awt.Color(255, 204, 153));
        jButton10.setFont(new java.awt.Font("Helvetica", 1, 16)); // NOI18N
        jButton10.setText("Delete");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 130, 200, 50));

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
        jPanel2.add(jTextFieldSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 480, 200, 50));

        jButtonSearch.setBackground(new java.awt.Color(255, 204, 153));
        jButtonSearch.setFont(new java.awt.Font("Helvetica", 1, 16)); // NOI18N
        jButtonSearch.setText("Search");
        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 550, 200, 50));

        jButton11.setBackground(new java.awt.Color(255, 204, 153));
        jButton11.setFont(new java.awt.Font("Helvetica", 1, 16)); // NOI18N
        jButton11.setText("Edit");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 250, 200, 50));

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
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1345, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        dispose();//To close the current window

        Menu closeCurrentWindow = new Menu();
        closeCurrentWindow.setVisible(true);//Open the new window
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        
          if (jTextField1.getText().equals("") || jTextField3.getText().equals("") || jTextField4.getText().equals("") || jTextField5.getText().equals("") || jTextField6.getText().equals("") || jTextField7.getText().equals("")) {
            //  jButton1.setEnabled(true);
           JOptionPane.showMessageDialog(null, "Add required fields", "InfoBox: " + "Warning!", JOptionPane.INFORMATION_MESSAGE); 
          
                } 
        
        else {
            
      String query = "INSERT INTO `Contract`(`ID_contract`,`fk_ID_researcher`,`fk_ID_project`,`Position`,`Details`,`Date_From`,`Date_To`,`Salary`,`Monthly_Hours`,`Hourly_Rate`) VALUES ('"+jTextField1.getText()+"','"+(jComboBox1.getSelectedIndex()+1)+"','"+(jComboBox2.getSelectedIndex()+1)+"','"+jComboBox3.getSelectedItem().toString()+"','"+jTextField2.getText()+"','"+jTextField3.getText()+"','"+jTextField4.getText()+"','"+jTextField5.getText()+"','"+jTextField6.getText()+"','"+jTextField7.getText()+"')";

       executeSQlQuery(query, "Inserted");
          }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        
        
         int i = jTable2.getSelectedRow();

        TableModel model = jTable2.getModel();
        
         // Display Slected Row In JTexteFields
        
        jTextField1.setText(model.getValueAt(i,0).toString());
        jComboBox1.setSelectedItem(model.getValueAt(i,1).toString());
        jComboBox2.setSelectedItem(model.getValueAt(i,2).toString());
        jComboBox3.setSelectedItem(model.getValueAt(i,3).toString());
        jTextField2.setText(model.getValueAt(i,4).toString());
        jTextField3.setText(model.getValueAt(i,5).toString());
        jTextField4.setText(model.getValueAt(i,6).toString());
        jTextField5.setText(model.getValueAt(i,7).toString());
        jTextField6.setText(model.getValueAt(i,8).toString());
        jTextField7.setText(model.getValueAt(i,9).toString());
 
        
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
    
    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
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

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        //HEREEEEEE

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
                    Logger.getLogger(AddContracts.class.getName()).log(Level.SEVERE,null,ex);
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
                    d.add("\\n");
                    data.add(d);
                }
            }
            catch (BiffException e) {
                e.printStackTrace();
            }

    }//GEN-LAST:event_jButton9ActionPerformed

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
        
        
int rows = jTable2.getRowCount();

System.out.println(""+rows);
for(int row = 0; row<rows ; row++)
{

String id = (String) jTable2.getValueAt(row, 0);
String res = (String) jTable2.getValueAt(row, 1);

String pro = (String) jTable2.getValueAt(row, 2);
String pos = (String) jTable2.getValueAt(row, 3);
String det = (String) jTable2.getValueAt(row, 4);


String from = (String) jTable2.getValueAt(row, 5);
String to = (String) jTable2.getValueAt(row, 6);

String salary = (String) jTable2.getValueAt(row, 7);
String monthly = (String) jTable2.getValueAt(row, 8);

String rate = (String) jTable2.getValueAt(row, 9);




 try{
  Class.forName("com.mysql.jdbc.Driver");
    java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost/kios","root","9667");


   String query = "INSERT INTO `Contract`(`ID_contract`,`fk_ID_researcher`,`fk_ID_project`,`Position`,`Details`,`Date_From`,`Date_To`,`Salary`,`Monthly_Hours`,`Hourly_Rate`) VALUES (?,?,?,?,?,?,?,?,?,?)";


 PreparedStatement stmt = con.prepareStatement(query);
 stmt.setString(1, id); //Invoice No
 stmt.setString(2, res); //Code
 stmt.setString(3, pro);
 stmt.setString(4, pos);
 stmt.setString(5,det);
 stmt.setString(6, from);
 stmt.setString(7, to);
 stmt.setString(8, salary);
 stmt.setString(9, monthly);
 stmt.setString(10, rate);
 



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
         

                       

    }//GEN-LAST:event_jButton8ActionPerformed

    
    
    
       public void set()
{
      try
        {
                 
     
           Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost/kios","root","9667");
         String sql="select * from contract";
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
    
    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        
         String query = "DELETE FROM `Contract`(`ID_contract`,`fk_ID_researcher`,`fk_ID_project`,`Position`,`Details`,`Date_From`,`Date_To`,`Salary`,`Monthly_Hours`,`Hourly_Rate`) VALUES ('"+jTextField1.getText()+"','"+(jComboBox1.getSelectedIndex()+1)+"','"+(jComboBox2.getSelectedIndex()+1)+"','"+jComboBox3.getSelectedItem().toString()+"','"+jTextField2.getText()+"','"+jTextField3.getText()+"','"+jTextField4.getText()+"','"+jTextField5.getText()+"','"+jTextField6.getText()+"','"+jTextField7.getText()+"')";

       executeSQlQuery(query, "Deleted");
    }//GEN-LAST:event_jButton10ActionPerformed

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

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
       String query = "UPDATE `Contract`(`ID_contract`,`fk_ID_researcher`,`fk_ID_project`,`Position`,`Details`,`Date_From`,`Date_To`,`Salary`,`Monthly_Hours`,`Hourly_Rate`) VALUES ('"+jTextField1.getText()+"','"+(jComboBox1.getSelectedIndex()+1)+"','"+(jComboBox2.getSelectedIndex()+1)+"','"+jComboBox3.getSelectedItem().toString()+"','"+jTextField2.getText()+"','"+jTextField3.getText()+"','"+jTextField4.getText()+"','"+jTextField5.getText()+"','"+jTextField6.getText()+"','"+jTextField7.getText()+"')";

       executeSQlQuery(query, "Updated");
    }//GEN-LAST:event_jButton11ActionPerformed

    
     private void FillComboBox(){
        
          Connection connection = getConnection();
       
       String query = "SELECT * FROM  `researchers` ";
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
     
     private void FillComboBox1(){
        
          Connection connection = getConnection();
       
       String query = "SELECT * FROM  `project` ";
       Statement st;
       ResultSet rs;
       
       try {
           st = connection.createStatement();
           rs = st.executeQuery(query);

           
        while(rs.next()){
            String memberType = rs.getString("Project_Name");
            jComboBox2.addItem(memberType);

        }
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
}
     
     
     private void FillComboBox2(){
        
          Connection connection = getConnection();
       
       String query = "SELECT * FROM  `contract` ";
       Statement st;
       ResultSet rs;
       
       try {
           st = connection.createStatement();
           rs = st.executeQuery(query);

           
        while(rs.next()){
            String memberType = rs.getString("Position");
            jComboBox3.addItem(memberType);

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
            java.util.logging.Logger.getLogger(AddContracts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddContracts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddContracts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddContracts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddContracts().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextArea jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables
}
