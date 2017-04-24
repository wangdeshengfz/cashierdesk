package com.bosssoft.itfinance.epay.v2.cashierdesk.common.exception;

import com.bosssoft.itfinance.epay.v2.teamcommon.BaseException;
import com.bosssoft.itfinance.epay.v2.teamcommon.EnumVal;

public class CashierdeskException extends BaseException{
    public CashierdeskException(EnumVal enumVal)
    {
        super(enumVal);
    }
    
    public CashierdeskException(String errCode, String errMsg)
    {
        super(errCode, errMsg);
    }
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
}
