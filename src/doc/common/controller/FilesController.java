package doc.common.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import pushunsoft.common.JsonResult;

/**
 * 上传下载
 * 
 * @author jerry
 *
 */
@Controller
@RequestMapping("/file")
public class FilesController extends BaseController {
	/**
	 * 文件上传
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/popup")
	public String popup(HttpServletRequest request, Model model) {
		model.addAttribute("title", "文件上传");
		String uploadType = request.getParameter("upload_type");
		if (uploadType == null || uploadType.isEmpty()) {
			uploadType = "temp";
		}
		model.addAttribute("upload_type", uploadType);
		return "public/popup/selectFile";
	}

	/**
	 * 上传一个文件
	 * 
	 * @param files
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/upload")
	public JsonResult upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		String targetDir = AppData.Config.getUploadPath();
		String uploadType = request.getParameter("upload_type");
		String fileName = request.getParameter("fileName");
		if (uploadType == null || "".equals(uploadType)) {
			uploadType = "temp";
		}
		targetDir = targetDir + uploadType;

		File dirFile = new File(targetDir);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		String extension = "";
		if (file.getOriginalFilename().lastIndexOf(".") != -1) {
			extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
		}
		String targetFileName = null;
		if (fileName != null && !fileName.isEmpty()) {
			targetFileName = fileName;
		} else {
			targetFileName = UUID.randomUUID().toString().replaceAll("-", "");
		}
		targetFileName = targetFileName + "." + extension;
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
		// 返回
		JsonResult json = new JsonResult();
		json.setState(result);
		if (result == true) {
			UploadFile data = new UploadFile();
			data.setName(targetFileName);
			data.setSize(targetFile.length());
			data.setOriginalName(file.getOriginalFilename());
			data.setVirtualPath(uploadType + "/" + targetFileName);
			data.setRealPath(data.getVirtualPath());
			data.setFormat(extension);
			json.setData(data);
		}
		json.setMessage(message);
		return json;
	}

	@RequestMapping("/down")
	public ResponseEntity<byte[]> down(HttpServletRequest request, HttpServletResponse response) {
		String filePath = request.getParameter("realPath");
		String fileName = request.getParameter("fileName");// 获取要下载的文件名
		String realPath = AppData.Config.getUploadPath() + filePath;
		File file = new File(realPath);
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

	@RequestMapping("/show")
	public void show(HttpServletRequest request, HttpServletResponse response) {
		String filePath = request.getParameter("filePath");
		String url = AppData.Config.getFilePath() + filePath;
		try {
			response.sendRedirect(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}