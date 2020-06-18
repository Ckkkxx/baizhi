package springboot.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springboot.springboot.entity.StartAlarm;
import springboot.springboot.service.StartAlarmService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("zhiling/alarmre")
public class StartAlarmController {
    @Autowired
    StartAlarmService startAlarmService;

    @PostMapping("/startAlarm")
    public void post(String devId, String uuid, String name, String addr, String text, String type, String owner, String phone, String dateTime, String sign, MultipartFile imageFile, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        JSONObject jsonData= new JSONObject();
        UUID resUuid = UUID.randomUUID();
        try{
            StartAlarm startAlarm = startAlarmService.setStartAlarm(devId, uuid, name, addr, text, type, owner, phone, dateTime, sign, imageFile,resUuid.toString());
            System.out.println("aa----------"+startAlarm);
        }catch (Exception e){
            System.out.println("bb----------"+e.toString());
            jsonData.put("result","fail");
            jsonData.put("message",e);
            jsonData.put("alarmId",resUuid.toString());
        }
        jsonData.put("result","success");
        jsonData.put("message","创建成功");
        jsonData.put("alarmId",resUuid.toString());
        response.getWriter().print(jsonData);
    }

}
