package springboot.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springboot.springboot.entity.StartAlarm;
import springboot.springboot.service.StartAlarmService;

import java.util.UUID;

@SpringBootTest
class ApplicationTests {

    @Autowired
    StartAlarmService startAlarmService;
    @Test
    void contextLoads() {
//        UUID uuid = UUID.randomUUID();
//        StartAlarm startAlarm = new StartAlarm();
//       startAlarm.setDevId("1222");
//       startAlarm.setUuid("1");
//       startAlarm.setName("1");
//       startAlarm.setAddr("1");
//       startAlarm.setText("1");
//       startAlarm.setType("1");
//       startAlarm.setOwner("1");
//       startAlarm.setPhone("1");
//       startAlarm.setDateTime("1");
//       startAlarm.setImageFile("1");
//       startAlarm.setResId(uuid.toString());
//        StartAlarm startAlarm1 = startAlarmService.setStartAlarm(startAlarm);
//        System.out.println(startAlarm1);
    }

}
