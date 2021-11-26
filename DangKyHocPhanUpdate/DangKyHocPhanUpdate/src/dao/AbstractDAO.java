/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.*;
import java.util.List;
import util.DerbyConnection;
/**
 *
 * @author chung
 */
public abstract class AbstractDAO<T> {
  protected final Connection con;
  public AbstractDAO(){
    this.con=DerbyConnection.getConnection();
  }
  public abstract boolean add(T dto) throws SQLException;
  public abstract boolean update(T dto) throws SQLException;
  public abstract boolean delete(T dto) throws SQLException;
  public abstract T get(String key) throws SQLException;
  public abstract List<T> getAll() throws SQLException;
  public abstract List<T> map(ResultSet rs) throws SQLException;
  public T mapOne(ResultSet rs) throws SQLException{
    List<T> res=map(rs);
    return res.size()>0?res.get(0):null;
  }
}
