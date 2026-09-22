package com.example.bank.request;

import com.example.bank.model.GetNasabahByKtpRequestModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GetNasabahByKtpRequest {
    @JsonProperty("merchant_id")
    private String merchantId;

    @JsonProperty("data")
    private GetNasabahByKtpRequestModel data;
}
