package com.example.bank.response;

import com.example.bank.model.DeleteNasabahResponseModel;
import com.example.bank.model.UpdateNasabahModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeleteNasabahResponse {
    @JsonProperty("ResponseCode")
    private String responseCode;

    @JsonProperty("ResponseDescription")
    private String responseDescription;

    @JsonProperty("data")
    private DeleteNasabahResponseModel data;
}
