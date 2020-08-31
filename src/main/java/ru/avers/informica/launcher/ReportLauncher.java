package ru.avers.informica.launcher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.stereotype.Component;
import ru.avers.informica.common.config.CProfile;
import ru.avers.informica.infcfg.IConst;
import ru.avers.informica.report.ReportGenerator;
import ru.avers.informica.report.ReportSetting;
import ru.avers.informica.report.xml.PushDataRequest;
import ru.avers.informica.utils.CHelper;
import ru.avers.informica.utils.xml.CUtil;
import ru.edu.eo.ResponsePushData;
import ru.edu.eo.client.Dispatcher;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.xml.bind.JAXBContext;
import javax.xml.namespace.QName;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReportLauncher {
    private Marker markerEmail = MarkerFactory.getMarker("EMAIL_LOG");
    private final ReportGenerator reportGenerator;
    private final ReportSetting reportSetting;

    private String debug;

    public void buildReport(String launchSite, boolean checkingLaunch) {
        logStart(launchSite);

        if (checkingLaunch && !reportSetting.getCProfile().getReports()
                .getReportInformica().isAutoUploadEnabled()) {
            log.debug("ReportLauncher: Автовыгрузка отчета Информики отключена");
            return;
        }
        if (!reportSetting.getCProfile().getReports().getReportInformica().isEmailLog()) {
            log.debug("ReportLauncher: Отправка лога на informika@iicavers.ru отключена");
            markerEmail = null;
        }
        try {
            PushDataRequest report = reportGenerator.generateReport(reportSetting.getCProfile());

            saveSendReport(report);

            debug = "1";
        } catch (Exception ex) {
            String x_str = "Ошибка при построении отчета";
            log.error(x_str, ex);
            log.error(markerEmail, "{}. 1. Ошибка построения отчета",
                    reportSetting.getCProfile().getReports()
                            .getReportInformica().getStateName());
        }

        debug = "2";
    }

    private void saveSendReport(PushDataRequest report) {
        // Формирование XML-файла (InformicaDaemon)
        byte[] reportVer5Bytes = getBytesReport(report);
        if (reportVer5Bytes != null) {
            String prefix = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSSZ").format(new Date());
            try {
                File tempFile = File.createTempFile(prefix + "-inf-v5-", ".xml",
                        new File(reportSetting.getTempPath() + "/"));
                final File tempFileSend = File.createTempFile(prefix + "-inf-v5-send-", ".xml",
                        new File(reportSetting.getTempPath() + "/"));
                log.debug("file: {}, sendfile: {}",
                        tempFile.getAbsolutePath(), tempFileSend.getAbsolutePath());
                // отчет
                FileOutputStream fos = new FileOutputStream(tempFile);
                fos.write(reportVer5Bytes);
                fos.close();

                // отчет для отправки
                // Пользовательский обработчик
                List<Handler> handlerChain = new ArrayList<Handler>();
                handlerChain.add(new SOAPHandler<SOAPMessageContext>() {

                    @Override
                    public boolean handleMessage(SOAPMessageContext soapMessageContext) {
                        exec(soapMessageContext);
                        return false;
                    }

                    @Override
                    public boolean handleFault(SOAPMessageContext soapMessageContext) {
                        exec(soapMessageContext);
                        return false;
                    }

                    @Override
                    public void close(MessageContext messageContext) {

                    }

                    @Override
                    public Set<QName> getHeaders() {
                        return null;
                    }

                    private void exec(SOAPMessageContext soapMessageContext) {
                        Boolean isOutBound = (Boolean) soapMessageContext
                                .get(SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY);
                        if (isOutBound) {
                            try {
                                FileOutputStream fos = new FileOutputStream(tempFileSend);
                                soapMessageContext.getMessage().writeTo(fos);
                                fos.close();
                            } catch (Exception ex) {
                                log.error("Ошибка сохранения файла отчета для передачи.", ex);
                            }
                        }
                    }
                });
                // передача результата через web-client

                ResponsePushData responsePushData = ru.edu.eo.client.Dispatcher.pushDataEx(
                        ru.edu.eo.client.Dispatcher.getService(null,
                                handlerChain, null, null),
                        reportVer5Bytes,
                        "login",
                        "password",
                        IConst.s_schema_ver_2_0,
                        Dispatcher.ProcessMode.pushZipData,
                        Dispatcher.PushDataType.ToZipAndBase64);


            } catch (Exception ex) {
                log.error("Ошибка сохранения отчета в файл.", ex);
            }

        }
    }

    private void logStart(String launchSite) {
        log.info("Место запуска отчета - " + launchSite);
        Calendar currTime = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm");
        log.debug("ReportLauncher: Создание отчета информики, текущее время:"
                + dateFormat.format(currTime.getTime()));
    }

    private byte[] getBytesReport(PushDataRequest report) {
        byte[] rezult;
        if (report != null) {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                CUtil.toOutputStream(baos, report,
                        JAXBContext.newInstance(PushDataRequest.class),
                        true, null,
                        "http://eo.edu.ru/v4 http://eo.edu.ru/v4/schema.xsd");
                baos.close();
                rezult = baos.toByteArray();
            } catch (Exception ex) {
                rezult = null;
                log.error("Не удалось сформировать байтовый массив для отчета.", ex);
            }
        } else {
            rezult = null;
        }
        return rezult;
    }
}
