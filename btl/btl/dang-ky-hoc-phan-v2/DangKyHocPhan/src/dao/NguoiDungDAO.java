/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import entity.NguoiDung;
import java.sql.*;
import util.DerbyConnection;
/**
 *
 * @author cuong
 */
public class NguoiDungDAO {
  private final String LOGIN_SQL="select * from nguoidung where mand=? and matkhau=?";
  private final String CHANGE_PASSWORD_SQL="update nguoidung set matkhau=? where mand=?";
  private final Connection con=DerbyConnection.getConnection();
  public NguoiDung login(NguoiDung nd) throws SQLException{
    PreparedStatement stm=con.prepareStatement(LOGIN_SQL);
    stm.setString(1, nd.getUsername());
    stm.setString(2, nd.getPassword());
    ResultSet rs=stm.executeQuery();
    if(!rs.next())  throw new RuntimeException("Tên tài khoản hoặc mật khẩu không chính xác!");
    nd.setRole(rs.getString("vaitro"));
    return nd;
  }
  public boolean changePassword(String username,String newPass) throws SQLException{
    PreparedStatement stm=con.prepareStatement(CHANGE_PASSWORD_SQL);
    stm.setString(1, newPass);
    stm.setString(2, username);
    return stm.executeUpdate()>0;
  }
}
