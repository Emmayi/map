package cn.edu.bupt.map.controller;

import cn.edu.bupt.map.entity.Warning;
import cn.edu.bupt.map.mapper.WarningMapper;
import cn.edu.bupt.map.service.WarningService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author zy
 * @date 2018/12/19 下午8:24
 */

@RestController
@RequestMapping("/api/v1/map")
@CrossOrigin
public class WarningController {

    @Autowired
    WarningService warningService;

    @Autowired
    WarningMapper warningMapper;

    //创建
    @RequestMapping(value = "/warning", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String createWarn(@RequestBody String warnInfo) throws Exception{
        JsonObject warnString = new JsonParser().parse(warnInfo).getAsJsonObject();
        Warning warning = Json2Warn(warnString);
        try {
            warningService.insert(warning);
            return warning.toString();
        } catch (Exception e) {
            throw new Exception("createWarn error!");
        }
    }

    private Warning Json2Warn(JsonObject trackString) {
        Warning warning = new Warning();
        warning.setCreatedat(trackString.get("createdat").getAsLong());
        warning.setContent(trackString.get("content").getAsString());
        warning.setDeviceid(trackString.get("deviceid").getAsString());
        warning.setTenantid(trackString.get("tenantid").getAsInt());
        warning.setUpdatedat(trackString.get("updatedat").getAsLong());
        warning.setVersion(trackString.get("version").getAsLong());
        warning.setStatus(trackString.get("status").getAsBoolean());

        return warning;
    }

    //通过Id查找
    @RequestMapping(value = "/warning",params = {"warnId"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getWarnById(@RequestParam Integer warnId) throws Exception{
        try {
            return warningService.findById(warnId).toString();
        }catch (Exception e){
            throw new Exception("getWarnById error!");
        }
    }

    //根据Id删除
    @RequestMapping(value = "/warning",params = {"warnId"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteWarnById(@RequestParam Integer warnId){
        try {
            warningService.deleteById(warnId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //获取所有的信息
    @RequestMapping(value = "/warningAll", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getWarningAll() throws Exception{
        try {

            String warnStr = warningMapper.selectAll().toString();
            String yes = warnStr.replace("\\", "\\");


            return yes;
        }catch (Exception e){
            throw new Exception("getWarningAll error!");
        }
    }

}
