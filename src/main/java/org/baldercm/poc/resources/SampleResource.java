package org.baldercm.poc.resources;

import org.baldercm.poc.sample.Sample;
import org.baldercm.poc.sample.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.concurrent.Callable;

@RestController
@RequestMapping(path = "/api/sample", produces = "application/json")
public class SampleResource {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity find() {
        Collection<Sample> samples = repository.findAll();
        return ResponseEntity.ok(samples);
    }

    @RequestMapping(path = "/async", method = RequestMethod.GET)
    public Callable<Collection<Sample>> findAsync() {
        return () -> {
            return repository.findAll();
        };
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity create(@Valid @NotNull @RequestBody Sample sample) {
        sample = repository.save(sample);
        return ResponseEntity.status(HttpStatus.CREATED).body(sample);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity deleteAll() {
        repository.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @Autowired
    private SampleRepository repository;

    @Autowired
    private javax.validation.Validator validator;

}
