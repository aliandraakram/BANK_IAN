package com.example.bank.request;

import com.example.bank.model.UpdateNasabahModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateNasabahRequest {
    @JsonProperty("merchant_id")
    private String merchantId;

    @JsonProperty("data")
    private UpdateNasabahModel data;
}
