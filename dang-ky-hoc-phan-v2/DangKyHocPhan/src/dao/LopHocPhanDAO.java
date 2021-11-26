/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.GiangVien;
import entity.LopHocPhan;
import entity.Nganh;
import entity.SinhVien;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author chung
 */
public class LopHocPhanDAO extends AbstractDAO<LopHocPhan> {

  private final HocPhanDAO hpdao;
  private final GiangVienDAO gdao = new GiangVienDAO();
  private final String GET_ALL_SQL = "select * from lophp";
  private final String UPDATE_SQL = "update lophp set thu=?,tiet=?,phong=?,hocky=?,namhoc=?,mahp=?,ngaybatdau=? where malhp=?";
  private final String GET_SQL = "select * from lophp where malhp=?";
  private final String ADD_SQL = "insert into lophp values (?,?,?,?,?,?,?,?)";
  private final String GET_TO_CHECK_SQL = "select * from lophp where thu=? and phong=? and hocky=? and namhoc=?";
  private final String DELETE_SQL = "delete from lophp where malhp=?";
  private final String GET_ALL_OF_MAJOR = "select * from lophp lhp join hocphan using(mahp) join chuongtrinh ct using (mahp) where manganh=?";
  private final String COUNT_SQL = "select count(*) from dangkyhoc where malhp=?";
  private final String GET_ALL_OF_STUDENT = "select * from lophp join dangkyhoc using(malhp) join hocphan using(mahp) where masv=? and hocky=? and namhoc=? and thu=?";
  private final String GET_ALL_OF_TEACHER = "select * from lophp join dangkygiangday using(malhp) join hocphan using(mahp) where magv=? and hocky=? and namhoc=? and thu=?";

  public LopHocPhanDAO() {
    hpdao = new HocPhanDAO();
  }

  public List<LopHocPhan> getAllOfStudent(SinhVien sv, String hocky, String namhoc, String thu) throws SQLException {
    try (PreparedStatement stm = con.prepareStatement(GET_ALL_OF_STUDENT)) {
      stm.setString(1, sv.getMasv());
      stm.setString(2, hocky);
      stm.setString(3, namhoc);
      stm.setString(4, thu);
      return map(stm.executeQuery());
    }
  }

  public List<LopHocPhan> getAllOfTeacher(GiangVien gv, String hocky, String namhoc, String thu) throws SQLException {
    try (PreparedStatement stm = con.prepareStatement(GET_ALL_OF_TEACHER)) {
      stm.setString(1, gv.getMagv());
      stm.setString(2, hocky);
      stm.setString(3, namhoc);
      stm.setString(4, thu);
      return map(stm.executeQuery());
    }
  }

  public List<LopHocPhan> getAllOfMajor(Nganh nganh) throws SQLException {
    try (PreparedStatement stm = con.prepareStatement(GET_ALL_OF_MAJOR)) {
      stm.setString(1, nganh.getMaNganh());
      return map(stm.executeQuery());
    }
  }

  private boolean checkValid(LopHocPhan lhp) throws SQLException {
    try (PreparedStatement stm = con.prepareStatement(GET_TO_CHECK_SQL)) {
      stm.setString(1, lhp.getThu());
      stm.setString(2, lhp.getPhong());
      stm.setString(3, lhp.getHocKy());
      stm.setString(4, lhp.getNamHoc());
      ResultSet rs = stm.executeQuery();
      while (rs.next()) {
        List<String> tietLi1 = new ArrayList<>(Arrays.asList(rs.getString("tiet").split(",")));
        List<String> tietLi2 = new ArrayList<>(Arrays.asList(lhp.getTiet().split(",")));
        tietLi1.retainAll(tietLi2);
        if (tietLi1.size() > 0) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public boolean add(LopHocPhan dto) throws SQLException {
    try (PreparedStatement stm = con.prepareStatement(ADD_SQL)) {
      if (!checkValid(dto)) {
        throw new RuntimeException("Phòng học đã được sử dụng bởi một lớp học phần khác!");
      }
      stm.setString(1, dto.getMaLHP());
      stm.setString(2, dto.getThu());
      stm.setString(3, dto.getTiet());
      stm.setString(4, dto.getPhong());
      stm.setString(5, dto.getHocKy());
      stm.setString(6, dto.getNamHoc());
      stm.setString(7, dto.getHocPhan().getMaHP());
      stm.setDate(8, dto.getNgayBatDau());
      return stm.executeUpdate()>0;
    }
  }

  @Override
  public boolean update(LopHocPhan dto) throws SQLException {
    try (PreparedStatement stm = con.prepareStatement(UPDATE_SQL)) {
      stm.setString(1, dto.getThu());
      stm.setString(2, dto.getTiet());
      stm.setString(3, dto.getPhong());
      stm.setString(4, dto.getHocKy());
      stm.setString(5, dto.getNamHoc());
      stm.setString(6, dto.getHocPhan().getMaHP());
      stm.setDate(7, dto.getNgayBatDau());
      stm.setString(8, dto.getMaLHP());
      return stm.executeUpdate()>0;
    }
  }

  @Override
  public boolean delete(LopHocPhan dto) throws SQLException {
    try (PreparedStatement stm = con.prepareStatement(DELETE_SQL)) {
      stm.setString(1, dto.getMaLHP());
      return stm.executeUpdate()>0;
    }
  }

  @Override
  public LopHocPhan get(String key) throws SQLException {
    try (PreparedStatement stm = con.prepareStatement(GET_SQL)) {
      stm.setString(1, key);
      return mapOne(stm.executeQuery());
    }
  }

  @Override
  public List<LopHocPhan> getAll() throws SQLException {
    try (Statement stm = con.createStatement()) {
      return map(stm.executeQuery(GET_ALL_SQL));
    }
  }

  private int countSinhVien(LopHocPhan lhp) throws SQLException{
    try (PreparedStatement ps = con.prepareStatement(COUNT_SQL)) {
      ps.setString(1, lhp.getMaLHP());
      ResultSet rs = ps.executeQuery();
      rs.next();
      return rs.getInt(1);
    }
  }

  @Override
  public List<LopHocPhan> map(ResultSet rs) throws SQLException {
    List<LopHocPhan> dslhp = new ArrayList<>();
    while (rs.next()) {
      LopHocPhan lhp = new LopHocPhan();
      lhp.setMaLHP(rs.getString("malhp"));
      lhp.setThu(rs.getString("thu"));
      lhp.setTiet(rs.getString("tiet"));
      lhp.setPhong(rs.getString("phong"));
      lhp.setHocKy(rs.getString("hocky"));
      lhp.setNamHoc(rs.getString("namhoc"));
      lhp.setHocPhan(hpdao.get(rs.getString("mahp")));
      lhp.setNgayBatDau(rs.getDate("ngaybatdau"));
      lhp.setGiangVien(gdao.getTeacherOfClass(lhp));
      lhp.setSiso(countSinhVien(lhp));
      dslhp.add(lhp);
    }
    return dslhp;
  }
}
