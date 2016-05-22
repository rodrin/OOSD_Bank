package com.model;

/**
 *
 * @author Rodrin
 */
public class FactoryCommand {
     public command getcommand(String commandType){
      if(commandType == null){
         return null;
      }		
      if(commandType.equalsIgnoreCase("Deposit")){
         return new Deposit();
         
      } else if(commandType.equalsIgnoreCase("Withdraw")){
         return new Withdrawal();
         
      } else if(commandType.equalsIgnoreCase("Transfer")){
         return new Transfer();
      
      }
      return null;
   }
}

/*

              FactoryCommand command = new FactoryCommand();
              command commandDep = command.getcommand("Deposit");    
              commandDep.execute( // variable to parameter);

*/