<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Camuntelia - Connected to everything</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="css/open-iconic-bootstrap.min.css">
    <link rel="stylesheet" href="css/animate.css">

    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <link rel="stylesheet" href="css/owl.theme.default.min.css">
    <link rel="stylesheet" href="css/magnific-popup.css">

    <link rel="stylesheet" href="css/aos.css">

    <link rel="stylesheet" href="css/ionicons.min.css">

    <link rel="stylesheet" href="css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="css/jquery.timepicker.css">


    <link rel="stylesheet" href="css/flaticon.css">
    <link rel="stylesheet" href="css/icomoon.css">
    <link rel="stylesheet" href="css/style.css">
  </head>
  <body>

	  <nav class="navbar navbar-expand-lg ftco-navbar-light w-100 rounded px-auto scrolled awake" style="background: #fff !important;top:0;" id="ftco-navbar">
	    <div class="container rounded">
	      <a class="navbar-brand" href="index.jsp?lang=${param.lang}" style="color:black !important;">Camuntelia</a>
	      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="oi oi-menu"></span> ${param.lang == 'de' ? 'Men&uuml;' : 'Menu'}
	      </button>

	      <div class="collapse navbar-collapse" id="ftco-nav">
	        <ul class="navbar-nav ml-auto">
	          <li class="nav-item active"><a href="index.jsp?lang=${param.lang}" class="nav-link font-weight-bold"  style="color:black !important;">Home</a></li>
	          <li class="nav-item"><a href="index.jsp?lang=${param.lang}" class="nav-link font-weight-bold" style="color:black !important;">${param.lang == 'de' ? '&Uuml;ber' : 'About'}</a></li>
	          <li class="nav-item"><a href="documents.jsp?lang=${param.lang}" class="nav-link font-weight-bold" style="color:black !important;">${param.lang == 'de' ? 'Kontakt' : 'Contact'}</a></li>
	        </ul>
	      </div>
	    </div>
	  </nav>
    <!-- END nav -->

    <div class="hero-wrap" style="background-image: url('images/bg_2.jpg');height:929px;background-position: 50% 0px;" data-stellar-background-ratio="0.5">
      <div class="overlay" style="opacity:.4;position:fixed;"></div>
      <div class="container">
        <div class="row no-gutters slider-text js-fullheight align-items-start justify-content-start mt-5" data-scrollax-parent="true">
          <div class="col-xl-10 ftco-animate my-5 py-5" data-scrollax=" properties: { translateY: '30%' }">
          	<p class="my-5 pt-5"></p>
            <h1 class="mb-5" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">${param.lang == 'de' ? 'Verbinde dich' : 'Stay Connected'}<br> ${param.lang == 'de' ? '&Uuml;berall' : 'Everywhere'}</h1>

						<div class="ftco-search">
							<div class="row">
		            <div class="col-md-12 nav-link-wrap">
			            <div class="nav nav-pills text-center" id="v-pills-tab" role="tablist" aria-orientation="vertical">
			              <span class="bg-white text-dark rounded py-3 w-25 px-5 text-left mr-md-1 font-weight-bold" id="v-pills-1-tab" data-toggle="pill" href="#v-pills-1" role="tab" aria-controls="v-pills-1" aria-selected="true">${param.lang == 'de' ? 'Verf&uuml;gbarkeit' : 'Check availabilty'}</span>
			            </div>
			          </div>
			          <div class="col-md-12 tab-wrap">

			            <div class="tab-content py-3 pl-3 pr-1" id="v-pills-tabContent">

			              <div class="tab-pane fade show active" id="v-pills-1" role="tabpanel" aria-labelledby="v-pills-nextgen-tab">
			              	<form action="services.jsp" method="get" class="search-job">
                        		<input type="hidden" name="lang" value="${param.lang}" />
			              		<div class="row" style="margin-right:-45px;">
			              			<div class="col-md">
			              				<div class="form-group">
				              				<div class="form-field">
				              					<div class="icon"><span class="icon-home"></span></div>
								                <input required type="text" class="form-control" placeholder="eg. ${param.lang == 'de' ? 'M&uuml;llerstra&szlig;e 23a' : 'your street 23a'}">
								              </div>
							              </div>
			              			</div>
			              			<div class="col-md">
			              				<div class="form-group">
			              					<div class="form-field">
				              					<div class="select-wrap">
						                      <div class="icon"><span class="icon-compass"></span></div>
						                      <input required type="text" class="form-control" placeholder="${param.lang == 'de' ? 'Postleitzahl' : 'zip-code'}">
						                    </div>
								              </div>
							              </div>
			              			</div>
			              			<div class="col-md">
			              				<div class="form-group">
			              					<div class="form-field">
				              					<div class="icon"><span class="icon-map-marker"></span></div>
								                <input required type="text" class="form-control" placeholder="${param.lang == 'de' ? 'Ort' : 'Location'}">
								              </div>
							              </div>
			              			</div>
			              			<div class="col-md">
                            <button value="Send Message" class="btn btn-primary py-2 px-4 align-text-top">${param.lang == 'de' ? 'Anschrift pr&uuml;fen' : 'Check Availabilty'}</button>
			              			</div>
			              		</div>
			              	</form>
			              </div>
			            </div>
			          </div>
			        </div>
		        </div>
          </div>
        </div>
      </div>
    </div>

    <section class="ftco-section services-section bg-light">
      <div class="container">
        <div class="row d-flex">
          <div class="col-md-3 d-flex align-self-stretch ftco-animate">
            <div class="media block-6 services d-block">
              <div class="icon"><span class="flaticon-resume"></span></div>
              <div class="media-body">
                <h3 class="heading mb-3">${param.lang == 'de' ? 'Schreiben Sie Millionen Emails' : 'Write Millions Of Emails'}</h3>
                <p>Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy.</p>
              </div>
            </div>
          </div>
          <div class="col-md-3 d-flex align-self-stretch ftco-animate">
            <div class="media block-6 services d-block">
              <div class="icon"><span class="flaticon-collaboration"></span></div>
              <div class="media-body">
                <h3 class="heading mb-3">${param.lang == 'de' ? 'Verbindungsmanagement' : 'Easy To Manage Connections'}</h3>
                <p>Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy.</p>
              </div>
            </div>
          </div>
          <div class="col-md-3 d-flex align-self-stretch ftco-animate">
            <div class="media block-6 services d-block">
              <div class="icon"><span class="flaticon-promotions"></span></div>
              <div class="media-body">
                <h3 class="heading mb-3">${param.lang == 'de' ? 'Top Karrierem&ouml;glichkeiten' : 'Top Careers'}</h3>
                <p>Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy.</p>
              </div>
            </div>
          </div>
          <div class="col-md-3 d-flex align-self-stretch ftco-animate">
            <div class="media block-6 services d-block">
              <div class="row">
                <div class="icon"><span class="flaticon-employee"></span></div>
                <div class="icon"><span class="flaticon-employee"></span></div>
              </div>
              <div class="media-body">
                <h3 class="heading mb-3">${param.lang == 'de' ? 'Finden Sie neue Freunde' : 'Find Friends On Social Media'}</h3>
                <p>Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy.</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <section class="ftco-section ftco-counter img" id="section-counter" style="background-image: url(images/bg_1.jpg);" data-stellar-background-ratio="0.5">
    	<div class="container">
    		<div class="row justify-content-center">
    			<div class="col-md-10">
		    		<div class="row">
		          <div class="col-md-3 d-flex justify-content-center counter-wrap ftco-animate">
		            <div class="block-18 text-center">
		              <div class="text">
		                <strong class="number" data-number="130850000">0</strong>
		                <span>${param.lang == 'de' ? 'Parallele Verbindungen' : 'Parallel connections'}</span>
		              </div>
		            </div>
		          </div>
		          <div class="col-md-3 d-flex justify-content-center counter-wrap ftco-animate">
		            <div class="block-18 text-center">
		              <div class="text">
		                <strong class="number" data-number="4000000">0</strong>
		                <span>${param.lang == 'de' ? 'Starke Nutzer' : 'Heavy users'}</span>
		              </div>
		            </div>
		          </div>
		          <div class="col-md-3 d-flex justify-content-center counter-wrap ftco-animate">
		            <div class="block-18 text-center">
		              <div class="text">
		                <strong class="number" data-number="30000">0</strong>
		                <span>${param.lang == 'de' ? 'Mitarbeiter' : 'Employees'}</span>
		              </div>
		            </div>
		          </div>
		          <div class="col-md-3 d-flex justify-content-center counter-wrap ftco-animate">
		            <div class="block-18 text-center">
		              <div class="text">
		                <strong class="number" data-number="10500">0</strong>
		                <span>${param.lang == 'de' ? 'B2B Kunden' : 'B2B customers'}</span>
		              </div>
		            </div>
		          </div>
		        </div>
	        </div>
        </div>
    	</div>
    </section>

    <section class="ftco-section-parallax">
      <div class="parallax-img d-flex align-items-center">
        <div class="container">
          <div class="row d-flex justify-content-center">
            <div class="col-md-7 text-center heading-section heading-section-white ftco-animate">
              <h2>${param.lang == 'de' ? 'Abbonieren Sie unseren Newsletter' : 'Subcribe to our Newsletter'}</h2>
              <p>Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam.</p>
              <div class="row d-flex justify-content-center mt-4 mb-4">
                <div class="col-md-8">
                  <form action="#" class="subscribe-form">
                    <div class="form-group d-flex">
                      <input type="text" class="form-control" placeholder="${param.lang == 'de' ? 'Email-Adresse eingeben' : 'Enter email address'}">
                      <input type="submit" value="${param.lang == 'de' ? 'Abbonieren' : 'Subscribe'}" class="submit px-3">
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <footer class="ftco-footer ftco-bg-dark ftco-section">
      <div class="container">
        <div class="row mb-5">
        	<div class="col-md">
             <div class="ftco-footer-widget mb-4">
              <h2 class="ftco-heading-2">${param.lang == 'de' ? '&Uuml;ber Uns' : 'About'}</h2>
              <p>Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam.</p>
              <ul class="ftco-footer-social list-unstyled float-md-left float-lft mt-3">
                <li class="ftco-animate"><a href="#"><span class="icon-twitter"></span></a></li>
                <li class="ftco-animate"><a href="#"><span class="icon-facebook"></span></a></li>
                <li class="ftco-animate"><a href="#"><span class="icon-instagram"></span></a></li>
              </ul>
            </div>
          </div>
          <div class="col-md">
            <div class="ftco-footer-widget mb-4">
              <h2 class="ftco-heading-2">${param.lang == 'de' ? '' : 'Companys'}</h2>
              <ul class="list-unstyled">
                <li><a href="#" class="py-2 d-block">${param.lang == 'de' ? 'Wie es funktioniert' : 'How it works'}</a></li>
                <li><a href="#" class="py-2 d-block">${param.lang == 'de' ? 'Registrieren' : 'Register'}</a></li>
                <li><a href="#" class="py-2 d-block">${param.lang == 'de' ? 'Individuelle Sets' : 'Buy individual set'}</a></li>
                <li><a href="#" class="py-2 d-block">${param.lang == 'de' ? 'Fortgeschrittene Sets' : 'Advanced set components'}</a></li>
                <li><a href="#" class="py-2 d-block">${param.lang == 'de' ? 'Spezielle Sets' : 'Special Service'}</a></li>
                <li><a href="#" class="py-2 d-block">Faq</a></li>
              </ul>
            </div>
          </div>
          <div class="col-md">
            <div class="ftco-footer-widget mb-4 ml-md-4">
              <h2 class="ftco-heading-2">Customers</h2>
              <ul class="list-unstyled">
                <li><a href="#" class="py-2 d-block">${param.lang == 'de' ? 'Wie es funktioniert' : 'How it works'}</a></li>
                <li><a href="#" class="py-2 d-block">${param.lang == 'de' ? 'Registrieren' : 'Register'}</a></li>
                <li><a href="#" class="py-2 d-block">${param.lang == 'de' ? 'Kaufe dein Set' : 'Buy Your Set'}</a></li>
                <li><a href="#" class="py-2 d-block">${param.lang == 'de' ? 'Internet Suchen' : 'Internet Search'}</a></li>
                <li><a href="#" class="py-2 d-block">${param.lang == 'de' ? 'Internet-Paket Suche' : 'Intenet Bundle Search'}</a></li>
              </ul>
            </div>
          </div>
          <div class="col-md">
            <div class="ftco-footer-widget mb-4">
            	<h2 class="ftco-heading-2">${param.lang == 'de' ? 'Noch weitere Fragen?' : 'Have a Questions?'}</h2>
            	<div class="block-23 mb-3">
	              <ul>
	                <li><span class="icon icon-map-marker"></span><span class="text">${param.lang == 'de' ? 'Zossener Str. 55, 10961 Berlin' : '3001 Brighton Blvd, Suite 450, Denver, CO 80216'}</span></li>
	                <li><a href="#"><span class="icon icon-phone"></span><span class="text">${param.lang == 'de' ? '030 664040900' : '+1 415 513 0111'}</span></a></li>
	                <li><a href="#"><span class="icon icon-envelope"></span><span class="text">${param.lang == 'de' ? 'info@camunda.com' : 'contact@camunda.com'}</span></a></li>
	              </ul>
	            </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12 text-center">

            <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
  Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="icon-heart text-danger" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
  <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
          </div>
        </div>
      </div>
    </footer>



  <!-- loader -->
  <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>


  <script src="js/jquery.min.js"></script>
  <script src="js/jquery-migrate-3.0.1.min.js"></script>
  <script src="js/popper.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/jquery.easing.1.3.js"></script>
  <script src="js/jquery.waypoints.min.js"></script>
  <script src="js/jquery.stellar.min.js"></script>
  <script src="js/owl.carousel.min.js"></script>
  <script src="js/jquery.magnific-popup.min.js"></script>
  <script src="js/aos.js"></script>
  <script src="js/jquery.animateNumber.min.js"></script>
  <script src="js/bootstrap-datepicker.js"></script>
  <script src="js/jquery.timepicker.min.js"></script>
  <script src="js/scrollax.min.js"></script>
  <script src="js/main.js"></script>
  <script src="app.js"></script>

  </body>
</html>
