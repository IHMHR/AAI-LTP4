package classes;

import banco.Banco;
import erro.ErrorHandle;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
    
    public ResultSet PesquisaPeloCod() throws ErrorHandle, IOException
    {
        retorno = null;
        try
        {
            retorno = Banco.Selecionar("cod_vendedor, nome_vendedor, data_cad_vendedor", TABLE_NAME, "WHERE cod_vendedor = " + codVendedor);
        }
        catch (ErrorHandle | ClassNotFoundException e)
        {
            FileWriter arq = new FileWriter("erroLog.txt", true);
            arq.write(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()) + " - " + e + " (PesquisaPeloCod)(Vendedores)\n");
            arq.flush();
            arq.close();
            throw new ErrorHandle("Falha na pesquisa pelo Código do Vendedor");
        }
        return retorno;
    }
    
    public ResultSet PesquisaPeloNome() throws ErrorHandle, IOException
    {
        retorno = null;
        try
        {
            retorno = Banco.Selecionar("cod_vendedor, nome_vendedor, data_cad_vendedor", TABLE_NAME, "WHERE nome_vendedor LIKE '" + nomeVendedor + "%' ORDER BY nome_vendedor");
        }
        catch (ErrorHandle | ClassNotFoundException e)
        {
            FileWriter arq = new FileWriter("erroLog.txt", true);
            arq.write(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()) + " - " + e + " (PesquisaPeloNome)(Vendedores)\n");
            arq.flush();
            arq.close();
            throw new ErrorHandle("Falha na pesquisa pelo Nome do Vendedor");
        }
        return retorno;
    }
    
    public void Inserir() throws ErrorHandle, IOException
    {
        try
        {
            dataCadVendedor = new Date(Calendar.getInstance().getTime().getTime());
            Banco.Inserir(TABLE_NAME, "nome_vendedor, data_cad_vendedor", "'" + nomeVendedor + "', '" + dataCadVendedor + "'");
        }
        catch (ErrorHandle | ClassNotFoundException e)
        {
            FileWriter arq = new FileWriter("erroLog.txt", true);
            arq.write(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()) + " - " + e + " (Inserir)(Vendedores)\n");
            arq.flush();
            arq.close();
            throw new ErrorHandle("Falha na inserção de novo Vendedor");
        }
    }
    
    public void Alterar() throws ErrorHandle, IOException
    {
        try
        {
            Banco.Alterar(TABLE_NAME, "nome_vendedor = '" + nomeVendedor + "'", "cod_vendedor = " + codVendedor);
        }
        catch (ErrorHandle | ClassNotFoundException e)
        {
            FileWriter arq = new FileWriter("erroLog.txt", true);
            arq.write(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()) + " - " + e + " (Alterar)(Vendedores)\n");
            arq.flush();
            arq.close();
            throw new ErrorHandle("Falha na alteração de Vendedor");
        }
    }
    
    public void Apagar() throws ErrorHandle, IOException
    {
        retorno = null;
        try
        {
            retorno = Banco.Selecionar("1", "vendas", "WHERE Cod_Vendedor = " + codVendedor);
            if (retorno.next())
            {
                throw new ErrorHandle("Não pode ser realizar a exclusão do vendedor devido ao fato do mesmo já ter realizado vendas.");
            }
            
            Banco.Apagar(TABLE_NAME, "cod_vendedor = " + codVendedor);
        }
        catch (ErrorHandle | ClassNotFoundException | SQLException e)
        {
            FileWriter arq = new FileWriter("erroLog.txt", true);
            arq.write(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()) + " - " + e + " (Apagar)(Vendedores)\n");
            arq.flush();
            arq.close();
            
            if(e.getMessage().startsWith("Não pode ser realizar a exclusão"))
            {
                throw new ErrorHandle(e.getMessage());
            }
            else
            {
                throw new ErrorHandle("Falha na exclusao de Vendedor");
            }
        }
        finally
        {
            retorno = null;
        }
    }
    
    public static ResultSet listaVendedores() throws ErrorHandle, IOException
    {
        retorno = null;
        try
        {
            retorno = Banco.Selecionar("cod_vendedor, nome_vendedor, data_cad_vendedor", TABLE_NAME, "ORDER BY nome_vendedor");
        }
        catch (ErrorHandle | ClassNotFoundException e)
        {
            FileWriter arq = new FileWriter("erroLog.txt", true);
            arq.write(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()) + " - " + e + " (listaVendedores)(Vendedores)\n");
            arq.flush();
            arq.close();
            throw new ErrorHandle("Falha na pesquisa pelo Nome do Vendedor");
        }
        return retorno;
    }
}