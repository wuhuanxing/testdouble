package org.whx.testdouble.service;

import org.whx.testdouble.bean.PaymentChannel;

public class DefaultPriceCalculator implements PriceCalculator {

    @Override
    public Double calcPrice(PaymentChannel channel) {
        return channel.getChannelNo().doubleValue();
    }
}
