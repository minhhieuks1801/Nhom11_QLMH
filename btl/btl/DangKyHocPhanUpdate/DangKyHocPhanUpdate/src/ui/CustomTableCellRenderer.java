/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import entity.LopHocPhan;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author chung
 */
public class CustomTableCellRenderer extends JLabel implements TableCellRenderer {

  @Override
  public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4, int arg5) {
    if (arg1 == null) {
      return null;
    }
    LopHocPhan lhp = (LopHocPhan) arg1;
    String html = "<html><b>" + lhp.getMaLHP() + "</b><br><i>" + lhp.getHocPhan().getTenHP() + "</i><br><b>" + lhp.getTiet() + "</b><br><i>" + lhp.getPhong() + "</i></html>";
    setText(html);
    return this;
  }

}
