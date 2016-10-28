package classes;

import banco.Banco;
import erro.ErrorHandle;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.Calendar;

/**
 * @version 1.0
 * @author Igor Martinelli
 */
public class Vendas
{
    private static int codVenda;
    private static int codVendedor;
    private static int codCliente;
    private static Date dataVenda;
    private static ResultSet retorno;
    private static final String TABLE_NAME = "vendas";

    /**
     * @return the codVenda
     */
    public int getCodVenda() {
        return codVenda;
    }

    /**
     * @param aCodVenda the codVenda to set
     */
    public void setCodVenda(int aCodVenda) {
        codVenda = aCodVenda;
    }

    /**
     * @return the codVendedor
     */
    public int getCodVendedor() {
        return codVendedor;
    }

    /**
     * @param aCodVendedor the codVendedor to set
     */
    public void setCodVendedor(int aCodVendedor) {
        codVendedor = aCodVendedor;
    }

    /**
     * @return the codCliente
     */
    public int getCodCliente() {
        return codCliente;
    }

    /**
     * @param aCodCliente the codCliente to set
     */
    public void setCodCliente(int aCodCliente) {
        codCliente = aCodCliente;
    }

    /**
     * @return the dataVenda
     */
    public Date getDataVenda() {
        return dataVenda;
    }

    /**
     * @param aDataVenda the dataVenda to set
     */
    public void setDataVenda(Date aDataVenda) {
        dataVenda = aDataVenda;
    }

    public ResultSet PesquisaPeloCod() throws ErrorHandle
    {
        retorno = null;
        try
        {
            retorno = Banco.Selecionar("v.codVenda, c.nome, ven.nome_vendedor, v.data_venda", TABLE_NAME + " v INNER JOIN vendedores ven ON v.cod_vendedor = ven.cod_vendedor INNER JOIN cliente c INNER JOIN c.codCliente = codCliente", "WHERE codVenda = " + codVenda);
        }
        catch (ErrorHandle | ClassNotFoundException e)
        {
            throw new ErrorHandle("Falha na pesquisa pelo Código");
        }
        return retorno;
    }
    
    public void Inserir() throws ErrorHandle
    {
        try
        {
            dataVenda = new Date(Calendar.getInstance().getTime().getTime());
            Banco.Inserir(TABLE_NAME, "cod_vendedor, codCliente, data_venda", codVendedor + "," + codCliente + "'" + dataVenda + "'");
        }
        catch (ErrorHandle | ClassNotFoundException e)
        {
            throw new ErrorHandle("Falha na inserção de nova Venda");
        }
    }
    
    public ResultSet InserirComRetorno() throws ErrorHandle
    {
        retorno = null;
        try
        {
            dataVenda = new Date(Calendar.getInstance().getTime().getTime());
            retorno = Banco.InserirComRetorno(TABLE_NAME, "cod_vendedor, codCliente, data_venda", codVendedor + "," + codCliente + "'" + dataVenda + "'");
        }
        catch (ErrorHandle | ClassNotFoundException e)
        {
            throw new ErrorHandle("Falha na inserção de nova Venda");
        }
        return retorno;
    }
    
    public void Alterar() throws ErrorHandle
    {
        try
        {
            Banco.Alterar(TABLE_NAME, "cod_vendedor = " + codVendedor + ", codcliente = " + codCliente, "codvenda = " + codVenda);
        }
        catch (ErrorHandle | ClassNotFoundException e)
        {
            throw new ErrorHandle("Falha na alteração de Venda");
        }
    }
    
    public void Apagar() throws ErrorHandle
    {
        try
        {
            Banco.Apagar(TABLE_NAME, "codVenda = " + codVenda);
        }
        catch (ErrorHandle | ClassNotFoundException e)
        {
            throw new ErrorHandle("Falha na exclusão de Venda");
        }
    }
    
    public ResultSet ConsultaPeloPeriodoVendas(String dataComeco, String dataFinal) throws ErrorHandle
    {
        retorno = null;
        try
        {
            // TODO
            retorno = Banco.Selecionar("v.codVenda, c.nome, ven.nome_vendedor, v.data_venda"
                    + "Nome do produto, Quantidade , Valor da Venda."
                    , TABLE_NAME + " v INNER JOIN vendedores ven ON v.cod_vendedor = ven.cod_vendedor INNER JOIN cliente c INNER JOIN c.codCliente = codCliente INNER JOIN tabProdutos p ON "
                    , "WHERE data_venda BETWEEN '" + dataComeco + "' AND '" + dataFinal + "'");
        }
        catch (ErrorHandle | ClassNotFoundException e)
        {
            throw new ErrorHandle("Falha ao gerar relatório pelo Período de vendas");
        }
        return retorno;
    }
}
