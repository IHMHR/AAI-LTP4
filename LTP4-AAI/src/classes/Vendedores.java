package classes;

import banco.Banco;
import erro.ErroHandle;
import java.sql.ResultSet;

/**
 * @version 1.0
 * @author Igor Martinelli
 */
public class Vendedores
{
    private static int codVendedor;
    private static String nomeVendedor;
    private static String dataCadVendedor;
    private static ResultSet retorno;

    /**
     * @return the codVendedor
     */
    public int getCodVendedor() {
        return codVendedor;
    }

    /**
     * @param codVendedor the codVendedor to set
     */
    public void setCodVendedor(int codVendedor) {
        Vendedores.codVendedor = codVendedor;
    }

    /**
     * @return the nomeVendedor
     */
    public String getNomeVendedor() {
        return nomeVendedor;
    }

    /**
     * @param nomeVendedor the nomeVendedor to set
     */
    public void setNomeVendedor(String nomeVendedor) {
        Vendedores.nomeVendedor = nomeVendedor;
    }

    /**
     * @return the dataCadVendedor
     */
    public String getDataCadVendedor() {
        return dataCadVendedor;
    }

    /**
     * @param dataCadVendedor the dataCadVendedor to set
     */
    public void setDataCadVendedor(String dataCadVendedor) {
        Vendedores.dataCadVendedor = dataCadVendedor;
    }
    
    public static ResultSet PesquisaPeloCod() throws ErroHandle
    {
        retorno = null;
        try
        {
            retorno = Banco.Selecionar("cod_vendedor, nome_vendedor, data_cad_vendedor", "Vendedores", "WHERE cod_vendedor = " + codVendedor);
        }
        catch (ErroHandle | ClassNotFoundException e)
        {
            throw new erro.ErroHandle("Falha na pesquisa pelo Código do Vendedor");
        }
        return retorno;
    }
    
    public static void PesquisaPeloNome()
    {
        try
        {
            retorno = Banco.Selecionar("cod_vendedor, nome_vendedor, data_cad_vendedor", "Vendedores", "WHERE cod_vendedor = '" + codVendedor + "' ORDER BY nome_vendedor");
        }
        catch (Exception e)
        {
        }
    }
    
    public static void aaa()
    {
        try
        {
            
        }
        catch (Exception e)
        {
        }
    }
    
    public static void aaaa()
    {
        try
        {
            
        }
        catch (Exception e)
        {
        }
    }
}
