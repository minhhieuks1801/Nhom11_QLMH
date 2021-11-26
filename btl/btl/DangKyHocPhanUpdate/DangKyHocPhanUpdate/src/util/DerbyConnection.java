/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author chung
 */
public class DerbyConnection {

  private static Connection conn = null;

  public static Connection getConnection() {
    if (conn == null) {
      try {
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/qlhocphan");
      } catch (SQLException ex) {
        Logger.getLogger(DerbyConnection.class.getName()).log(Level.SEVERE, null, ex);
        System.exit(0);
      }
    }
    return conn;
  }

  public static void closeConnection() throws SQLException {
    if (conn != null) {
      conn.close();
    }
  }
}
