package com.example.bank.service;

import com.example.bank.entity.Nasabah;
import com.example.bank.repository.NasabahRepository;
import com.example.bank.request.DaftarNasabahBaruRequest;
import com.example.bank.response.DaftarNasabahBaruResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DaftarNasabahBaruService {

    @Autowired
    private NasabahRepository nasabahRepository;

    public DaftarNasabahBaruResponse inquiry (DaftarNasabahBaruRequest request){
        Nasabah newNasabah = new Nasabah();
        DaftarNasabahBaruResponse response = new DaftarNasabahBaruResponse();

        if (request != null){



            try {
                newNasabah.setNamaLengkap(request.getData().getNamaLengkap());
                newNasabah.setAlamat(request.getData().getAlamat());
                newNasabah.setTempatLahir(request.getData().getTempatLahir());
                newNasabah.setTanggalLahir(request.getData().getTanggalLahir());
                newNasabah.setNoKtp(request.getData().getNoKtp());
                newNasabah.setNoHandphone(request.getData().getNoHandphone());
                nasabahRepository.save(newNasabah);

                response.setData(request.getData());
                response.setResponseCode("200");
                response.setResponseDescription("Success");
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("error messages : " + e.getMessage());
                response.setResponseCode("400");
                response.setResponseDescription(e.getMessage());
                response.setData(request.getData());

            }
        } else {
            response.setResponseCode("00");
            response.setResponseDescription("request Kosong");
            response.setData(null);
        }

    return response;
    };

}
