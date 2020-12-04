package org.whx.testdouble.service;

import org.whx.testdouble.bean.Customer;
import org.whx.testdouble.bean.OrderPaymentRequest;
import org.whx.testdouble.bean.PaymentChannel;

public class OrderService {

    private EmailLogger logger;

    public OrderService(EmailLogger logger) {
        this.logger = logger;
    }

    /**
     * 创建支付请求
     *
     * @param customer 客户信息
     * @param channel 支付渠道
     */
    public OrderPaymentRequest createOrderPaymentRequest(Customer customer, PaymentChannel channel) {

        logger.append(String.format("正在为客户%s创建支付订单...", customer.getName()));

        OrderPaymentRequest request = new OrderPaymentRequest();
        request.setCustomerName(customer.getName());
        request.setChannelNo(channel.getChannelNo());

        return request;
    }


    /**
     * 计算价格
     *
     * @param channel 支付渠道
     * @param calculator 价格计算器
     */
    public Double calcPrice(PaymentChannel channel, PriceCalculator calculator) {
        return calculator.calcPrice(channel);
    }
}
