package com.example.bank.service;

import com.example.bank.entity.Nasabah;
import com.example.bank.model.GetNasabahByKtpResponseModel;
import com.example.bank.repository.NasabahRepository;
import com.example.bank.request.GetNasabahByKtpRequest;
import com.example.bank.response.GetNasabahByKtpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetNasabahByKtpService {

    @Autowired
    private NasabahRepository nasabahRepository;

    public GetNasabahByKtpResponse inquiry (GetNasabahByKtpRequest request){
        GetNasabahByKtpResponse response = new GetNasabahByKtpResponse();

        if (request != null){

            try {
                List<Nasabah> listNasabah = nasabahRepository.getNasabahByNoKtp("%" + request.getData().getNoKtp() + "%", 0);

                if (listNasabah.isEmpty()){
                    response.setResponseCode("01");
                    response.setResponseDescription("User Not Found");
                    response.setData(null);
                } else if (listNasabah.size() > 1){
                    response.setResponseCode("02");
                    response.setResponseDescription("User Not Unique");
                    response.setData(null);
                } else {
                    Nasabah nasabah = listNasabah.get(0);
                    response.setResponseCode("00");
                    response.setResponseDescription("Success");
                    response.setData(GetNasabahByKtpResponseModel.builder()
                            .namaLengkap(nasabah.getNamaLengkap())
                            .alamat(nasabah.getAlamat())
                            .tempatLahir(nasabah.getTempatLahir())
                            .tanggalLahir(nasabah.getTanggalLahir())
                            .noKtp(nasabah.getNoKtp())
                            .noHandphone(nasabah.getNoHandphone())
                            .build());
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.setResponseCode("400");
                response.setResponseDescription(e.getMessage());
                response.setData(null);
            }

        }
        return response;
    }
}
