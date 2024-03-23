package com.roze.service.admin.faq.impl;

import com.roze.dto.FAQDto;
import com.roze.entity.FAQ;
import com.roze.entity.Product;
import com.roze.repository.FAQRepository;
import com.roze.repository.ProductRepository;
import com.roze.service.admin.faq.FAQService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FAQServiceImpl implements FAQService {
    private final ProductRepository productRepository;
    private final FAQRepository faqRepository;

    public FAQDto postFAQ(Long productId, FAQDto faqDto) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            FAQ faq = new FAQ();
            faq.setQuestion(faqDto.getQuestion());
            faq.setAnswer(faqDto.getAnswer());
            faq.setProduct(optionalProduct.get());
            return faqRepository.save(faq).getFAQDto();
        } else {
            return null;
        }
    }
}
