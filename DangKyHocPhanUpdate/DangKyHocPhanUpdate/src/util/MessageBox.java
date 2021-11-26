/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author chung
 */
public class MessageBox {
  public static void showErrorMessage(Component parent,String mess){
    JOptionPane.showMessageDialog(parent, mess,"Có lỗi xảy ra!",JOptionPane.ERROR_MESSAGE);
  }
  public static void showInfoMessage(Component parent,String mess){
    JOptionPane.showMessageDialog(parent, mess,"Thông báo",JOptionPane.INFORMATION_MESSAGE);
  }
  public static boolean showConfirmDialog(Component parent,String mess){
    return JOptionPane.showConfirmDialog(parent, mess,"Xác nhận?",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION;
  }
  public static File showFileChooser(Component parent,String title){
    JFileChooser chooser=new JFileChooser();
    chooser.setDialogTitle(title);
    return chooser.showSaveDialog(parent)==JFileChooser.APPROVE_OPTION?chooser.getSelectedFile():null;
  }
}
