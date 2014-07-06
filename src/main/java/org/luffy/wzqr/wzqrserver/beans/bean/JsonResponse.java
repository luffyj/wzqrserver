/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.luffy.wzqr.wzqrserver.beans.bean;

/**
 *
 * @author luffy
 */
public class JsonResponse {
    
    public static final int SUCCESS = 200;
    public static final int FAILED = 400;
    public static final int ERROR = 500;
    
    private int responseCode;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
    
}
