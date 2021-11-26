/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.DangKyDay;
import entity.GiangVien;
import entity.LopHocPhan;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author dat
 */
public class DangKyDayDAO extends AbstractDAO<DangKyDay>{

  private final String ADD_SQL = "insert into dangkygiangday values (?,?,?)";
  private final String GET_ALL_OF_TEACHER = "select * from dangkygiangday where magv=?";
  private final String DELETE_SQL = "delete from dangkygiangday where magv=? and malhp=?";
  private final String GET_SQL = "select * from dangkygiangday where magv=? and malhp=?";
  private final String EXISTS_SQL="select * from dangkygiangday where malhp=?";
  private final GiangVienDAO gvdao = new GiangVienDAO();
  private final LopHocPhanDAO lhpdao = new LopHocPhanDAO();

  private void checkValidLopHocPhan(LopHocPhan lhp) throws SQLException{
    try(PreparedStatement stm=con.prepareStatement(EXISTS_SQL)){
      stm.setString(1, lhp.getMaLHP());
      if(map(stm.executeQuery()).size()>0){
        throw new RuntimeException("Lớp học phần đã có giảng viên đăng ký dạy!");
      }
    }
  }
  private void checkValidDangKy(DangKyDay dkd) throws SQLException {
    LopHocPhan lhp = dkd.getLopHocPhan();
    GiangVien gv = dkd.getGiangVien();
    checkValidLopHocPhan(lhp);
    List<LopHocPhan> dslhpddk = lhpdao.getAllOfTeacher(gv, lhp.getHocKy(), lhp.getNamHoc(), lhp.getThu());
    List<String> tiet = Arrays.asList(lhp.getTiet().split(","));
    for (LopHocPhan l : dslhpddk) {
      List<String> tiet1 = new ArrayList<>(Arrays.asList(l.getTiet().split(",")));
      tiet1.retainAll(tiet);
      if (tiet1.size() > 0) {
        throw new RuntimeException("Trùng lịch dạy của một học phần khác!");
      }
    }
  }

  @Override
  public boolean add(DangKyDay dto) throws SQLException {
    try (PreparedStatement stm = con.prepareStatement(ADD_SQL)) {
      checkValidDangKy(dto);
      stm.setString(1, dto.getGiangVien().getMagv());
      stm.setString(2, dto.getLopHocPhan().getMaLHP());
      stm.setDate(3, dto.getNgayDK());
      return stm.executeUpdate()>0;
    }
  }

  @Override
  public boolean update(DangKyDay dto) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean delete(DangKyDay dto) throws SQLException {
    try (PreparedStatement stm = con.prepareStatement(DELETE_SQL)) {
      stm.setString(1, dto.getGiangVien().getMagv());
      stm.setString(2, dto.getLopHocPhan().getMaLHP());
      return stm.executeUpdate()>0;
    }
  }

  @Override
  public DangKyDay get(String key) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  public DangKyDay get(String masv, String malhp) throws SQLException {
    try (PreparedStatement stm = con.prepareStatement(GET_SQL)) {
      stm.setString(1, masv);
      stm.setString(2, malhp);
      return mapOne(stm.executeQuery());
    }
  }

  public List<DangKyDay> getAllOfTeacher(GiangVien gv) throws SQLException {
    try (PreparedStatement stm = con.prepareStatement(GET_ALL_OF_TEACHER)) {
      stm.setString(1, gv.getMagv());
      return map(stm.executeQuery());
    }
  }

  @Override
  public List<DangKyDay> getAll() throws SQLException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public List<DangKyDay> map(ResultSet rs) throws SQLException {
    List<DangKyDay> dsdkd = new ArrayList<>();
    while (rs.next()) {
      DangKyDay dkd = new DangKyDay();
      dkd.setLopHocPhan(lhpdao.get(rs.getString("malhp")));
      dkd.setGiangVien(gvdao.get(rs.getString("magv")));
      dkd.setNgayDK(rs.getDate("ngaydk"));
      dsdkd.add(dkd);
    }
    return dsdkd;
  }
  
}
