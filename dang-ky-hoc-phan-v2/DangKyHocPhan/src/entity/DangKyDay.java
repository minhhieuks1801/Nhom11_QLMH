/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author chung
 */
public class DangKyDay extends DangKy{
  private GiangVien giangVien;
  private LopHocPhan lopHocPhan;

  public GiangVien getGiangVien() {
    return giangVien;
  }

  public void setGiangVien(GiangVien giangVien) {
    this.giangVien = giangVien;
  }

  public LopHocPhan getLopHocPhan() {
    return lopHocPhan;
  }

  public void setLopHocPhan(LopHocPhan lopHocPhan) {
    this.lopHocPhan = lopHocPhan;
  }
  
}
