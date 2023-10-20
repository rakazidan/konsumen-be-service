package com.salt.spring.saltservice.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.salt.spring.saltservice.model.Konsumen;

@Repository
public class JdbcKonsumenRepository implements KonsumenRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Konsumen> findAll() {
        return jdbcTemplate.query("SELECT * from tb_konsumen", BeanPropertyRowMapper.newInstance(Konsumen.class));
    }

    @Override
    public Konsumen findById(int id) {
        try {
            Konsumen konsumen = jdbcTemplate.queryForObject("SELECT * FROM tb_konsumen WHERE id=?",
                BeanPropertyRowMapper.newInstance(Konsumen.class), id);
            return konsumen;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Konsumen> findByParam(String param) {
        String q = "SELECT * FROM tb_konsumen WHERE nama ILIKE '%" + param + "%' OR alamat ILIKE '%" + param + "%' OR kota ILIKE '%" + param + "%' OR provinsi ILIKE '%" + param + "%' OR status ILIKE '%" + param + "%'";

        return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Konsumen.class));
    }

    @Override
    public int save(Konsumen konsumen) {
        return jdbcTemplate.update("INSERT INTO tb_konsumen (id, nama, alamat, kota, provinsi, tgl_registrasi, status) VALUES(?,?,?,?,?,?,?)",
        new Object[] { konsumen.getId(), konsumen.getNama(), konsumen.getAlamat(), konsumen.getKota(), konsumen.getProvinsi(), konsumen.getTglRegistrasi(), konsumen.getStatus() });
    }

    @Override
    public int update(Konsumen konsumen) {
        return jdbcTemplate.update("UPDATE tb_konsumen SET nama=?, alamat=?, kota=?, provinsi=?, tgl_registrasi=?, status=? WHERE id=?",
        new Object[] { konsumen.getNama(), konsumen.getAlamat(), konsumen.getKota(), konsumen.getProvinsi(), konsumen.getTglRegistrasi(), konsumen.getStatus(), konsumen.getId() });
    }

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM tb_konsumen WHERE id=?", id);
    }
    
}
