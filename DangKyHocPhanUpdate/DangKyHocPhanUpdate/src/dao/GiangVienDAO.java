/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.GiangVien;
import entity.LopHocPhan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author chung
 */
public class GiangVienDAO extends AbstractDAO<GiangVien>{
    

  private final String GET_SQL="select * from giangvien where magv=?";
  private final String GET_TEACHER_OF_CLASS="select * from dangkygiangday where malhp=?";
  private final String UPDATE_SQL = "UPDATE GIANGVIEN  SET HoTen = ?, DiaChi = ? WHERE MaGV = ?" ;
  private final KhoaDAO kdao=new KhoaDAO();
  
  @Override
  public boolean add(GiangVien dto) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean update(GiangVien dto) throws SQLException {
     try (PreparedStatement stm = con.prepareStatement(UPDATE_SQL)) {
          stm.setString(1,dto.getHoten());
          stm.setString(2,dto.getDiachi());
          return stm.executeUpdate()>0;
      }
  }

  @Override
  public boolean delete(GiangVien dto) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public GiangVien get(String key) throws SQLException {
    try(PreparedStatement stm=con.prepareStatement(GET_SQL)){
      stm.setString(1, key);
      return mapOne(stm.executeQuery());
    }
  }

  @Override
  public List<GiangVien> getAll() throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
  public GiangVien getTeacherOfClass(LopHocPhan lhp) throws SQLException{
    try(PreparedStatement stm=con.prepareStatement(GET_TEACHER_OF_CLASS)){
      stm.setString(1, lhp.getMaLHP());
      ResultSet rs=stm.executeQuery();
      System.out.println(rs.getFetchSize());
      if(rs.next()){
        return get(rs.getString("magv"));
      }
      return null;
    }
  }
  @Override
  public List<GiangVien> map(ResultSet rs) throws SQLException {
    List<GiangVien> dsgv=new ArrayList<>();
    while(rs.next()){
      GiangVien gv=new GiangVien();
      gv.setMagv(rs.getString("magv"));
      gv.setHoten(rs.getString("hoten"));
      gv.setGioitinh(rs.getBoolean("gioitinh"));
      gv.setDiachi(rs.getString("diachi"));
      gv.setSdt(rs.getString("sdt"));
      gv.setNgaysinh(rs.getDate("ngaysinh"));
      gv.setKhoa(kdao.get(rs.getString("makhoa")));
      dsgv.add(gv);
    }
    return dsgv;
  }
  
}
