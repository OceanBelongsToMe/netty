package exception;

/**
 * <一句话描述>
 *
 * @author wangyang
 * @version [需求编号, 2018/7/11]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MyCodeException extends Exception {

    private int code;
    private String message;

    public MyCodeException(int code) {
    }


    public MyCodeException(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
