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
public class ErrorResponse extends JsonResponse {

    public ErrorResponse(int code) {
        super(code);
    }

    public ErrorResponse(int code, String originalMessage) {
        super(code, originalMessage);
    }

    public ErrorResponse() {
    }

}
