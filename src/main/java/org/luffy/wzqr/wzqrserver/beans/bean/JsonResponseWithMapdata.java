/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.luffy.wzqr.wzqrserver.beans.bean;

import java.util.Map;

/**
 *
 * @author luffy
 */
public class JsonResponseWithMapdata extends JsonResponse{
    
    private Map data;

    public JsonResponseWithMapdata(Map data) {
        super(200);
        this.data = data;
    }

    public JsonResponseWithMapdata(Map data, int code) {
        super(code);
        this.data = data;
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }
    
}
