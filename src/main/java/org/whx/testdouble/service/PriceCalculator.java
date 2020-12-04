package org.whx.testdouble.service;

import org.whx.testdouble.bean.PaymentChannel;

public interface PriceCalculator {


    Double calcPrice(PaymentChannel channel);
}
