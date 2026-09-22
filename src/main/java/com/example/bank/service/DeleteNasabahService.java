package com.example.bank.service;

import com.example.bank.entity.Nasabah;
import com.example.bank.model.DeleteNasabahResponseModel;
import com.example.bank.repository.NasabahRepository;
import com.example.bank.request.DeleteNasabahRequest;
import com.example.bank.response.DeleteNasabahResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteNasabahService {

    @Autowired
    private NasabahRepository nasabahRepository;

    @Transactional
    public DeleteNasabahResponse inquiry (DeleteNasabahRequest request){
        DeleteNasabahResponse response = new DeleteNasabahResponse();

        if (request != null){
            try {
                List<Nasabah> listNasabah = nasabahRepository.getNasabahByNoKtp(request.getData().getNoKtp(),0);
                if (listNasabah.isEmpty()){
                    response.setResponseCode("01");
                    response.setResponseDescription("User Not Found");
                    response.setData(null);
                } else if (listNasabah.size() > 1){
                    response.setResponseCode("02");
                    response.setResponseDescription("User Not Unique");
                    response.setData(null);
                }else {
                    Nasabah nasabah = listNasabah.get(0);
                    int result = nasabahRepository.deleteNasabahByNoKtp(nasabah.getNoKtp(),
                            1);

                    response.setResponseCode("00");
                    response.setResponseDescription("Success");
                    response.setData(DeleteNasabahResponseModel.builder()
                            .namaLengkap(nasabah.getNamaLengkap())
                            .alamat(nasabah.getAlamat())
                            .tempatLahir(nasabah.getTempatLahir())
                            .tanggalLahir(nasabah.getTanggalLahir())
                            .noKtp(nasabah.getNoKtp())
                            .noHandphone(nasabah.getNoHandphone())
                            .build()
                    );

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
