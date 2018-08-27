package ocean.example.netty.httpxml.domain;

import java.util.List;

/**
 * <一句话描述>
 *
 * @author wangyang
 * @version [需求编号, 2018/8/6]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class Customer
{
    private long customerNumber;

    private String firstName;

    private String lastName;

    private List<String> middleNames;

    public long getCustomerNumber()
    {
        return customerNumber;
    }

    public void setCustomerNumber(long customerNumber)
    {
        this.customerNumber = customerNumber;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public List<String> getMiddleNames()
    {
        return middleNames;
    }

    public void setMiddleNames(List<String> middleNames)
    {
        this.middleNames = middleNames;
    }

    @Override
    public String toString()
    {
        return "Customer{" +
            "customerNumber=" + customerNumber +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", middleNames=" + middleNames +
            '}';
    }
}
