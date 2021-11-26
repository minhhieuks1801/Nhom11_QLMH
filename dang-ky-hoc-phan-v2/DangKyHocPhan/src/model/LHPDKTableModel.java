/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.LopHocPhan;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author chung
 */
public class LHPDKTableModel extends MyAbstractTableModel<LopHocPhan>{

  private final List<String> headers=Arrays.asList("Mã lớp học phần","Tên lớp học phần","Thứ","Tiết","Phòng","Học kỳ","Năm học","Ngày bắt đầu","Sĩ số","Giảng viên");

  public LHPDKTableModel(List<LopHocPhan> dslhp) {
    super(dslhp);
  }
  
  @Override
  public Object getValueAt(int arg0, int arg1) {
    LopHocPhan lhp=data.get(arg0);
    switch(arg1){
      case 0: return lhp.getMaLHP();
      case 1: return lhp.getHocPhan();
      case 2: return lhp.getThu();
      case 3: return lhp.getTiet();
      case 4: return lhp.getPhong();
      case 5: return lhp.getHocKy();
      case 6: return lhp.getNamHoc();
      case 7:return lhp.getNgayBatDau();
      case 8:return lhp.getSiso()+"/70";
      default: return lhp.getGiangVien();
    }
  }

  @Override
  public List<String> getHeaders() {
    return headers;
  }
  
}
