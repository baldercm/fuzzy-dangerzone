package org.baldercm.poc.rest;

import org.baldercm.poc.commerce.Commerce;
import org.baldercm.poc.commerce.CommerceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/commerce", produces = "application/json")
public class CommerceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommerceController.class);

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable Integer id) {
        LOGGER.debug("GET /api/commerce/{}", id);
        Commerce commerce = repository.findOne(id);

        if (commerce == null) {
            return ResponseEntity.notFound().build();
        }

        LOGGER.debug("GET /api/commerce/{} found commerce {}", id, commerce.getName());

        return ResponseEntity.ok(commerce);
    }

    @Autowired
    private CommerceRepository repository;
}
