package doc.file.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import doc.file.entity.Directory;
import doc.file.service.FileService;
import doc.file.view.FileV;
import doc.file.view.UploadFileV;
import doc.system.entity.User;
import doc.util.OfficeUtil;
import pushunsoft.common.JsonResult;
import pushunsoft.common.PageData;

/**
 * 
 * 
 * @author jerry
 *
 */
@Controller
@RequestMapping("/files")
public class FileController extends BaseController {
	@Resource
	private FileService fileService;
	/**
	 * 文件下载 多个文件下载
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/downFiles")
	public ResponseEntity<byte[]> downFiles(HttpServletRequest request, HttpServletResponse response) {
		String id=request.getParameter("id");
		String mingCheng=request.getParameter("mingCheng");
		File file=fileService.getFiles(id,mingCheng);
		String fileName=mingCheng+".zip";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		try {
			fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		headers.setContentDispositionFormData("attachment", fileName);
		byte[] bytes = null;
		try {
			bytes = FileUtils.readFileToByteArray(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.CREATED);
	}

	/**
	 * @param 
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/showDoc")
	public String showDoc(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("title", "文档在线阅读器");
		// 接收数据

		String	realPath = AppData.Config.getUploadPath()+request.getParameter("realPath");
		int last = realPath.lastIndexOf(".");
		String pdfPath = realPath.substring(0, last)+".pdf";
		 File f=new File(pdfPath);    
		 if(!f.exists()){
			//将Docx,doc,xlsx格式的转为PDF格式的
			 new OfficeUtil().libreOffice2PDF(new File(realPath),
					 	new File(pdfPath));
		 }
	        File file = new File(pdfPath);
	        byte[] data = null;
	        try {
	            FileInputStream input = new FileInputStream(file);
	            data = new byte[input.available()];
	            input.read(data);
	            response.getOutputStream().write(data);
	            input.close();
	        } catch (Exception e) {
	            System.out.println("pdf文件处理异常：" + e.getMessage());
	        }
		return "reader/showPdf/showPdf";
	}
	/**
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/page")
	public Model page(HttpServletRequest request, Model model) {
		model.addAttribute("title", "论文目录");
		return model;
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doPage")
	public JsonResult doPage(HttpServletRequest request) {
		int page = Integer.parseInt(request.getParameter("page"));
		FileV v = new FileV();
		String mingCheng = request.getParameter("mingCheng");
		if (mingCheng != null && !mingCheng.isEmpty()) {
			v.setMingCheng(mingCheng);
		}
		
		String directoryId = request.getParameter("directoryId");
		if (directoryId != null && !directoryId.isEmpty()) {
			v.setDirectoryId(directoryId);
		}
		String unitId = request.getParameter("unitId");
		if (unitId != null && !unitId.isEmpty()) {
			v.setUnitId(unitId);
		}
	
		PageData<FileV> pageData = fileService.getPage(page, v);
		JsonResult json = new JsonResult();
		json.setState(true);
		json.setData(pageData);
		return json;
	}
	/**
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/student/doPage")
	public JsonResult studentdoPage(HttpServletRequest request) {
		int page = Integer.parseInt(request.getParameter("page"));
		FileV v = new FileV();
		String mingCheng = request.getParameter("mingCheng");
		if (mingCheng != null && !mingCheng.isEmpty()) {
			v.setMingCheng(mingCheng);
		}
		String xueqi = request.getParameter("xueqi");
		if (xueqi != null && !xueqi.isEmpty()) {
			v.setXueqi(xueqi);;
		}
		HttpSession session = request.getSession(false);
		if (session != null) {
			String creator = ((User) session.getAttribute(AppData.Session_User)).getId();
			v.setCreator(creator);
		}
		PageData<FileV> pageData = fileService.getstudentPage(page, v);
		JsonResult json = new JsonResult();
		json.setState(true);
		json.setData(pageData);
		return json;
	}
	/**
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
		doc.file.entity.File entity = new doc.file.entity.File();
		String mingCheng = request.getParameter("mingCheng");
		if (mingCheng != null && !mingCheng.isEmpty()) {
			entity.setMingCheng(mingCheng);
		}
	
		String directoryId = request.getParameter("directoryId");
		if (directoryId != null && !directoryId.isEmpty()) {
			entity.setDirectoryId(directoryId);
		}
		String geShi = request.getParameter("format");
		if (geShi != null && !geShi.isEmpty()) {
			entity.setGeShi(geShi);
		}
		Integer size = Integer.valueOf(request.getParameter("size"));
		if (directoryId != null && !directoryId.isEmpty()) {
			entity.setSize(size);
		}
		String realPath = request.getParameter("realPath");
		if (realPath != null && !realPath.isEmpty()) {
			entity.setRealPath(realPath);
		}
		String originalName = request.getParameter("originalName");
		if (originalName != null && !originalName.isEmpty()) {
			entity.setOriginalName(originalName);
		}
		String memo = request.getParameter("memo");
		if (memo != null && !memo.isEmpty()) {
			entity.setMemo(memo);
		}
		HttpSession session = request.getSession(false);
		if (session != null) {
			String creator = ((User) session.getAttribute(AppData.Session_User)).getId();
			entity.setCreator(creator);
		}
		boolean result = fileService.add(entity);
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(fileService.getMessage());
		return json;
	}
	/**
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doEdit")
	public JsonResult doEdit(HttpServletRequest request) {
		FileV entity = new FileV();
		String score = request.getParameter("score");
		if (score != null && !score.isEmpty()) {
			entity.setScore(score);
		}
		String id = request.getParameter("id");
		if (id != null && !id.isEmpty()) {
			entity.setId(id);
		}
		
		boolean result = fileService.edit(entity);
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(fileService.getMessage());
		return json;
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doRemove")
	public JsonResult doRemove(HttpServletRequest request) {
		String id = request.getParameter("id");
		if (id == null || id.isEmpty()) {
			JsonResult json = new JsonResult();
			json.setState(false);
			json.setMessage("操作异常");
			return json;
		}

		boolean result = fileService.remove(id);
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(fileService.getMessage());
		return json;
	}
}