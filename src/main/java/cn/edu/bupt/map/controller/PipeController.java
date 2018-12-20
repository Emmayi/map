package cn.edu.bupt.map.controller;

import cn.edu.bupt.map.entity.Pipe;
import cn.edu.bupt.map.mapper.PipeMapper;
import cn.edu.bupt.map.service.PipeService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author zy
 * @date 2018/12/19 下午7:37
 */

@RestController
@RequestMapping("/api/v1/map")
@CrossOrigin
public class PipeController {

    @Autowired
    PipeService pipeService;

    //创建
    @RequestMapping(value = "/pipe", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String createPipe(@RequestBody String pipeInfo) throws Exception{
        JsonObject pipeString = new JsonParser().parse(pipeInfo).getAsJsonObject();
        Pipe pipe = Json2Pipe(pipeString);
        try {
            pipeService.insert(pipe);
            return pipe.toString();
        } catch (Exception e) {
            throw new Exception("createPipe error!");
        }
    }

    private Pipe Json2Pipe(JsonObject pipeString) {
        Pipe pipe = new Pipe();
        pipe.setCreatedat(pipeString.get("createdat").getAsLong());
        pipe.setDrawpoint(pipeString.get("drawpoint").getAsString());
        pipe.setName(pipeString.get("name").getAsString());
        pipe.setPipecolor(pipeString.get("pipecolor").getAsString());
        pipe.setPipetype(pipeString.get("pipetype").getAsString());
        pipe.setPipewidth(pipeString.get("pipewidth").getAsDouble());
        pipe.setTenantid(pipeString.get("tenantid").getAsInt());
        pipe.setUpdatedat(pipeString.get("updatedat").getAsLong());
        pipe.setVersion(pipeString.get("version").getAsLong());

        return pipe;
    }

    //通过Id查找
    @RequestMapping(value = "/pipe",params = {"pipeId"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getPipeById(@RequestParam Integer pipeId) throws Exception{
        try {
            return pipeService.findById(pipeId).toString();
        }catch (Exception e){
            throw new Exception("getPipeById error!");
        }
    }

    //根据Id删除
    @RequestMapping(value = "/pipe",params = {"pipeId"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deletePipeById(@RequestParam Integer pipeId){
        try {
            pipeService.deleteById(pipeId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
