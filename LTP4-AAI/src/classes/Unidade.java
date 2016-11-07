package classes;

import banco.Banco;
import erro.ErrorHandle;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @version 1.0
 * @author Igor Martinelli
 */
public class Unidade
{
    private static int codUnidade;
    private static String unidade;
    private static ResultSet retorno;
    private static final String TABLE_NAME = "unidades";

    /**
     * @return the codUnidade
     */
    public int getCodUnidade() {
        return codUnidade;
    }

    /**
     * @param aCodUnidade the codUnidade to set
     */
    public void setCodUnidade(int aCodUnidade) {
        codUnidade = aCodUnidade;
    }

    /**
     * @return the unidade
     */
    public String getUnidade() {
        return unidade;
    }

    /**
     * @param aUnidade the unidade to set
     */
    public void setUnidade(String aUnidade) {
        unidade = aUnidade;
    }
    
    public static ResultSet listaUnidades() throws ErrorHandle, IOException
    {
        retorno = null;
        try 
        {
            retorno = Banco.Selecionar("codUnidade, unidade", TABLE_NAME);
        }
        catch (ErrorHandle | ClassNotFoundException e)
        {
            FileWriter arq = new FileWriter("erroLog.txt", true);
            arq.write(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()) + " - " + e + " (listaUnidades)(Unidade)\n");
            arq.flush();
            arq.close();
            throw new ErrorHandle("Falha ao lista as unidades disponíveis");
        }
        return retorno;
    }
}
