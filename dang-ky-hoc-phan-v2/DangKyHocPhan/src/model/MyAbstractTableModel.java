/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author chung
 */
public abstract class MyAbstractTableModel<T> extends AbstractTableModel{
  protected final List<T> data;

  public MyAbstractTableModel(List<T> data) {
    this.data = data;
  }
  public abstract List<String> getHeaders();

  @Override
  public String getColumnName(int column) {
    return getHeaders().get(column);//To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public int getColumnCount(){
    return getHeaders().size();
  }

  @Override
  public int getRowCount(){
    return data.size();
  }
  public T getRowAt(int row){
    return data.get(row);
  }
}
