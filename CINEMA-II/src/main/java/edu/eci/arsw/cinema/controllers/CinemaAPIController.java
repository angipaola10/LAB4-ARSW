/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.controllers;

import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.services.CinemaException;
import edu.eci.arsw.cinema.services.CinemaServices;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author cristian
 */
@RestController
@RequestMapping(value = "/cinemas")
public class CinemaAPIController {
    
    @Autowired
    CinemaServices cs;
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllCinemas(){
        try{
            //obtener datos que se enviarán a través del API
            return new ResponseEntity<>(cs.getAllCinemas(),HttpStatus.ACCEPTED);
        }catch(Exception e){
            Logger.getLogger(RestController.class.getName()).log(Level.SEVERE, null, e);
	    return new ResponseEntity<>("Error, cinemas not found",HttpStatus.NOT_FOUND);
        }
    } 
    
    @RequestMapping(value="/{name}", method= RequestMethod.GET)
    public ResponseEntity<?> getCinemaByName(@PathVariable("name") String name){
    	try {
            return new ResponseEntity<>(cs.getCinemaByName(name),HttpStatus.ACCEPTED);
        }catch (CinemaException e) {
            Logger.getLogger(RestController.class.getName()).log(Level.SEVERE, null, e);
	    return new ResponseEntity<>("Error, cinema not found",HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(value="/{name}/{date}", method= RequestMethod.GET)
    public ResponseEntity<?> getFunctionsByCinemaAndDate(@PathVariable("name") String name, @PathVariable("date") String date){
    	try {
            return new ResponseEntity<>(cs.getFunctionsbyCinemaAndDate(name, date),HttpStatus.ACCEPTED);
        }catch (CinemaException e) {
            Logger.getLogger(RestController.class.getName()).log(Level.SEVERE, null, e);
	    return new ResponseEntity<>("Error, functios not found",HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(value="/{name}/{date}/{movieName}", method= RequestMethod.GET)
    public ResponseEntity<?> getFunctionByCinemaDateAndMovie(@PathVariable("name") String name, @PathVariable("date") String date, @PathVariable("movieName") String movieName){
    	try {
            return new ResponseEntity<>(cs.getFunctionByCinemaDateAndMovie(name, date, movieName),HttpStatus.ACCEPTED);
        }catch (CinemaException e) {
            Logger.getLogger(RestController.class.getName()).log(Level.SEVERE, null, e);
	    return new ResponseEntity<>("Error, function not found",HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(value= "/{name}", method = RequestMethod.POST)	
    public ResponseEntity<?> postCinemaFuncion(@PathVariable("name") String name, @RequestBody CinemaFunction cf){
        try {
            //registrar dato
            cs.addFunction(name, cf);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            Logger.getLogger(RestController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>("Error bla bla bla",HttpStatus.FORBIDDEN);            
        }        
    }
 
}
