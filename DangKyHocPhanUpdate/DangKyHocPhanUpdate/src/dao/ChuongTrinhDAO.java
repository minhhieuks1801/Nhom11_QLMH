/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.ChuongTrinh;
import java.sql.*;
import java.util.List;

/**
 *
 * @author chung
 */
public class ChuongTrinhDAO extends AbstractDAO<ChuongTrinh> {

  private final String ADD_SQL = "insert into chuongtrinh values (?,?)";
  private final String DELETE_SQL = "delete from chuongtrinh where mahp=?";

  @Override
  public boolean add(ChuongTrinh dto) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  public boolean add(List<ChuongTrinh> cts) throws SQLException {
    try (PreparedStatement stm = con.prepareStatement(ADD_SQL)) {
      int count=0;
      for (ChuongTrinh ct : cts) {
        stm.setString(1, ct.getHocPhan().getMaHP());
        stm.setString(2, ct.getNganh().getMaNganh());
        count+=stm.executeUpdate();
      }
      return count==cts.size();
    }
  }

  @Override
  public boolean update(ChuongTrinh dto) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean delete(ChuongTrinh dto) throws SQLException {
    try (PreparedStatement stm = con.prepareStatement(DELETE_SQL)) {
      stm.setString(1, dto.getHocPhan().getMaHP());
      return stm.executeUpdate()>0;
    }
  }

  @Override
  public ChuongTrinh get(String key) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

  }

  @Override
  public List<ChuongTrinh> getAll() throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public List<ChuongTrinh> map(ResultSet rs) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}
