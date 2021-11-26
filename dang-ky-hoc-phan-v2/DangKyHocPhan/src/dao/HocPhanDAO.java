/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.HocPhan;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author chung
 */
public class HocPhanDAO extends AbstractDAO<HocPhan> {

  private final String GET_SQL = "select * from hocphan hp where mahp=?";
  private final String GET_ALL_SQL = "select * from hocphan hp";
  private final String DELETE_SQL = "delete from hocphan where mahp=?";
  private final String ADD_SQL = "insert into hocphan values (?,?,?,?,?,?,?)";
  private final String UPDATE_SQL = "update hocphan set mahptd=?,tenhp=?,mota=?,sotc=?,ppdg=?,batbuoc=? where mahp=?";
  private final NganhDAO ndao;
  public HocPhanDAO() {
    ndao=new NganhDAO();
  }

  @Override
  public boolean add(HocPhan dto) throws SQLException {
    try (PreparedStatement stm = con.prepareStatement(ADD_SQL)) {
      stm.setString(1, dto.getMaHP());
      stm.setString(2, dto.getHptd()==null?null:dto.getHptd().getMaHP());
      stm.setString(3, dto.getTenHP());
      stm.setString(4, dto.getMoTa());
      stm.setInt(5, dto.getSoTC());
      stm.setString(6, dto.getPpdg());
      stm.setBoolean(7, dto.isBatBuoc());
      return stm.executeUpdate()>0;
    }
  }

  @Override
  public boolean delete(HocPhan dto) throws SQLException {
    try (PreparedStatement stm = con.prepareStatement(DELETE_SQL)) {
      stm.setString(1, dto.getMaHP());
      return stm.executeUpdate()>0;
    }
  }

  @Override
  public HocPhan get(String key) throws SQLException {
    try (PreparedStatement stm = con.prepareStatement(GET_SQL)) {
      stm.setString(1, key);
      return mapOne(stm.executeQuery());
    }
  }

  private boolean checkValidHptd(HocPhan hp){
    Set<HocPhan> set=new HashSet<>();
    HocPhan cur=hp;
    while(cur!=null){
      if(set.contains(cur))   return false;
      set.add(cur);
      cur=cur.getHptd();
    }
    return true;
  }
  @Override
  public boolean update(HocPhan dto) throws SQLException {
    try (PreparedStatement stm = con.prepareStatement(UPDATE_SQL)) {
      if(dto.getHptd()!=null){
        if(!checkValidHptd(dto))  throw new RuntimeException("Học phần tiền đề không hợp lệ!");
        stm.setString(1,dto.getHptd().getMaHP());
      } else {
        stm.setString(1, null);
      }
      stm.setString(2, dto.getTenHP());
      stm.setString(3, dto.getMoTa());
      stm.setInt(4, dto.getSoTC());
      stm.setString(5, dto.getPpdg());
      stm.setBoolean(6, dto.isBatBuoc());
      stm.setString(7, dto.getMaHP());
      return stm.executeUpdate()>0;
    }
  }

  @Override
  public List<HocPhan> getAll() throws SQLException {
    try (PreparedStatement stm = con.prepareStatement(GET_ALL_SQL)) {
      return map(stm.executeQuery());
    }
  }

  @Override
  public List<HocPhan> map(ResultSet rs) throws SQLException {
    List<HocPhan> dsHP = new ArrayList<>();
    while (rs.next()) {
      HocPhan hp=new HocPhan();
      hp.setMaHP(rs.getString("mahp"));
      hp.setHptd(rs.getString("mahptd")!=null?get(rs.getString("mahptd")):null);
      hp.setDsNganh(ndao.getMajorsOfSubject(hp));
      hp.setTenHP(rs.getString("tenhp"));
      hp.setMoTa(rs.getString("mota"));
      hp.setSoTC(rs.getInt("sotc"));
      hp.setPpdg(rs.getString("ppdg"));
      hp.setBatBuoc(rs.getBoolean("batbuoc"));
      dsHP.add(hp);
    }
    return dsHP;
  }
}
