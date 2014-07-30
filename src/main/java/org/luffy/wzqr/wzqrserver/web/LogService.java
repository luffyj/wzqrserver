/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luffy.wzqr.wzqrserver.web;

import org.luffy.wzqr.wzqrserver.entity.OLog;
import org.luffy.wzqr.wzqrserver.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author luffy
 */
@Controller
@RequestMapping("log")
public class LogService {

    @Autowired
    private LogRepository logRepository;

//    @RequestMapping("/{id}")
//    public String showUserForm(@PathVariable("id") OLog user, Model model) {
//
//        model.addAttribute("user", user);
//        return "userForm";
//    }
    
//    
    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    HttpEntity<PagedResources<OLog>> persons(Pageable pageable,
            PagedResourcesAssembler assembler) {

        Page<OLog> persons = this.logRepository.findAll(pageable);   
        PagedResources<OLog> resource  = assembler.toResource(persons);
        return new ResponseEntity(resource,HttpStatus.OK);
    }

    @RequestMapping("/findCustom")
    public HttpEntity<PagedResources<Resource<OLog>>> showUsers(Pageable pageable, PagedResourcesAssembler<OLog> assembler) {
//        model.addAttribute("log", logRepository.findAll(pageable));
//        return "log";        
        //ResourceAssembler Page<OLog>, PagedResources<Resource<OLog>>
//        PagedResources<Resource<OLog>> aaa= assembler.toResource(this.logRepository.findAll(pageable));
        return new ResponseEntity<>(assembler.toResource(logRepository.findAll(pageable)), HttpStatus.OK);
    }
}
