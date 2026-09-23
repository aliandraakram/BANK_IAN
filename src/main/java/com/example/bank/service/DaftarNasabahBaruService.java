package com.example.bank.service;

import com.example.bank.entity.Nasabah;
import com.example.bank.repository.NasabahRepository;
import com.example.bank.request.DaftarNasabahBaruRequest;
import com.example.bank.response.DaftarNasabahBaruResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static com.example.bank.util.AppUtil.requestResponseToString;

@Slf4j
@Service
public class DaftarNasabahBaruService {

    @Autowired
    private NasabahRepository nasabahRepository;

    public DaftarNasabahBaruResponse inquiry (DaftarNasabahBaruRequest request){
        Nasabah newNasabah = new Nasabah();
        DaftarNasabahBaruResponse response = new DaftarNasabahBaruResponse();

        ObjectMapper mapper = new ObjectMapper();


        log.info("Request = {}", requestResponseToString(mapper, request));
        if (request != null){

            try {
                List<Nasabah> listNasabah = nasabahRepository.getNasabahByNoKtp(request.getData().getNoKtp(), 0);
                if (!listNasabah.isEmpty()){
                    response.setData(request.getData());
                    response.setResponseCode("00");
                    response.setResponseDescription("Nasabah Already Exist");
                } else{
                    newNasabah.setNamaLengkap(request.getData().getNamaLengkap());
                    newNasabah.setAlamat(request.getData().getAlamat());
                    newNasabah.setTempatLahir(request.getData().getTempatLahir());
                    newNasabah.setTanggalLahir(request.getData().getTanggalLahir());
                    newNasabah.setNoKtp(request.getData().getNoKtp());
                    newNasabah.setNoHandphone(request.getData().getNoHandphone());
                    nasabahRepository.save(newNasabah);

                    response.setData(request.getData());
                    response.setResponseCode("00");
                    response.setResponseDescription("Success");
                }
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
        log.info("Response = {}", requestResponseToString(mapper, response));

    return response;
    }

}
