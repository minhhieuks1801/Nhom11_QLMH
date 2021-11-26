/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import java.sql.Date;
/**
 *
 * @author chung
 */
public class Nguoi {
  private String hoten,sdt,diachi;
  private boolean gioitinh;
  private Date ngaysinh;

  public String getHoten() {
    return hoten;
  }

  public void setHoten(String hoten) {
    this.hoten = hoten;
  }

  public String getSdt() {
    return sdt;
  }

  public void setSdt(String sdt) {
    this.sdt = sdt;
  }

  public String getDiachi() {
    return diachi;
  }

  public void setDiachi(String diachi) {
    this.diachi = diachi;
  }

  public boolean isGioitinh() {
    return gioitinh;
  }

  public void setGioitinh(boolean gioitinh) {
    this.gioitinh = gioitinh;
  }

  public Date getNgaysinh() {
    return ngaysinh;
  }

  public void setNgaysinh(Date ngaysinh) {
    this.ngaysinh = ngaysinh;
  }
  
}
