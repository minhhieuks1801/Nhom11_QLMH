/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.sinhvienui;

import dao.DangKyHocDAO;
import dao.LopHocPhanDAO;
import dao.SinhVienDAO;
import entity.DangKyHoc;
import entity.LopHocPhan;
import entity.SinhVien;
import java.sql.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import model.LHPDDKTableModel;
import model.LHPDKTableModel;
import util.LoginContext;
import util.MessageBox;

/**
 *
 * @author chung
 */
public class DangKyHocPhanPanel extends javax.swing.JPanel {

  private final LopHocPhanDAO lhpdao;
  private final DangKyHocDAO dkhpdao;
  private final SinhVienDAO svdao;
  private TableRowSorter<LHPDKTableModel> sorter;
  /**
   * Creates new form DangKyHocPhanPanel
   */
  public DangKyHocPhanPanel() {
    initComponents();
    lhpdao = new LopHocPhanDAO();
    dkhpdao = new DangKyHocDAO();
    svdao = new SinhVienDAO();
    load();
    filterTextTf.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent arg0) {
        onFilter();
      }

      @Override
      public void removeUpdate(DocumentEvent arg0) {
        onFilter();
      }

      @Override
      public void changedUpdate(DocumentEvent arg0) {
        onFilter();
      }
    });
  }

  private void load() {
    System.out.println(LoginContext.getUser().getUsername());
    try {
      SinhVien sv = svdao.get(LoginContext.getUser().getUsername());
      List<LopHocPhan> dslhp = lhpdao.getAllOfMajor(sv.getNganh());
      LHPDKTableModel lhpTableModel=new LHPDKTableModel(dslhp);
      lhpTb.setModel(lhpTableModel);
      sorter=new TableRowSorter<>(lhpTableModel);
      lhpTb.setRowSorter(sorter);
      filterByCombo.setModel(new DefaultComboBoxModel<>(new Vector(lhpTableModel.getHeaders())));
      List<DangKyHoc> dslhpddk = dkhpdao.getAllOfStudent(sv);
      lhpdkTb.setModel(new LHPDDKTableModel(dslhpddk));
    } catch (Exception e) {
      MessageBox.showErrorMessage(this, e.getMessage());
      e.printStackTrace();
    }
  }

  private void onFilter(){
    String filterText=filterTextTf.getText();
    sorter.setRowFilter(RowFilter.regexFilter(filterText, filterByCombo.getSelectedIndex()));
  }
  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
   * content of this method is always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jPanel1 = new javax.swing.JPanel();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jPanel2 = new javax.swing.JPanel();
    jLabel3 = new javax.swing.JLabel();
    filterByCombo = new javax.swing.JComboBox<>();
    filterTextTf = new javax.swing.JTextField();
    jButton1 = new javax.swing.JButton();
    jLabel4 = new javax.swing.JLabel();
    jButton2 = new javax.swing.JButton();
    jScrollPane3 = new javax.swing.JScrollPane();
    lhpTb = new ui.WithTooltipTable();
    jScrollPane1 = new javax.swing.JScrollPane();
    lhpdkTb = new javax.swing.JTable();

    jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabel1.setText("ĐĂNG KÝ HỌC PHẦN");

    jLabel2.setText("Danh sách lớp học phần:");

    jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

    jLabel3.setText("Tìm kiếm:");
    jPanel2.add(jLabel3);

    filterByCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
    jPanel2.add(filterByCombo);

    filterTextTf.setColumns(15);
    jPanel2.add(filterTextTf);

    jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/remove.png"))); // NOI18N
    jButton1.setText("Hủy");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton1ActionPerformed(evt);
      }
    });

    jLabel4.setText("Danh sách lớp học phần đã đăng ký:");

    jButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/add.png"))); // NOI18N
    jButton2.setText("Đăng ký");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton2ActionPerformed(evt);
      }
    });

    lhpTb.setAutoCreateRowSorter(true);
    lhpTb.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {
        {null, null, null, null},
        {null, null, null, null},
        {null, null, null, null},
        {null, null, null, null}
      },
      new String [] {
        "Title 1", "Title 2", "Title 3", "Title 4"
      }
    ));
    jScrollPane3.setViewportView(lhpTb);

    lhpdkTb.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {
        {null, null, null, null},
        {null, null, null, null},
        {null, null, null, null},
        {null, null, null, null}
      },
      new String [] {
        "Title 1", "Title 2", "Title 3", "Title 4"
      }
    ));
    jScrollPane1.setViewportView(lhpdkTb);

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addComponent(jLabel2)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
          .addComponent(jScrollPane1)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jButton2)
              .addComponent(jLabel4)
              .addComponent(jButton1))
            .addGap(0, 0, Short.MAX_VALUE)))
        .addContainerGap())
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(jLabel1)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(55, 55, 55)
            .addComponent(jLabel2))
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel1)
            .addGap(18, 18, 18)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addGap(6, 6, 6)
        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(20, 20, 20)
        .addComponent(jButton2)
        .addGap(20, 20, 20)
        .addComponent(jLabel4)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(20, 20, 20)
        .addComponent(jButton1)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap(10, Short.MAX_VALUE)
        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
  }// </editor-fold>//GEN-END:initComponents

  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    try {
      int row = lhpdkTb.getSelectedRow();
      if (row == -1) {
        throw new RuntimeException("Chọn một lớp học phần để hủy!");
      }
      if (MessageBox.showConfirmDialog(this, "Hủy học phần này?")) {
        DangKyHoc lhp = ((LHPDDKTableModel) lhpdkTb.getModel()).getRowAt(row);
        dkhpdao.delete(lhp);
        load();
      }
    } catch (Exception e) {
      MessageBox.showErrorMessage(this, e.getMessage());
    }
    // TODO add your handling code here:
  }//GEN-LAST:event_jButton1ActionPerformed

  private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

    try {
      int row = lhpTb.getSelectedRow();
      if (row == -1) {
        throw new RuntimeException("Chọn một lớp học phần để đăng ký!");
      }
      SinhVien sv = svdao.get(LoginContext.getUser().getUsername());
      DangKyHoc dkh = new DangKyHoc();
      LopHocPhan lhp = ((LHPDKTableModel) lhpTb.getModel()).getRowAt(row);
      dkh.setSv(sv);
      dkh.setLhp(lhp);
      dkh.setNgayDK(new Date(System.currentTimeMillis()));
      dkhpdao.add(dkh);
      load();
    } catch (Exception e) {
      MessageBox.showErrorMessage(this, e.getMessage());
    }
    // TODO add your handling code here:
  }//GEN-LAST:event_jButton2ActionPerformed


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JComboBox<String> filterByCombo;
  private javax.swing.JTextField filterTextTf;
  private javax.swing.JButton jButton1;
  private javax.swing.JButton jButton2;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JScrollPane jScrollPane3;
  private ui.WithTooltipTable lhpTb;
  private javax.swing.JTable lhpdkTb;
  // End of variables declaration//GEN-END:variables
}