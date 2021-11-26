/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.DangKyHoc;
import entity.HocPhan;
import entity.LopHocPhan;
import entity.SinhVien;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author chung
 */
public class DangKyHocDAO extends AbstractDAO<DangKyHoc> {

  private final String ADD_SQL = "insert into dangkyhoc values (?,?,?)";
  private final String GET_ALL_OF_STUDENT = "select * from dangkyhoc where masv=?";
  private final String DELETE_SQL = "delete from dangkyhoc where masv=? and malhp=?";
  private final String GET_SQL = "select * from dangkyhoc where masv=? and malhp=?";
  private final SinhVienDAO svdao = new SinhVienDAO();
  private final LopHocPhanDAO lhpdao = new LopHocPhanDAO();

  private void checkValidDangKy(DangKyHoc dkh) throws SQLException {
    if (dkh.getLhp().getSiso() > 69) {
      throw new RuntimeException("Lớp học phần này đã đầy");
    }
    LopHocPhan lhp = dkh.getLhp();
    SinhVien sv = dkh.getSv();
    List<LopHocPhan> dslhpddk = lhpdao.getAllOfStudent(dkh.getSv(), lhp.getHocKy(), lhp.getNamHoc(), lhp.getThu());
    List<String> tiet = Arrays.asList(lhp.getTiet().split(","));
    for (LopHocPhan l : dslhpddk) {
      List<String> tiet1 = new ArrayList<>(Arrays.asList(l.getTiet().split(",")));
      tiet1.retainAll(tiet);
      if (tiet1.size() > 0) {
        throw new RuntimeException("Trùng lịch học của một học phần khác!");
      }
    }
    if(lhp.getHocPhan().getHptd()!=null){
      Set<HocPhan> dsHpdk=getAllOfStudent(sv).stream().map(dk->dk.getLhp().getHocPhan()).collect(Collectors.toSet());
      if(!dsHpdk.contains(lhp.getHocPhan().getHptd())){
        throw new RuntimeException("Phải đăng ký học phần tiền đề của môn học này trước!");
      }
    }
  }

  @Override
  public boolean add(DangKyHoc dto) throws SQLException {
    try (PreparedStatement stm = con.prepareStatement(ADD_SQL)) {
      checkValidDangKy(dto);
      stm.setString(1, dto.getSv().getMasv());
      stm.setString(2, dto.getLhp().getMaLHP());
      stm.setDate(3, dto.getNgayDK());
      return stm.executeUpdate()>0;
    }
  }

  @Override
  public boolean update(DangKyHoc dto) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean delete(DangKyHoc dto) throws SQLException {
    try (PreparedStatement stm = con.prepareStatement(DELETE_SQL)) {
      stm.setString(1, dto.getSv().getMasv());
      stm.setString(2, dto.getLhp().getMaLHP());
      return stm.executeUpdate()>0;
    }
  }

  @Override
  public DangKyHoc get(String key) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  public DangKyHoc get(String masv, String malhp) throws SQLException {
    try (PreparedStatement stm = con.prepareStatement(GET_SQL)) {
      stm.setString(1, masv);
      stm.setString(2, malhp);
      return mapOne(stm.executeQuery());
    }
  }

  public List<DangKyHoc> getAllOfStudent(SinhVien sv) throws SQLException {
    try (PreparedStatement stm = con.prepareStatement(GET_ALL_OF_STUDENT)) {
      stm.setString(1, sv.getMasv());
      return map(stm.executeQuery());
    }
  }

  @Override
  public List<DangKyHoc> getAll() throws SQLException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public List<DangKyHoc> map(ResultSet rs) throws SQLException {
    List<DangKyHoc> dsdkh = new ArrayList<>();
    while (rs.next()) {
      DangKyHoc dkh = new DangKyHoc();
      dkh.setLhp(lhpdao.get(rs.getString("malhp")));
      dkh.setSv(svdao.get(rs.getString("masv")));
      dkh.setNgayDK(rs.getDate("ngaydk"));
      dsdkh.add(dkh);
    }
    return dsdkh;
  }

}
