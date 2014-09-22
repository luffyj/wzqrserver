/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luffy.wzqr.wzqrserver.rest;

import org.luffy.wzqr.wzqrserver.entity.Application;
import org.luffy.wzqr.wzqrserver.repositories.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Service;

/**
 *
 * @author luffy
 */
@Service
@RepositoryEventHandler
public class DBManager {

    @Autowired
    private ApplicationRepository applicationRepository;

    @HandleBeforeSave(Application.class)
    public void handlePersonSave(Application p) {
        if (p.getPicture() == null && p.getAttachment() == null) {
            Object[] data = applicationRepository.selectBytes(p.getId());
            if (data.length == 1) {
                Object[] realData = (Object[]) data[0];
                byte[] pi = (byte[]) realData[0];
                byte[] at = (byte[]) realData[1];
                p.setPicture(pi);
                p.setAttachment(at);                
            }
        }
    }

}
