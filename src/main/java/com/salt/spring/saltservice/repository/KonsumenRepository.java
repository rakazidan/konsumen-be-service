package com.salt.spring.saltservice.repository;

import java.util.List;

import com.salt.spring.saltservice.model.Konsumen;


public interface KonsumenRepository  {
    List<Konsumen> findAll();
    Konsumen findById(int id);
    List<Konsumen> findByParam(String nama);
    int save(Konsumen konsumen);
    int update(Konsumen konsumen);
    int deleteById(int id);
}