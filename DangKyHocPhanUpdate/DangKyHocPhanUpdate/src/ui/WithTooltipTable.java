/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.JTable;

/**
 *
 * @author chung
 */
public class WithTooltipTable extends JTable{

  @Override
  public String getToolTipText(MouseEvent event) {
    Point p=event.getPoint();
    Object text=getValueAt(rowAtPoint(p), columnAtPoint(p));
    return text==null?"<trống>":text.toString();
  }

}
