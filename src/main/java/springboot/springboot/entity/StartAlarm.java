package springboot.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class StartAlarm {

    private String devId;
    private String uuid;
    private String name;
    private String addr;
    private String text;
    private String type;
    private String owner;
    private String phone;
    private String dateTime;
    private String imageFile;
    private String resId;
    private String sign;



}
