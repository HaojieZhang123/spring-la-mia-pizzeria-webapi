package org.exercise.java.spring_la_mia_pizzeria_webapi.service;

import java.util.List;
import java.util.Optional;

import org.exercise.java.spring_la_mia_pizzeria_webapi.model.Offer;
import org.exercise.java.spring_la_mia_pizzeria_webapi.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferService {
    @Autowired
    private OfferRepository offerRepository;

    public List<Offer> findAll() {
        return offerRepository.findAll();
    }

    public Optional<Offer> findById(Integer id) {
        return offerRepository.findById(id);
    }

    public Offer getById(Integer id) {
        return offerRepository.findById(id).get();
    }

    public Offer store(Offer offer) {
        return offerRepository.save(offer);
    }

    public Offer update(Offer offer) {
        return offerRepository.save(offer);
    }

    public void delete(Offer offer) {
        offerRepository.delete(offer);
    }

}
