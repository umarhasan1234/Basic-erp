package com.nrt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.nrt.request.CouponRequest;
import com.nrt.service.CouponService;
import com.nrt.util.CommonUtil;

//this is coupon controller all coupon releted opration in this controller
@Controller
@RequestMapping("/coupon")
public class CouponController {

	@Autowired
	private CouponService couponService;

	// this methos redirect coupon register page
	@GetMapping("/register/page")
	public ModelAndView CouponHomePage(ModelAndView modelAndView) {
		modelAndView.setViewName("/html/coupon/coupon_register");
		return modelAndView;

	}

	// this method insert coupon in data base
	@PostMapping("/register")
	public ModelAndView Register(@ModelAttribute("couponRequest") CouponRequest couponRequest,
			ModelAndView modelAndView) {
		modelAndView.addObject("title", "Successful register");
		modelAndView.addObject("message", "Coupon registered successfully!");
		modelAndView.addObject("url", "http://localhost:9090/coupon/list");
		modelAndView.addObject("button", "to Home");
		modelAndView.addObject("details", "\"Congratulations! Your coupon registration was successful.!");
		modelAndView.addObject("error", "An error occurred while processing your request. Please try again later.");
		modelAndView.setViewName(
				couponService.Register(couponRequest) ? "/html/coupon/response_success" : "/html/coupon/error");
		return modelAndView;

	}

	// this method find the data by coupon id
	@GetMapping("/edit-coupon/{couponId}")
	public ModelAndView editCoupon(@PathVariable("couponId") String couponId, @RequestParam("flag") String flag,
			ModelAndView modelAndView) {
		CouponRequest couponRequest = null;
		if (flag.equals("DELETE")) {
			modelAndView.addObject("message", "Coupon Deleted successfully!");
			modelAndView.addObject("details", "Congratulations! Your coupon delete was successful.!");
			modelAndView.setViewName(
					couponService.Delete(CommonUtil.changeStringToLong(couponId)) ? "/html/coupon/response_success"
							: "/html/coupon/error");
		} else {
			couponRequest = couponService.getCouponById(CommonUtil.changeStringToLong(couponId));
			modelAndView.setViewName(couponRequest != null ? "/html/coupon/coupon_update" : "/html/coupon/error");
		}
		modelAndView.addObject("url", "http://localhost:9090/coupon/list");
		modelAndView.addObject("button", "to Home");
		modelAndView.addObject("coupon", couponRequest);
		modelAndView.addObject("error", "Please Inter vailid id . And  try again.");
		return modelAndView;
	}

	// this method update coupon in data base
	@PostMapping("/update")
	public ModelAndView Update(@ModelAttribute("couponRequest") CouponRequest couponRequest,
			ModelAndView modelAndView) {
		modelAndView.addObject("title", "Successful updated");
		modelAndView.addObject("url", "http://localhost:9090/coupon/list");
		modelAndView.addObject("button", "to Home");
		modelAndView.addObject("message", "Coupon updated successfully!");
		modelAndView.addObject("details", "\"Congratulations! Your coupon updation was successful.!");
		modelAndView.addObject("error", "An error occurred while processing your request. Please try again later.");
		modelAndView.setViewName(
				couponService.Register(couponRequest) ? "/html/coupon/response_success" : "/html/coupon/error");
		return modelAndView;

	}

	// this method provid list of coupon
	@GetMapping("/list")
	public ModelAndView CouponList(ModelAndView modelAndView) {
		modelAndView.addObject("listCoupon", couponService.FindAllCoupon());
		modelAndView.setViewName("/html/coupon/List_of_coupon.html");
		return modelAndView;

	}
}
