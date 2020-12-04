package org.whx.testdouble.bean;

public class OrderPaymentRequest {


    private Long channelNo;
    private String customerName;

    public OrderPaymentRequest(){}

    public OrderPaymentRequest(Long channelNo, String customerName) {
        setChannelNo(channelNo);
        setCustomerName(customerName);
    }

    public Long getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(Long channelNo) {
        this.channelNo = channelNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
