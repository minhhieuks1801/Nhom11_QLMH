/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Objects;

/**
 *
 * @author chung
 */
public class Nganh {
  private String maNganh,tenNganh;
  private Khoa khoa;

  public String getMaNganh() {
    return maNganh;
  }

  public void setMaNganh(String maNganh) {
    this.maNganh = maNganh;
  }

  public String getTenNganh() {
    return tenNganh;
  }

  public void setTenNganh(String tenNganh) {
    this.tenNganh = tenNganh;
  }

  public Khoa getKhoa() {
    return khoa;
  }

  public void setKhoa(Khoa khoa) {
    this.khoa = khoa;
  }

  @Override
  public String toString() {
    return String.format("%s (%s)", maNganh,tenNganh);
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 31 * hash + Objects.hashCode(this.maNganh);
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
    final Nganh other = (Nganh) obj;
    if (!Objects.equals(this.maNganh, other.maNganh)) {
      return false;
    }
    return true;
  }
  
}
