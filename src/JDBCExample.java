//STEP 1. Import required packages
import java.sql.*;


public class JDBCExample {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://192.168.1.95/ngsp";

   //  Database credentials
   static final String USER = "ngsp";
   static final String PASS = "ngsp";
   
   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
	   Class.forName("com.mysql.jdbc.Driver").newInstance();
	   
      //STEP 3: Open a connection
      System.out.println("Connecting to a selected database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      System.out.println("Connected database successfully...");
      
      //STEP 4: Execute a query
      System.out.println("Creating statement...");
      stmt = conn.createStatement();
//      String sql = "UPDATE Registration " +
//                   "SET age = 30 WHERE id in (100, 101)";
//      stmt.executeUpdate(sql);

      // Now you can extract all the records
      // to see the updated records
      String sql = "SELECT id,label from grade where id like '%e'";
      ResultSet rs = stmt.executeQuery(sql);

      while(rs.next()){
         //Retrieve by column name
         String id1  = rs.getString("id");
         String id2 = rs.getString("label");
        // int dd = rs.getInt("grade_book_index");
         

         //Display values
         System.out.print("grade_id: " + id1);
         System.out.print(", label: " + id2 + "\n");
         //System.out.print(", grade_book_index: " + dd + "\n");

      }
      rs.close();
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            conn.close();
      }catch(SQLException se){
      }// do nothing
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("\nEnd!");
}//end main
}//end JDBCExample