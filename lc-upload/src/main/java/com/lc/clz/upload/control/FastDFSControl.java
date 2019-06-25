package com.lc.clz.upload.control;

/*import com.lc.clz.upload.util.fastDfsClient;*/
import com.lc.clz.upload.util.FastDFSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author shenjiang
 * @Description:
 * @Date: 2019/6/10 09:39
 */
@Controller
public class FastDFSControl {

    @Value("${tracker.server}")
    private String trackerServerUrl;


    @RequestMapping(value = "/getFileUpload")
    public String getFileUpload(){
        return "uploadTest";
    }

    @RequestMapping("/uploadFile")
    @ResponseBody
    public String uploadFile(MultipartFile file) throws Exception {
        FastDFSClient  fastDFSClient= new FastDFSClient(trackerServerUrl);
        String fileName = file.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf("."),fileName.length());
        String[] url = fastDFSClient.uploadFile(fileName,fileType,null);
        String urlAll="";
        for (String string:url) {
            urlAll+="/"+string;
        }
        return urlAll;
    }

}
