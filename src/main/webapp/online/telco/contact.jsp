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

    <link rel="stylesheet" href="css/bootstrap-custom.css">
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
          <span class="oi oi-menu"></span> Menu
        </button>

        <div class="collapse navbar-collapse" id="ftco-nav">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item"><a href="index.jsp?lang=${param.lang}" class="nav-link font-weight-bold"  style="color:black !important;">Home</a></li>
	          <li class="nav-item"><a href="index.jsp?lang=${param.lang}" class="nav-link font-weight-bold" style="color:black !important;">${param.lang == 'de' ? '&Uuml;ber' : 'About'}</a></li>
	          <li class="nav-item active"><a href="contact.jsp?lang=${param.lang}" class="nav-link font-weight-bold" style="color:black !important;">${param.lang == 'de' ? 'Kontakt' : 'Contact'}</a></li>
          </ul>
        </div>
      </div>
    </nav>
    <!-- END nav -->

    <div class="hero-wrap" style="background-image: url('images/bg_2.jpg');height:929px;background-position: 50% 0px;" data-stellar-background-ratio="0.5">
      <div class="overlay" style="opacity:.4;position:fixed;"></div>
      <div class="container">
        <div class="row no-gutters slider-text align-items-start justify-content-start mt-5" data-scrollax-parent="true">
          <div class="col-md-8 mt-5 pt-5 ftco-animate text-center text-md-left mb-2" data-scrollax=" properties: { translateY: '30%' }">${param.lang == 'de' ? '' : ''}
            <h1 class="mb-3 bread font-weight-bold" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">${param.lang == 'de' ? 'Kontakt' : 'Contact Us'}</h1>
          </div>
        <div class="row w-100  bg-white" style="border-radius:15px;">
          <div class="container rounded">
            <div class="row justify-content-between">
              <div class="p-5 rounded-left bg-white col-md-5 text-left bg-white text-dark">
                <div class="mb-4">
                  <h3>${param.lang == 'de' ? 'Kontaktinformationen' : 'Contact Information'}</h3>
                </div>
                <div class="w-100"></div>
                <div  class="mb-5">
                  <span>${param.lang == 'de' ? 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et.' : 'A small river named Duden flows by their place and supplies.Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in.'}</span>
                </div>
                <div class="col-md-12">
                  <div class="bulletpoint-no-point">${param.lang == 'de' ? 'Adresse' : 'Address'}: <span >${param.lang == 'de' ? 'Zossener Str. 55, 10961 Berlin' : '3001 Brighton Blvd, Suite 450, Denver, CO 80216'}</span></div>
                </div>
                <div class="col-md-12 bg-white"></div>
                <div class="col-md-12">
                  <div><span class="bulletpoint-no-point">${param.lang == 'de' ? 'Telefon' : 'Phone'}: <a href="tel://1234567920" >${param.lang == 'de' ? '030 664040900' : '+1 415 513 0111'}</a></span></div>
                </div>
                <div class="col-md-12">
                  <div><span class="bulletpoint-no-point">Email:<a href="mailto:info@yoursite.com" > ${param.lang == 'de' ? 'info@camunda.com' : 'contact@camunda.com'}</a></span> </div>
                </div>
              </div>
              <div class="col-md-1 bg-white"><div style="height:12.5%;"></div> <div class="border-right h-75 align-text-top"></div> </div>
              <div class="col-md-6 bg-white rounded-right">
                <form action="#" class="p-5 contact-form rounded">
                  <div class="form-group  text-dark">
                    <label>${param.lang == 'de' ? 'Referenznummer' : 'Reference number'}</label>
                    <input type="text" class="form-control" placeholder="${param.lang == 'de' ? 'Ihre Referenznummer' : 'Your reference number'}">
                  </div>
                  <div class="form-group mt-4">
                    <label class="text-dark">${param.lang == 'de' ? 'Dokument w&auml;hlen' : 'Choose your document'}</label>
                    <textarea class="form-control" style="border-bottom-left-radius: 0;" placeholder="${param.lang == 'de' ? 'Weitere Informationen' : 'Additional information'}" cols="30" rows="7"></textarea>
                    <div class="input-group">
                      <div class="row w-100 m-0">
                        <div class="custom-file col-md-6">
                          <input type="file" class="custom-file-input" lang="${param.lang}" id="documentToUpload" name="Dokument hochladen" accept="application/pdf,application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document"
                            aria-describedby="inputGroupFileAddon01">
                          <label class="custom-file-label border-top-0" style="border-top-left-radius:0;border-top-right-radius:0;" for="documentToUpload">${param.lang == 'de' ? 'Dokument w&auml;hlen' : 'Choose file'}</label>
                        </div>
                        <div class="col-md-6 mt-auto text-center">
                          <span id="fileDesc" class="span-decoration-underlined w-75 text-secondary" style="overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">${param.lang == 'de' ? 'Ihr Dokument...' : 'Your document...'}</span>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="form-group text-right">
                    <button value="Send Message" class="btn btn-primary py-2 px-4">${param.lang == 'de' ? 'Versenden' : 'Send Message'}</button>
                  </div>
                </form>

              </div>
            </div>
          </div>
        </div>
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
