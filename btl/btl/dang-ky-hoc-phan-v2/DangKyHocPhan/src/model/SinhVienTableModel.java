/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.SinhVien;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author chung
 */
public class SinhVienTableModel extends MyAbstractTableModel<SinhVien>{

  private final List<String> headers=Arrays.asList("Mã sinh viên","Họ tên","Ngày sinh","Giới tính","Địa chỉ","Điện thoại");
  public SinhVienTableModel(List<SinhVien> data) {
    super(data);
  }

  @Override
  public List<String> getHeaders() {
    return headers;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    SinhVien sv=data.get(rowIndex);
    switch (columnIndex){
      case 0: return sv.getMasv();
      case 1: return sv.getHoten();
      case 2: return sv.getNgaysinh();
      case 3: return sv.isGioitinh()?"Nam":"Nữ";
      case 4: return sv.getDiachi();
      default:return sv.getSdt();
    }
  }
  
}
