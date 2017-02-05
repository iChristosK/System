/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpackage;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 *
 * @author Christos
 */



@Entity
public class ImportExcelFile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ImportExcelFile)) {
            return false;
        }
        ImportExcelFile other = (ImportExcelFile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testpackage.ImportExcelFile[ id=" + id + " ]";
    }
    





//public class ImportExcelFile 
//{
   public static void main(String[] args) throws Exception 
   {
      Class.forName("com.mysql.jdbc.Driver");
      Connection connect = DriverManager.getConnection(       
      "jdbc:mysql://localhost:3306/kios" , 
      "root" , 
      "9667"
      );
      Statement statement = connect.createStatement();
      ResultSet resultSet = statement
      .executeQuery("select * from supervisors");
      XSSFWorkbook workbook = new XSSFWorkbook(); 
      XSSFSheet spreadsheet = workbook
      .createSheet("supervisors db");
      XSSFRow row=spreadsheet.createRow(1);
      XSSFCell cell;
      cell=row.createCell(1);
      cell.setCellValue("EMP ID");
      cell=row.createCell(2);
      cell.setCellValue("EMP NAME");
     /* cell=row.createCell(3);
      cell.setCellValue("DEG");
      cell=row.createCell(4);
      cell.setCellValue("SALARY");
      cell=row.createCell(5);
      cell.setCellValue("DEPT");*/
      int i=2;
      while(resultSet.next())
      {
         row=spreadsheet.createRow(i);
         cell=row.createCell(1);
         cell.setCellValue(resultSet.getInt("ID"));
         cell=row.createCell(2);
         cell.setCellValue(resultSet.getString("FullName"));
         /*
         cell=row.createCell(3);
         cell.setCellValue(resultSet.getString("deg"));
         cell=row.createCell(4);
         cell.setCellValue(resultSet.getString("salary"));
         cell=row.createCell(5);
         cell.setCellValue(resultSet.getString("dept"));*/
         i++;
      }
      FileOutputStream out = new FileOutputStream(
      new File("exceldatabase.xlsx"));
      workbook.write(out);
      out.close();
      System.out.println(
      "exceldatabase.xlsx written successfully");
   }
}