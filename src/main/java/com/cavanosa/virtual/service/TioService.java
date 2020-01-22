package com.cavanosa.virtual.service;

import com.cavanosa.virtual.entity.Tio;
import com.cavanosa.virtual.repository.TioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TioService {

    @Autowired
    TioRepository tioRepository;

    @Transactional(readOnly = true)
    public List<Tio> findAll(){
        return tioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Tio> getOneById(int id){
        return tioRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Tio> getOneByNombre(String nombre){
        return tioRepository.findByNombre(nombre);
    }

    @Transactional(readOnly = true)
    public Optional<Tio> getOneByEmail(String email){
        return tioRepository.findByEmail(email);
    }

    public void save(Tio tio){
        tioRepository.save(tio);
    }

    public void delete(int id){
        tioRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return tioRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return tioRepository.existsByNombre(nombre);
    }

    public boolean exixtsByEmail(String email){
        return tioRepository.existsByEmail(email);
    }
}
