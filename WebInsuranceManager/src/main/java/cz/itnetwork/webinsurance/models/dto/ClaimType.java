package cz.itnetwork.webinsurance.models.dto;

public enum ClaimType {

    // Property and Home Insurance
    PROPERTY_DAMAGE("Property Damage"),
    HOME_INSURANCE("Home Insurance"),
    WIND_AND_HAIL_DAMAGE("Wind and Hail Damage"),
    WATER_DAMAGE_AND_FREEZING("Water Damage and Freezing"),
    THEFT("Theft"),
    FIRE("Fire"),
    FIRE_AND_LIGHTNING("Fire and Lightning"),

    // Health and Life Insurance
    MEDICAL("Medical Expenses for Physical Injuries"),
    HEALTH_INSURANCE("Health Insurance"),
    LIFE_INSURANCE("Life Insurance"),
    BODILY_INJURY("Bodily Injury"),
    DEATH_CLAIM("Death Claim"),

    // General and Liability Claims
    LIABILITY("Liability"),
    GENERAL_CLAIMS("General Claims"),
    TRAFFIC_COLLISION("Traffic Collision"),
    CUSTOMER_SLIP_AND_FALL("Customer Slip and Fall"),
    NATURAL_DISASTER_CLAIMS("Natural Disaster Claims"),
    CLAIM_CLOSURE("Claim Closure"),
    STRUCK_BY_OBJECT("Struck-by Object"),

    // Collision and Uninsured Coverage
    COLLISION("Collision Coverage"),
    UNINSURED("Uninsured Motorist Coverage");

    private final String claimName;

    ClaimType(String claimName) {
        this.claimName = claimName;
    }

    public String getClaimName() {
        return claimName;
    }
}



