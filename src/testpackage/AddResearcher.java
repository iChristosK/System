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
import java.sql.SQLException;
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
public class AddResearcher extends javax.swing.JFrame {
    
      
    static Vector headers = new Vector();
    static DefaultTableModel model = null;
    static Vector data = new Vector();
 
    static int tableWidth = 0; // set the tableWidth 
    static int tableHeight = 0;
    
    
    
    
public class Researcher {
    
        private String id;
        private String firstName;
        private int fk; 
        private String addr;
        private String tel; 
        private String email;
        private int access;
        private String office;
        private int active;
        private String details;
        private String fk2;
        private String equipment;
         
     
    public Researcher(String ID, String FirstName,int Supervisor,String Address,String Telephone,String Email, int Access,String Office,int Active,String Details,String Second,String Equipment)
    {
        this.id = ID;
        this.firstName = FirstName;
        this.fk = Supervisor;
        this.addr = Address;
        this.tel = Telephone;
        this.email = Email;
        this.access = Access;
        this.office = Office;
        this.active = Active;
        this.details = Details;
        this.fk2 = Second;
        this.equipment = Equipment;
        
  
    }

   
    public String getId()
    {
        return id;
    }
    
    public String getFirstName()
    {
        return firstName;
    }
    
    
     public int getfk()
    {
        return fk;
    }
    
    public String getAddr()
    {
        return addr;
    }
    
     public String getTel()
    {
        return tel;
    }

      public String getEmail()
    {
        return email;
    }
      
      public int getAccess()
      {
          return access;
      }
      
     public String getOffice()
      {
          return office;
      }
     
     public int getActive()
     {
         return active;
     }
      
      public String getDetails()
      {
          return details;
      }
     
      public String getSecond()
      {
          return fk2;
      }

      public String getEquipment()
      {
          return equipment;
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
    
    
public ArrayList<Researcher> getResList()
   {
       ArrayList<Researcher> ResearcherList = new ArrayList<Researcher>();
       Connection connection = getConnection();
       
       String query = "SELECT * FROM  `researchers` ";
       Statement st;
       ResultSet rs;
       
       try {
           st = connection.createStatement();
           rs = st.executeQuery(query);

           Researcher researcher;

           while(rs.next())
           {
            researcher = new Researcher(rs.getString("ID"),rs.getString("FullName"),rs.getInt("fk_Supervisor"),rs.getString("Address"),rs.getString("Telephone"),rs.getString("Email"),rs.getInt("AccessToKios"),rs.getString("OfficeNumber"),rs.getInt("Active"),rs.getString("Details"),rs.getString("supervisor2"),rs.getString("Equipment"));
            
               ResearcherList.add(researcher);
           }

       } 
      catch (Exception e) {
           e.printStackTrace();
       }
       return ResearcherList;
   }




   public void Show_Res_In_JTable()
   {
       ArrayList<Researcher> list = getResList();
       DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
       Object[] row = new Object[12];
       for(int i = 0; i < list.size(); i++)
       {
           row[0] = list.get(i).getId();
           row[1] = list.get(i).getFirstName();
           row[2] = list.get(i).getfk();
           row[3] = list.get(i).getAddr();
           row[4] = list.get(i).getTel();
           row[5] = list.get(i).getEmail();
           row[6] = list.get(i).getAccess();
           row[7] = list.get(i).getOffice();
           row[8] = list.get(i).getActive();
           row[9] = list.get(i).getDetails();
           row[10] = list.get(i).getSecond();
           row[11] = list.get(i).getEquipment();
            
            
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
               DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
               model.setRowCount(0);
               Show_Res_In_JTable(); 
               
               JOptionPane.showMessageDialog(null, "Data "+message+" Succefully");
           }else{
               JOptionPane.showMessageDialog(null, "Data Not "+message);
           }
       }catch(Exception ex){
           ex.printStackTrace();
       }
   }
    /**
     * Creates new form AddResearcher
     */
    public AddResearcher() {
        initComponents();
        Show_Res_In_JTable();
        FillComboBox();
        FillComboBox2();
        FillComboBoxLast();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jLabel26 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jComboBox9 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox<>();
        jFileChooser1 = new javax.swing.JFileChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel31 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jMenuBar = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Personal Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
        jLabel1.setText("Researcher ID:*");
        jPanel5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 303, 30));

