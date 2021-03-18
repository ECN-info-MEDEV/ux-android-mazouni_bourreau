package co.stormix.je.data.model;

public class Company {

    private String site;
    private String companyType;
    private String companyId;
    private String displayName;

    public Company(String companyId, String displayName, String site, String companyType) {
        this.companyId = companyId;
        this.displayName = displayName;
        this.site = site;
        this.companyType = companyType;
    }

    public String getCompanyId() {
        return companyId;
    }
    public String getDisplayName() {
        return displayName;
    }
    public String getCompanyType() {
        return companyType;
    }
    public String getSite() {
        return site;
    }

    public static Company createFakeCompany(String id){
        return new Company(id, "Apple Inc. " + id, "apple"+id+".com", "PME");
    }
}
