package com.bosssoft.itfinance.epay.v2.cashierdesk.common.framework.core;

import java.net.InetAddress;
import java.net.UnknownHostException;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class IpConvert extends ClassicConverter
{

    @Override
    public String convert(ILoggingEvent event)
    {
        try
        {
            InetAddress addr = InetAddress.getLocalHost();
            String ip = addr.getHostAddress().toString();
            String hostName = addr.getHostName();
            return hostName+"_"+ip;
        } catch (UnknownHostException e)
        {
            return "can't find ip.";
        }
    }

}