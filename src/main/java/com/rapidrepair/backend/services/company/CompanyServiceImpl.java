package com.rapidrepair.backend.services.company;

import com.rapidrepair.backend.dto.AdDTO;
import com.rapidrepair.backend.dto.ReservationDTO;
import com.rapidrepair.backend.entity.Ad;
import com.rapidrepair.backend.entity.Reservation;
import com.rapidrepair.backend.entity.User;
import com.rapidrepair.backend.enums.ReservationStatus;
import com.rapidrepair.backend.repository.AdRepository;
import com.rapidrepair.backend.repository.ReservationRepository;
import com.rapidrepair.backend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    private static final Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Transactional
    @Override
    public boolean postAd(Long userId, AdDTO adDTO) throws IOException {
        logger.info("Attempting to post an ad for user ID: {}", userId);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            Ad ad = new Ad();
            ad.setServiceName(adDTO.getServiceName());
            ad.setDescription(adDTO.getDescription());
            ad.setImg(adDTO.getImg().getBytes());
            ad.setPrice(adDTO.getPrice());
            ad.setUser(optionalUser.get());

            adRepository.save(ad);
            logger.info("Ad posted successfully for user ID: {}", userId);
            return true;
        }
        logger.error("User ID: {} not found, unable to post ad", userId);
        return false;
    }

    @Transactional(readOnly = true)
    @Override
    public List<AdDTO> getAllAds(Long userId) {
        logger.info("Retrieving all ads for user ID: {}", userId);
        return adRepository.findAllByUserIdWithUser(userId).stream().map(ad -> {
            AdDTO adDTO = ad.getAdDto();
            adDTO.setCompanyName(ad.getUser().getName()); // Ensure this doesn't cause LazyInitializationException
            return adDTO;
        }).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public AdDTO getAdById(Long adId) {
        logger.info("Retrieving ad details for ad ID: {}", adId);
        Optional<Ad> optionalAd = adRepository.findById(adId);
        if (optionalAd.isPresent()) {
            return optionalAd.get().getAdDto();
        }
        logger.error("Ad ID: {} not found", adId);
        return null;
    }

    @Transactional
    @Override
    public boolean updateAd(Long adId, AdDTO adDTO) throws IOException {
        logger.info("Attempting to update ad ID: {}", adId);
        Optional<Ad> optionalAd = adRepository.findById(adId);
        if (optionalAd.isPresent()) {
            Ad ad = optionalAd.get();
            ad.setServiceName(adDTO.getServiceName());
            ad.setDescription(adDTO.getDescription());
            ad.setPrice(adDTO.getPrice());

            if (adDTO.getImg() != null) {
                ad.setImg(adDTO.getImg().getBytes());
            }

            adRepository.save(ad);
            logger.info("Ad ID: {} updated successfully", adId);
            return true;
        } else {
            logger.error("Ad ID: {} not found, unable to update", adId);
            return false;
        }
    }

    @Transactional
    @Override
    public boolean deleteAd(Long adId) {
        logger.info("Attempting to delete ad ID: {}", adId);
        Optional<Ad> optionalAd = adRepository.findById(adId);
        if (optionalAd.isPresent()) {
            adRepository.delete(optionalAd.get());
            logger.info("Ad ID: {} deleted successfully", adId);
            return true;
        }
        logger.error("Ad ID: {} not found, unable to delete", adId);
        return false;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ReservationDTO> getAllAdBookings(Long companyId) {
        logger.info("Retrieving all bookings for company ID: {}", companyId);
        return reservationRepository.findAllByCompanyId(companyId)
                .stream().map(Reservation::getReservationDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public boolean changeBookingStatus(Long bookingId, String status) {
        logger.info("Attempting to change booking status for booking ID: {} to {}", bookingId, status);
        Optional<Reservation> optionalReservation = reservationRepository.findById(bookingId);
        if (optionalReservation.isPresent()) {
            Reservation existingReservation = optionalReservation.get();
            if (Objects.equals(status, "Approve")) {
                existingReservation.setReservationStatus(ReservationStatus.APPROVED);
            } else {
                existingReservation.setReservationStatus(ReservationStatus.REJECTED);
            }

            reservationRepository.save(existingReservation);
            logger.info("Booking status for booking ID: {} changed to {}", bookingId, status);
            return true;
        }
        logger.error("Booking ID: {} not found, unable to change status", bookingId);
        return false;
    }

    @Override
    public List<AdDTO> getUserWithAds(Long userId) {
        logger.info("Retrieving ads for user ID: {}", userId);
        return List.of(); // This method needs proper implementation if used
    }
}
