/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Khoa;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chung
 */
public class KhoaDAO extends AbstractDAO<Khoa> {

  private final String GET_SQL = "select * from khoa where makhoa=?";

  @Override
  public boolean add(Khoa dto) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean update(Khoa dto) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean delete(Khoa dto) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Khoa get(String key) throws SQLException {
    try ( PreparedStatement stm = con.prepareStatement(GET_SQL)) {
      stm.setString(1, key);
      return mapOne(stm.executeQuery());
    }
  }

  @Override
  public List<Khoa> getAll() throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public List<Khoa> map(ResultSet rs) throws SQLException {
    List<Khoa> dsk = new ArrayList<>();
    while (rs.next()) {
      Khoa k = new Khoa();
      k.setMaKhoa(rs.getString("makhoa"));
      k.setTenKhoa(rs.getString("tenkhoa"));
      dsk.add(k);
    }
    return dsk;
  }
}
