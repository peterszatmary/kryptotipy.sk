(function($) {

	"use strict";

	/*---------------------------------------------------- */
  	/* Smooth Scrolling
  	------------------------------------------------------ */
  	$('.smoothscroll').on('click', function (e) {

	 	e.preventDefault();

   	var target = this.hash,
    	$target = $(target);

    	$('html, body').stop().animate({
       	'scrollTop': $target.offset().top
      }, 800, 'swing', function () {
      	window.location.hash = target;
      });
  	});


  	/*-----------------------------------------------------
      Back to top
      ------------------------------------------------------- */
    	var pxShow = 400; // height on which the button will show
    	var fadeInTime = 400; // how slow/fast you want the button to show
    	var fadeOutTime = 400; // how slow/fast you want the button to hide
    	var scrollSpeed = 300; // how slow/fast you want the button to scroll to top. can be a value, 'slow', 'normal' or 'fast'

        $(window).load(function() {
              $("#go-top").attr("style","display:none;");
        });

       // Show or hide the sticky footer button
    	jQuery(window).scroll(function() {
            if (jQuery(window).scrollTop() >= pxShow) {
                jQuery("#go-top").fadeIn(fadeInTime);
            } else {
                jQuery("#go-top").fadeOut(fadeOutTime);
            }
    	});
    })(jQuery);