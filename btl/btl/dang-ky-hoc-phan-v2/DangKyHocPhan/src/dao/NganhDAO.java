/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.HocPhan;
import entity.Nganh;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chung
 */
public class NganhDAO extends AbstractDAO<Nganh>{

  private final String GET_SQL="select * from chuyennganh where manganh=?";
  private final String GET_ALL_SQL="select * from chuyennganh";
   private final String GET_MAJORS_OF_SUBJECT="select * from chuongtrinh ct join chuyennganh n using(manganh) where mahp=?";
  private final KhoaDAO kdao;
  public NganhDAO() {
    kdao=new KhoaDAO();
  }

  public List<Nganh> getMajorsOfSubject(HocPhan hp) throws SQLException {
    try(PreparedStatement stm=con.prepareStatement(GET_MAJORS_OF_SUBJECT)){
      stm.setString(1, hp.getMaHP());
      return map(stm.executeQuery());
    }
  }
  @Override
  public boolean add(Nganh dto) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean update(Nganh dto) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean delete(Nganh dto) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Nganh get(String key) throws SQLException {
    try(PreparedStatement stm=con.prepareStatement(GET_SQL)){
      stm.setString(1, key);
      return mapOne(stm.executeQuery());
    }
  }

  @Override
  public List<Nganh> getAll() throws SQLException {
    try(Statement stm=con.createStatement()){
      return map(stm.executeQuery(GET_ALL_SQL));
    }
  }

  @Override
  public List<Nganh> map(ResultSet rs) throws SQLException {
    List<Nganh> dsN=new ArrayList<>();
    while(rs.next()){
      Nganh n=new Nganh();
      n.setMaNganh(rs.getString("manganh"));
      n.setTenNganh(rs.getString("tennganh"));
      n.setKhoa(kdao.get(rs.getString("makhoa")));
      dsN.add(n);
    }
    return dsN;
  }
}
