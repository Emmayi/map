package cn.edu.bupt.map.controller;

import cn.edu.bupt.map.entity.Track;
import cn.edu.bupt.map.mapper.TrackMapper;
import cn.edu.bupt.map.service.TrackService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author zy
 * @date 2018/12/19 下午8:15
 */

@RestController
@RequestMapping("/api/v1/map")
@CrossOrigin
public class TrackController {

    @Autowired
    TrackService trackService;

    @Autowired
    TrackMapper trackMapper;

    //创建
    @RequestMapping(value = "/track", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String createTrack(@RequestBody String trackInfo) throws Exception{
        JsonObject trackString = new JsonParser().parse(trackInfo).getAsJsonObject();
        Track track = Json2Track(trackString);
        try {
            trackService.insert(track);
            return track.toString();
        } catch (Exception e) {
            throw new Exception("createTrack error!");
        }
    }

    private Track Json2Track(JsonObject trackString) {
        Track track = new Track();
        track.setCreatedat(trackString.get("createdat").getAsLong());
        track.setDrawpoint(trackString.get("drawpoint").getAsString());
        track.setStaffname(trackString.get("staffname").getAsString());
        track.setTenantid(trackString.get("tenantid").getAsInt());
//        track.setUpdatedat(trackString.get("updatedat").getAsLong());
//        track.setVersion(trackString.get("version").getAsLong());

        return track;
    }

    //更新
    @RequestMapping(value = "/track", method = RequestMethod.PUT, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String update(@RequestBody String trackInfo) throws Exception{
        JsonObject trackString = new JsonParser().parse(trackInfo).getAsJsonObject();
        if(trackString.get("id").getAsString().equals("")) {
            throw new RuntimeException("没有Id，无法更新!");
        }
        Track track = new Track();
        track.setId(trackString.get("id").getAsInt());
        track.setCreatedat(trackString.get("createdat").getAsLong());
        track.setDrawpoint(trackString.get("drawpoint").getAsString());
        track.setStaffname(trackString.get("staffname").getAsString());
        track.setTenantid(trackString.get("tenantid").getAsInt());
        try {
            trackMapper.updateByPrimaryKey(track);
            return track.toString();
        } catch (Exception e) {
            throw new Exception("update error!");
        }
    }

    //通过Id查找
    @RequestMapping(value = "/track",params = {"trackId"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getTrackById(@RequestParam Integer trackId) throws Exception{
        try {
            return trackService.findById(trackId).toString();
        }catch (Exception e){
            throw new Exception("getTrackById error!");
        }
    }

    //根据Id删除
    @RequestMapping(value = "/track",params = {"trackId"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteTrackById(@RequestParam Integer trackId){
        try {
            trackService.deleteById(trackId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //获取所有的信息
    @RequestMapping(value = "/trackAll", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getTrackAll() throws Exception{
        try {
            return trackMapper.selectAll().toString();
        }catch (Exception e){
            throw new Exception("getTrackAll error!");
        }
    }
}
