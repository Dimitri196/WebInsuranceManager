package cz.itnetwork.webinsurance.controllers;

import cz.itnetwork.webinsurance.models.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * StatisticsController handles the display of various statistics related to clients, policies, users, and claims.
 * It gathers statistical data and presents it on the statistics page.
 */
@Controller
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    /**
     * Retrieves statistical data related to clients, policies, users, and claims and populates the model
     * with the data to be displayed on the statistics page.
     *
     * The data includes:
     * - Total number of clients
     * - Total number of policies
     * - Total number of users
     * - Total number of claims
     * - Policies categorized by type
     * - Claims categorized by type
     *
     * @param model the model used to pass statistical data to the view.
     * @return the path to the statistics page.
     */
    @GetMapping("/statistics")
    public String getStatistics(Model model) {
        model.addAttribute("totalClients", statisticsService.getTotalClients());
        model.addAttribute("totalPolicies", statisticsService.getTotalPolicies());
        model.addAttribute("totalUsers", statisticsService.getTotalUsers());
        model.addAttribute("totalClaims", statisticsService.getTotalClaims());
        model.addAttribute("policiesByType", statisticsService.getPoliciesByType());
        model.addAttribute("claimsByType", statisticsService.getClaimsByType());
        return "pages/statistics/index";
    }
}
