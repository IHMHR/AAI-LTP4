package classes;

import banco.Banco;
import erro.ErroHandle;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    private static final String TABLE_NAME = "Vendedores";

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
            retorno = Banco.Selecionar("cod_vendedor, nome_vendedor, data_cad_vendedor", TABLE_NAME, "WHERE cod_vendedor = " + codVendedor);
        }
        catch (ErroHandle | ClassNotFoundException e)
        {
            throw new erro.ErroHandle("Falha na pesquisa pelo Código do Vendedor");
        }
        return retorno;
    }
    
    public static ResultSet PesquisaPeloNome() throws ErroHandle
    {
        retorno = null;
        try
        {
            retorno = Banco.Selecionar("cod_vendedor, nome_vendedor, data_cad_vendedor", TABLE_NAME, "WHERE cod_vendedor = '" + codVendedor + "' ORDER BY nome_vendedor");
        }
        catch (ErroHandle | ClassNotFoundException e)
        {
            throw new erro.ErroHandle("Falha na pesquisa pelo Nome do Vendedor");
        }
        return retorno;
    }
    
    public static void Inserir() throws ErroHandle
    {
        try
        {
            Banco.Inserir(TABLE_NAME, "nome", "'" + nomeVendedor + "'");
        }
        catch (ErroHandle | ClassNotFoundException e)
        {
            throw new erro.ErroHandle("Falha na inserção de novo Vendedor");
        }
    }
    
    public static void Alterar() throws ErroHandle
    {
        try
        {
            Banco.Alterar(TABLE_NAME, "nome = '" + nomeVendedor + "'", "cod_vendedor = " + codVendedor);
        }
        catch (ErroHandle | ClassNotFoundException e)
        {
            throw new erro.ErroHandle("Falha na alteração de Vendedor");
        }
    }
    
    public static void Apagar() throws ErroHandle
    {
        retorno = null;
        try
        {
            retorno = Banco.Selecionar("COUNT(1)", "vendas", "WHERE Cod_Vendedor = " + codVendedor);
            if (retorno.next())
            {
                throw new erro.ErroHandle("Não pode ser realizar a exclusão do vendedor devido ao fato do mesmo já ter realizado vendas.");
            }
            
            Banco.Apagar(TABLE_NAME, "cod_vendedor = " + codVendedor);
        }
        catch (ErroHandle | ClassNotFoundException | SQLException e)
        {
            throw new erro.ErroHandle("Falha na exclusao de Vendedor");
        }
        finally
        {
            retorno = null;
        }
    }
}