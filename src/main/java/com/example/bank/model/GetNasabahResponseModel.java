package com.example.bank.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GetNasabahResponseModel {
    @JsonProperty("nama_lengkap")
    private String namaLengkap;

    @JsonProperty("alamat")
    private String alamat;

    @JsonProperty("tempat_lahir")
    private String tempatLahir;

    @JsonProperty("tanggal_lahir")
    private String tanggalLahir;

    @JsonProperty("no_ktp")
    private String noKtp;

    @JsonProperty("no_handphone")
    private String noHandphone;
}
