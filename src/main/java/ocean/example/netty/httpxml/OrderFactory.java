package ocean.example.netty.httpxml;

import ocean.example.netty.httpxml.common.Shipping;
import ocean.example.netty.httpxml.domain.Address;
import ocean.example.netty.httpxml.domain.Customer;
import ocean.example.netty.httpxml.domain.Order;

/**
 * <一句话描述>
 *
 * @author wangyang
 * @version [需求编号, 2018/9/7]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class OrderFactory {

    public static Order create(long orderID) {
        Order order = new Order();
        order.setOrderNumber(orderID);
        order.setTotal(9999.999f);
        Address address = new Address();
        address.setCity("杭州");
        address.setCountry("中国");
        address.setPostCode("330000");
        address.setState("浙江省");
        address.setStreet1("水陆寺巷");
        order.setBillTo(address);
        Customer customer = new Customer();
        customer.setCustomerNumber(orderID);
        customer.setFirstName("挖坑");
        customer.setLastName("埋你");
        order.setCustomer(customer);
        order.setShipping(Shipping.INTERNATIONAL_MAIL);
        order.setShipTo(address);
        return order;
    }
}