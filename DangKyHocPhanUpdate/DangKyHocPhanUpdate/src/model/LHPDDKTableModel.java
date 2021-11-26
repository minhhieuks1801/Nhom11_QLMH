/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.DangKyHoc;
import entity.LopHocPhan;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author chung
 */
public class LHPDDKTableModel extends MyAbstractTableModel<DangKyHoc>{

  private List<String> headers=Arrays.asList("Mã lớp học phần","Tên lớp học phần","Thứ","Tiết","Phòng","Học kỳ","Năm học","Ngày bắt đầu","Ngày đăng ký","Học phí","Giảng viên");
  public LHPDDKTableModel(List<DangKyHoc> data) {
    super(data);
  }

  @Override
  public Object getValueAt(int arg0, int arg1) {
    DangKyHoc dkh=data.get(arg0);
    LopHocPhan lhp=dkh.getLhp();
    switch(arg1){
      case 0: return lhp.getMaLHP();
      case 1: return lhp.getHocPhan();
      case 2: return lhp.getThu();
      case 3: return lhp.getTiet();
      case 4: return lhp.getPhong();
      case 5: return lhp.getHocKy();
      case 6: return lhp.getNamHoc();
      case 7:return lhp.getNgayBatDau();
      case 8: return dkh.getNgayDK();
      case 9:return lhp.getHocPhi();
      default:return lhp.getGiangVien();
    }
  }

  @Override
  public List<String> getHeaders() {
    return headers;
  }
  
}
