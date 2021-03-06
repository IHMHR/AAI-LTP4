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
public class Uf
{
    private static String uf;
    private static String estado;
    private static ResultSet retorno;
    private static final String TABLE_NAME = "estados";

    /**
     * @return the uf
     */
    public String getUf() {
        return uf;
    }

    /**
     * @param aUf the uf to set
     */
    public void setUf(String aUf) {
        uf = aUf;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param aEstado the estado to set
     */
    public void setEstado(String aEstado) {
        estado = aEstado;
    }
    
    public static ResultSet listaEstados() throws ErrorHandle, IOException
    {
        retorno = null;
        try
        {
            retorno = Banco.Selecionar("uf, estado", TABLE_NAME);
        }
        catch (ErrorHandle | ClassNotFoundException e)
        {
            FileWriter arq = new FileWriter("erroLog.txt", true);
            arq.write(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()) + " - " + e + " (listaEstados)(Uf)\n");
            arq.flush();
            arq.close();
            throw new ErrorHandle("Falha ao listar estados " + e.getMessage());
        }
        return retorno;
    }
}
