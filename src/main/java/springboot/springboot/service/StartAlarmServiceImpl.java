package springboot.springboot.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import springboot.springboot.dao.StartAlarmDAO;
import springboot.springboot.entity.StartAlarm;
import springboot.springboot.utils.CkUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class StartAlarmServiceImpl implements StartAlarmService {
    @Autowired
    private StartAlarmDAO startAlarmDAO;
    JSONObject jsonData= new JSONObject();

    @Autowired
    CkUtils ckUtils;


    @Override
    public StartAlarm setStartAlarm(String devId, String uuid, String name, String addr, String text, String type, String owner, String phone, String dateTime, String sign, MultipartFile imageFile,String resId) {

        //获取文件的原始名称
        String oldFileName = imageFile.getOriginalFilename();
        //获取文件后缀
        String extension = "." + FilenameUtils.getExtension(imageFile.getOriginalFilename());
        //生成新的文件名称
        String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + UUID.randomUUID().toString().replace("-","")+extension;


        //处理根据日期生成目录
        String realPath = null;
        try {
            realPath = ResourceUtils.getURL("classpath:").getPath() + "static/files";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
    }
        String dateFormat = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String dateDirPath = realPath + "/" + dateFormat;
        File dateDir = new File(dateDirPath);
        if(!dateDir.exists()){
            dateDir.mkdirs();
        }
        //处理文件上传
        try {
            imageFile.transferTo(new File(dateDir,newFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("路径："+dateDirPath);

        StartAlarm startAlarm = new StartAlarm();
        startAlarm.setDevId(devId);
        startAlarm.setUuid(uuid);
        startAlarm.setName(name);
        startAlarm.setAddr(addr);
        startAlarm.setText(text);
        startAlarm.setType(type);
        startAlarm.setOwner(owner);
        startAlarm.setPhone(phone);
        startAlarm.setDateTime(dateTime);
        startAlarm.setSign(sign);
        startAlarm.setImageFile(dateDirPath);
        startAlarm.setResId(resId);
        startAlarmDAO.setStartAlarm(startAlarm);

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("warningType=");
        stringBuffer.append(type);
        stringBuffer.append("&");
        stringBuffer.append("aralmId=");
        stringBuffer.append(resId);
        stringBuffer.append("&");
        stringBuffer.append("deviceid=");
        stringBuffer.append(devId);
        String s = stringBuffer.toString();
        System.out.println(s);


        String pushUrl = "http://admin.zhongmenginformation.com/api/WeiFanVideoApi/weifanVideoWarningPush";
        String s1 = ckUtils.httpPost(pushUrl, s);
        System.out.println("推动结果："+s1);

        return startAlarm;
    }
}
