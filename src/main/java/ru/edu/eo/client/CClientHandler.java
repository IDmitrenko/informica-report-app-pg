/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.edu.eo.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.Set;

/**
 * @author alexanderm
 */
public class CClientHandler implements SOAPHandler<SOAPMessageContext> {
    static final private Logger s_logger = LoggerFactory.getLogger(CClientHandler.class);

    @Override
    public boolean handleMessage(SOAPMessageContext p_ctx) {
        Boolean x_is_outbound = (Boolean) p_ctx.get(SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY);

        String x_msg = "\r\n" + (x_is_outbound ? "Outbound" : "Inbound") + " message:\r\n{}";
        s_logger.debug(x_msg, SoapUtil.toInfoString(p_ctx.getMessage()));
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext p_ctx) {
        Boolean x_is_outbound = (Boolean) p_ctx.get(SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY);

        String x_msg = "\r\n" + (x_is_outbound ? "Outbound" : "Inbound") + " message:\r\n{}";
        s_logger.debug(x_msg, SoapUtil.toInfoString(p_ctx.getMessage()));

        return true;
    }

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public void close(MessageContext p_mc) {
    }
}
