/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.LopHocPhan;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chung
 */
public class TKBTableModel extends MyAbstractTableModel<LopHocPhan>{

  private final List<String> headers= Arrays.asList("Thứ hai","Thứ ba","Thứ tư","Thứ năm","Thứ sáu","Thứ bảy","Chủ nhật");
  private final List<String> dsthu=Arrays.asList("Hai","Ba","Tư","Năm","Sáu","Bảy","Chủ nhật");
  private int rowCount;
  private Map<String,List<LopHocPhan>> mapData;
  public TKBTableModel(List<LopHocPhan> data) {
    super(data);
    mapData=new HashMap<>();
    for(LopHocPhan lhp:data){
      String thu=lhp.getThu();
      if(mapData.containsKey(thu)){
        mapData.get(thu).add(lhp);
      } else {
        mapData.put(thu, new ArrayList<>(Arrays.asList(lhp)));
      }
    }
    for (Map.Entry<String, List<LopHocPhan>> entry : mapData.entrySet()) {
      if(entry.getValue().size()>rowCount){
        rowCount=entry.getValue().size();
      }
    }
  }
  
  @Override
  public int getRowCount() {
    return rowCount;
  }

  @Override
  public Object getValueAt(int row, int col) {
    String thu=dsthu.get(col);
    List<LopHocPhan> dslhp=mapData.get(thu);
    if(dslhp==null || row>=dslhp.size()) return null;
    return dslhp.get(row);
  }

  @Override
  public List<String> getHeaders() {
    return headers;
  }

  public Map<String, List<LopHocPhan>> getMapData() {
    return mapData;
  }
}
