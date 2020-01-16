package com.vedantu.ecommerce.service;

import org.springframework.stereotype.Service;

@Service
public interface SequenceGenerator {

    public int generateSequence(String seqName);
}
