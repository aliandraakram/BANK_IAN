package com.example.bank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
//@AllArgsConstructor
@Data
@Entity
@Table(name = "nasabah")
public class Nasabah {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nama_lengkap")
    private String namaLengkap;

    @Column(name = "alamat")
    private String alamat;

    @Column(name = "tempat_lahir")
    private String tempatLahir;

    @Column(name = "tanggal_lahir")
    private String tanggalLahir;

    @Column(name = "no_ktp")
    private String noKtp;

    @Column(name = "no_handphone")
    private String noHandphone;

    @Column(name = "is_deleted")
    private int isDeleted;

}
