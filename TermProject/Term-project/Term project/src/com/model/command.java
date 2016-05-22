/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

/**
 *
 * @author Kamiyama
 */
public interface command {
         void execute(String acc, double amount);
         void execute(String acc1,String acc2, double amount);
}
