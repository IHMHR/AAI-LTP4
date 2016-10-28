package classes;

import banco.Banco;
import erro.ErrorHandle;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

/**
 * @version 1.0
 * @author Igor Martinelli
 */
public class Vendedores
{
    private static int codVendedor;
    private static String nomeVendedor;
    private static Date dataCadVendedor;
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
    public Date getDataCadVendedor() {
        return dataCadVendedor;
    }

    /**
     * @param dataCadVendedor the dataCadVendedor to set
     */
    public void setDataCadVendedor(Date dataCadVendedor) {
        Vendedores.dataCadVendedor = dataCadVendedor;
    }
    
    public ResultSet PesquisaPeloCod() throws ErrorHandle
    {
        retorno = null;
        try
        {
            retorno = Banco.Selecionar("cod_vendedor, nome_vendedor, data_cad_vendedor", TABLE_NAME, "WHERE cod_vendedor = " + codVendedor);
        }
        catch (ErrorHandle | ClassNotFoundException e)
        {
            throw new ErrorHandle("Falha na pesquisa pelo Código do Vendedor");
        }
        return retorno;
    }
    
    public ResultSet PesquisaPeloNome() throws ErrorHandle
    {
        retorno = null;
        try
        {
            retorno = Banco.Selecionar("cod_vendedor, nome_vendedor, data_cad_vendedor", TABLE_NAME, "WHERE cod_vendedor = '" + codVendedor + "' ORDER BY nome_vendedor");
        }
        catch (ErrorHandle | ClassNotFoundException e)
        {
            throw new ErrorHandle("Falha na pesquisa pelo Nome do Vendedor");
        }
        return retorno;
    }
    
    public void Inserir() throws ErrorHandle
    {
        try
        {
            dataCadVendedor = new Date(Calendar.getInstance().getTime().getTime());
            Banco.Inserir(TABLE_NAME, "nome_vendedor, data_cad_vendedor", "'" + nomeVendedor + "', '" + dataCadVendedor + "'");
        }
        catch (ErrorHandle | ClassNotFoundException e)
        {
            throw new ErrorHandle("Falha na inserção de novo Vendedor");
        }
    }
    
    public void Alterar() throws ErrorHandle
    {
        try
        {
            Banco.Alterar(TABLE_NAME, "nome = '" + nomeVendedor + "'", "cod_vendedor = " + codVendedor);
        }
        catch (ErrorHandle | ClassNotFoundException e)
        {
            throw new ErrorHandle("Falha na alteração de Vendedor");
        }
    }
    
    public void Apagar() throws ErrorHandle
    {
        retorno = null;
        try
        {
            retorno = Banco.Selecionar("COUNT(1)", "vendas", "WHERE Cod_Vendedor = " + codVendedor);
            if (retorno.next())
            {
                throw new ErrorHandle("Não pode ser realizar a exclusão do vendedor devido ao fato do mesmo já ter realizado vendas.");
            }
            
            Banco.Apagar(TABLE_NAME, "cod_vendedor = " + codVendedor);
        }
        catch (ErrorHandle | ClassNotFoundException | SQLException e)
        {
            throw new ErrorHandle("Falha na exclusao de Vendedor");
        }
        finally
        {
            retorno = null;
        }
    }
    
    public static ResultSet listaVendedores() throws ErrorHandle
    {
        retorno = null;
        try
        {
            retorno = Banco.Selecionar("cod_vendedor, nome_vendedor, data_cad_vendedor", TABLE_NAME, "ORDER BY nome_vendedor");
        }
        catch (ErrorHandle | ClassNotFoundException e)
        {
            throw new ErrorHandle("Falha na pesquisa pelo Nome do Vendedor");
        }
        return retorno;
    }
}