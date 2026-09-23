package com.example.bank.service;

import com.example.bank.entity.Nasabah;
import com.example.bank.model.GetNasabahByKtpResponseModel;
import com.example.bank.repository.NasabahRepository;
import com.example.bank.request.GetNasabahByKtpRequest;
import com.example.bank.response.GetNasabahByKtpResponse;
import com.example.bank.util.AppUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class GetNasabahByKtpService {

    @Autowired
    private NasabahRepository nasabahRepository;

    public GetNasabahByKtpResponse inquiry (GetNasabahByKtpRequest request){
        GetNasabahByKtpResponse response = new GetNasabahByKtpResponse();
        ObjectMapper mapper = new ObjectMapper();
        log.info("Request = {}", AppUtil.requestResponseToString(mapper, request));
        if (request != null){

            try {
                List<Nasabah> listNasabah = nasabahRepository.getNasabahByNoKtp("%" + request.getData().getNoKtp() + "%", 0);

                if (listNasabah.isEmpty()){
                    response.setResponseCode("01");
                    response.setResponseDescription("User Not Found");
                    response.setData(null);
                } else {
                    response.setResponseCode("00");
                    response.setResponseDescription("Success");
                    response.setData(getNasabahData(listNasabah));
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.setResponseCode("400");
                response.setResponseDescription(e.getMessage());
                response.setData(null);
            }

        }
        log.info("Response = {}", AppUtil.requestResponseToString(mapper, response));
        return response;
    }

    public List<GetNasabahByKtpResponseModel> getNasabahData(List<Nasabah> list){
        List<GetNasabahByKtpResponseModel> listNasabah = new ArrayList<>();
        for (Nasabah item: list){
            GetNasabahByKtpResponseModel nasabah = new GetNasabahByKtpResponseModel();
            nasabah.setNamaLengkap(item.getNamaLengkap());
            nasabah.setAlamat(item.getAlamat());
            nasabah.setTempatLahir(item.getTempatLahir());
            nasabah.setTanggalLahir(item.getTanggalLahir());
            nasabah.setNoKtp(item.getNoKtp());
            nasabah.setNoHandphone(item.getNoHandphone());

            listNasabah.add(nasabah);
        }
        return listNasabah;
    }
}
