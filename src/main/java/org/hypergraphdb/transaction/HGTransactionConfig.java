package org.hypergraphdb.transaction;

/**
 * 
 * <p>
 * Encapsulates configuration parameters for a single transaction. 
 * </p>
 * 
 * @author Borislav Iordanov
 *
 */
public class HGTransactionConfig
{
    public static final HGTransactionConfig DEFAULT = new HGTransactionConfig();
    public static final HGTransactionConfig NO_STORAGE = new HGTransactionConfig();
    public static final HGTransactionConfig READONLY = new HGTransactionConfig();
    
    static
    {
        NO_STORAGE.setNoStorage(true);
        READONLY.setReadonly(true);
    }
    
    private boolean noStorage = false;
    private boolean readonly = false;
    
    public boolean isNoStorage()
    {
        return noStorage;
    }

    public void setNoStorage(boolean noStorage)
    {
        this.noStorage = noStorage;
    }

    public boolean isReadonly()
    {
        return readonly;
    }

    public void setReadonly(boolean readonly)
    {
        this.readonly = readonly;
    }    
}