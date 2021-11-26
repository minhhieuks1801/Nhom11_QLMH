/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.HocPhan;
import entity.Nganh;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author chung
 */
public class HocPhanTableModel extends MyAbstractTableModel<HocPhan> {

  private final List<String> headers = Arrays.asList("Mã học phần", "Học phần tiền đề", "Chuyên ngành", "Tên học phần", "Mô tả", "Số tín chỉ", "Phương pháp đánh giá", "Bắt buộc");

  public HocPhanTableModel(List<HocPhan> dshp) {
    super(dshp);
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    HocPhan hp = data.get(rowIndex);
    switch (columnIndex) {
      case 0:
        return hp.getMaHP();
      case 1:
        return hp.getHptd();
      case 2:
        return hp.getDsNganh().stream().map(Nganh::toString).collect(Collectors.joining(","));
      case 3:
        return hp.getTenHP();
      case 4:
        return hp.getMoTa();
      case 5:
        return hp.getSoTC();
      case 6:
        return hp.getPpdg();
      default:
        return hp.isBatBuoc() ? "Bắt buộc" : "Tự chọn";
    }
  }

  @Override
  public List getHeaders() {
    return headers;
  }
}
