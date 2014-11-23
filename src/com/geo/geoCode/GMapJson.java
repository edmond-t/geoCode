
package com.geo.geoCode;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class GMapJson {

    @Expose
    private Double dstOffset;
    @Expose
    private Double rawOffset;
    @Expose
    private String status;
    @Expose
    private String timeZoneId;
    @Expose
    private String timeZoneName;

    /**
     * 
     * @return
     *     The dstOffset
     */
    public Double getDstOffset() {
        return dstOffset;
    }

    /**
     * 
     * @param dstOffset
     *     The dstOffset
     */
    public void setDstOffset(Double dstOffset) {
        this.dstOffset = dstOffset;
    }

    /**
     * 
     * @return
     *     The rawOffset
     */
    public Double getRawOffset() {
        return rawOffset;
    }

    /**
     * 
     * @param rawOffset
     *     The rawOffset
     */
    public void setRawOffset(Double rawOffset) {
        this.rawOffset = rawOffset;
    }

    /**
     * 
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The timeZoneId
     */
    public String getTimeZoneId() {
        return timeZoneId;
    }

    /**
     * 
     * @param timeZoneId
     *     The timeZoneId
     */
    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    /**
     * 
     * @return
     *     The timeZoneName
     */
    public String getTimeZoneName() {
        return timeZoneName;
    }

    /**
     * 
     * @param timeZoneName
     *     The timeZoneName
     */
    public void setTimeZoneName(String timeZoneName) {
        this.timeZoneName = timeZoneName;
    }

}
