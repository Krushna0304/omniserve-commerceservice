package com.omniserve.commerceservice.controller;

import com.omniserve.commerceservice.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    //Done
    @GetMapping("/getAllItems")
    public ResponseEntity<?> getAllItems()
    {
        try{
            return new ResponseEntity<>(catalogService.getAllItems(), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Done
    @GetMapping("/getItemById")
    public ResponseEntity<?> getItemById(@RequestParam String itemId)
    {
        try{
            return new ResponseEntity<>(catalogService.getItemById(itemId),HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
