/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.adminui;

import dao.HocPhanDAO;
import dao.LopHocPhanDAO;
import entity.HocPhan;
import entity.LopHocPhan;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import model.LopHocPhanTableModel;
import util.MessageBox;

/**
 *
 * @author chung
 */
public class QllhpPanel extends javax.swing.JPanel {

  /**
   * Creates new form QllhpPanel
   */
  private final List<JCheckBox> tietCheckBoxs;
  private final LopHocPhanDAO lhpdao;
  private final HocPhanDAO hpdao;
  private TableRowSorter<LopHocPhanTableModel> rowSorter;
  
  public QllhpPanel() {
    initComponents();
    tietCheckBoxs = Arrays.asList(jCheckBox1, jCheckBox2, jCheckBox3, jCheckBox4, jCheckBox5, jCheckBox6, jCheckBox7, jCheckBox8, jCheckBox9, jCheckBox10);
    lhpdao = new LopHocPhanDAO();
    hpdao = new HocPhanDAO();
    lhpTable.getSelectionModel().addListSelectionListener(e -> onRowClick());
    keyTf.getDocument().addDocumentListener(new DocumentListener() {
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
    load();
    ngaybatdauPicker.getModel().setSelected(true);
    LopHocPhanTableModel model = (LopHocPhanTableModel) lhpTable.getModel();
    filterByCombo.setModel(new DefaultComboBoxModel<>(new Vector<>(model.getHeaders())));
  }
  
  private void onRowClick(){
    int row = lhpTable.getSelectedRow();
      if (row == -1) {
        return;
      }
      LopHocPhan lhp = ((LopHocPhanTableModel) lhpTable.getModel()).getRowAt(row);
      malhpTf.setEnabled(false);
      malhpTf.setText(lhp.getMaLHP());
      hpCombo.setSelectedItem(lhp.getHocPhan());
      thuCombo.setSelectedItem(lhp.getThu());
      for (JCheckBox cb : tietCheckBoxs) {
        cb.setSelected(false);
      }
      for (String t : lhp.getTiet().split(",")) {
        int i = Integer.parseInt(t) - 1;
        tietCheckBoxs.get(i).setSelected(true);
      }
      hockyCombo.setSelectedItem(lhp.getHocKy());
      namhocStartTf.setText(lhp.getNamHoc().substring(0, 4));
      namhocEndTf.setText(lhp.getNamHoc().substring(5));
      phongTf.setText(lhp.getPhong());
      LocalDate nbd = lhp.getNgayBatDau().toLocalDate();
      ngaybatdauPicker.getModel().setDate(nbd.getYear(), nbd.getMonthValue(), nbd.getDayOfMonth());
  }
  private void onFilter() {
    System.out.println("onFilter");
    System.out.println(keyTf.getText());
    rowSorter.setRowFilter(RowFilter.regexFilter(keyTf.getText(), filterByCombo.getSelectedIndex()));
  }

  private void load() {
    try {
      Vector<HocPhan> dshp = new Vector<>(hpdao.getAll());
      DefaultComboBoxModel<HocPhan> hpComboBoxModel = new DefaultComboBoxModel<>(dshp);
      hpCombo.setModel(hpComboBoxModel);
      LopHocPhanTableModel lhpModel = new LopHocPhanTableModel(lhpdao.getAll());
      lhpTable.setModel(lhpModel);
      rowSorter = new TableRowSorter<>(lhpModel);
      lhpTable.setRowSorter(rowSorter);
    } catch (SQLException ex) {
      Logger.getLogger(QllhpPanel.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  private void refresh() {
    load();
    malhpTf.setEnabled(true);
    malhpTf.setText("");
    hpCombo.setSelectedItem(0);
    thuCombo.setSelectedItem(0);
    for (JCheckBox cb : tietCheckBoxs) {
      cb.setSelected(false);
    }
    hockyCombo.setSelectedItem(0);
    namhocStartTf.setText("");
    namhocEndTf.setText("");
    phongTf.setText("");
    LocalDate nbd = LocalDate.now();
    ngaybatdauPicker.getModel().setDate(nbd.getYear(), nbd.getMonthValue(), nbd.getDayOfMonth());
  }

  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
   * content of this method is always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jPanel1 = new javax.swing.JPanel();
    jPanel2 = new javax.swing.JPanel();
    jLabel1 = new javax.swing.JLabel();
    jPanel4 = new javax.swing.JPanel();
    jButton1 = new javax.swing.JButton();
    jButton2 = new javax.swing.JButton();
    jButton3 = new javax.swing.JButton();
    jButton4 = new javax.swing.JButton();
    jPanel5 = new javax.swing.JPanel();
    ngaybatdauPicker = new org.jdatepicker.JDatePicker();
    jLabel4 = new javax.swing.JLabel();
    thuCombo = new javax.swing.JComboBox<>();
    hpCombo = new javax.swing.JComboBox<>();
    jLabel5 = new javax.swing.JLabel();
    malhpTf = new javax.swing.JTextField();
    jPanel3 = new javax.swing.JPanel();
    jCheckBox1 = new javax.swing.JCheckBox();
    jCheckBox2 = new javax.swing.JCheckBox();
    jCheckBox3 = new javax.swing.JCheckBox();
    jCheckBox4 = new javax.swing.JCheckBox();
    jCheckBox5 = new javax.swing.JCheckBox();
    jCheckBox6 = new javax.swing.JCheckBox();
    jCheckBox7 = new javax.swing.JCheckBox();
    jCheckBox8 = new javax.swing.JCheckBox();
    jCheckBox10 = new javax.swing.JCheckBox();
    jCheckBox9 = new javax.swing.JCheckBox();
    jLabel10 = new javax.swing.JLabel();
    hockyCombo = new javax.swing.JComboBox<>();
    namhocEndTf = new javax.swing.JTextField();
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    phongTf = new javax.swing.JTextField();
    namhocStartTf = new javax.swing.JTextField();
    jLabel7 = new javax.swing.JLabel();
    jLabel6 = new javax.swing.JLabel();
    jLabel8 = new javax.swing.JLabel();
    jLabel9 = new javax.swing.JLabel();
    jPanel6 = new javax.swing.JPanel();
    jLabel11 = new javax.swing.JLabel();
    filterByCombo = new javax.swing.JComboBox<>();
    keyTf = new javax.swing.JTextField();
    jScrollPane2 = new javax.swing.JScrollPane();
    lhpTable = new ui.WithTooltipTable();

    jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabel1.setText("QUẢN LÝ LỚP HỌC PHẦN");

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel1)
        .addContainerGap())
    );
    jPanel2Layout.setVerticalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel1)
        .addContainerGap())
    );

    jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/add.png"))); // NOI18N
    jButton1.setText("Thêm");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton1ActionPerformed(evt);
      }
    });
    jPanel4.add(jButton1);

    jButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/edit.png"))); // NOI18N
    jButton2.setText("Sửa");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton2ActionPerformed(evt);
      }
    });
    jPanel4.add(jButton2);

    jButton3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/remove.png"))); // NOI18N
    jButton3.setText("Xóa");
    jButton3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton3ActionPerformed(evt);
      }
    });
    jPanel4.add(jButton3);

    jButton4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/refresh.png"))); // NOI18N
    jButton4.setText("Làm mới");
    jButton4.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton4ActionPerformed(evt);
      }
    });
    jPanel4.add(jButton4);

    ngaybatdauPicker.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        ngaybatdauPickerActionPerformed(evt);
      }
    });

    jLabel4.setText("Thứ:");

    thuCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hai", "Ba", "Tư", "Năm", "Sáu", "Bảy", "Chủ nhật" }));

    jLabel5.setText("Tiết:");

    malhpTf.setColumns(15);

    jCheckBox1.setText("1");
    jPanel3.add(jCheckBox1);

    jCheckBox2.setText("2");
    jPanel3.add(jCheckBox2);

    jCheckBox3.setText("3");
    jPanel3.add(jCheckBox3);

    jCheckBox4.setText("4");
    jPanel3.add(jCheckBox4);

    jCheckBox5.setText("5");
    jPanel3.add(jCheckBox5);

    jCheckBox6.setText("6");
    jPanel3.add(jCheckBox6);

    jCheckBox7.setText("7");
    jPanel3.add(jCheckBox7);

    jCheckBox8.setText("8");
    jPanel3.add(jCheckBox8);

    jCheckBox10.setText("9");
    jPanel3.add(jCheckBox10);

    jCheckBox9.setText("10");
    jCheckBox9.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jCheckBox9ActionPerformed(evt);
      }
    });
    jPanel3.add(jCheckBox9);

    jLabel10.setText("Phòng:");

    hockyCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "I", "II", "Phụ" }));

    namhocEndTf.setColumns(6);

    jLabel2.setText("Mã lớp học phần:");

    jLabel3.setText("Học phần:");

    phongTf.setColumns(15);

    namhocStartTf.setColumns(6);

    jLabel7.setText("Năm học:");

    jLabel6.setText("Học kỳ:");

    jLabel8.setText("Ngày bắt đầu:");

    jLabel9.setText("-");

    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
    jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(
      jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel5Layout.createSequentialGroup()
        .addGap(20, 20, 20)
        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel10)
          .addComponent(jLabel2)
          .addComponent(jLabel3))
        .addGap(21, 21, 21)
        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(malhpTf)
          .addComponent(thuCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(hpCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(phongTf))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel5Layout.createSequentialGroup()
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel6)
              .addComponent(jLabel7))
            .addGap(36, 36, 36)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
              .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(namhocStartTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(namhocEndTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addComponent(hockyCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
          .addGroup(jPanel5Layout.createSequentialGroup()
            .addComponent(jLabel8)
            .addGap(14, 14, 14)
            .addComponent(ngaybatdauPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(jPanel5Layout.createSequentialGroup()
            .addComponent(jLabel5)
            .addGap(58, 58, 58)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap())
    );
    jPanel5Layout.setVerticalGroup(
      jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel5Layout.createSequentialGroup()
        .addGap(4, 4, 4)
        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel5Layout.createSequentialGroup()
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(jLabel2)
              .addComponent(malhpTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(jLabel3)
              .addComponent(hpCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(7, 7, 7)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(thuCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(jLabel4))
            .addGap(11, 11, 11)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(phongTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(jLabel10)))
          .addGroup(jPanel5Layout.createSequentialGroup()
            .addGap(1, 1, 1)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(jLabel6)
              .addComponent(hockyCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(namhocEndTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel7)
                .addComponent(namhocStartTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel9)))
            .addGap(7, 7, 7)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(ngaybatdauPicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(jLabel8))
            .addGap(11, 11, 11)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel5)
              .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
    );

    jLabel11.setText("Tìm kiếm:");
    jPanel6.add(jLabel11);
    jPanel6.add(filterByCombo);

    keyTf.setColumns(15);
    jPanel6.add(keyTf);

    lhpTable.setModel(new javax.swing.table.DefaultTableModel(
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
    lhpTable.setShowGrid(true);
    jScrollPane2.setViewportView(lhpTable);

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(0, 0, Short.MAX_VALUE))
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap(12, Short.MAX_VALUE)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(22, Short.MAX_VALUE))
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jScrollPane2)
        .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
  }// </editor-fold>//GEN-END:initComponents

  private void jCheckBox9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox9ActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_jCheckBox9ActionPerformed
  
  private LopHocPhan getLopHocPhanFromForm() {
    LopHocPhan lhp = new LopHocPhan();
    lhp.setMaLHP(malhpTf.getText());
    lhp.setHocPhan((HocPhan) hpCombo.getSelectedItem());
    lhp.setThu((String) thuCombo.getSelectedItem());
    List<String> tietList = new ArrayList<>();
    for (JCheckBox cb : tietCheckBoxs) {
      if (cb.isSelected()) {
        tietList.add(cb.getText());
      }
    }
    lhp.setTiet(String.join(",", tietList));
    lhp.setPhong(phongTf.getText());
    lhp.setHocKy((String) hockyCombo.getSelectedItem());
    lhp.setNamHoc(namhocStartTf.getText() + "-" + namhocEndTf.getText());
    Calendar cal = (Calendar) ngaybatdauPicker.getModel().getValue();
    if (cal == null) {
      throw new RuntimeException("Chọn một ngày bắt đầu!");
    }
    lhp.setNgayBatDau(new Date((cal.getTimeInMillis())));
    return lhp;
  }
  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    
    try {
      LopHocPhan lhp = getLopHocPhanFromForm();
      lhpdao.add(lhp);
      refresh();
      // TODO add your handling code here:
    } catch (Exception e) {
      MessageBox.showErrorMessage(this, e.getMessage());
    }
  }//GEN-LAST:event_jButton1ActionPerformed

  private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    
    refresh();
    // TODO add your handling code here:
  }//GEN-LAST:event_jButton4ActionPerformed

  private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    
    try {
      LopHocPhan lhp = getLopHocPhanFromForm();
      lhpdao.update(lhp);
      refresh();
      // TODO add your handling code here:
    } catch (Exception e) {
      e.printStackTrace();
      MessageBox.showErrorMessage(this, e.getMessage());
    }
  }//GEN-LAST:event_jButton2ActionPerformed

  private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    
    try {
      int row = lhpTable.getSelectedRow();
      if (row == -1) {
        MessageBox.showErrorMessage(this, "Chọn một hàng để xóa!");
        return;
      }
      LopHocPhan lhp = new LopHocPhan();
      lhp.setMaLHP(malhpTf.getText());
      if (MessageBox.showConfirmDialog(this, "Xóa lớp học phần này?")) {
        lhpdao.delete(lhp);
        refresh();
      }
      // TODO add your handling code here:
    } catch (Exception e) {
      MessageBox.showErrorMessage(this, e.getMessage());
    }
  }//GEN-LAST:event_jButton3ActionPerformed

  private void ngaybatdauPickerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ngaybatdauPickerActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_ngaybatdauPickerActionPerformed


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JComboBox<String> filterByCombo;
  private javax.swing.JComboBox<String> hockyCombo;
  private javax.swing.JComboBox<HocPhan> hpCombo;
  private javax.swing.JButton jButton1;
  private javax.swing.JButton jButton2;
  private javax.swing.JButton jButton3;
  private javax.swing.JButton jButton4;
  private javax.swing.JCheckBox jCheckBox1;
  private javax.swing.JCheckBox jCheckBox10;
  private javax.swing.JCheckBox jCheckBox2;
  private javax.swing.JCheckBox jCheckBox3;
  private javax.swing.JCheckBox jCheckBox4;
  private javax.swing.JCheckBox jCheckBox5;
  private javax.swing.JCheckBox jCheckBox6;
  private javax.swing.JCheckBox jCheckBox7;
  private javax.swing.JCheckBox jCheckBox8;
  private javax.swing.JCheckBox jCheckBox9;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel10;
  private javax.swing.JLabel jLabel11;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JLabel jLabel8;
  private javax.swing.JLabel jLabel9;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JPanel jPanel3;
  private javax.swing.JPanel jPanel4;
  private javax.swing.JPanel jPanel5;
  private javax.swing.JPanel jPanel6;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JTextField keyTf;
  private ui.WithTooltipTable lhpTable;
  private javax.swing.JTextField malhpTf;
  private javax.swing.JTextField namhocEndTf;
  private javax.swing.JTextField namhocStartTf;
  private org.jdatepicker.JDatePicker ngaybatdauPicker;
  private javax.swing.JTextField phongTf;
  private javax.swing.JComboBox<String> thuCombo;
  // End of variables declaration//GEN-END:variables
}
