/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author chung
 */
public class HocPhan {
  private String maHP,tenHP,moTa,ppdg;
  private int soTC;
  private boolean batBuoc;
  private HocPhan hptd;
  private List<Nganh> dsNganh;

  public List<Nganh> getDsNganh() {
    return dsNganh;
  }

  public void setDsNganh(List<Nganh> dsNganh) {
    if (dsNganh.size()<1) throw new RuntimeException("Phải chọn ít nhất một ngành học học phần này!");
    this.dsNganh = dsNganh;
  }

  public String getMaHP() {
    return maHP;
  }

  public void setMaHP(String maHP) {
    if(maHP.isBlank())  throw new RuntimeException("Không được để trống mã học phần!");
    this.maHP = maHP;
  }

  public String getTenHP() {
    return tenHP;
  }

  public void setTenHP(String tenHP) {
    if(tenHP.isBlank())  throw new RuntimeException("Không được để trống tên học phần!");
    this.tenHP = tenHP;
  }

  public String getMoTa() {
    return moTa;
  }

  public void setMoTa(String moTa) {
    this.moTa = moTa;
  }

  public String getPpdg() {
    return ppdg;
  }

  public void setPpdg(String ppdg) {
    this.ppdg = ppdg;
  }

  public int getSoTC() {
    return soTC;
  }

  public void setSoTC(int soTC) {
    if(soTC<1)  throw new RuntimeException("Số tín chỉ không hợp lệ!");
    this.soTC = soTC;
  }

  public boolean isBatBuoc() {
    return batBuoc;
  }

  public void setBatBuoc(boolean batBuoc) {
    this.batBuoc = batBuoc;
  }

  public HocPhan getHptd() {
    return hptd;
  }

  public void setHptd(HocPhan hptd) {
    this.hptd = hptd;
  }

  @Override
  public String toString() {
    return String.format("%s (%s)", maHP,tenHP);
  }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.maHP);
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
        final HocPhan other = (HocPhan) obj;
        if (!Objects.equals(this.maHP, other.maHP)) {
            return false;
        }
        return true;
    }
}
