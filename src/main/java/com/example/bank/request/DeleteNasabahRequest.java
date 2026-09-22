package com.example.bank.request;

import com.example.bank.model.DaftarNasabahModel;
import com.example.bank.model.DeleteNasabahRequestModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeleteNasabahRequest {
    @JsonProperty("merchant_id")
    private String merchantId;

    @JsonProperty("data")
    private DeleteNasabahRequestModel data;
}
