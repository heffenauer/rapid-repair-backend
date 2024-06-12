package com.rapidrepair.backend.controller;

import com.rapidrepair.backend.dto.AdDTO;
import com.rapidrepair.backend.dto.ReservationDTO;
import com.rapidrepair.backend.services.company.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private CompanyService companyService;

    @PostMapping("/ad/{userId}")
    public ResponseEntity<?> postAd(@PathVariable Long userId, @ModelAttribute AdDTO adDTO) throws IOException {
        logger.info("Posting ad for user ID: {}", userId);
        boolean success = companyService.postAd(userId, adDTO);
        if (success) {
            logger.info("Ad posted successfully for user ID: {}", userId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            logger.error("Failed to post ad for user ID: {}", userId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/ads/{userId}")
    public ResponseEntity<?> getAllAdsByUserId(@PathVariable Long userId) {
        logger.info("Fetching all ads for user ID: {}", userId);
        return ResponseEntity.ok(companyService.getAllAds(userId));
    }

    @GetMapping("/ad/{adId}")
    public ResponseEntity<?> getAdById(@PathVariable Long adId) {
        logger.info("Fetching ad by ID: {}", adId);
        AdDTO adDTO = companyService.getAdById(adId);
        if (adDTO != null) {
            logger.info("Ad fetched successfully for ID: {}", adId);
            return ResponseEntity.ok(adDTO);
        } else {
            logger.error("Ad not found for ID: {}", adId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/ad/{adId}")
    public ResponseEntity<?> updateAd(@PathVariable Long adId, @ModelAttribute AdDTO adDTO) throws IOException {
        logger.info("Updating ad ID: {}", adId);
        boolean success = companyService.updateAd(adId, adDTO);
        if (success) {
            logger.info("Ad updated successfully for ID: {}", adId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            logger.error("Failed to update ad for ID: {}", adId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/ad/{adId}")
    public ResponseEntity<?> deleteAd(@PathVariable Long adId) {
        logger.info("Deleting ad ID: {}", adId);
        boolean success = companyService.deleteAd(adId);
        if (success) {
            logger.info("Ad deleted successfully for ID: {}", adId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            logger.error("Failed to delete ad for ID: {}", adId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/bookings/{companyId}")
    public ResponseEntity<List<ReservationDTO>> getAllAdBookings(@PathVariable Long companyId) {
        logger.info("Fetching all bookings for company ID: {}", companyId);
        return ResponseEntity.ok(companyService.getAllAdBookings(companyId));
    }

    @GetMapping("/booking/{bookingId}/{status}")
    public ResponseEntity<?> changeBookingStatus(@PathVariable Long bookingId, @PathVariable String status) {
        logger.info("Changing booking status for booking ID: {} to {}", bookingId, status);
        boolean success = companyService.changeBookingStatus(bookingId, status);
        if (success) {
            logger.info("Booking status changed successfully for booking ID: {}", bookingId);
            return ResponseEntity.ok().build();
        }
        logger.error("Failed to change booking status for booking ID: {}", bookingId);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/users/{userId}/ads-details")
    public ResponseEntity<List<AdDTO>> getUserAdsDetails(@PathVariable Long userId) {
        logger.info("Fetching user ads details for user ID: {}", userId);
        return ResponseEntity.ok(companyService.getUserWithAds(userId));
    }
}
