package org.whx.testdouble;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.whx.testdouble.bean.Customer;
import org.whx.testdouble.bean.OrderPaymentRequest;
import org.whx.testdouble.bean.PaymentChannel;
import org.whx.testdouble.service.DefaultPriceCalculator;
import org.whx.testdouble.service.EmailLogger;
import org.whx.testdouble.service.OrderService;
import org.whx.testdouble.service.PriceCalculator;

@SpringBootTest
public class OrderServiceTest {

    // ============================== Dummy ================================ //

    public class DummyLogger implements EmailLogger {

        @Override
        public void append(String msg) {
            // do nothing
        }
    }

    @Test
    public void shouldCreatePaymentRequest() {

        EmailLogger dummyLogger = new DummyLogger();

        OrderService orderService = new OrderService(dummyLogger);

        Customer customer = new Customer("张三");
        PaymentChannel channel = new PaymentChannel(1000L, "微信公众号支付");
        OrderPaymentRequest actualRequest = orderService.createOrderPaymentRequest(customer, channel);

        OrderPaymentRequest expectedRequest = new OrderPaymentRequest(channel.getChannelNo(), customer.getName());

        Assert.assertEquals(expectedRequest.getChannelNo(), actualRequest.getChannelNo());
        Assert.assertEquals(expectedRequest.getCustomerName(), actualRequest.getCustomerName());
    }

    // ============================= Stub ================================= //

    public class StubPriceCalculator implements PriceCalculator {

        public StubPriceCalculator() {
        }

        @Override
        public Double calcPrice(PaymentChannel channel) {
            return channel.getChannelNo().doubleValue();
        }
    }


    @Test
    public void shouldCalcTheOrderPrice() {
        EmailLogger logger = new DummyLogger();
        OrderService orderService = new OrderService(logger);
        //
        PriceCalculator stubPriceCalculator = new StubPriceCalculator();
        PaymentChannel channel = new PaymentChannel(1000L, "微信公众号支付");

        Double actual = orderService.calcPrice(channel, stubPriceCalculator);

        Assert.assertEquals(new Double(1000L), actual);

    }

    // ============================= Mock ================================= //


    @Test
    public void shouldCalcTheOrderPriceWithCalculator() {
        EmailLogger logger = new DummyLogger();
        OrderService orderService = new OrderService(logger);

        PriceCalculator mockPriceCalculator = Mockito.mock(DefaultPriceCalculator.class);
        PaymentChannel channel = new PaymentChannel(1000L, "微信公众号支付");

        orderService.calcPrice(channel, mockPriceCalculator);

        Mockito.verify(mockPriceCalculator, times(1)).calcPrice(any());
    }

    // ============================= Spy ================================= //

    @Test
    public void shouldCalcTheOrderPriceWithCalculator2() {
        EmailLogger logger = new DummyLogger();
        OrderService orderService = new OrderService(logger);

        PriceCalculator spyPriceCalculator = Mockito.spy(DefaultPriceCalculator.class);
        PaymentChannel channel = new PaymentChannel(1000L, "微信公众号支付");

        Double actual = orderService.calcPrice(channel, spyPriceCalculator);

        Assert.assertEquals(new Double(channel.getChannelNo()), actual);
        Mockito.verify(spyPriceCalculator, times(1)).calcPrice(any());
    }
}
