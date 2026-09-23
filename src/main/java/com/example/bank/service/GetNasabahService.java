package com.example.bank.service;

import com.example.bank.entity.Nasabah;
import com.example.bank.model.GetNasabahResponseModel;
import com.example.bank.repository.NasabahRepository;
import com.example.bank.request.GetNasabahRequest;
import com.example.bank.response.GetNasabahResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static com.example.bank.util.AppUtil.requestResponseToString;

@Slf4j
@Service
public class GetNasabahService {

    @Autowired
    private NasabahRepository nasabahRepository;

    public GetNasabahResponse inquiry (GetNasabahRequest request){
        GetNasabahResponse response = new GetNasabahResponse();
        ObjectMapper mapper = new ObjectMapper();


        log.info("Request = {}", requestResponseToString(mapper, request));
        if (request != null){

            try {
                List<Nasabah> listNasabah = nasabahRepository.getAllNasabah();

                if (listNasabah != null){
                    response.setResponseCode("00");
                    response.setResponseDescription("Success");
                    response.setListNasabah(getNasabahData(listNasabah));
                }
            } catch (Exception e) {
                response.setResponseCode("400");
                response.setResponseDescription(e.getMessage());
                response.setListNasabah(null);
            }

        }
        log.info("Response = {}", requestResponseToString(mapper, response));
        return response;
    }

    public List<GetNasabahResponseModel> getNasabahData(List<Nasabah> list){
        List<GetNasabahResponseModel> listNasabah = new ArrayList<>();
        for (Nasabah item: list){
            GetNasabahResponseModel nasabahModel = new GetNasabahResponseModel();
          nasabahModel.setNamaLengkap(item.getNamaLengkap());
          nasabahModel.setAlamat(item.getAlamat());
          nasabahModel.setTempatLahir(item.getTempatLahir());
          nasabahModel.setTanggalLahir(item.getTanggalLahir());
          nasabahModel.setNoKtp(item.getNoKtp());
          nasabahModel.setNoHandphone(item.getNoHandphone());

          listNasabah.add(nasabahModel);
        }
        return listNasabah;
    }
}
