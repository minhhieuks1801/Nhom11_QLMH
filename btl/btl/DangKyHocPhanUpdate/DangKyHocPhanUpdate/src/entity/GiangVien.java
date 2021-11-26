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
public class GiangVien extends Nguoi{
  private String magv;
  private Khoa khoa;

  public String getMagv() {
    return magv;
  }

  public void setMagv(String magv) {
    this.magv = magv;
  }

  public Khoa getKhoa() {
    return khoa;
  }

  public void setKhoa(Khoa khoa) {
    this.khoa = khoa;
  }

  @Override
  public String toString() {
    return getHoten();
  }
  
}
