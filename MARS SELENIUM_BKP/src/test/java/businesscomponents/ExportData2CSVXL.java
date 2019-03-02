package businesscomponents;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
public class ExportData2CSVXL {

	public ResultSet rset;
    public String filename;
    public Boolean colomnName;
    public String charSep;

    public void ExportData2CSVXL(ResultSet rset, String filename, Boolean colomnName, String charSep) {
        this.rset = rset;
        this.filename = filename;
        this.colomnName = colomnName;
        this.charSep = charSep;
    }
    public void createFileCsvXL() throws SQLException, IOException {
       // FileWriter cname = null;
    
    	Workbook writeWorkbook = new HSSFWorkbook();
        Sheet desSheet = writeWorkbook.createSheet("new sheet");
        try {

                // WRITE COLOMN NAME
            ResultSetMetaData rsmd = rset.getMetaData();
          //  cname = new FileWriter(filename);
            int columnsNumber = rsmd.getColumnCount();
            Row desRow1 = desSheet.createRow(0);
            for(int col=0 ;col < columnsNumber;col++) {
                Cell newpath = desRow1.createCell(col);
                newpath.setCellValue(rsmd.getColumnLabel(col+1));
            }
            // WRITE DATA
                while(rset.next()) {
                    System.out.println("Row number" + rset.getRow() );
                    Row desRow = desSheet.createRow(rset.getRow());
                    for(int col1=0 ;col1 < columnsNumber;col1++) {
                        Cell newpath1 = desRow.createCell(col1);
                        newpath1.setCellValue(rset.getString(col1+1));  
                    }
                    FileOutputStream fileOut = new FileOutputStream(filename);
                    writeWorkbook.write(fileOut);
                    fileOut.close();
                }
              
            


        } catch (Exception e) {
            e.printStackTrace();

        } 
//        finally {
//            if (cname != null) {
//                cname.flush();
//                cname.close();
//            }
//            if (rset != null) {
//                rset.close();
//            }
//
//        }

    }    

	
}
