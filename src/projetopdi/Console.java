/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetopdi;

/**
 *
 * @author gusta
 */
public class Console {
     public static void setConsole(String type,String message){
        FXMLMainController.instancia.setConsole("[" + type + "]:  " + message + "\n");
    }
}
