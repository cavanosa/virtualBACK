package com.cavanosa.virtual.controller;

import com.cavanosa.virtual.entity.Tio;
import com.cavanosa.virtual.dto.Mensaje;
import com.cavanosa.virtual.dto.TioDto;
import com.cavanosa.virtual.service.TioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("tio")
@CrossOrigin
public class TioController {

    @Autowired
    TioService tioService;

    @GetMapping("/lista")
    public ResponseEntity<List<Tio>> lista(){
        List<Tio> list = tioService.findAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<Tio> getOne(@PathVariable("id") int id){
        if(!tioService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Tio tio = tioService.getOneById(id).get();
        return new ResponseEntity(tio, HttpStatus.OK);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody TioDto tioDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        if(tioService.existsByNombre(tioDto.getNombre()))
            return new ResponseEntity(new Mensaje("ya existe ese nombre"), HttpStatus.BAD_REQUEST);
        if(tioService.exixtsByEmail(tioDto.getEmail()))
            return new ResponseEntity(new Mensaje("ya existe ese email"), HttpStatus.BAD_REQUEST);
        Tio tio = new Tio(tioDto.getNombre(), tioDto.getEmail());
        tioService.save(tio);
        return new ResponseEntity(new Mensaje("tio guardado"), HttpStatus.CREATED);

    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody TioDto tioDto, BindingResult bindingResult, @PathVariable("id") int id){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        if(!tioService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(tioService.existsByNombre(tioDto.getNombre()) && tioService.getOneByNombre(tioDto.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("ya existe ese nombre"), HttpStatus.BAD_REQUEST);
        if(tioService.exixtsByEmail(tioDto.getEmail()) && tioService.getOneByEmail(tioDto.getEmail()).get().getId() != id)
            return new ResponseEntity(new Mensaje("ya existe ese email"), HttpStatus.BAD_REQUEST);
        Tio tio = tioService.getOneById(id).get();
        tio.setNombre(tioDto.getNombre());
        tio.setEmail(tioDto.getEmail());
        tioService.save(tio);
        return new ResponseEntity(new Mensaje("tio actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable("id") int id){
        if(!tioService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        tioService.delete(id);
        return new ResponseEntity(new Mensaje("tio eliminado"), HttpStatus.OK);
    }
}
