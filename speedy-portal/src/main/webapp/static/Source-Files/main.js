$(function($) {
    "use strict";

    // For page transitions
    $(".animsition").animsition();

    // For Matching all .campaign-card heights
	$(".nav-col").matchHeight();

    // For counters
	$(".counter").counterUp({
		delay: 10,
		time: 1000
	});

    // For Owl Carousel
    $(".owl-carousel-single").owlCarousel({
        loop: true,
        navRewind: false,
        margin: 10,
        dots: true,
        nav: false,
        autoplay: false,
        navText: [],
        items: 1
    });

    // For Swiper
    Swiper(".swiper-container", {
        loop: true,
        pagination: ".swiper-pagination",
        paginationClickable: true,
        nextButton: ".swiper-button-next",
        prevButton: ".swiper-button-prev"
    });

    // For Swiper Background Images
    $(".swiper-slide", "#swiper").each(function(){
        var bgImg = $(this).data("slide-img");
        $(this).css("background-image", "url(" + bgImg + ")");
    });

    // For Tooltips
    $("[data-toggle='tooltip']").tooltip();

    // For revealing elements on scroll
    var elemReveal = {
        delay: 200,
        duration: 600,
        distance: "60px",
        easing: "ease-in-out",
        rotate: { z: 0 },
        scale: 1.0
    };
    window.sr = ScrollReveal();
    sr.reveal(".one-step", elemReveal, 500);

    // For Text Animation in Header
    $("#animated-text").textillate({
        loop: true
    });

    // For changing body class on scroll
    $(window).on("scroll resize", function() {
        if ($(window).scrollTop() >= 75) {
            $("body").addClass("body-scrolled");
        }
        else {
            return $("body").removeClass("body-scrolled");
        }
    });


});
