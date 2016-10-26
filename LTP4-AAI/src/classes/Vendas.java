package classes;

import java.sql.Date;
import java.sql.ResultSet;

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
    public static int getCodVenda() {
        return codVenda;
    }

    /**
     * @param aCodVenda the codVenda to set
     */
    public static void setCodVenda(int aCodVenda) {
        codVenda = aCodVenda;
    }

    /**
     * @return the codVendedor
     */
    public static int getCodVendedor() {
        return codVendedor;
    }

    /**
     * @param aCodVendedor the codVendedor to set
     */
    public static void setCodVendedor(int aCodVendedor) {
        codVendedor = aCodVendedor;
    }

    /**
     * @return the codCliente
     */
    public static int getCodCliente() {
        return codCliente;
    }

    /**
     * @param aCodCliente the codCliente to set
     */
    public static void setCodCliente(int aCodCliente) {
        codCliente = aCodCliente;
    }

    /**
     * @return the dataVenda
     */
    public static Date getDataVenda() {
        return dataVenda;
    }

    /**
     * @param aDataVenda the dataVenda to set
     */
    public static void setDataVenda(Date aDataVenda) {
        dataVenda = aDataVenda;
    }

}
