package com.roze.service.admin.coupon.impl;

import com.roze.entity.Coupon;
import com.roze.exceptions.ValidationException;
import com.roze.repository.CouponRepository;
import com.roze.service.admin.coupon.AdminCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminCouponServiceImpl implements AdminCouponService {
    @Autowired
    private CouponRepository couponRepository;

    public Coupon createCoupon(Coupon coupon) {
        if (couponRepository.existsByCode(coupon.getCode())) {
            throw new ValidationException("Coupon code already exists!!");
        }
        return couponRepository.save(coupon);
    }

    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }
}
