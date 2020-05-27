package ru.avers.informica.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.avers.informica.common.config.ReportInformica;
import ru.avers.informica.utils.FspeoVersion;

/**
 *
 * @author Dias
 */
public class FspeoFactory {
    private static final Logger s_logger = LoggerFactory.getLogger(FspeoFactory.class);

// TODO ���������� �����������
/*
    private final UserIdDto m_id_user;
    private final IInqryEducYearBegin m_educ_year_begin;
    private final CDBProviders m_cmsn_providers;
    private final IInteractionProvider m_interaction_provider;
    
    final private String m_contingent_endpoint;
    
    @Override
    public String toString() {
        return new StringBuilder(getClass().getName())
                        .append(" [id_user=").append(m_id_user)
                        .append(", educ_year_begin=").append(m_educ_year_begin)
                        .append(", contingent_endpoint=").append(m_contingent_endpoint)
                        .append("]")
                .toString();
    }        
    
    
    
//    public FspeoFactory(
//            UserIdDto p_id_user, 
//            IInqryEducYearBegin p_educ_year_begin, 
//            CDBProviders p_cmsn_providers, 
//            IInteractionProvider p_interaction_provider) {
//        this(p_id_user, p_educ_year_begin, p_cmsn_providers, p_interaction_provider, null);
//    }    
    
    public FspeoFactory(
            UserIdDto p_id_user, 
            IInqryEducYearBegin p_educ_year_begin, 
            CDBProviders p_cmsn_providers, 
            IInteractionProvider p_interaction_provider,
            String p_contingent_endpoint) {
        m_id_user = p_id_user;
        m_educ_year_begin = p_educ_year_begin;
        m_cmsn_providers = p_cmsn_providers;
        m_interaction_provider = p_interaction_provider;
        m_contingent_endpoint = p_contingent_endpoint;
    }    
    
    
    
    public FspeoReport createReport(FspeoVersion p_version, boolean p_is_threaded) throws FspeoException {
        return createReport(p_version, null, IDataAdapter.DataMode.Detail, p_is_threaded);
    }
    
    public FspeoReport createReport(
                        FspeoVersion p_version, 
                        Integer p_id_uch,
                        IDataAdapter.DataMode p_data_mode, 
                        boolean p_is_threaded) throws FspeoException {
        try {
            s_logger.debug("Build begin " + new Date().toString());
            IPushDataRequest x_push_data_request;
            if(FspeoVersion.Three.equals(p_version)) {
                x_push_data_request = new org.avers.fspeo.v3.PushDataRequest(TypeSchemaVersion.tThreeDotO);
            } else 
            if(FspeoVersion.Two.equals(p_version)) {
                x_push_data_request = new org.avers.fspeo.v2.PushDataRequest(TypeSchemaVersion.tTwoDotO);
            } else
            if(FspeoVersion.Four.equals(p_version)) {
                x_push_data_request = new org.avers.fspeo.v3.PushDataRequest(TypeSchemaVersion.tThreeDotO);
            } else {
                throw new FspeoException("Unknown version " + String.valueOf(p_version));
            }
            Config x_config = retrieveInformicaConfig(p_version);
            if (x_config == null) 
                throw new FspeoException(
                        String.format("�� ������� ��������� ��������� ��� �������������� (������: %s)",
                                                                                            String.valueOf(p_version)));
            String x_message = createDataAdapter(x_config, p_is_threaded)
                                                .fill(x_push_data_request, p_data_mode, p_id_uch);
            //  todo ����������� ����������� �������� ������ ��� �������������
            //  create additional report with counters for ver4
            CDataAdapterVer4 x_da_v4 = new CDataAdapterVer4(x_config, m_educ_year_begin, m_cmsn_providers);
            
            org.avers.fspeo.v3.PushDataRequest x_pdr4ver4 = 
                                new org.avers.fspeo.v3.PushDataRequest(TypeSchemaVersion.tThreeDotO) {
                                    
                @Override
                public IReport createReport() {
                    Report x_report = new Report() {
                        @Override
                        public void fillData(UchInf p_uch_inf) {
                            Municipality x_municipality = new Municipality();
                            setMunicipality(x_municipality);
                            Organization x_org = new Organization();
                            x_org.setCode(Organization.constructCodeByUchCode(p_uch_inf.getCode()));
                            setOrganization(x_org);
                        }
                    };
                    getReports().add(x_report);
                    return x_report;
                }
            };
            
            x_da_v4.fill(x_pdr4ver4, p_data_mode, p_id_uch);
            //
            s_logger.debug("Build end " + new Date().toString());
            
//            FspeoReport x_rv = new FspeoReport(x_push_data_request, x_message);
            FspeoReport x_rv = new CFspeoReport(
                                        x_push_data_request, 
                                        x_pdr4ver4, 
                                        m_contingent_endpoint, 
                                        m_cmsn_providers.getUchProvider(), 
                                        x_message);
            
            return x_rv;
        }
        catch(BaseInquirerDbBLException ex) {
            throw new FspeoException("������ ���������� ���������", ex);
        }
        catch(CBaseInqryDbBLException ex) {
            throw new FspeoException("������ ���������� ��������", ex);
        }
    }
    
    private IDataAdapter createDataAdapter(
                            Config p_config, boolean p_is_threaded) throws BaseInquirerDbBLException, FspeoException {        
        if (p_is_threaded)
            return new ThreadedDataAdapter(m_id_user, p_config, m_educ_year_begin, m_cmsn_providers);
        else
            return new SimpleDataAdapter(m_id_user, p_config, m_educ_year_begin, m_cmsn_providers);
    }    
    
    public Config retrieveInformicaConfig(FspeoVersion p_version) throws BaseInquirerDbBLException, FspeoException {
        String x_config_name;
        if(FspeoVersion.Three.equals(p_version)) {
            x_config_name = CDict87SysInteraction.s_fspeo_v3_interaction_name;
        } else if(FspeoVersion.Two.equals(p_version)) {
            x_config_name = CDict87SysInteraction.s_informica_interaction_name;
        } else if(FspeoVersion.Four.equals(p_version)) {
            x_config_name = CDict87SysInteraction.s_fspeo_v4_interaction_name;
        } else {
            throw new FspeoException("Unknown version " + String.valueOf(p_version));
        }
        String x_cfg = m_interaction_provider.getCfg(x_config_name);
        return Config.reestablish(x_cfg);
    }

    public Object getConfigReportBean(FspeoVersion p_version) throws FspeoException {
            Date x_rep_date = Calendar.getInstance().getTime();
            Date x_curr_educ_date =
                    DateUtil.getCurrEducDate(x_rep_date, m_educ_year_begin.getMonth(), m_educ_year_begin.getDay());
            Config x_config;
            try {
                x_config = retrieveInformicaConfig(p_version);
            }catch(BaseInquirerDbBLException ex) {
                s_logger.debug("getInformicaConfigReportBean", ex);
                throw new FspeoException(ex);
            }
            Descriptor x_descr = new Descriptor(x_rep_date, x_curr_educ_date, m_cmsn_providers);
            return x_descr.build(x_config);
    }
    
*/

    
    static public FspeoVersion transformVersion(ReportInformica.Version p_val) {
        //  ������ 5 �� ���������
        FspeoVersion x_rv = FspeoVersion.Five;
//        if(ReportInformica.Version.Six.equals(p_val)) x_rv = FspeoVersion.Six;
        return x_rv;
    }
}
