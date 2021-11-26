/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author chung
 */
public class LopHocPhan {
  private String maLHP,thu,tiet,phong,hocKy,namHoc;
  private Date ngayBatDau;
  private HocPhan hocPhan;
  private int siso;
  private GiangVien giangVien;

  public GiangVien getGiangVien() {
    return giangVien;
  }

  public void setGiangVien(GiangVien giangVien) {
    this.giangVien = giangVien;
  }

  
  public String getMaLHP() {
    return maLHP;
  }

  public void setMaLHP(String maLHP) {
    if(maLHP.isBlank()) throw new RuntimeException("Mã lớp học phần không được bỏ trống!");
    this.maLHP = maLHP;
  }

  public int getSiso() {
    return siso;
  }

  public void setSiso(int siso) {
    this.siso = siso;
  }

  public String getThu() {
    return thu;
  }

  public void setThu(String thu) {
    this.thu = thu;
  }

  public String getTiet() {
    return tiet;
  }

  public void setTiet(String tiet) {
    if(tiet.isBlank()) throw new RuntimeException("Lớp học phần phải có ít nhất một tiết học!");
    this.tiet = tiet;
  }

  public String getPhong() {
    return phong;
  }

  public void setPhong(String phong) {
    if(phong.isBlank()) throw new RuntimeException("Phòng học không được bỏ trống!");
    this.phong = phong;
  }

  public String getHocKy() {
    return hocKy;
  }

  public void setHocKy(String hocKy) {
    this.hocKy = hocKy;
  }

  public String getNamHoc() {
    return namHoc;
  }

  public void setNamHoc(String namHoc) {
    if(namHoc.isBlank()) throw new RuntimeException("Năm học không được bỏ trống!");
    if(!namHoc.trim().matches("\\d{4}-\\d{4}"))  throw new RuntimeException("Năm học không hợp lệ!");
    this.namHoc = namHoc;
  }

  public Date getNgayBatDau() {
    return ngayBatDau;
  }

  public void setNgayBatDau(Date ngayBatDau) {
    this.ngayBatDau = ngayBatDau;
  }

  public HocPhan getHocPhan() {
    return hocPhan;
  }

  public void setHocPhan(HocPhan hocPhan) {
    this.hocPhan = hocPhan;
  }
  public int getHocPhi(){
    return hocPhan.getSoTC()*400000;
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 71 * hash + Objects.hashCode(this.maLHP);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final LopHocPhan other = (LopHocPhan) obj;
    if (!Objects.equals(this.maLHP, other.maLHP)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return String.format("%s (%s)", maLHP,hocPhan.getTenHP());
  }
}
