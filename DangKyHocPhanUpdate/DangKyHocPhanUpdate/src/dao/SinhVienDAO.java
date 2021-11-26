/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.LopHocPhan;
import entity.SinhVien;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
/**
 *
 * @author chung
 */
public class SinhVienDAO extends AbstractDAO<SinhVien>{

  private final String GET_SQL="select * from sinhvien where masv=?";
  private final String GET_ALL_OF_CLASS="select * from dangkyhoc join sinhvien using (masv) where malhp=?";
  private final String UPDATE_SQL = "UPDATE SinhVien  SET HoTen = ?, DiaChi = ? WHERE masv = ?" ;

  private final NganhDAO ndao=new NganhDAO();
  public List<SinhVien> getAllStudentOfClass(LopHocPhan lhp) throws SQLException{
    try(PreparedStatement stm=con.prepareStatement(GET_ALL_OF_CLASS)){
      stm.setString(1, lhp.getMaLHP());
      return map(stm.executeQuery());
    }
  }
  @Override
  public boolean add(SinhVien dto) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean update(SinhVien dto) throws SQLException {
      try (PreparedStatement stm = con.prepareStatement(UPDATE_SQL)) {
          stm.setString(1,dto.getHoten());
          stm.setString(2,dto.getDiachi());
          return stm.executeUpdate()>0;
      }
  }

  @Override
  public boolean delete(SinhVien dto) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public SinhVien get(String key) throws SQLException {
    try(PreparedStatement stm=con.prepareStatement(GET_SQL)){
      stm.setString(1, key);
      return mapOne(stm.executeQuery());
    }
  }

  @Override
  public List<SinhVien> getAll() throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public List<SinhVien> map(ResultSet rs) throws SQLException {
    List<SinhVien> dssv=new ArrayList<>();
    while(rs.next()){
      SinhVien sv=new SinhVien();
      sv.setMasv(rs.getString("masv"));
      sv.setHoten(rs.getString("hoten"));
      sv.setGioitinh(rs.getBoolean("gioitinh"));
      sv.setDiachi(rs.getString("diachi"));
      sv.setSdt(rs.getString("sdt"));
      sv.setNgaysinh(rs.getDate("ngaysinh"));
      sv.setNganh(ndao.get(rs.getString("manganh")));
      dssv.add(sv);
    }
    return dssv;
  }
  
}
