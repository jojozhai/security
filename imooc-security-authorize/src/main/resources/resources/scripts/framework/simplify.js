$(function	()	{
	//scrollable sidebar
	$('.scrollable-sidebar').slimScroll({
		height: '100%',
		size: '0px'
	});
	
	//Collapsible Sidebar Menu
	$('.sidebar-menu .openable > a').click(function()	{
		
		if(!$('aside').hasClass('sidebar-mini') || Modernizr.mq('(max-width: 991px)'))	{
			if( $(this).parent().children('.submenu').is(':hidden') ) {
				$(this).parent().siblings().removeClass('open').children('.submenu').slideUp(200);
				$(this).parent().addClass('open').children('.submenu').slideDown(200);
			}
			else	{
				$(this).parent().removeClass('open').children('.submenu').slideUp(200);
			}
		}
		
		return false;
	});

	//Open active menu
	if(!$('.sidebar-menu').hasClass('sidebar-mini') || Modernizr.mq('(max-width: 767px)'))	{
		$('.openable.open').children('.submenu').slideDown(200);
	}
	
	//Toggle User container on sidebar menu
	$('#btn-collapse').click(function()	{
		$('.sidebar-header').toggleClass('active');
	});
	
	//theme setting
	$("#theme-setting-icon").click(function()	{ 
		if($('#theme-setting').hasClass('open'))	{
			$('#theme-setting').removeClass('open');
			$('#theme-setting-icon').removeClass('open');
		}
		else	{
			$('#theme-setting').addClass('open');
			$('#theme-setting-icon').addClass('open');
		}

		return false;
	});
	
	$('#sidebarToggleLG').click(function()	{
		if($('.wrapper').hasClass('display-right'))	{
			$('.wrapper').removeClass('display-right');
			$('.sidebar-right').removeClass('active');
		}
		else	{
			//$('.nav-header').toggleClass('hide');
			$('.top-nav').toggleClass('sidebar-mini');
			$('aside').toggleClass('sidebar-mini');
			$('footer').toggleClass('sidebar-mini');
			$('.main-container').toggleClass('sidebar-mini');
			
			$('.main-menu').find('.openable').removeClass('open');
			$('.main-menu').find('.submenu').removeAttr('style');
		}		
	});
	
	$('#sidebarToggleSM').click(function()	{
		$('aside').toggleClass('active');
		$('.wrapper').toggleClass('display-left');
	});
	
	$('.dropdown-menu input').click(function(e) {
        e.stopPropagation(); //This will prevent the event from bubbling up and close the dropdown when you type/click on text boxes.
    });
	
	//to do list
	$('.task-finish').click(function()	{ 
		if($(this).is(':checked'))	{
			$(this).parent().parent().addClass('selected');					
		}
		else	{
			$(this).parent().parent().removeClass('selected');
		}
	});

	//Delete to do list
	$('.task-del').click(function()	{			
		var activeList = $(this).parent().parent();

		activeList.addClass('removed');
				
		setTimeout(function() {
			activeList.remove();
		}, 1000);
			
		return false;
	});
	
	var $activeWidget = '';
	var $activeWidgetHeader;
	var $headerHeight;
	var $screenHeight;
	var $widgetHeight;
	var $borderHeight = 3;

	//Smart Widget

	// Refresh Widget
	$('.widget-refresh-option').click(function()	{

		$activeWidget = $(this).parent().parent().parent();
		
		var $activeSpinIcon = $activeWidget.find('.refresh-icon-animated').fadeIn();

		setTimeout(function() {
			$activeSpinIcon.fadeOut();
		},2000);

		return false;
	});

	// Collasible Widget
	$('.widget-collapse-option').click(function()	{
		$activeWidget = $(this).parent().parent().parent();

		$activeWidget.find('.smart-widget-inner').slideToggle();
		$activeWidget.toggleClass('smart-widget-collapsed');

		var $activeSpinIcon = $activeWidget.find('.refresh-icon-animated').fadeIn();

		setTimeout(function() {
			$activeSpinIcon.fadeOut();
		},500);

		$activeWidget = '';

		return false;
	});

	//Changing Widget Color
	$('.widget-toggle-hidden-option').click(function()	{
		$activeWidget = $(this).parent().parent().parent();

		$activeWidget.find('.smart-widget-hidden-section').slideToggle();	

		var $activeSpinIcon = $activeWidget.find('.refresh-icon-animated').fadeIn();

		setTimeout(function() {
			$activeSpinIcon.fadeOut();
		},500);


		$activeWidget = '';			

		return false;
	});

	//Changing Widget Header Background
	$('.widget-color-list li').click(function()	{
		$activeWidget = $(this).parent().parent().parent().parent();
		$selectedColor = $(this).data('color');

		$activeWidget.removeClass('widget-light-grey');
		$activeWidget.removeClass('widget-dark');
		$activeWidget.removeClass('widget-dark-blue');
		$activeWidget.removeClass('widget-blue');
		$activeWidget.removeClass('widget-green');
		$activeWidget.removeClass('widget-yellow');
		$activeWidget.removeClass('widget-orange');
		$activeWidget.removeClass('widget-red');
		$activeWidget.removeClass('widget-purple');

		if($selectedColor != 'reset')
			$activeWidget.addClass($selectedColor);
		
		return false;
	});

	// Remove Widget
	$('.widget-remove-option').click(function()	{

		$activeWidget = $(this).parent().parent().parent();

		$('#deleteWidgetConfirm').popup('show');

		return false;

	});

	$('.remove-widget-btn').click(function()	{
		$('#deleteWidgetConfirm').popup('hide');
		$activeWidget.fadeOut();

		$activeWidget = '';

		return false;
	});

	//Scroll to Top
	$(".scroll-to-top").click(function()	{
		$("html, body").animate({ scrollTop: 0 }, 600);
		 return false;
	});

});


$(window).load(function() {
	$('body').removeClass('overflow-hidden');

	//Enable animation
	$('.wrapper').removeClass('preload');

	//Chat Notification on top navigation
	setTimeout(function() {
		$('.chat-notification').find('.badge').addClass('active');
		$('.chat-alert').addClass('active');
	}, 3000);

	setTimeout(function() {
		$('.chat-alert').removeClass('active');
	}, 8000);
});

// Toggle Scroll to Top button
$(window).scroll(function(){
		
	 var position = $(window).scrollTop();
	
	 if(position >= 200)	{
		$('.scroll-to-top').addClass('active')
	 }
	 else	{
		$('.scroll-to-top').removeClass('active')
	 }
});