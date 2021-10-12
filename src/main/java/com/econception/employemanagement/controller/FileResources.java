package com.econception.employemanagement.controller;

import com.econception.employemanagement.domain.*;
import com.econception.employemanagement.dto.filesDTO;
import com.econception.employemanagement.repository.CommentRepository;
import com.econception.employemanagement.repository.EmployeeBillsClaimRepository;
import com.econception.employemanagement.service.FilesServics;
import com.econception.employemanagement.service.UserService;
import com.econception.employemanagement.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;

import javax.servlet.MultipartConfigElement;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/file")
public class FileResources {
    @Autowired
    UserService userService;
    @Autowired
    EmployeeBillsClaimRepository employeeBillsClaimRepository;
    @Autowired
    FilesServics filesServics;
    @Autowired
    CommentRepository commentRepository;


//        @RequestMapping ("/uploadFile")
//    public ResponseEntity<String> handleFileUpload(EmployeeBillsClaim employeeBillsClaim, @RequestParam("file") MultipartFile file,@RequestParam("extraImage") MultipartFile[] multipartFiles ) throws IOException {
//
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//
//
//        employeeBillsClaim.setImage(fileName);
//        int count=0;
//        for(MultipartFile extraMultipart: multipartFiles){
//            String extraImageName=StringUtils.cleanPath(extraMultipart.getOriginalFilename());
//            if(count==0) employeeBillsClaim.setImage1(extraImageName);
//            if(count==1) employeeBillsClaim.setImage2(extraImageName);
//            if(count==2) employeeBillsClaim.setImage3(extraImageName);
//
//            count ++;
//        }
//        EmployeeBillsClaim savedImage=employeeBillsClaimRepository.save(employeeBillsClaim);
//            String uploadDir = "bill-photos/" + savedImage.getId();
//            FileUploadUtil.saveFile(uploadDir, fileName, file);
//            for(MultipartFile extraMultipart: multipartFiles){
//                String fileName1=StringUtils.cleanPath(extraMultipart.getOriginalFilename());
//                FileUploadUtil.saveFile(uploadDir, fileName1, extraMultipart);
//            }
//
//        return ResponseEntity.ok("Success");
//   }


    @RequestMapping("/billPic")
    public String pic(@RequestParam(value = "employeeBillId1") Long employeeBillId1, Model model) {

        EmployeeBillsClaim employeeBillsClaim = employeeBillsClaimRepository.findById(employeeBillId1)
                .map(iss -> iss)
                .orElseThrow();

        model.addAttribute("employeeBillId1", employeeBillId1);
        model.addAttribute("employeeBillsClaim", employeeBillsClaim);

        return "billPics";
    }

    @RequestMapping("/uploadBill")
    public ResponseEntity<String> saveBillPic(@Valid Files files, @RequestParam("file") MultipartFile multipartFile) {

        //files.setFile(filename);


        return null;
    }

}
