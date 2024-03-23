package com.roze.service.admin.faq;

import com.roze.dto.FAQDto;

public interface FAQService {
    FAQDto postFAQ(Long productId, FAQDto faqDto);
}
