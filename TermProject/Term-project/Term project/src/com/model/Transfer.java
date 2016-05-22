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
public class Transfer implements command{
        @Override
    public void execute(String acc, double amount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void execute(String acc1, String acc2, double amount) {
       CSDbDelegate   db = new CSDbDelegate("csprog-in.sit.kmutt.ac.th", "3306", "CSC105_G3", "csc105_2014", "csc105"); 
        Transfer test = new Transfer();
        String code = "TRS";
        String code2 = "TRR";
        double balanceDep = test.getBalance(acc1);
        balanceDep -= amount;
        
        double balanceWit = getBalance(acc2);
        balanceWit += amount;
       
        db.connect();
        String sql = "INSERT INTO `CSC105_G1`.`ToIBank_Statement` (`statement_number`, `accdeposite`, `accwithdraw`, `amount`, `accde_balance`, `accwi_balance`, `time`) VALUES (NULL, '"+acc1+"', '"+acc2+"', '"+amount+"', '"+balanceDep+"', '"+balanceWit+"', CURRENT_TIMESTAMP);";
        db.executeQuery(sql);

        sql = "UPDATE `ToIBank_Account` SET `Balance`=" + balanceDep + " WHERE `main_AccID` = '" + acc1 + "'";
        db.executeQuery(sql);
        
        sql = "UPDATE `ToIBank_Account` SET `Balance`=" + balanceWit + " WHERE `main_AccID` = '" + acc2 + "'";    
        db.executeQuery(sql);
        db.disconnect();
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
