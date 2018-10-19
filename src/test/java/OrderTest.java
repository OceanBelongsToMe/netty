import ocean.example.netty.httpxml.OrderFactory;
import ocean.example.netty.httpxml.domain.Order;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;

import java.io.StringReader;
import java.io.StringWriter;

/**
 * <一句话描述>
 *
 * @author wangyang
 * @version [需求编号, 2018/9/7]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class OrderTest
{
    //
    private IBindingFactory factory = null;

    private StringReader reader = null;

    private StringWriter writer = null;

    private final static String UTF8 = "UTF-8";

    public String order2Xml(Order order)
        throws JiBXException
    {
        factory = BindingDirectory.getFactory(Order.class);
        writer = new StringWriter();
        IMarshallingContext context = factory.createMarshallingContext();
        context.setIndent(2);
        //marshal 是由 Java 对象生成 XML 文挡
        context.marshalDocument(order, UTF8, null, writer);
        String xmlStr = writer.toString();
        System.out.printf(xmlStr);
        return xmlStr;
    }

    public Order xml2Order(String xml)
        throws JiBXException
    {
        reader = new StringReader(xml);
        IUnmarshallingContext context = factory.createUnmarshallingContext();
        //unmarshal 是根据 XML 文挡建立 Java 对象
        Order order = (Order)context.unmarshalDocument(reader);
        return order;
    }

    public static void main(String[] args)
        throws JiBXException
    {
        OrderTest test = new OrderTest();
        Order order = OrderFactory.create(123);
        String xml = test.order2Xml(order);
        Order order2 = test.xml2Order(xml);
        System.out.println(order2);
    }
}
