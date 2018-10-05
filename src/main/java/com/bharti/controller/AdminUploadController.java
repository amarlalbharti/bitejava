package com.bharti.controller;

import java.io.File;
import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bharti.constraints.ProjectConfig;
import com.bharti.constraints.Roles;
import com.bharti.constraints.StaticMethods;
import com.bharti.constraints.StaticValues;
import com.bharti.constraints.Validation;
import com.bharti.domain.UploadFile;
import com.bharti.model.SubjectModel;
import com.bharti.model.UploadModel;
import com.bharti.service.KeynoteDetailService;
import com.bharti.service.LoginInfoService;
import com.bharti.service.UploadFileService;
import com.bharti.utils.Util;

@Controller
public class AdminUploadController 
{
	@Autowired private UploadFileService uploadFileService; 
	@Autowired private LoginInfoService loginInfoService;
	
	private Logger logger = Logger.getLogger(AdminUploadController.class);
	
	@RequestMapping(value = "/adminUploads", method = RequestMethod.GET)
	public String adminUploads(ModelMap map, HttpServletRequest request, Principal principal)
	{
		logger.info("Get Recent documents from admin upload page");
		return "uploadfiles";
	}
	
	@RequestMapping(value = "/getUploadedFiles", method = RequestMethod.GET)
	public String getUploadedFiles(ModelMap map, HttpServletRequest request, Principal principal)
	{
		String pn = request.getParameter("pn");
		int pageno = 1;
		if(Validation.isNumeric(pn)) {
			try {
				pageno = Util.getNumericPositive(pn);
			} catch(NumberFormatException e) {
				e.printStackTrace();
			}
		}
		logger.info("Looking uploaded files for Page " + pageno);
		
		map.addAttribute("fList", uploadFileService.getRecentUploadFiles((pageno-1)*StaticValues.rpp, StaticValues.rpp));
		map.addAttribute("total_count", (int)uploadFileService.countUploadedFiles());
		map.addAttribute("rpp", StaticValues.rpp);
		map.addAttribute("pn", pageno);
		
		return "uploadList";
	}
	
	@RequestMapping(value = "/adminUploadFile", method = RequestMethod.GET)
	public String adminUploadFile(ModelMap map, HttpServletRequest request, Principal principal)
	{
		logger.info("Upload new file GET");
		map.addAttribute("uploadForm", new UploadModel());
		return "uploadNewFile";
	}
	
	
	
	
	@RequestMapping(value = "/adminUploadFile", method = RequestMethod.POST)
	public String adminUploadFile(@ModelAttribute(value = "uploadForm") @Valid UploadModel model,BindingResult result, ModelMap map, HttpServletRequest request, Principal principal)
	{
		if (result.hasErrors()) {
			logger.info("Validation Failed  " + result.toString());
			return "uploadNewFile";
		} else {
			logger.info("Validation successful ");
			try {
				MultipartFile uploadfile = model.getFile();
				if(uploadfile != null) {
					String file_name = uploadfile.getOriginalFilename();
					if(file_name != null && file_name.trim().length() > 0) {
						file_name = file_name.replaceAll("[^a-zA-Z0-9.-]", "_");
						
						Date date = new Date();
						java.sql.Date dt = new java.sql.Date(date.getTime());
						
						UploadFile uf = new UploadFile();
						uf.setLoginInfo(this.loginInfoService.getLoginInfoByUserid(principal.getName()));
						uf.setCreateDate(dt);
						uf.setFileName(model.getFileName());
						uf.setFileType(StaticMethods.getFileType(uploadfile.getOriginalFilename()));
						uf.setFileDetail(model.getFileDetail());
						uf.setFileURL(file_name);
						long fid = uploadFileService.addUploadFile(uf);
						logger.info("Upload file inserted in table : "+ fid);
						File file = new File (ProjectConfig.upload_path+"/uploadedfiles/"+fid+"/"+file_name);
						
						if(!file.exists()) {
							logger.info("File dir not Exist");
							file.mkdirs();
						}
						uploadfile.transferTo(file);  
						logger.info("File Uploaded successfully");
						return "redirect:adminUploads";
					}
				}
			} catch (Exception e) {
				logger.error("Exception in post : ", e);
			}
			
			return "redirect:adminUploads";
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/adminDeleteFile", method = RequestMethod.GET)
	@ResponseBody
	public String adminDeleteFile(ModelMap map, HttpServletRequest request, Principal principal)
	{
		String fid = request.getParameter("fid");
		logger.info("Delete Upload file Ajax Call for Fid : " + fid);
		
		JSONObject obj = new JSONObject();
		
		if(fid != null && fid.trim().length() > 0) {
			try {
				UploadFile file = uploadFileService.getUploadFile(Long.parseLong(fid));
				if(file != null  && (request.isUserInRole(Roles.ROLE_ADMIN) || (request.isUserInRole(Roles.ROLE_PUBLISHER)
						&&  (file.getLoginInfo() != null && file.getLoginInfo().getUserid().equals(principal.getName()))))) {
					logger.info("File exist in datbase " + file.getFileURL());
					Date date = new Date();
					java.sql.Date dt = new java.sql.Date(date.getTime());
					
					file.setDeleteDate(dt);
					uploadFileService.updateUploadFile(file);
					obj.put("success", true);
					return obj.toJSONString();
				}
			} catch (Exception e) {
				logger.error("Delete file failed :: ", e);
			}
		}
		
		obj.put("success", false);
		return obj.toJSONString();
	}
	
}
