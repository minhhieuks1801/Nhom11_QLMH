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
public class SinhVien extends Nguoi{
  private String masv;
  private Nganh nganh;

  public String getMasv() {
    return masv;
  }

  public void setMasv(String masv) {
    this.masv = masv;
  }

  public Nganh getNganh() {
    return nganh;
  }

  public void setNganh(Nganh nganh) {
    this.nganh = nganh;
  }
  
}
