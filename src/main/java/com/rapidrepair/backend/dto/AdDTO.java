package com.rapidrepair.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdDTO {

    private Long id;
    private String serviceName;
    private String description;
    private Double price;
    private MultipartFile img;
    private byte[] returnedImg;
    private Long userId;
    private String companyName;
}
