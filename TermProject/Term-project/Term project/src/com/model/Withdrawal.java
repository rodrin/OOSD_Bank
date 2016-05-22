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
public class Withdrawal implements command {
            @Override
    public void execute(String acc, double amount) {
        CSDbDelegate   db = new CSDbDelegate("csprog-in.sit.kmutt.ac.th", "3306", "CSC105_G3", "csc105_2014", "csc105");
        String code = "WID";
        Withdrawal test = new Withdrawal();
        double balance = test.getBalance(acc);
        balance -= amount;
        String sql = "INSERT INTO `BANK_TRANSACTION` (`Trans_id`, `code`, `staff_id`, `date`, `accwi_balance`) VALUES ( 'None','"+acc+"',"+amount+",0.00,"+balance+")";
        db.connect();
        db.executeQuery(sql);
        db.disconnect();
        
        sql = "UPDATE `ToIBank_Account` SET `Balance`="+balance+" WHERE `main_AccID` = '"+acc+"'";
        db.connect();
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
