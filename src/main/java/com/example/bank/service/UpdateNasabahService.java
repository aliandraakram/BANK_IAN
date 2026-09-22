package com.example.bank.service;

import com.example.bank.entity.Nasabah;
import com.example.bank.model.UpdateNasabahModel;
import com.example.bank.repository.NasabahRepository;
import com.example.bank.request.UpdateNasabahRequest;
import com.example.bank.response.UpdateNasabahResponse;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UpdateNasabahService {

    @Autowired
    private NasabahRepository nasabahRepository;

    @Transactional
    public UpdateNasabahResponse inquiry(UpdateNasabahRequest request){
        UpdateNasabahResponse response = new UpdateNasabahResponse();

        if (request != null){
            try {
                List<Nasabah> listNasabah = nasabahRepository.getNasabahByNoKtp(request.getData().getNoKtp(), 0);

                if (listNasabah.isEmpty()){
                    response.setResponseCode("01");
                    response.setResponseDescription("User Not Found");
                    response.setData(request.getData());
                } else if (listNasabah.size() > 1){
                    response.setResponseCode("02");
                    response.setResponseDescription("User Not Unique");
                    response.setData(request.getData());
                }else {
                    UpdateNasabahModel update = request.getData();
                    int result = nasabahRepository.updateNasabahByNoKtp(update.getNoKtp(),
                            update.getNamaLengkap(), update.getAlamat(), update.getTempatLahir(), update.getTanggalLahir(),
                            update.getNoHandphone());
                    System.out.println("result" + result);

                    if (result == 1){
                        response.setResponseCode("00");
                        response.setResponseDescription("Success");
                        response.setData(request.getData());
                    } else{
                        System.out.println("Perubahan Gagal");
                    }

                }
            }catch (Exception e){
                response.setResponseCode("400");
                response.setResponseDescription(e.getMessage());
                response.setData(null);
            }
        }
        return response;
    }
}
