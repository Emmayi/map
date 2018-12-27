package cn.edu.bupt.map.entity;

public class Track {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column track.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column track.tenantId
     *
     * @mbggenerated
     */
    private Integer tenantid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column track.staffName
     *
     * @mbggenerated
     */
    private String staffname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column track.createdAt
     *
     * @mbggenerated
     */
    private Long createdat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column track.updatedAt
     *
     * @mbggenerated
     */
    private Long updatedat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column track.version
     *
     * @mbggenerated
     */
    private Long version;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column track.drawPoint
     *
     * @mbggenerated
     */
    private String drawpoint;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column track.id
     *
     * @return the value of track.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column track.id
     *
     * @param id the value for track.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column track.tenantId
     *
     * @return the value of track.tenantId
     *
     * @mbggenerated
     */
    public Integer getTenantid() {
        return tenantid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column track.tenantId
     *
     * @param tenantid the value for track.tenantId
     *
     * @mbggenerated
     */
    public void setTenantid(Integer tenantid) {
        this.tenantid = tenantid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column track.staffName
     *
     * @return the value of track.staffName
     *
     * @mbggenerated
     */
    public String getStaffname() {
        return staffname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column track.staffName
     *
     * @param staffname the value for track.staffName
     *
     * @mbggenerated
     */
    public void setStaffname(String staffname) {
        this.staffname = staffname == null ? null : staffname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column track.createdAt
     *
     * @return the value of track.createdAt
     *
     * @mbggenerated
     */
    public Long getCreatedat() {
        return createdat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column track.createdAt
     *
     * @param createdat the value for track.createdAt
     *
     * @mbggenerated
     */
    public void setCreatedat(Long createdat) {
        this.createdat = createdat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column track.updatedAt
     *
     * @return the value of track.updatedAt
     *
     * @mbggenerated
     */
    public Long getUpdatedat() {
        return updatedat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column track.updatedAt
     *
     * @param updatedat the value for track.updatedAt
     *
     * @mbggenerated
     */
    public void setUpdatedat(Long updatedat) {
        this.updatedat = updatedat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column track.version
     *
     * @return the value of track.version
     *
     * @mbggenerated
     */
    public Long getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column track.version
     *
     * @param version the value for track.version
     *
     * @mbggenerated
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column track.drawPoint
     *
     * @return the value of track.drawPoint
     *
     * @mbggenerated
     */
    public String getDrawpoint() {
        return drawpoint;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column track.drawPoint
     *
     * @param drawpoint the value for track.drawPoint
     *
     * @mbggenerated
     */
    public void setDrawpoint(String drawpoint) {
        this.drawpoint = drawpoint == null ? null : drawpoint.trim();
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"tenantid\":")
                .append(tenantid);
        sb.append(",\"staffname\":\"")
                .append(staffname).append('\"');
        sb.append(",\"createdat\":\"")
                .append(createdat).append('\"');
        sb.append(",\"updatedat\":\"")
                .append(updatedat).append('\"');
        sb.append(",\"version\":")
                .append(version);
        sb.append(",\"drawpoint\":")
                .append(drawpoint);
        sb.append('}');

        return sb.toString();

    }
}