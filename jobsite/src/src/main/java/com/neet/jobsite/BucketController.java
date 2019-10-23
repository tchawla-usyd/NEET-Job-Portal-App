package com.neet.jobsite;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neet.jobsite.bal.AmazonClient;
import com.neet.jobsite.bal.CandidateService;
import com.neet.jobsite.response.ErrorResponse;
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

	@RequestMapping(value="/uploadFile", 
					method=RequestMethod.POST, 
					produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    public String uploadFile(@RequestPart(value = "file") MultipartFile file, 
    		HttpServletResponse response, @RequestHeader("Authorization") String userToken) {
        
        ObjectMapper objectMapper = new ObjectMapper();
		String jsonReturn = null;
                
        if(authenticateByToken(userToken)) {
			HttpSession session = context.getSession(false);
			Integer userId = (Integer) session.getAttribute("userId");
			
			String fileUrl = this.amazonClient.uploadFile(file);
		    boolean isUploaded = (fileUrl != null);
		    
		    if(isUploaded)
		    	candidateService.addResume(userId, fileUrl);
			
			S3UploadResponse res = new S3UploadResponse();
	        res.setFile_url(fileUrl);
	        res.setIs_success(isUploaded);
	        
	        jsonReturn = objectToJSON(objectMapper, res);
			
		}
		else {
			response.setStatus(403);
			jsonReturn = objectToJSON(objectMapper, new ErrorResponse("Authentication Failed"));
		} 
		
		return jsonReturn;
        
        
    }
}
	