        jLabel4.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
        jLabel4.setText("Full Name:*");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        jLabel5.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
        jLabel5.setText("Address:");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        jLabel6.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
        jLabel6.setText("Telephone:");
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        jLabel30.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
        jLabel30.setText("Email:");
        jPanel5.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 70, -1));
        jPanel5.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 303, -1));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 303, -1));

        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, 303, -1));

        jTextField17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField17ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField17, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 290, 303, -1));

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setFont(new java.awt.Font("Helvetica", 1, 24)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 153, 0));
        jButton7.setText("Researchers");
        jPanel5.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 350, 60));

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel29.setText("Details:");
        jPanel5.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 200, 60, -1));
        jPanel5.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 200, 303, -1));

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel25.setText("Access to KIOS:");
        jPanel5.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, 100, 30));

        jRadioButton7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioButton7.setText("Yes");
        jRadioButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton7ActionPerformed(evt);
            }
        });
        jPanel5.add(jRadioButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 90, 130, -1));

        jRadioButton8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioButton8.setText("No");
        jRadioButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton8ActionPerformed(evt);
            }
        });
        jPanel5.add(jRadioButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 90, 150, -1));

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel26.setText("Active:");
        jPanel5.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 160, 60, -1));

        jTextField16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField16ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField16, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 130, 303, 20));

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel27.setText("Equipment:");
        jPanel5.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 270, 90, 27));

        jComboBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox7ActionPerformed(evt);
            }
        });
        jPanel5.add(jComboBox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 280, 303, -1));

        jButton2.setBackground(new java.awt.Color(255, 204, 153));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton2.setText("Add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 190, 190, 54));

        jButton5.setBackground(new java.awt.Color(255, 204, 153));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton5.setText("Import");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 260, 190, 54));

        jButton4.setBackground(new java.awt.Color(255, 204, 153));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton4.setText("Edit Researcher");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 480, 186, 56));

        jButton6.setBackground(new java.awt.Color(255, 204, 153));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton6.setText("Save");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 340, 190, 54));

        jButton1.setBackground(new java.awt.Color(255, 204, 153));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 550, 190, 56));

        jButton3.setBackground(new java.awt.Color(255, 204, 153));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton3.setText("Clear All");
        jPanel5.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 410, 190, 56));

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel28.setText("Supervisor:*");
        jPanel5.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        jComboBox9.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jComboBox9MouseMoved(evt);
            }
        });
        jComboBox9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox9MouseClicked(evt);
            }
        });
        jComboBox9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox9ActionPerformed(evt);
            }
        });
        jPanel5.add(jComboBox9, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 240, 303, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("2nd Supervisor:");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 240, -1, -1));

        jComboBox8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox8ActionPerformed(evt);
            }
        });
        jPanel5.add(jComboBox8, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 303, -1));
        jPanel5.add(jFileChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1057, 922, 14, 20));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Full Name", "Supervisor", "Address", "Telephone", "Email", "Access Kios", "Office No.", "Active", "Details", "Supervisor 2", "Equipment"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 980, 330));

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel31.setText("Office Number:");
        jPanel5.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 130, -1, -1));

        jRadioButton1.setText("Yes");
        jPanel5.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 160, 130, -1));

        jRadioButton2.setText("No");
        jPanel5.add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 160, 150, -1));

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 1248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 744, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jPanel5.getAccessibleContext().setAccessibleName("Personal Detail");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField17ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jComboBox9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox9ActionPerformed
        // TODO add your handling code here:
        
        
        //FillComboBox();
    }//GEN-LAST:event_jComboBox9ActionPerformed

    
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
            jComboBox9.addItem(memberType);

        }
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
}
    
    
    private void FillComboBox2(){
        
          Connection connection = getConnection();
       
       String query = "SELECT * FROM  `researchers` ";
       Statement st;
       ResultSet rs;
       
       try {
           st = connection.createStatement();
           rs = st.executeQuery(query);

           
        while(rs.next()){
            String memberType = rs.getString("Equipment");
            jComboBox7.addItem(memberType);

        }
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
}
        private void FillComboBoxLast(){
        
          Connection connection = getConnection();
       
       String query = "SELECT * FROM  `supervisors` ";
       Statement st;
       ResultSet rs;
       
       try {
           st = connection.createStatement();
           rs = st.executeQuery(query);

           
        while(rs.next()){
           String memberType =  rs.getString("FullName");
            jComboBox8.addItem(memberType);

        }
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
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
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:

        dispose();//To close the current window

        EditResearcher closeCurrentWindow = new EditResearcher();
        closeCurrentWindow.setVisible(true);//Open the new window

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
         if (jTextField1.getText().equals("") || jTextField2.getText().equals("") || jTextField3.getText().equals("") || jTextField6.getText().equals("") || jTextField17.getText().equals("")) {
            //  jButton1.setEnabled(true);
           JOptionPane.showMessageDialog(null, "Add required fields", "InfoBox: " + "Warning!", JOptionPane.INFORMATION_MESSAGE); 
          
                } 
        
        else {
             jRadioButton7.setActionCommand("1");
             jRadioButton8.setActionCommand("0");
             jRadioButton1.setActionCommand("1");
             jRadioButton2.setActionCommand("0");
         

        
        String query = "INSERT INTO `researchers`(`ID`,`FullName`,`fk_Supervisor`,`Address`,`Telephone`,`Email`,`AccessToKios`,`OfficeNumber`,`Active`,`Details`,`supervisor2`,`Equipment`) VALUES ('"+jTextField1.getText()+"','"+jTextField2.getText()+"','"+(jComboBox8.getSelectedIndex()+1)+"','"+jTextField3.getText()+"','"+jTextField6.getText()+"','"+jTextField17.getText()+"','"+buttonGroup1.getSelection().getActionCommand()+"','"+jTextField16.getText()+"','"+buttonGroup2.getSelection().getActionCommand()+"','"+jTextField5.getText()+"','"+(jComboBox9.getSelectedIndex()+1)+"','"+(jComboBox7.getSelectedIndex()+1)+"')";
      
        executeSQlQuery(query, "Inserted");
        
        
         }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        dispose();//To close the current window

        Menu closeCurrentWindow = new Menu();
        closeCurrentWindow.setVisible(true);//Open the new window

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox7ActionPerformed

    private void jTextField16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField16ActionPerformed

    private void jRadioButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton8ActionPerformed

    private void jRadioButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton7ActionPerformed

    
    
    private void jComboBoxSelect()
    {

          Connection connection = getConnection();
       
       String query = "SELECT * FROM  `supervisors` ";
       Statement st;
       ResultSet rs;
       
       try {
           st = connection.createStatement();
           rs = st.executeQuery(query);

     String memberTypeList = jComboBox9.getSelectedItem().toString();
      String sql  ="SELECT * FROM  `supervisors` where FullName = '" + memberTypeList ;
       
        
         st = connection.createStatement();
           rs = st.executeQuery(sql);
           
           /////CONTINUE FROM HERE THE CODE 
           //NOT SURE WHAT I WANT TO DO
           // THIS IS A FUNCTION WHERE BASED ON THE SELECT ITEM ON THE COMBO BOX IT IS SAVED/SENDED ON THE TABLE AFTER
           //THE USER CLICKS THE "ADD" BUTTON
  
           /*
           

        while(rs.next()){

            String memberType = rs.getString("FullName");
            rs.setString(10, memberType);
        }
           
           
           
           
           
        while(rs.next()){
            String memberType = rs.getString("Equipment");
            jComboBox9.addItem(memberType);
           */
       
    

        
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
    }
            
            
    private void jComboBox9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox9MouseClicked
        // TODO add your handling code here:
 
    }//GEN-LAST:event_jComboBox9MouseClicked

    private void jComboBox9MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox9MouseMoved
        // TODO add your handling code here:
        //FillComboBox();
    }//GEN-LAST:event_jComboBox9MouseMoved

    private void jComboBox8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox8ActionPerformed

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
    
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        //works perfect
        //add to existing data in jTable

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
                toExcel(jTable1, new File(file));
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
                    jTable1.setPreferredSize( new Dimension (tableWidth,tableHeight));
                }
                jTable1.setModel(model);
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
                    jTable1.setPreferredSize( new Dimension (tableWidth,tableHeight));
                }
                jTable1.setModel(model);

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
                    Logger.getLogger(SearchNow.class.getName()).log(Level.SEVERE,null,ex);
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

    }//GEN-LAST:event_jButton5ActionPerformed

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

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        
        
         // Get The Index Of The Slected Row 
        int i = jTable1.getSelectedRow();

        TableModel model = jTable1.getModel();
        
         // Display Slected Row In JTexteFields
        jTextField1.setText(model.getValueAt(i,0).toString());
        jTextField2.setText(model.getValueAt(i,1).toString());
        jComboBox8.setSelectedItem(model.getValueAt(i,2).toString());
        jTextField3.setText(model.getValueAt(i,3).toString());
        jTextField6.setText(model.getValueAt(i,4).toString());
        jTextField17.setText(model.getValueAt(i,5).toString());
    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(AddResearcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddResearcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddResearcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddResearcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddResearcher().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
