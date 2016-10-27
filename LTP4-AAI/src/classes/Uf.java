package classes;

import banco.Banco;
import erro.ErrorHandle;
import java.sql.ResultSet;

/**
 * @version 1.0
 * @author Igor Martinelli
 */
public class Uf
{
    private static String uf;
    private static String estado;
    private static ResultSet retorno;
    private static final String TABLE_NAME = "Clientes";

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
    
    public static ResultSet listaEstados() throws ErrorHandle
    {
        retorno = null;
        try
        {
            retorno = Banco.Selecionar("uf, estados", TABLE_NAME);
        }
        catch (ErrorHandle | ClassNotFoundException e)
        {
            throw new ErrorHandle("Falha ao listar estados " + e.getMessage());
        }
        return retorno;
    }
}
