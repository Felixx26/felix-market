package com.felix.market.web.controller;

import com.felix.market.domain.Product;
import com.felix.market.domain.Purchase;
import com.felix.market.domain.service.PurchaseService;
import com.felix.market.persistence.entity.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/all")
    public ResponseEntity<List<Purchase>> getAll(){
        return new ResponseEntity<>( purchaseService.getAll(), HttpStatus.OK);
    }

    @GetMapping("client/{id}")
    public ResponseEntity<List<Purchase>> getByClientId(@PathVariable("id") String clientId){
        return purchaseService.getByClientId(clientId)
                .map(purchases -> new ResponseEntity<>(purchases, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/save")
    public ResponseEntity<Purchase> save(Purchase purchase){
        return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.OK);
    }

}
