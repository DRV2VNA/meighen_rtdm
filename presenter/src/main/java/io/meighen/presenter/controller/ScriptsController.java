package io.meighen.presenter.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.meighen.presenter.entity.objects.Module;
import io.meighen.presenter.entity.objects.Script;
import io.meighen.presenter.repository.ModuleRepository;
import io.meighen.presenter.repository.ScriptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/scripts")
public class ScriptsController {
    @Autowired
    ScriptRepository scriptRepository;

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getModulesByPage(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        try {
            List<Script> tutorials = new ArrayList<Script>();
            Pageable paging = PageRequest.of(page, size);

            Page<Script> pageTuts;
            if (name == null)
                pageTuts = scriptRepository.findAll(paging);
            else
                pageTuts = scriptRepository.findAllByNameContaining(name, paging);

            tutorials = pageTuts.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("scripts", tutorials);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byModifier")
    public ResponseEntity<Map<String, Object>> getModulesByLastModifier(
            @RequestParam String fname,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        try {
            List<Script> tutorials = new ArrayList<Script>();
            Pageable paging = PageRequest.of(page, size);

            Page<Script> pageTuts;
            pageTuts = scriptRepository.findAllByLastModifier_FirstName(fname, paging);

            tutorials = pageTuts.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("scripts", tutorials);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byDateCreation")
    public ResponseEntity<Map<String, Object>> getModulesByDateCreation(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        try {
            List<Script> tutorials = new ArrayList<Script>();
            Pageable paging = PageRequest.of(page, size);

            Page<Script> pageTuts;

            if (name == null)
                pageTuts = scriptRepository.findAllByOrderByDateCreation(paging);
            else
                pageTuts = scriptRepository.findAllByNameContainingOrderByDateCreation(name, paging);


            tutorials = pageTuts.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("scripts", tutorials);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byDateModification")
    public ResponseEntity<Map<String, Object>> getModulesByDateModification(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        try {
            List<Script> tutorials = new ArrayList<Script>();
            Pageable paging = PageRequest.of(page, size);

            Page<Script> pageTuts;

            if (name == null)
                pageTuts = scriptRepository.findAllByOrderByDateModification(paging);
            else
                pageTuts = scriptRepository.findAllByNameContainingOrderByDateModification(name, paging);


            tutorials = pageTuts.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("scripts", tutorials);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
