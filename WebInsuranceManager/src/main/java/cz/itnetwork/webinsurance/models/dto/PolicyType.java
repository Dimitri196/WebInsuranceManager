package cz.itnetwork.webinsurance.models.dto;

public enum PolicyType {

    REALTY("Real Estate"),
    TRANSPORT("Vehicles"),
    CROP("Crop Insurance"),
    HEALTH("Health Insurance"),
    HOME("Home Insurance"),
    COMMERCIAL_PROPERTY("Commercial Property Insurance"),
    CRITICAL_ILLNESS("Critical Illness Insurance"),
    DISABILITY("Disability Insurance"),
    TERM_LIFE("Term Life Insurance"),
    WHOLE_LIFE("Whole Life Insurance"),
    UNIVERSAL_LIFE("Universal Life Insurance"),
    AUTO_COMPREHENSIVE("Auto Comprehensive Coverage"),
    AUTO_COLLISION("Auto Collision Coverage"),
    AUTO_LIABILITY("Auto Liability Coverage"),
    TRAVEL_TRIP_CANCELLATION("Travel Trip Cancellation Insurance"),
    TRAVEL_MEDICAL("Travel Medical Insurance"),
    GENERAL_LIABILITY("General Liability Insurance"),
    PROFESSIONAL_LIABILITY("Professional Liability Insurance"),
    PET("Pet Insurance"),
    BOAT("Boat Insurance"),
    EVENT("Event Insurance"),
    UMBRELLA("Umbrella Insurance"),
    PRODUCT_LIABILITY("Product Liability Insurance"),
    WORKERS_COMPENSATION("Workers' Compensation Insurance"),
    EMPLOYERS_LIABILITY("Employers' Liability Insurance"),
    OTHER("Other");

    private final String policyName;

    PolicyType(String policyName) {
        this.policyName = policyName;
    }

    public String getPolicyName() {
        return policyName;
    }
}