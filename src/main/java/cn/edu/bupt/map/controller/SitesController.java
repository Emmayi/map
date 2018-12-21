package cn.edu.bupt.map.controller;

import cn.edu.bupt.map.entity.Sites;
import cn.edu.bupt.map.service.SitesSevice;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author zy
 * @date 2018/12/19 下午7:45
 */

@RestController
@RequestMapping("/api/v1/map")
@CrossOrigin
public class SitesController {

    @Autowired
    SitesSevice sitesSevice;

    //创建
    @RequestMapping(value = "/sites", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String createSites(@RequestBody String sitesInfo) throws Exception{
        JsonObject pipeString = new JsonParser().parse(sitesInfo).getAsJsonObject();
        Sites sites = Json2Pipe(pipeString);
        try {
            sitesSevice.insert(sites);
            return sites.toString();
        } catch (Exception e) {
            throw new Exception("createSites error!");
        }
    }

    private Sites Json2Pipe(JsonObject pipeString) {
        Sites pipe = new Sites();

        pipe.setTenantid(pipeString.get("tenantid").getAsInt());
        pipe.setName(pipeString.get("name").getAsString());
        pipe.setLatitude(pipeString.get("latitude").getAsDouble());
        pipe.setLongtitude(pipeString.get("longtitude").getAsDouble());
        pipe.setCreatedat(pipeString.get("createdat").getAsLong());


//        pipe.setCompressstatus(pipeString.get("compressstatus").getAsBoolean());
//        pipe.setDevicesmodelcount(pipeString.get("devicesmodelcount").getAsInt());
//        pipe.setUpdatedat(pipeString.get("updatedat").getAsLong());
//        pipe.setVersion(pipeString.get("version").getAsLong());
//        pipe.setSceneurl(pipeString.get("sceneurl").getAsString());
//        pipe.setOssstatus(pipeString.get("osstatus").getAsBoolean());
//        pipe.setScenemodelloca(pipeString.get("scenemodelloca").getAsString());

        return pipe;
    }


    //更新
    @RequestMapping(value = "/sites", method = RequestMethod.PUT, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateSites(@RequestBody String sitesInfo) throws Exception{
        JsonObject pipeString = new JsonParser().parse(sitesInfo).getAsJsonObject();
        if(pipeString.get("id").getAsString().equals("")) {
            throw new RuntimeException("没有Id，无法更新!");
        }
        Sites pipe = new Sites();
        pipe.setId(pipeString.get("id").getAsInt());
        pipe.setCreatedat(pipeString.get("createdat").getAsLong());
        pipe.setCompressstatus(pipeString.get("compressstatus").getAsBoolean());
        pipe.setName(pipeString.get("name").getAsString());
        pipe.setDevicesmodelcount(pipeString.get("devicesmodelcount").getAsInt());
        pipe.setLatitude(pipeString.get("latitude").getAsDouble());
        pipe.setLatitude(pipeString.get("latitude").getAsDouble());
        pipe.setTenantid(pipeString.get("tenantid").getAsInt());
        pipe.setUpdatedat(pipeString.get("updatedat").getAsLong());
        pipe.setVersion(pipeString.get("version").getAsLong());
        pipe.setSceneurl(pipeString.get("sceneurl").getAsString());
        pipe.setOssstatus(pipeString.get("osstatus").getAsBoolean());
        pipe.setScenemodelloca(pipeString.get("scenemodelloca").getAsString());
        try {
            sitesSevice.update(pipe);
            return pipe.toString();
        } catch (Exception e) {
            throw new Exception("updateSites error!");
        }
    }


    //通过Id查找
    @RequestMapping(value = "/sites",params = {"sitesId"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getSitesById(@RequestParam Integer sitesId) throws Exception{
        try {
            return sitesSevice.findById(sitesId).toString();
        }catch (Exception e){
            throw new Exception("getSitesById error!");
        }
    }

    //根据Id删除
    @RequestMapping(value = "/sites",params = {"sitesId"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteSitesById(@RequestParam Integer sitesId){
        try {
            sitesSevice.deleteById(sitesId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
