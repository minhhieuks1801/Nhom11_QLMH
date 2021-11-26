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
public class DangKyHoc extends DangKy{
  private SinhVien sv;
  private LopHocPhan lhp;

  public SinhVien getSv() {
    return sv;
  }

  public void setSv(SinhVien sv) {
    this.sv = sv;
  }

  public LopHocPhan getLhp() {
    return lhp;
  }

  public void setLhp(LopHocPhan lhp) {
    this.lhp = lhp;
  }
  
}
