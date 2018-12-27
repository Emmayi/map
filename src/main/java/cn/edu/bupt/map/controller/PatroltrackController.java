package cn.edu.bupt.map.controller;

import cn.edu.bupt.map.entity.Patroltrack;
import cn.edu.bupt.map.mapper.PatroltrackMapper;
import cn.edu.bupt.map.service.PatroltrackService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author zy
 * @date 2018/12/19 下午5:53
 */

@RestController
@RequestMapping("/api/v1/map")
@CrossOrigin
public class PatroltrackController {

    @Autowired
    PatroltrackService patroltrackService;

    @Autowired
    PatroltrackMapper patroltrackMapper;

    //创建
    @RequestMapping(value = "/patroltrack", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String createPatroltrack(@RequestBody String patroltrackInfo) throws Exception{
        JsonObject trackString = new JsonParser().parse(patroltrackInfo).getAsJsonObject();
        Patroltrack patroltrack = Json2Track(trackString);
        try {
            patroltrackService.insert(patroltrack);
            return patroltrack.toString();
        } catch (Exception e) {
            throw new Exception("createPatroltrack error!");
        }
    }

    private Patroltrack Json2Track(JsonObject trackString) {
        Patroltrack patroltrack = new Patroltrack();
        patroltrack.setCreatedat(trackString.get("createdat").getAsLong());
        patroltrack.setDrawpoint(trackString.get("drawpoint").getAsString());
        patroltrack.setName(trackString.get("name").getAsString());
        patroltrack.setPipecolor(trackString.get("pipecolor").getAsString());
        patroltrack.setPipetype(trackString.get("pipetype").getAsString());
        patroltrack.setPipewidth(trackString.get("pipewidth").getAsDouble());
        patroltrack.setTenantid(trackString.get("tenantid").getAsInt());
//        patroltrack.setUpdatedat(trackString.get("updatedat").getAsLong());
//        patroltrack.setVersion(trackString.get("version").getAsLong());

        return patroltrack;
    }

    //通过Id查找
    @RequestMapping(value = "/patroltrack",params = {"trackId"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getPatroltrackById(@RequestParam Integer trackId) throws Exception{
        try {
            return patroltrackService.findById(trackId).toString();
        }catch (Exception e){
            throw new Exception("getPatroltrackById error!");
        }
    }

    //根据Id删除
    @RequestMapping(value = "/patroltrack",params = {"trackId"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deletePatroltrackById(@RequestParam Integer trackId){
        try {
            patroltrackService.deleteById(trackId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //获取所有的信息
    @RequestMapping(value = "/patroltrackAll", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getPatroltrackAll() throws Exception{
        try {
            return patroltrackMapper.selectAll().toString();
        }catch (Exception e){
            throw new Exception("getPatroltrackAll error!");
        }
    }



}
