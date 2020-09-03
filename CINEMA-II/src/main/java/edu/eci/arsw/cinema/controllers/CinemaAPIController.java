/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.controllers;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;
import edu.eci.arsw.cinema.persistence.impl.InMemoryCinemaPersistence;
import edu.eci.arsw.cinema.services.CinemaException;
import edu.eci.arsw.cinema.services.CinemaServices;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @RequestMapping(value="/{name}")
    public ResponseEntity<?> getCinemaByName(@PathVariable String name){
    	try {
            return new ResponseEntity<>(cs.getCinemaByName(name),HttpStatus.ACCEPTED);
        }catch (CinemaException e) {
            Logger.getLogger(RestController.class.getName()).log(Level.SEVERE, null, e);
	    return new ResponseEntity<>("Error, cinema not found",HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(value="/{nameCinema}/{date}")
    public ResponseEntity<?> getFunctionsByCinemaAndDate(@PathVariable String nameCinema, @PathVariable String date){
    	try {
            return new ResponseEntity<>(cs.getFunctionsbyCinemaAndDate(nameCinema, date),HttpStatus.ACCEPTED);
        }catch (CinemaException e) {
            Logger.getLogger(RestController.class.getName()).log(Level.SEVERE, null, e);
	    return new ResponseEntity<>("Error, functios not found",HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(value="/{nameCinema}/{date}/{movieName}")
    public ResponseEntity<?> getFunctionByCinemaDateAndMovie(@PathVariable String nameCinema, @PathVariable String date, @PathVariable String movieName){
    	try {
            return new ResponseEntity<>(cs.getFunctionByCinemaDateAndMovie(nameCinema, date, movieName),HttpStatus.ACCEPTED);
        }catch (CinemaException e) {
            Logger.getLogger(RestController.class.getName()).log(Level.SEVERE, null, e);
	    return new ResponseEntity<>("Error, function not found",HttpStatus.NOT_FOUND);
        }
    }
 
}
