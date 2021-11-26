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
public class ChuongTrinh {
  private Nganh nganh;
  private HocPhan hocPhan;

  public ChuongTrinh(Nganh nganh, HocPhan hocPhan) {
    this.nganh = nganh;
    this.hocPhan = hocPhan;
  }

  public ChuongTrinh() {
  }

  
  public Nganh getNganh() {
    return nganh;
  }

  public void setNganh(Nganh nganh) {
    this.nganh = nganh;
  }

  public HocPhan getHocPhan() {
    return hocPhan;
  }

  public void setHocPhan(HocPhan hocPhan) {
    this.hocPhan = hocPhan;
  }
  
}
