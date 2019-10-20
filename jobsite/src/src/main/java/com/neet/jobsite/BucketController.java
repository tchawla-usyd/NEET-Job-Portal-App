package com.neet.jobsite;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neet.jobsite.bal.AmazonClient;
import com.neet.jobsite.bal.CandidateService;
import com.neet.jobsite.response.S3UploadResponse;

@Controller
@RequestMapping(value="/storage/**")
public class BucketController extends BaseMVCController{
	
	@Resource(name="amazonClient")
    private AmazonClient amazonClient;
	
	@Resource(name="candidateService")
    private CandidateService candidateService;

    @Autowired
    BucketController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }

	@RequestMapping(value="/uploadFile", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    public String uploadFile(@RequestPart(value = "file") MultipartFile file) {
        String fileUrl = this.amazonClient.uploadFile(file);
        boolean isUploaded = (fileUrl != null);
        
        S3UploadResponse res = new S3UploadResponse();
        res.setFile_url(fileUrl);
        res.setIs_success(isUploaded);
        
        String userToken = "abcd";
        
        candidateService.addResume(userToken, fileUrl);
        
        ObjectMapper objectMapper = new ObjectMapper();
		
		String jsonReturn = null;
		try {
			jsonReturn = objectMapper.writeValueAsString(res);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return jsonReturn;
        
        
    }
}
	