package com.lc.clz.upload.util;

import com.lc.clz.upload.util.*;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;


/**
 * @author shenjiang
 * @Description:
 * @Date: 2019/6/9 22:23
 */
public class FastDFSClient {

    private TrackerClient trackerClient;
    private TrackerServer trackerServer;
    private StorageClient storageClient;
    private StorageServer storageServer;

    public FastDFSClient(String config) throws Exception{
        ClientGlobal.init(config);
        trackerClient = new TrackerClient();
        trackerServer = trackerClient.getConnection();
        storageServer = null;
        storageClient = new StorageClient1(trackerServer,storageServer);
    }

    /**
     * 上传文件方法
     * @param fileName 文件内容 字节数组
     * @param exName 文件扩展名
     * @param metas 文件扩展信息
     * @return
     * @throws Exception
     */
    public String[] uploadFile(String fileName, String exName, NameValuePair[] metas) throws Exception{
        String[] result = storageClient.upload_file(fileName,exName,metas);
        return result;
    }

    public String[] uploadFile(String fileName)throws Exception{
        return uploadFile(fileName,null,null);
    }

    public String[] upload(String fileName,String exName)throws Exception{
        return storageClient.upload_file(fileName,exName,null);
    }


    /**
     * 上传文件方法
     * @param fileContent 文件内容 字节数组
     * @param exName 文件扩展名
     * @param nameValuePair 文件扩展信息
     * @return
     * @throws Exception
     */
    public String[] uploadFile(byte[] fileContent, String exName, NameValuePair[] nameValuePair) throws Exception {
        String[] result = storageClient.upload_file(fileContent,exName,nameValuePair);
        return result;
    }


    public String[] uploadFile(byte[] fileContent)throws Exception{
        return uploadFile(fileContent,null,null);
    }

    public String[] uploadFile(byte[] fileContent,String exName)throws Exception{
        return uploadFile(fileContent,exName,null);
    }






}
