/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * http://docs.spring.io/spring-data/jpa/docs/1.6.0.RELEASE/reference/html/jpa.repositories.html
 * 这里都是Entity
 * @NamedQuery(name = "User.findByEmailAddress",
 * query = "select u from User u where u.emailAddress = ?1") 
 * 标注实体
 * 
 * @Query("select u from User u where u.emailAddress = ?1")
 * @Query("select u from User u where u.firstname like %?1")
 * 标注接口方法
 * @Query("select u from User u where u.firstname = :firstname or u.lastname = :lastname")
 * User findByLastnameOrFirstname(@Param("lastname") String lastname,
 *                                @Param("firstname") String firstname);
 * 
 */
package org.luffy.wzqr.wzqrserver.entity;
