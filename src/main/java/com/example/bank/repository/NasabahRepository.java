package com.example.bank.repository;

import com.example.bank.entity.Nasabah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NasabahRepository extends JpaRepository <Nasabah, Integer> {

    @Query(value = "SELECT * FROM nasabah n WHERE n.no_ktp LIKE :noKTP AND n.is_deleted = :isDeleted", nativeQuery = true)
    List<Nasabah> getNasabahByNoKtp(@Param("noKTP") String noKtp, @Param("isDeleted") int isDeleted);

    @Query(value = "SELECT * FROM nasabah n", nativeQuery = true)
    List<Nasabah> getAllNasabah();


    @Query(value = "SELECT * FROM nasabah n WHERE n.id = :id", nativeQuery = true)
    List<Nasabah> getNasabahById(@Param("noKTP") Integer id);

    @Modifying
    @Query(value = "UPDATE nasabah SET nama_lengkap = :namaLengkap, alamat = :alamat, tempat_lahir = :tempatLahir, tanggal_lahir = :tanggalLahir, no_handphone = :noHandphone WHERE no_ktp = :noKTP",
            nativeQuery = true)
    int updateNasabahByNoKtp(@Param("noKTP") String noKTP,
                             @Param("namaLengkap") String nama,
                             @Param("alamat") String alamat,
                             @Param("tempatLahir") String tempatLahir,
                             @Param("tanggalLahir") String tanggalLahir,
                             @Param("noHandphone") String noHandphone);

    @Modifying
    @Query(value = "UPDATE nasabah SET is_deleted = :isDeleted WHERE no_ktp = :noKTP",
            nativeQuery = true)
    int deleteNasabahByNoKtp(@Param("noKTP") String noKtp, @Param("isDeleted") int isDeleted);
}
