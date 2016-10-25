package erro;

/**
 * @version 1.0
 * @author Igor Martinelli
 */
public class ErroHandle extends Exception
{
    public ErroHandle()
    {
        super();
    }
    
    public ErroHandle(String msg)
    {
        super(msg);
    }
}
