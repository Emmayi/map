package cn.edu.bupt.map.mapper;

import cn.edu.bupt.map.entity.Track;

import java.util.List;

public interface TrackMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table track
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table track
     *
     * @mbggenerated
     */
    int insert(Track record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table track
     *
     * @mbggenerated
     */
    int insertSelective(Track record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table track
     *
     * @mbggenerated
     */
    Track selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table track
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Track record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table track
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(Track record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table track
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Track record);

    List<Track> selectAll();
}