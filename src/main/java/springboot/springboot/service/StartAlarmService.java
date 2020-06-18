package springboot.springboot.service;

import org.springframework.web.multipart.MultipartFile;
import springboot.springboot.entity.StartAlarm;

public interface StartAlarmService {
    StartAlarm setStartAlarm(String devId, String uuid, String name, String addr, String text, String type, String owner, String phone, String dateTime, String sign, MultipartFile imageFile,String resId);
}
