package com.example.bank.controller;
import com.example.bank.model.GetNasabahResponseModel;
import com.example.bank.request.*;
import com.example.bank.response.*;
import com.example.bank.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/nasabah")
public class NasabahController{

    private final DaftarNasabahBaruService daftarNasabahBaruService;
    private final GetNasabahService getNasabahService;
    private final UpdateNasabahService updateNasabahService;
    private final DeleteNasabahService deleteNasabahService;
    private final GetNasabahByKtpService getNasabahByKtpService;


    public NasabahController(DaftarNasabahBaruService daftarNasabahBaruService,
                             GetNasabahService getNasabahService,
                             UpdateNasabahService updateNasabahService,
                             DeleteNasabahService deleteNasabahService,
                             GetNasabahByKtpService getNasabahByKtpService) {
        this.daftarNasabahBaruService = daftarNasabahBaruService;
        this.getNasabahService = getNasabahService;
        this.updateNasabahService = updateNasabahService;
        this.deleteNasabahService = deleteNasabahService;
        this.getNasabahByKtpService = getNasabahByKtpService;
    }

    @PostMapping("/new-nasabah")
    public ResponseEntity<DaftarNasabahBaruResponse> addNasabahBaru (@RequestBody DaftarNasabahBaruRequest request){
        DaftarNasabahBaruResponse nasabah = daftarNasabahBaruService.inquiry(request);
        return new ResponseEntity<>(nasabah, HttpStatus.OK);
    }

    @GetMapping("/get-all-nasabah")
    public ResponseEntity<GetNasabahResponse> getNasabah (@RequestBody GetNasabahRequest request){
        GetNasabahResponse response= getNasabahService.inquiry(request);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/get-nasabah")
    public ResponseEntity<GetNasabahByKtpResponse> getNasabahByKtp(@RequestBody GetNasabahByKtpRequest request){
            GetNasabahByKtpResponse response= getNasabahByKtpService.inquiry(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        
    }

    @PutMapping("/edit-nasabah")
    public ResponseEntity<UpdateNasabahResponse> updateNasabah (@RequestBody UpdateNasabahRequest request){
            UpdateNasabahResponse response= updateNasabahService.inquiry(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/delete-nasabah")
    public ResponseEntity<DeleteNasabahResponse> deleteNasabah (@RequestBody DeleteNasabahRequest request){
        DeleteNasabahResponse response= deleteNasabahService.inquiry(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
