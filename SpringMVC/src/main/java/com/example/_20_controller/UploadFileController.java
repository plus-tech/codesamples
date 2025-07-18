package com.example._20_controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example._90_util.AppConstant;

@Controller
public class UploadFileController {
	
	@Autowired
	private MessageSource msgSource;
	
	@Value("${upload.dir}")
	private String uploadDir;
	
	@GetMapping("/uploadfile")
	public String uploadFile() {
		
		return AppConstant.VIEW_UPLOADFILE;
	}
	
	
	@PostMapping("/uploaduserfile")
	public ModelAndView uploadUserFile(
			@RequestParam("file") MultipartFile file,
			ModelAndView mav) {
		
		String uploadMsg;
		
		Locale currentLocale = LocaleContextHolder.getLocale();
        System.out.println("--------- Current Locale: " + currentLocale);
		
        if (file.isEmpty()) {
        		uploadMsg = msgSource.getMessage(AppConstant.UPLOAD_SELECTFILE, null, Locale.US);
        }
        try {
            // Get the file's original name and create a path for saving
            String fileName = file.getOriginalFilename();
            Path uploadPath = Paths.get(uploadDir);
            Files.createDirectories(uploadPath); // Create directory if it doesn't exist
            Path filePath = uploadPath.resolve(fileName);

            // Save the file to the specified path
            Files.copy(file.getInputStream(), filePath);

            uploadMsg = msgSource.getMessage(AppConstant.UPLOAD_SUCCESS, 
            		new String[] {fileName}, Locale.US);
        } catch (IOException e) {
            e.printStackTrace();
            uploadMsg = msgSource.getMessage(AppConstant.UPLOAD_SUCCESS, 
            		new String[] {e.getMessage()}, Locale.US);
        }
        
        mav.addObject("msg", uploadMsg);
        mav.setViewName("redirect:/");
        
        return mav;
        
	}

}
