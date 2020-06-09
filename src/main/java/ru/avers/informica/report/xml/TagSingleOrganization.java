package ru.avers.informica.report.xml;

import javax.xml.bind.annotation.*;
import java.math.BigInteger;


/**
 * Информация о ДОО
 * 
 * <p>Java class for tag_single_organization complex type.
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tag_single_organization", propOrder = {
    "buildings",
    "ind_1",
    "ind_1_1",
    "ind_2_1",
    "ind_3_1",
    "ind_4",
    "ind_4_1",
    "ind_4_2",
    "ind_5",
    "ind_6",
    "ind_7",
    "ind_7_1",
    "ind_7_2",
    "ind_7_3",
    "ind_7_4",
    "ind_7_5",
    "ind_7_6",
    "ind_7_7",
    "ind_8",
    "ind_8_1",
    "ind_8_2",
    "ind_8_3",
    "ind_9",
    "ind_9_1",
    "ind_10",
    "ind_10_1",
    "ind_11",
    "ind_12",
    "ind_12_1",
    "ind_13",
    "ind_13_1",
    "ind_14",
    "ind_15",
    "ind_16",
    "ind_17",
    "ind_17_1",
    "ind_18",
    "ind_18_1",
    "ind_18_2",
    "ind_18_3",
    "ind_18_4",
    "ind_18_5",
    "ind_19",
    "ind_19_1",
    "ind_19_2",
    "ind_19_3",
    "ind_20",
    "ind_20_1",
    "ind_20_2",
    "ind_20_3",
    "ind_20_4",
    "ind_20_5",
    "ind_20_6",
    "ind_20_7",
    "ind_20_8",
    "ind_21",
    "ind_22",
    "ind_22_1",
    "ind_22_1_1",
    "ind_22_1_2",
    "ind_22_2",
    "ind_22_3",
    "ind_22_3_1",
    "ind_22_3_2",
    "ind_22_4",
    "ind_22_5",
    "ind_22_5_1",
    "ind_22_5_2",
    "ind_22_6",
    "ind_22_7",
    "ind_22_8",
    "ind_22_8_1",
    "ind_22_8_2",
    "ind_23",
    "ind_24",
    "ind_25",
    "ind_26",
    "ind_27",
    "ind_28",
    "ind_29",
    "ind_29_1",
    "ind_29_2",
    "ind_29_3",
    "ind_30",
    "ind_30_1",
    "ind_30_2",
    "ind_31",
    "ind_31_1",
    "ind_31_2",
    "ind_31_3",
    "ind_31_4",
    "ind_32",
    "ind_32_1",
    "ind_32_2",
    "ind_33",
    "advisory_Centr",
    "early_Assistant",
    "specialists"
})
public class TagSingleOrganization {

    @XmlElement(required = true)
    protected TagBuildings buildings;
    @XmlElement(required = true)
    protected TagAge1 ind_1;
    @XmlElement(required = true)
    protected TagAge16 ind_1_1;
    @XmlElement(required = true)
    protected TagAge16 ind_2_1;
    @XmlElement(required = true)
    protected TagAge16 ind_3_1;
    @XmlElement(required = true)
    protected TagAge16 ind_4;
    @XmlElement(required = true)
    protected TagAge16 ind_4_1;
    @XmlElement(required = true)
    protected TagAge16 ind_4_2;
    @XmlElement(required = true)
    protected TagAge16 ind_5;
    @XmlElement(required = true)
    protected TagAge16 ind_6;
    @XmlElement(required = true)
    protected TagAge16 ind_7;
    @XmlElement(required = true)
    protected TagAge16 ind_7_1;
    @XmlElement(required = true)
    protected TagAge16 ind_7_2;
    @XmlElement(required = true)
    protected TagAge16 ind_7_3;
    @XmlElement(required = true)
    protected TagAge16 ind_7_4;
    @XmlElement(required = true)
    protected TagAge16 ind_7_5;
    @XmlElement(required = true)
    protected TagAge16 ind_7_6;
    @XmlElement(required = true)
    protected TagAge16 ind_7_7;
    @XmlElement(required = true)
    protected TagAge8Special ind_8;
    @XmlElement(required = true)
    protected TagAge8Special ind_8_1;
    @XmlElement(required = true)
    protected TagAge8Special ind_8_2;
    @XmlElement(required = true)
    protected TagAge8Special ind_8_3;
    @XmlElement(required = true)
    protected TagAge16 ind_9;
    @XmlElement(required = true)
    protected TagAge16 ind_9_1;
    @XmlElement(required = true)
    protected TagAge1 ind_10;
    @XmlElement(required = true)
    protected TagAge16 ind_10_1;
    @XmlElement(required = true)
    protected TagAge1 ind_11;
    @XmlElement(required = true)
    protected TagAge16 ind_12;
    @XmlElement(required = true)
    protected TagAge16 ind_12_1;
    @XmlElement(required = true)
    protected TagAge16 ind_13;
    @XmlElement(required = true)
    protected TagAge16 ind_13_1;
    @XmlElement(required = true)
    protected TagAge16 ind_14;
    @XmlElement(required = true)
    protected TagAge16 ind_15;
    @XmlElement(required = true)
    protected TagAge16 ind_16;
    @XmlElement(required = true)
    protected TagAge16 ind_17;
    @XmlElement(required = true)
    protected TagAge16 ind_17_1;
    @XmlElement(required = true)
    protected TagAge16 ind_18;
    @XmlElement(required = true)
    protected TagAge16 ind_18_1;
    @XmlElement(required = true)
    protected TagAge16 ind_18_2;
    @XmlElement(required = true)
    protected TagAge16 ind_18_3;
    @XmlElement(required = true)
    protected TagAge16 ind_18_4;
    @XmlElement(required = true)
    protected TagAge16 ind_18_5;
    @XmlElement(required = true)
    protected TagAge16 ind_19;
    @XmlElement(required = true)
    protected TagAge16 ind_19_1;
    @XmlElement(required = true)
    protected TagAge16 ind_19_2;
    @XmlElement(required = true)
    protected TagAge16 ind_19_3;
    @XmlElement(required = true)
    protected TagAge16 ind_20;
    @XmlElement(required = true)
    protected TagAge16 ind_20_1;
    @XmlElement(required = true)
    protected TagAge16 ind_20_2;
    @XmlElement(required = true)
    protected TagAge16 ind_20_3;
    @XmlElement(required = true)
    protected TagAge16 ind_20_4;
    @XmlElement(required = true)
    protected TagAge16 ind_20_5;
    @XmlElement(required = true)
    protected TagAge16 ind_20_6;
    @XmlElement(required = true)
    protected TagAge16 ind_20_7;
    @XmlElement(required = true)
    protected TagAge16 ind_20_8;
    @XmlElement(required = true)
    protected TagAge16 ind_21;
    @XmlElement(required = true)
    protected TagAge16 ind_22;
    @XmlElement(required = true)
    protected TagAge16 ind_22_1;
    @XmlElement(required = true)
    protected TagAge16 ind_22_1_1;
    @XmlElement(required = true)
    protected TagAge16 ind_22_1_2;
    @XmlElement(required = true)
    protected TagAge16 ind_22_2;
    @XmlElement(required = true)
    protected TagAge16 ind_22_3;
    @XmlElement(required = true)
    protected TagAge16 ind_22_3_1;
    @XmlElement(required = true)
    protected TagAge16 ind_22_3_2;
    @XmlElement(required = true)
    protected TagAge16 ind_22_4;
    @XmlElement(required = true)
    protected TagAge16 ind_22_5;
    @XmlElement(required = true)
    protected TagAge16 ind_22_5_1;
    @XmlElement(required = true)
    protected TagAge16 ind_22_5_2;
    @XmlElement(required = true)
    protected TagAge16 ind_22_6;
    @XmlElement(required = true)
    protected TagAge16 ind_22_7;
    @XmlElement(required = true)
    protected TagAge16 ind_22_8;
    @XmlElement(required = true)
    protected TagAge16 ind_22_8_1;
    @XmlElement(required = true)
    protected TagAge16 ind_22_8_2;
    @XmlElement(required = true)
    protected TagAge16 ind_23;
    @XmlElement(required = true)
    protected TagAge16 ind_24;
    @XmlElement(required = true)
    protected TagAge16 ind_25;
    @XmlElement(required = true)
    protected TagAge16 ind_26;
    @XmlElement(required = true)
    protected TagAge16 ind_27;
    @XmlElement(required = true)
    protected TagAge16 ind_28;
    @XmlElement(required = true)
    protected TagAge1 ind_29;
    @XmlElement(required = true)
    protected TagAge16 ind_29_1;
    @XmlElement(required = true)
    protected TagAge16 ind_29_2;
    @XmlElement(required = true)
    protected TagAge16 ind_29_3;
    @XmlElement(required = true)
    protected TagAge16 ind_30;
    @XmlElement(required = true)
    protected TagAge16 ind_30_1;
    @XmlElement(required = true)
    protected TagAge16 ind_30_2;
    @XmlElement(required = true)
    protected TagAge8 ind_31;
    @XmlElement(required = true)
    protected TagAge8 ind_31_1;
    @XmlElement(required = true)
    protected TagAge8 ind_31_2;
    @XmlElement(required = true)
    protected TagAge8 ind_31_3;
    @XmlElement(required = true)
    protected TagAge8 ind_31_4;
    @XmlElement(required = true)
    protected TagAge1 ind_32;
    @XmlElement(required = true)
    protected TagAge16 ind_32_1;
    @XmlElement(required = true)
    protected TagAge16 ind_32_2;
    @XmlElement(required = true)
    protected TagAge1 ind_33;
    @XmlElement(name = "advisory_centr", required = true)
    protected AdvisoryCentr advisory_Centr;
    @XmlElement(name = "early_assistant", required = true)
    protected EarlyAssistant early_Assistant;
    @XmlElement(required = true)
    protected Specialists specialists;
    @XmlAttribute(name = "code", required = true)
    protected String code;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "type", required = true)
    protected String type;
    @XmlAttribute(name = "status", required = true)
    protected String status;
    @XmlAttribute(name = "structure", required = true)
    protected String structure;
    @XmlAttribute(name = "license", required = true)
    protected String license;
    @XmlAttribute(name = "type_area", required = true)
    protected String type_Area;
    @XmlAttribute(name = "director", required = true)
    protected String director;
    @XmlAttribute(name = "worktime", required = true)
    protected String worktime;
    @XmlAttribute(name = "meal_serving_type", required = true)
    protected String meal_Serving_Type;
    @XmlAttribute(name = "fias_org_guid", required = true)
    protected String fias_Org_Guid;
    @XmlAttribute(name = "org_address", required = true)
    protected String org_Address;
    @XmlAttribute(name = "website", required = true)
    protected String website;
    @XmlAttribute(name = "email", required = true)
    protected String email;
    @XmlAttribute(name = "phone", required = true)
    protected String phone;
    @XmlAttribute(name = "additional_education", required = true)
    protected String additional_Education;
    @XmlAttribute(name = "feature", required = true)
    protected String feature;
    @XmlAttribute(name = "num_filial", required = true)
    protected BigInteger num_Filial;
    @XmlAttribute(name = "num_building", required = true)
    protected BigInteger num_Building;
    @XmlAttribute(name = "num_group", required = true)
    protected BigInteger num_Group;
    @XmlAttribute(name = "partner_doo", required = true)
    protected String partner_Doo;
    @XmlAttribute(name = "commet_status")
    protected String commet_Status;
    @XmlAttribute(name = "lekoteka")
    protected String lekoteka;
    @XmlAttribute(name = "centre_game")
    protected String centre_Game;
    @XmlAttribute(name = "passport")
    protected String passport;

    public TagBuildings getBuildings() {
        return buildings;
    }

    public void setBuildings(TagBuildings value) {
        this.buildings = value;
    }

    public TagAge1 getInd_1() {
        return ind_1;
    }

    public void setInd_1(TagAge1 value) {
        this.ind_1 = value;
    }

    public TagAge16 getInd_1_1() {
        return ind_1_1;
    }

    public void setInd_1_1(TagAge16 value) {
        this.ind_1_1 = value;
    }

    public TagAge16 getInd_2_1() {
        return ind_2_1;
    }

    public void setInd_2_1(TagAge16 value) {
        this.ind_2_1 = value;
    }

    public TagAge16 getInd_3_1() {
        return ind_3_1;
    }

    public void setInd_3_1(TagAge16 value) {
        this.ind_3_1 = value;
    }

    public TagAge16 getInd_4() {
        return ind_4;
    }

    public void setInd_4(TagAge16 value) {
        this.ind_4 = value;
    }

    public TagAge16 getInd_4_1() {
        return ind_4_1;
    }

    public void setInd_4_1(TagAge16 value) {
        this.ind_4_1 = value;
    }

    public TagAge16 getInd_4_2() {
        return ind_4_2;
    }

    public void setInd_4_2(TagAge16 value) {
        this.ind_4_2 = value;
    }

    public TagAge16 getInd_5() {
        return ind_5;
    }

    public void setInd_5(TagAge16 value) {
        this.ind_5 = value;
    }

    public TagAge16 getInd_6() {
        return ind_6;
    }

    public void setInd_6(TagAge16 value) {
        this.ind_6 = value;
    }

    public TagAge16 getInd_7() {
        return ind_7;
    }

    public void setInd_7(TagAge16 value) {
        this.ind_7 = value;
    }

    public TagAge16 getInd_7_1() {
        return ind_7_1;
    }

    public void setInd_7_1(TagAge16 value) {
        this.ind_7_1 = value;
    }

    public TagAge16 getInd_7_2() {
        return ind_7_2;
    }

    public void setInd_7_2(TagAge16 value) {
        this.ind_7_2 = value;
    }

    public TagAge16 getInd_7_3() {
        return ind_7_3;
    }

    public void setInd_7_3(TagAge16 value) {
        this.ind_7_3 = value;
    }

    public TagAge16 getInd_7_4() {
        return ind_7_4;
    }

    public void setInd_7_4(TagAge16 value) {
        this.ind_7_4 = value;
    }

    public TagAge16 getInd_7_5() {
        return ind_7_5;
    }

    public void setInd_7_5(TagAge16 value) {
        this.ind_7_5 = value;
    }

    public TagAge16 getInd_7_6() {
        return ind_7_6;
    }

    public void setInd_7_6(TagAge16 value) {
        this.ind_7_6 = value;
    }

    public TagAge16 getInd_7_7() {
        return ind_7_7;
    }

    public void setInd_7_7(TagAge16 value) {
        this.ind_7_7 = value;
    }

    public TagAge8Special getInd_8() {
        return ind_8;
    }

    public void setInd_8(TagAge8Special value) {
        this.ind_8 = value;
    }

    public TagAge8Special getInd_8_1() {
        return ind_8_1;
    }

    public void setInd_8_1(TagAge8Special value) {
        this.ind_8_1 = value;
    }

    public TagAge8Special getInd_8_2() {
        return ind_8_2;
    }

    public void setInd_8_2(TagAge8Special value) {
        this.ind_8_2 = value;
    }

    public TagAge8Special getInd_8_3() {
        return ind_8_3;
    }

    public void setInd_8_3(TagAge8Special value) {
        this.ind_8_3 = value;
    }

    public TagAge16 getInd_9() {
        return ind_9;
    }

    public void setInd_9(TagAge16 value) {
        this.ind_9 = value;
    }

    public TagAge16 getInd_9_1() {
        return ind_9_1;
    }

    public void setInd_9_1(TagAge16 value) {
        this.ind_9_1 = value;
    }

    public TagAge1 getInd_10() {
        return ind_10;
    }

    public void setInd_10(TagAge1 value) {
        this.ind_10 = value;
    }

    public TagAge16 getInd_10_1() {
        return ind_10_1;
    }

    public void setInd_10_1(TagAge16 value) {
        this.ind_10_1 = value;
    }

    public TagAge1 getInd_11() {
        return ind_11;
    }

    public void setInd_11(TagAge1 value) {
        this.ind_11 = value;
    }

    public TagAge16 getInd_12() {
        return ind_12;
    }

    public void setInd_12(TagAge16 value) {
        this.ind_12 = value;
    }

    public TagAge16 getInd_12_1() {
        return ind_12_1;
    }

    public void setInd_12_1(TagAge16 value) {
        this.ind_12_1 = value;
    }

    public TagAge16 getInd_13() {
        return ind_13;
    }

    public void setInd_13(TagAge16 value) {
        this.ind_13 = value;
    }

    public TagAge16 getInd_13_1() {
        return ind_13_1;
    }

    public void setInd_13_1(TagAge16 value) {
        this.ind_13_1 = value;
    }

    public TagAge16 getInd_14() {
        return ind_14;
    }

    public void setInd_14(TagAge16 value) {
        this.ind_14 = value;
    }

    public TagAge16 getInd_15() {
        return ind_15;
    }

    public void setInd_15(TagAge16 value) {
        this.ind_15 = value;
    }

    public TagAge16 getInd_16() {
        return ind_16;
    }

    public void setInd_16(TagAge16 value) {
        this.ind_16 = value;
    }

    public TagAge16 getInd_17() {
        return ind_17;
    }

    public void setInd_17(TagAge16 value) {
        this.ind_17 = value;
    }

    public TagAge16 getInd_17_1() {
        return ind_17_1;
    }

    public void setInd_17_1(TagAge16 value) {
        this.ind_17_1 = value;
    }

    public TagAge16 getInd_18() {
        return ind_18;
    }

    public void setInd_18(TagAge16 value) {
        this.ind_18 = value;
    }

    public TagAge16 getInd_18_1() {
        return ind_18_1;
    }

    public void setInd_18_1(TagAge16 value) {
        this.ind_18_1 = value;
    }

    public TagAge16 getInd_18_2() {
        return ind_18_2;
    }

    public void setInd_18_2(TagAge16 value) {
        this.ind_18_2 = value;
    }

    public TagAge16 getInd_18_3() {
        return ind_18_3;
    }

    public void setInd_18_3(TagAge16 value) {
        this.ind_18_3 = value;
    }

    public TagAge16 getInd_18_4() {
        return ind_18_4;
    }

    public void setInd_18_4(TagAge16 value) {
        this.ind_18_4 = value;
    }

    public TagAge16 getInd_18_5() {
        return ind_18_5;
    }

    public void setInd_18_5(TagAge16 value) {
        this.ind_18_5 = value;
    }

    public TagAge16 getInd_19() {
        return ind_19;
    }

    public void setInd_19(TagAge16 value) {
        this.ind_19 = value;
    }

    public TagAge16 getInd_19_1() {
        return ind_19_1;
    }

    public void setInd_19_1(TagAge16 value) {
        this.ind_19_1 = value;
    }

    public TagAge16 getInd_19_2() {
        return ind_19_2;
    }

    public void setInd_19_2(TagAge16 value) {
        this.ind_19_2 = value;
    }

    public TagAge16 getInd_19_3() {
        return ind_19_3;
    }

    public void setInd_19_3(TagAge16 value) {
        this.ind_19_3 = value;
    }

    public TagAge16 getInd_20() {
        return ind_20;
    }

    public void setInd_20(TagAge16 value) {
        this.ind_20 = value;
    }

    public TagAge16 getInd_20_1() {
        return ind_20_1;
    }

    public void setInd_20_1(TagAge16 value) {
        this.ind_20_1 = value;
    }

    public TagAge16 getInd_20_2() {
        return ind_20_2;
    }

    public void setInd_20_2(TagAge16 value) {
        this.ind_20_2 = value;
    }

    public TagAge16 getInd_20_3() {
        return ind_20_3;
    }

    public void setInd_20_3(TagAge16 value) {
        this.ind_20_3 = value;
    }

    public TagAge16 getInd_20_4() {
        return ind_20_4;
    }

    public void setInd_20_4(TagAge16 value) {
        this.ind_20_4 = value;
    }

    public TagAge16 getInd_20_5() {
        return ind_20_5;
    }

    public void setInd_20_5(TagAge16 value) {
        this.ind_20_5 = value;
    }

    public TagAge16 getInd_20_6() {
        return ind_20_6;
    }

    public void setInd_20_6(TagAge16 value) {
        this.ind_20_6 = value;
    }

    public TagAge16 getInd_20_7() {
        return ind_20_7;
    }

    public void setInd_20_7(TagAge16 value) {
        this.ind_20_7 = value;
    }

    public TagAge16 getInd_20_8() {
        return ind_20_8;
    }

    public void setInd_20_8(TagAge16 value) {
        this.ind_20_8 = value;
    }

    public TagAge16 getInd_21() {
        return ind_21;
    }

    public void setInd_21(TagAge16 value) {
        this.ind_21 = value;
    }

    public TagAge16 getInd_22() {
        return ind_22;
    }

    public void setInd_22(TagAge16 value) {
        this.ind_22 = value;
    }

    public TagAge16 getInd_22_1() {
        return ind_22_1;
    }

    public void setInd_22_1(TagAge16 value) {
        this.ind_22_1 = value;
    }

    public TagAge16 getInd_22_1_1() {
        return ind_22_1_1;
    }

    public void setInd_22_1_1(TagAge16 value) {
        this.ind_22_1_1 = value;
    }

    public TagAge16 getInd_22_1_2() {
        return ind_22_1_2;
    }

    public void setInd_22_1_2(TagAge16 value) {
        this.ind_22_1_2 = value;
    }

    public TagAge16 getInd_22_2() {
        return ind_22_2;
    }

    public void setInd_22_2(TagAge16 value) {
        this.ind_22_2 = value;
    }

    public TagAge16 getInd_22_3() {
        return ind_22_3;
    }

    public void setInd_22_3(TagAge16 value) {
        this.ind_22_3 = value;
    }

    public TagAge16 getInd_22_3_1() {
        return ind_22_3_1;
    }

    public void setInd_22_3_1(TagAge16 value) {
        this.ind_22_3_1 = value;
    }

    public TagAge16 getInd_22_3_2() {
        return ind_22_3_2;
    }

    public void setInd_22_3_2(TagAge16 value) {
        this.ind_22_3_2 = value;
    }

    public TagAge16 getInd_22_4() {
        return ind_22_4;
    }

    public void setInd_22_4(TagAge16 value) {
        this.ind_22_4 = value;
    }

    public TagAge16 getInd_22_5() {
        return ind_22_5;
    }

    public void setInd_22_5(TagAge16 value) {
        this.ind_22_5 = value;
    }

    public TagAge16 getInd_22_5_1() {
        return ind_22_5_1;
    }

    public void setInd_22_5_1(TagAge16 value) {
        this.ind_22_5_1 = value;
    }

    public TagAge16 getInd_22_5_2() {
        return ind_22_5_2;
    }

    public void setInd_22_5_2(TagAge16 value) {
        this.ind_22_5_2 = value;
    }

    public TagAge16 getInd_22_6() {
        return ind_22_6;
    }

    public void setInd_22_6(TagAge16 value) {
        this.ind_22_6 = value;
    }

    public TagAge16 getInd_22_7() {
        return ind_22_7;
    }

    public void setInd_22_7(TagAge16 value) {
        this.ind_22_7 = value;
    }

    public TagAge16 getInd_22_8() {
        return ind_22_8;
    }

    public void setInd_22_8(TagAge16 value) {
        this.ind_22_8 = value;
    }

    public TagAge16 getInd_22_8_1() {
        return ind_22_8_1;
    }

    public void setInd_22_8_1(TagAge16 value) {
        this.ind_22_8_1 = value;
    }

    public TagAge16 getInd_22_8_2() {
        return ind_22_8_2;
    }

    public void setInd_22_8_2(TagAge16 value) {
        this.ind_22_8_2 = value;
    }

    public TagAge16 getInd_23() {
        return ind_23;
    }

    public void setInd_23(TagAge16 value) {
        this.ind_23 = value;
    }

    public TagAge16 getInd_24() {
        return ind_24;
    }

    public void setInd_24(TagAge16 value) {
        this.ind_24 = value;
    }

    public TagAge16 getInd_25() {
        return ind_25;
    }

    public void setInd_25(TagAge16 value) {
        this.ind_25 = value;
    }

    public TagAge16 getInd_26() {
        return ind_26;
    }

    public void setInd_26(TagAge16 value) {
        this.ind_26 = value;
    }

    public TagAge16 getInd_27() {
        return ind_27;
    }

    public void setInd_27(TagAge16 value) {
        this.ind_27 = value;
    }

    public TagAge16 getInd_28() {
        return ind_28;
    }

    public void setInd_28(TagAge16 value) {
        this.ind_28 = value;
    }

    public TagAge1 getInd_29() {
        return ind_29;
    }

    public void setInd_29(TagAge1 value) {
        this.ind_29 = value;
    }

    public TagAge16 getInd_29_1() {
        return ind_29_1;
    }

    public void setInd_29_1(TagAge16 value) {
        this.ind_29_1 = value;
    }

    public TagAge16 getInd_29_2() {
        return ind_29_2;
    }

    public void setInd_29_2(TagAge16 value) {
        this.ind_29_2 = value;
    }

    public TagAge16 getInd_29_3() {
        return ind_29_3;
    }

    public void setInd_29_3(TagAge16 value) {
        this.ind_29_3 = value;
    }

    public TagAge16 getInd_30() {
        return ind_30;
    }

    public void setInd_30(TagAge16 value) {
        this.ind_30 = value;
    }

    public TagAge16 getInd_30_1() {
        return ind_30_1;
    }

    public void setInd_30_1(TagAge16 value) {
        this.ind_30_1 = value;
    }

    public TagAge16 getInd_30_2() {
        return ind_30_2;
    }

    public void setInd_30_2(TagAge16 value) {
        this.ind_30_2 = value;
    }

    public TagAge8 getInd_31() {
        return ind_31;
    }

    public void setInd_31(TagAge8 value) {
        this.ind_31 = value;
    }

    public TagAge8 getInd_31_1() {
        return ind_31_1;
    }

    public void setInd_31_1(TagAge8 value) {
        this.ind_31_1 = value;
    }

    public TagAge8 getInd_31_2() {
        return ind_31_2;
    }

    public void setInd_31_2(TagAge8 value) {
        this.ind_31_2 = value;
    }

    public TagAge8 getInd_31_3() {
        return ind_31_3;
    }

    public void setInd_31_3(TagAge8 value) {
        this.ind_31_3 = value;
    }

    public TagAge8 getInd_31_4() {
        return ind_31_4;
    }

    public void setInd_31_4(TagAge8 value) {
        this.ind_31_4 = value;
    }

    public TagAge1 getInd_32() {
        return ind_32;
    }

    public void setInd_32(TagAge1 value) {
        this.ind_32 = value;
    }

    public TagAge16 getInd_32_1() {
        return ind_32_1;
    }

    public void setInd_32_1(TagAge16 value) {
        this.ind_32_1 = value;
    }

    public TagAge16 getInd_32_2() {
        return ind_32_2;
    }

    public void setInd_32_2(TagAge16 value) {
        this.ind_32_2 = value;
    }

    public TagAge1 getInd_33() {
        return ind_33;
    }

    public void setInd_33(TagAge1 value) {
        this.ind_33 = value;
    }

    public AdvisoryCentr getAdvisory_Centr() {
        return advisory_Centr;
    }

    public void setAdvisory_Centr(AdvisoryCentr value) {
        this.advisory_Centr = value;
    }

    public EarlyAssistant getEarly_Assistant() {
        return early_Assistant;
    }

    public void setEarly_Assistant(EarlyAssistant value) {
        this.early_Assistant = value;
    }

    public Specialists getSpecialists() {
        return specialists;
    }

    public void setSpecialists(Specialists value) {
        this.specialists = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String value) {
        this.code = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String value) {
        this.type = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String value) {
        this.status = value;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String value) {
        this.structure = value;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String value) {
        this.license = value;
    }

    public String getType_Area() {
        return type_Area;
    }

    public void setType_Area(String value) {
        this.type_Area = value;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String value) {
        this.director = value;
    }

    public String getWorktime() {
        return worktime;
    }

    public void setWorktime(String value) {
        this.worktime = value;
    }

    public String getMeal_Serving_Type() {
        return meal_Serving_Type;
    }

    public void setMeal_Serving_Type(String value) {
        this.meal_Serving_Type = value;
    }

    public String getFias_Org_Guid() {
        return fias_Org_Guid;
    }

    public void setFias_Org_Guid(String value) {
        this.fias_Org_Guid = value;
    }

    public String getOrg_Address() {
        return org_Address;
    }

    public void setOrg_Address(String value) {
        this.org_Address = value;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String value) {
        this.website = value;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String value) {
        this.email = value;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String value) {
        this.phone = value;
    }

    public String getAdditional_Education() {
        return additional_Education;
    }

    public void setAdditional_Education(String value) {
        this.additional_Education = value;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String value) {
        this.feature = value;
    }

    public BigInteger getNum_Filial() {
        return num_Filial;
    }

    public void setNum_Filial(BigInteger value) {
        this.num_Filial = value;
    }

    public BigInteger getNum_Building() {
        return num_Building;
    }

    public void setNum_Building(BigInteger value) {
        this.num_Building = value;
    }

    public BigInteger getNum_Group() {
        return num_Group;
    }

    public void setNum_Group(BigInteger value) {
        this.num_Group = value;
    }

    public String getPartner_Doo() {
        return partner_Doo;
    }

    public void setPartner_Doo(String value) {
        this.partner_Doo = value;
    }

    public String getCommet_Status() {
        if (commet_Status == null) {
            return "\u043d\u0435\u0442";
        } else {
            return commet_Status;
        }
    }

    public void setCommet_Status(String value) {
        this.commet_Status = value;
    }

    public String getLekoteka() {
        if (lekoteka == null) {
            return "0";
        } else {
            return lekoteka;
        }
    }

    public void setLekoteka(String value) {
        this.lekoteka = value;
    }

    public String getCentre_Game() {
        if (centre_Game == null) {
            return "0";
        } else {
            return centre_Game;
        }
    }

    public void setCentre_Game(String value) {
        this.centre_Game = value;
    }

    public String getPassport() {
        if (passport == null) {
            return "\u043d\u0435\u0442";
        } else {
            return passport;
        }
    }

    public void setPassport(String value) {
        this.passport = value;
    }

}
