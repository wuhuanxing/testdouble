package org.whx.testdouble.bean;

public class PaymentChannel {

    private Long channelNo;
    private String channelName;

    public PaymentChannel() {
    }

    public PaymentChannel(Long channelNo, String channelName) {
        setChannelNo(channelNo);
        setChannelName(channelName);
    }

    public Long getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(Long channelNo) {
        this.channelNo = channelNo;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
}
