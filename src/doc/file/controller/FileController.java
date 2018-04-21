package doc.file.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import doc.common.AppData;
import doc.common.BaseController;
import doc.common.annotation.LoginAnnotation;
import doc.file.service.FileService;
import doc.file.view.UploadFileV;
import pushunsoft.common.JsonResult;

/**
 * 
 * 
 * @author jerry
 *
 */
@Controller
@RequestMapping("/file")
public class FileController extends BaseController {
	@Resource
	private FileService fileService;

	/**
	
	 * 
	 * @param files
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/upload")
	public JsonResult upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

		String uploadType = request.getParameter("upload_type");
		if (uploadType == null || uploadType.isEmpty()) {
			uploadType = "temp";
		}
		String baseDir = AppData.App_UploadPath;
		String fileDir = uploadType + File.separator;

		String targetDir = baseDir + fileDir;
		File dirFile = new File(targetDir);
		if (!dirFile.exists()) {
			dirFile.mkdir();
		}
		String targetFileName = UUID.randomUUID().toString().replaceAll("-", "")
				+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		File targetFile = new File(targetDir, targetFileName);
		boolean result = false;
		String message = null;
		try {
			file.transferTo(targetFile);
			result = true;
		} catch (IllegalStateException e) {
			message = e.getMessage();
		} catch (IOException e) {
			message = e.getMessage();
		}
		
		JsonResult json = new JsonResult();
		json.setState(result);
		if (result) {
			UploadFileV view = new UploadFileV();
			view.setFilePath(fileDir + targetFileName);
			view.setOriginalName(file.getOriginalFilename());
			view.setFileName(targetFileName);
			json.setData(view);
		}
		json.setMessage(message);
		return json;
	}

	/**

	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/add")
	public Model add(HttpServletRequest request, Model model) {
		model.addAttribute("title", "上传文档");

		String parentId = request.getParameter("parentId");
		model.addAttribute("parentId", parentId);
		return model;
	}
	/**
	 * �����ļ�
	 * @param request
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/download")
	public ResponseEntity<byte[]> download(HttpServletRequest request) {
		String id = request.getParameter("id");
		doc.file.entity.File entity = fileService.get(id);
		if(entity==null) {			
			return null;
		}
		File file = new File(AppData.App_UploadPath + entity.getRealPath());
        HttpHeaders headers = new HttpHeaders();    
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        String showFileName=entity.getMingCheng();
        try {
			showFileName=new String(entity.getMingCheng().getBytes("UTF-8"),"iso-8859-1");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
        headers.setContentDispositionFormData("attachment", showFileName);    
        byte[] bytes=null;
		try {
			bytes = FileUtils.readFileToByteArray(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
        return new ResponseEntity<byte[]>(bytes,headers, HttpStatus.CREATED); 
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doAdd")
	public JsonResult doAdd(HttpServletRequest request) {
		// ��������
		doc.file.entity.File entity = new doc.file.entity.File();
		String mingCheng = request.getParameter("mingCheng");
		if (mingCheng != null && !mingCheng.isEmpty()) {
			entity.setMingCheng(mingCheng);
		}
		String originalName = request.getParameter("originalName");
		if (originalName != null && !originalName.isEmpty()) {
			String type = fileService.getFileType(originalName);
			entity.setGeShi(type);
			entity.setIcon(type);
		}
		String parentId = request.getParameter("parentId");
		if (parentId != null && !parentId.isEmpty()) {
			entity.setParentId(parentId);
		}
		String virtualPath = request.getParameter("virtualPath");
		if (virtualPath != null && !virtualPath.isEmpty()) {
			entity.setVirtualPath(virtualPath);
		}
		String realPath = request.getParameter("realPath");
		if (realPath != null && !realPath.isEmpty()) {
			entity.setRealPath(realPath);
		}
		String secret = request.getParameter("secret");
		if (secret != null && !secret.isEmpty()) {
			entity.setSecret(secret);
		}
		String memo = request.getParameter("memo");
		if (memo != null && !memo.isEmpty()) {
			entity.setMemo(memo);
		}

		boolean result = fileService.add(entity);
	
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(fileService.getMessage());
		return json;
	}

	/**
	 * ִ��ɾ���ļ�
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doRemove")
	public JsonResult doRemove(HttpServletRequest request) {
		// ��������
		String id = request.getParameter("id");
		if (id == null || id.isEmpty()) {
			// ����
			JsonResult json = new JsonResult();
			json.setState(false);
			json.setMessage("操作异常");
			return json;
		}

		// ִ�в���
		boolean result = fileService.remove(id);
		// ����
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(fileService.getMessage());
		return json;
	}
}