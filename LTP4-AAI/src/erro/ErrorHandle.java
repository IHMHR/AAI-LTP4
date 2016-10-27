package erro;

/**
 * @version 1.0
 * @author Igor Martinelli
 */
public class ErrorHandle extends Exception
{
    public ErrorHandle()
    {
        super();
    }
    
    public ErrorHandle(String msg)
    {
        super(msg);
    }
}
