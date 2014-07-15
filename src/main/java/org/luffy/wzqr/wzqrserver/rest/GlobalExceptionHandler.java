/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luffy.wzqr.wzqrserver.rest;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.luffy.wzqr.wzqrserver.beans.bean.ErrorResponse;
import org.luffy.wzqr.wzqrserver.beans.bean.JsonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * TransactionSystemException DatabaseException RollbackException
 * SQLIntegrityConstraintViolationException SqlException
 *
 * @author luffy
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Log log = LogFactory.getLog(GlobalExceptionHandler.class);
    private static final Logger logger= Logger.getLogger(GlobalExceptionHandler.class.getName());
    
//    @Autowired
//    private ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver;
    @ResponseBody
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    JsonResponse handleSQLIntegrityConstraintViolationException(HttpServletRequest req, SQLIntegrityConstraintViolationException ex) {
        ErrorResponse r = new ErrorResponse();
        r.setOriginalMessage("冲突");
        return r;
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    JsonResponse handleTransactionSystemException(HttpServletRequest req, TransactionSystemException ex) {
        // 应该只在json下工作
//        String type = req.getContentType();
//        if ("application/json".equals(type)) {            
//            return null;
//        }
//        logger.throwing("", "", ex);
        log.error("", ex);
        ErrorResponse r = new ErrorResponse();
        Throwable cause = ex.getOriginalException();
        while(true){
            if(cause.getCause()==null)
                break;
            if(cause.getCause() instanceof SQLIntegrityConstraintViolationException){
                r.setCode(501);
//                break;
            }
            cause = cause.getCause();
        }
        r.setOriginalMessage(cause.getLocalizedMessage());
        return r;
    }

    private Throwable rootCause(Throwable ex) {
        if (ex.getCause() == null) {
            return ex;
        }
        return rootCause(ex.getCause());
    }
}
