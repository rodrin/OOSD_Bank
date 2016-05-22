/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import edu.sit.cs.db.CSDbDelegate;
import java.util.HashMap;

/**
 *
 * @author Rodrin
 */
public class Deposit implements command {

    @Override
    public void execute(String acc, double amount) {
             CSDbDelegate db = new CSDbDelegate("csprog-in.sit.kmutt.ac.th", "3306", "CSC105_G3", "csc105_2014", "csc105");
        Deposit test = new Deposit();
        String code = "DPS";
         double balance = test.getBalance(acc);
        balance += amount;
        String sql = "INSERT INTO `ToIBank_Statement`(`accdeposite`, `accwithdraw`, `amount`, `accde_balance`, `accwi_balance`) VALUES ( '"+acc+"','None',"+amount+","+balance+",0.00)";
        db.connect();
        db.executeQuery(sql);     
        sql = "UPDATE `ToIBank_Account` SET `Balance`="+balance+" WHERE `main_AccID` = '"+acc+"'";     
        db.executeQuery(sql);
        db.disconnect();
    }

    @Override
    public void execute(String acc1, String acc2, double amount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
      public Double getBalance(String accno)
        {
            CSDbDelegate   db = new CSDbDelegate("csprog-in.sit.kmutt.ac.th", "3306", "CSC105_G3", "csc105_2014", "csc105");
            db.connect();
            String sqlgetBalance = "SELECT `balance` FROM `Bank_Account` WHERE `acc_id` = "+accno+"";
            HashMap balanceQuery = db.queryRow(sqlgetBalance);
            String balance = (String) balanceQuery.get("balance");
            Double balancedouble = Double.parseDouble((String) balance);
            db.disconnect();
            return  balancedouble;
        } 
     
}
