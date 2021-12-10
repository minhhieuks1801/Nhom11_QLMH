/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entity.NguoiDung;

/**
 *
 * @author chung
 */
public class LoginContext {
  private static NguoiDung user;

  public static NguoiDung getUser() {
    return user;
  }

  public static void setUser(NguoiDung user) {
    LoginContext.user = user;
  }
  
}
