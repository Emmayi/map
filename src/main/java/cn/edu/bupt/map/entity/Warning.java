package cn.edu.bupt.map.entity;

public class Warning {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column warning.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column warning.tenantId
     *
     * @mbggenerated
     */
    private Integer tenantid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column warning.deviceId
     *
     * @mbggenerated
     */
    private String deviceid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column warning.content
     *
     * @mbggenerated
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column warning.status
     *
     * @mbggenerated
     */
    private Boolean status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column warning.updatedAt
     *
     * @mbggenerated
     */
    private Long updatedat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column warning.createdAt
     *
     * @mbggenerated
     */
    private Long createdat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column warning.version
     *
     * @mbggenerated
     */
    private Long version;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column warning.id
     *
     * @return the value of warning.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column warning.id
     *
     * @param id the value for warning.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column warning.tenantId
     *
     * @return the value of warning.tenantId
     *
     * @mbggenerated
     */
    public Integer getTenantid() {
        return tenantid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column warning.tenantId
     *
     * @param tenantid the value for warning.tenantId
     *
     * @mbggenerated
     */
    public void setTenantid(Integer tenantid) {
        this.tenantid = tenantid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column warning.deviceId
     *
     * @return the value of warning.deviceId
     *
     * @mbggenerated
     */
    public String getDeviceid() {
        return deviceid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column warning.deviceId
     *
     * @param deviceid the value for warning.deviceId
     *
     * @mbggenerated
     */
    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid == null ? null : deviceid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column warning.content
     *
     * @return the value of warning.content
     *
     * @mbggenerated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column warning.content
     *
     * @param content the value for warning.content
     *
     * @mbggenerated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column warning.status
     *
     * @return the value of warning.status
     *
     * @mbggenerated
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column warning.status
     *
     * @param status the value for warning.status
     *
     * @mbggenerated
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column warning.updatedAt
     *
     * @return the value of warning.updatedAt
     *
     * @mbggenerated
     */
    public Long getUpdatedat() {
        return updatedat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column warning.updatedAt
     *
     * @param updatedat the value for warning.updatedAt
     *
     * @mbggenerated
     */
    public void setUpdatedat(Long updatedat) {
        this.updatedat = updatedat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column warning.createdAt
     *
     * @return the value of warning.createdAt
     *
     * @mbggenerated
     */
    public Long getCreatedat() {
        return createdat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column warning.createdAt
     *
     * @param createdat the value for warning.createdAt
     *
     * @mbggenerated
     */
    public void setCreatedat(Long createdat) {
        this.createdat = createdat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column warning.version
     *
     * @return the value of warning.version
     *
     * @mbggenerated
     */
    public Long getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column warning.version
     *
     * @param version the value for warning.version
     *
     * @mbggenerated
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"tenantid\":")
                .append(tenantid);
        sb.append(",\"deviceid\":\"")
                .append(deviceid).append('\"');
        sb.append(",\"content\":")
                .append(content);
        sb.append(",\"status\":\"")
                .append(status).append('\"');
        sb.append(",\"createdat\":\"")
                .append(createdat).append('\"');
        sb.append(",\"updatedat\":\"")
                .append(updatedat).append('\"');
        sb.append(",\"version\":")
                .append(version);
        sb.append('}');

        return sb.toString();
    }
}