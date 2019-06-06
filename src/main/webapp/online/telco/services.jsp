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
    <link rel="stylesheet" href="css/bootstrap-custom.css">
    <link rel="stylesheet" href="css/jquery.timepicker.css">


    <link rel="stylesheet" href="css/flaticon.css">
    <link rel="stylesheet" href="css/icomoon.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/bootstrap-custom.css">
  </head>
  <body>

    <nav class="navbar navbar-expand-lg ftco-navbar-light w-100 rounded px-auto scrolled awake" style="background: #fff !important;top:0;" id="ftco-navbar">
	    <div class="container rounded">
	      <a class="navbar-brand" href="index.jsp?lang=${param.lang}" style="color:black !important;">Camuntelia</a>
	      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="oi oi-menu"></span> Menu
	      </button>

	      <div class="collapse navbar-collapse" id="ftco-nav">
	        <ul class="navbar-nav ml-auto">
            <li class="nav-item"><a href="index.jsp?lang=${param.lang}" class="nav-link font-weight-bold"  style="color:black !important;">Home</a></li>
	          <li class="nav-item"><a href="index.jsp?lang=${param.lang}" class="nav-link font-weight-bold" style="color:black !important;">${param.lang == 'de' ? '&Uuml;ber' : 'About'}</a></li>
	          <li class="nav-item"><a href="contact.jsp?lang=${param.lang}" class="nav-link font-weight-bold" style="color:black !important;">${param.lang == 'de' ? 'Kontakt' : 'Contact'}</a></li>
	        </ul>
	      </div>
	    </div>
	  </nav>
    <!-- END nav -->

    <div class="hero-wrap" style="background-image: url('images/bg_4.jpg');background-size:100% auto;background-position: 50% 0px;" data-stellar-background-ratio="0.5">
      <div class="container">
        <div class="row no-gutters slider-text  align-items-center" data-scrollax-parent="true">
          <div class="col-xl-12 ftco-animate mb-5 pb-5" data-scrollax=" properties: { translateY: '30%' }">
          	<p class="mb-4 mt-5 pt-5 justify-content-start" data-scrollax="properties: { translateY: '30%', opacity: 1.3 }">${param.lang == 'de' ? 'Es warten <span class="number" data-number="1000000">0</span> mega bytes darauf, Sie zu treffen!' : 'We have <span class="number" data-number="1000000">0</span> mega bytes who are waiting to meet you!'}</p>
            <h1 class="mb-5 justify-content-start" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">${param.lang == 'de' ? 'Unsere Dienstleistungen' : 'Our services are available'}<br>${param.lang == 'de' ? ' sind verf&uuml;gbar!' : ' in your area!'}</h1>

						<div class="ftco-search">
                <!-- choose product category -->
              <div class="container-fluid" style="clear:both;margin-bottom:3em;">
                <div class="row justify-content-between" style="text-align:center;color:black;opacity:0.99;">
                  <div id="basic" class="col-sm-4 col-md-4 col-lg-4 tab-content px-0 productdiv">
                    <div class="mr-2 pb-2">
                      <img src="images/products/onlyOnline_9.jpg" alt="" class="productimg">
                      <h4 class="font-weight-bold">Only online</h4>
                      <ul style="text-align:left;list-style: none;">
                        <li class="accListElem"><img srcset="images/icons/cross-icon.svg" class="smallCross" /><span style="font-weight:550;">&nbsp;${param.lang == 'de' ? 'Kein mobiles Internet' : 'No Mobile Internet'}</span></li>
                        <li class="accListElem"><img srcset="images/icons/check-icon.svg" class="smallCheckmark" /><span>&nbsp;50 mb/s</span></li>
                        <li class="accListElem"><img srcset="images/icons/check-icon.svg" class="bigCheckmark" /><span >&nbsp;${param.lang == 'de' ? 'Bis zu 1000 GB Volumen' : 'Up To 1000 GB Volume'}</span></li>
                        <li class="accListElem"><img srcset="images/icons/check-icon.svg" class="bigCheckmark" /><span style="font-weight:550;">&nbsp;3 ${param.lang == 'de' ? 'Rufnummern' : 'Phone Numbers'}</span></li>
                      </ul>
                      <button id="basicButton" type="button" class="btn btn-primary" style="margin-top:1em;background:#003679;">&nbsp;${param.lang == 'de' ? 'Jetzt Bestellen' : 'Buy Now'}</button>
                    </div>
                  </div>
                  <div id="standard" class="col-sm-4 col-md-4 col-lg-4 tab-content px-0 productdiv">
                    <div class="pb-2">
                      <img src="images/products/onlineEverywhere_8_5.jpg" alt="" class="productimg">
                      <h4 class="font-weight-bold">Online everywhere</h4>
                      <ul style="text-align:left;list-style: none;">
                        <li class="accListElem"><img srcset="images/icons/check-icon.svg" class="smallCheckmark" /><span>&nbsp;${param.lang == 'de' ? '5 GB Mobile Daten' : 'Mobile Internet 5 GB'}</span></li>
                        <li class="accListElem"><img srcset="images/icons/check-icon.svg" class="smallCheckmark" /><span>&nbsp;100 mb/s</span></li>
                        <li class="accListElem"><img srcset="images/icons/check-icon.svg" class="bigCheckmark" /><span style="font-weight:550;">&nbsp;Flatrate</span></li>
                        <li class="accListElem"><img srcset="images/icons/check-icon.svg" class="bigCheckmark" /><span style="font-weight:550;">&nbsp;5 ${param.lang == 'de' ? 'Rufnummern' : 'Phone Numbers'}</span></li>
                      </ul>
                      <button id="standardButton" type="button" class="btn btn-primary" style="margin-top:1em;background:#003679;">&nbsp;${param.lang == 'de' ? 'Jetzt Bestellen' : 'Buy Now'}</button>
                    </div>
                  </div>
                  <div id="premium" class="productdiv px-0 col-sm-4 col-md-4 col-lg-4 tab-content">
                    <div class="pb-2">
                      <img src="images/products/highSpeed_6_5.jpg" alt="" class="productimg">
                      <h4 class="font-weight-bold">Super High Speed</h4>
                      <ul style="text-align:left;list-style: none;">
                        <li class="accListElem"><img srcset="images/icons/check-icon.svg" class="smallCheckmark" /><span>&nbsp;${param.lang == 'de' ? '10 GB Mobile Daten' : 'Mobile Internet 10 GB'}</span></li>
                        <li class="accListElem"><img srcset="images/icons/check-icon.svg" class="smallCheckmark" /><span>&nbsp;250 mb/s</span></li>
                        <li class="accListElem"><img srcset="images/icons/check-icon.svg" class="bigCheckmark" /><span style="font-weight:1000;">&nbsp;Flatrate</span></li>
                          <li class="accListElem"><img srcset="images/icons/check-icon.svg" class="bigCheckmark" /><span style="font-weight:1000;">&nbsp;10 ${param.lang == 'de' ? 'Rufnummern' : 'Phone Numbers'}</span></li>
                      </ul>
                      <button id="premiumButton" type="button" class="btn btn-primary" style="margin-top:1em;background:#003679;">&nbsp;${param.lang == 'de' ? 'Jetzt Bestellen' : 'Buy Now'}</button>
                    </div>
                  </div>
                </div>
              </div>

		        </div>
          </div>
        </div>
      </div>
      <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 tab-content mt-5 py-3 w-100 bg-white" style="margin:auto;color:black;" id="contact-form" style="margin-bottom: 6em;margin-left: 15em;clear:both;">

      <div class="divider"></div>
      <div id="fieldsetForm">
         <form class="reply justify-content-between" id="contact">
            <fieldset class="w-75 m-auto">
               <h3 class="title">${param.lang == 'de' ? 'Anmelden' : 'Sign Up'}</h3>
               <div class="w-75">
               <div class="row">

                  <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                     <label>Name: <span>*</span></label>
                     <input class="form-control" id="applicant" name="name" type="text" value="" required>
                  </div>

                  <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                    <label>${param.lang == 'de' ? 'Anstellungsverh&auml;ltnis' : 'Employment'}: <span>*</span></label>
                    <select class="form-control" id="employment">
                       <option value="${param.lang == 'de' ? 'Fest angestellt' : 'Salaried'}"  selected>${param.lang == 'de' ? 'Fest angestellt' : 'Salaried'}</option>
                       <option value="${param.lang == 'de' ? 'Selbstst&auml;ndig' : 'Self-employed'}">${param.lang == 'de' ? 'Selbstst&auml;ndig' : 'Self-employed'}</option>
                       <option value="${param.lang == 'de' ? 'Teilzeit' : 'Part-time'}">${param.lang == 'de' ? 'Teilzeit' : 'Part-time'}</option>
                       <option value="Freelancer">Freelancer</option>
                       <option value="${param.lang == 'de' ? 'Nicht erwerbst&auml;tig' : 'Unemployed'}">${param.lang == 'de' ? 'Nicht erwerbst&auml;tig' : 'Unemployed'}</option>
                    </select>
                  </div>
               </div>

               <div class="row mt-4">

                  <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                    <label>${param.lang == 'de' ? 'Geburtstag' : 'Date of Birth'}: <span>*</span></label>
                    <input class="form-control" type="date" id="birthdate" name="birthdate" value="1980-01-01" required>
                  </div>
               </div>

               <div class="row mt-4">
                  <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                     <label>Email: <span>*</span></label>
                     <input class="form-control" type="email" id="email" name="email" value="" required>
                  </div>

                  <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                    <label>${param.lang == 'de' ? 'Paket' : 'Internet Package Type'}: <span>*</span></label>
                    <select class="form-control" id="category" disabled style="background:white;-webkit-appearance:none;">
                       <option value="${param.lang == 'de' ? 'Basispaket' : 'Basic Package'}">Only online</option>
                       <option value="${param.lang == 'de' ? 'Standard Paket' : 'Standard Package'}" selected>Online everywhere</option>
                       <option value="${param.lang == 'de' ? 'Premium Paket' : 'Premium Package'}">Super High Speed</option>
                    </select>
                  </div>
               </div>

               <div class="row mt-4">
                  <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                     <label>${param.lang == 'de' ? 'Geschlecht' : 'Gender'}: <span>*</span></label>
                     <select class="form-control" id="selectSex" name="selectSex">
                       <option value="${param.lang == 'de' ? 'Frau' : 'female'}">${param.lang == 'de' ? 'Frau' : 'female'}</option>
                       <option value="${param.lang == 'de' ? 'Mann' : 'male'}">${param.lang == 'de' ? 'Mann' : 'male'}</option>
                     </select>
                  </div>

                  <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                    <label>${param.lang == 'de' ? 'Preisindikation' : 'Price Indication'}: </label>
                    <input class="form-control" type="text" id="priceIndicationInCent" name="preisindikation" value="5,00 &euro;" readonly>
                  </div>
               </div>
               <div class="row text-right mt-4">
                 <div class="col-md-8"></div>
                 <div class="col-md-4 form-control border-0">
                   <button type="button" id="triggerStartApplication" class="btn btn-primary py-2 w-50" type="button">${param.lang == 'de' ? 'Senden' : 'Send'}</button>
                 </div>
               </div>
             </div>
            </fieldset>

         </form>
         </div>

         <div id="applicationReceived" class="success alert-success alert" style="display:none;text-align:center;line-height:500%;font-size:1.8em;">
               <div>${param.lang == 'de' ? 'Anfrage erhalten - wir kontaktieren Sie innerhalb k&uuml;rzester Zeit!' : 'Application received - we get in touch with you shortly'}!</div>
               <div>${param.lang == 'de' ? 'Ihre Anfragfen-Id ist' : 'Your application id is'}: <span id="applicationId"></span></div>
         </div>

         <div class="clearfix">
         </div>

      </div>
    </div>

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
