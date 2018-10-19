package ocean.example.netty.httpxml.domain;

/**
 * <一句话描述>
 *
 * @author wangyang
 * @version [需求编号, 2018/8/6]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class Address
{
    private String street1;

    private String stree2;

    private String city;

    private String state;

    private String country;

    private String postCode;

    public String getStreet1()
    {
        return street1;
    }

    public void setStreet1(String street1)
    {
        this.street1 = street1;
    }

    public String getStree2()
    {
        return stree2;
    }

    public void setStree2(String stree2)
    {
        this.stree2 = stree2;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getPostCode()
    {
        return postCode;
    }

    public void setPostCode(String postCode)
    {
        this.postCode = postCode;
    }

    @Override
    public String toString()
    {
        return "Address{" +
            "street1='" + street1 + '\'' +
            ", stree2='" + stree2 + '\'' +
            ", city='" + city + '\'' +
            ", state='" + state + '\'' +
            ", country='" + country + '\'' +
            ", postCode='" + postCode + '\'' +
            '}';
    }
}
