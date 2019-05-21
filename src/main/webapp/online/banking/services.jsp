<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title -->
    <title>Camunbankia - The Business Account Company</title>

    <!-- Favicon -->
    <link rel="icon" href="img/core-img/favicon.ico">

    <!-- Stylesheet -->
    <link rel="stylesheet" href="style.css">

</head>

<body>
    <!-- Preloader -->
    <div class="preloader d-flex align-items-center justify-content-center">
        <div class="lds-ellipsis">
            <div></div>
            <div></div>
            <div></div>
            <div></div>
        </div>
    </div>

    <!-- ##### Header Area Start ##### -->
    <header class="header-area">
        <!-- Top Header Area -->
        <div class="top-header-area">
            <div class="container h-100">
                <div class="row h-100 align-items-center">
                    <div class="col-12 d-flex justify-content-between">
                        <!-- Logo Area -->
                        <div class="logo">
                            <a href="index.jsp"><img src="img/core-img/logo.png" alt=""></a>
                        </div>

                        <!-- Top Contact Info -->
                        <div class="top-contact-info d-flex align-items-center">
                            <a href="#" data-toggle="tooltip" data-placement="bottom" title="Zossener Str. 55, 10961 Berlin"><img src="img/core-img/placeholder.png" alt=""> <span>${param.lang == 'de' ? 'Zossener Str. 55, 10961 Berlin' :'3001 Brighton Blvd, Suite 450, Denver, CO 80216'}</span></a>
                            <a href="#" data-toggle="tooltip" data-placement="bottom" title="info@camunda.com"><img src="img/core-img/message.png" alt=""> <span>${param.lang == 'de' ? 'info@camunda.com' :'contact@camunda.com'}</span></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Navbar Area -->
        <div class="credit-main-menu" id="sticker">
            <div class="classy-nav-container breakpoint-off">
                <div class="container">
                    <!-- Menu -->
                    <nav class="classy-navbar justify-content-between" id="creditNav">

                        <!-- Navbar Toggler -->
                        <div class="classy-navbar-toggler">
                            <span class="navbarToggler"><span></span><span></span><span></span></span>
                        </div>

                        <!-- Menu -->
                        <div class="classy-menu">

                            <!-- Close Button -->
                            <div class="classycloseIcon">
                                <div class="cross-wrap"><span class="top"></span><span class="bottom"></span></div>
                            </div>

                            <!-- Nav Start -->
                            <div class="classynav">
                                <ul>
                                    <li><a href="index.jsp?lang=${param.lang}">Home</a></li>
                                    <li><a href="#">${param.lang == 'de' ? 'Seiten' :'Pages'}</a>
                                        <ul class="dropdown">
                                            <li><a >${param.lang == 'de' ? '&Uuml;ber Uns' : 'About Us'}</a></li>
                                            <li><a href="services.jsp?lang=${param.lang}">${param.lang == 'de' ? 'Kredite' : 'Bank Accounts'}</a></li>
                                            <li><a href="documents.jsp?lang=${param.lang}">${param.lang == 'de' ? 'Dokument einreichen' : 'Hand In Documents'}</a></li>
                                            <li><a >${param.lang == 'de' ? 'Kontakt' : 'Contact'}</a></li>
                                            <li><a >${param.lang == 'de' ? 'Weitere Elemente' : 'Elements'}</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                            <!-- Nav End -->
                        </div>

                        <!-- Contact -->
                        <div class="contact">
                            <a href="#"><img src="img/core-img/call2.png" alt="">${param.lang == 'de' ? '030 664040900' :'+1 415 513 0111'}</a>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
    </header>
    <!-- ##### Header Area End ##### -->

    <!-- ##### Breadcrumb Area Start ##### -->
    <section class="breadcrumb-area bg-img bg-overlay jarallax" style="background-image: url(img/bg-img/13.jpg);">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <div class="breadcrumb-content">
                        <h2>Services</h2>
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="#">Home</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Services</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ##### Breadcrumb Area End ##### -->

    <!-- ##### Services Area Start ###### -->
    <section class="services-area section-padding-100-0" style="margin-bottom:6em;">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <!-- Section Heading -->
                    <div class="section-heading text-center ">
                        <p>${param.lang == 'de' ? 'Informieren Sie sich &uuml;ber unsere' : 'Take look at our'}</p>
                    </div>
                </div>
            </div>

            <!-- Single Service Area -->
            <div class="section-heading text-center">
                <div class="single-service-area">
                    <div class="icon" style="margin-left:17.5em;">
                        <i class="icon-coin" style="width=60px;"></i>
                    </div>
                    <div class="text" style="">
                      <h3 style="color:black;margin-bottom:2em;margin-left:${param.lang == 'de' ? 17 : 16}em;float:left;" class="text" >${param.lang == 'de' ? 'Kontotypen' : 'Account Types'}</h3>
                    </div>
                </div>
            </div>

            <!-- choose product category -->

            <div class="container-fluid" style="clear: both;margin-bottom:3em;">
              <div class="row" style="text-align:center;">
                <div class="col-sm-4 col-md-4 col-lg-4">
                  <h4>${param.lang == 'de' ? 'Only online' : 'Only online'}</h4>
                  <img src="img/laptop_8.jpg" alt="" style="margin-bottom:2em;">
                  <ul style="text-align:left;">
                    <li class="accListElem">❌<span> ${param.lang == 'de' ? 'Bargeldausgabe' : 'Cash Money'}</span></li>
                    <li class="accListElem">❌<span > ${param.lang == 'de' ? 'Dispokredit' : 'Overdraft Facility'}</span></li>
                    <li class="accListElem">✅<span style="font-weight:550;"> ${param.lang == 'de' ? 'Nur 3€/Monat' : 'Only 3$ monthly'}</span></li>
                    <li class="accListElem">✅<span style="font-weight:550;"> ${param.lang == 'de' ? 'Einfacher Online-Zugang' : 'Easy Online-Acces'}</span></li>

                  </ul>
                  <button id="basicButton" type="button" class="btn btn-primary" style="margin-top:1em;background:#003679;">${param.lang == 'de' ? 'W&auml;hlen' : 'choose'}</button>
                </div>
                <div class="col-sm-4 col-md-4 col-lg-4">
                  <h4>${param.lang == 'de' ? 'Aktivkonto' : 'Active Account'}</h4>
                  <img src="img/bank_7.jpg" alt="" style="margin-bottom:2em;">
                  <ul style="text-align:left;">
                    <li class="accListElem">✅<span> ${param.lang == 'de' ? 'Bargeldausgabe' : 'Cash Money'}</span></li>
                    <li class="accListElem">✅<span> ${param.lang == 'de' ? 'Nur 5€/Monat' : 'Only 5$ monthly'}</span></li>
                    <li class="accListElem">✅<span style="font-weight:550;"> ${param.lang == 'de' ? 'Dispokredit bis 1000 €' : 'Overdraft Facility up to 1000$'}</span></li>
                    <li class="accListElem">✅<span style="font-weight:550;"> ${param.lang == 'de' ? 'Mastercard' : 'Mastercard'}</span></li>
                  </ul>
                  <button id="standardButton" type="button" class="btn btn-primary" style="margin-top:1em;background:#003679;">${param.lang == 'de' ? 'W&auml;hlen' : 'choose'}</button>
                </div>
                <div class="col-sm-4 col-md-4 col-lg-4">
                  <h4>${param.lang == 'de' ? 'Premium Konto' : 'Premium Account'}</h4>
                  <img src="img/skyscraper_6.jpg" alt="" style="margin-bottom:2em;">
                  <ul style="text-align:left;">
                    <li class="accListElem">✅<span> ${param.lang == 'de' ? 'Bargeldausgabe' : 'Cash Money'}</span></li>
                    <li class="accListElem">✅<span> ${param.lang == 'de' ? '15€/Monat' : 'Only 15$ monthly'}</span></li>
                    <li class="accListElem">✅<span style="font-weight:1000;"> ${param.lang == 'de' ? 'Dispokredit bis 5000 €' : 'Overdraft Facility up to 5000$'}</span></li>
                    <li class="accListElem">✅<span style="font-weight:1000;"> ${param.lang == 'de' ? 'Platin-Mastercard' : 'Platin-Mastercard'}</span></li>
                  </ul>
                  <button id="premiumButton" type="button" class="btn btn-primary" style="margin-top:1em;background:#003679;">${param.lang == 'de' ? 'W&auml;hlen' : 'choose'}</button>
                </div>
              </div>
            </div>

            <div class="col-lg-8 col-md-8 col-sm-6 col-xs-12" id="contact-form" style="margin-bottom: 6em;margin-left: 15em;clear:both;">
            <h3 class="title">${param.lang == 'de' ? 'Konto er&ouml;ffnen' : 'Open An Account'}</h3>
            <div class="divider"></div>
            <div id="fieldsetForm">
               <form method="post" class="reply" id="contact">
                  <fieldset>
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
                             <option value="Freelancer">${param.lang == 'de' ? 'Freelancer' : 'Freelancer'}</option>
                             <option value="${param.lang == 'de' ? 'Nicht erwerbst&auml;tig' : 'Unemployed'}">${param.lang == 'de' ? 'Nicht erwerbst&auml;tig' : 'Unemployed'}</option>
                          </select>
                        </div>
                     </div>

                     <div class="row" style="margin-top:2em;">

                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                          <label>${param.lang == 'de' ? 'Geburtsdatum' : 'Date of Birth'}: <span>*</span></label>
                          <input class="form-control" type="date" id="birthdate" name="birthdate" value="1980-01-01" required>
                        </div>
                     </div>

                     <div class="row" style="margin-top:2em;">
                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                           <label>Email: <span>*</span></label>
                           <input class="form-control" type="email" id="email" name="email" value="" required>
                        </div>

                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                          <label>${param.lang == 'de' ? 'Typ' : 'Account Type'}: <span>*</span></label>
                          <select class="form-control" id="category" disabled style="background:white;-webkit-appearance:none;">
                             <option value="${param.lang == 'de' ? 'Basispaket' : 'Basic Package'}">${param.lang == 'de' ? 'Only online' : 'Only online'}</option>
                             <option value="${param.lang == 'de' ? 'Standard Paket' : 'Standard Package'}" selected>${param.lang == 'de' ? 'Aktivkonto' : 'Active Account'}</option>
                             <option value="${param.lang == 'de' ? 'Premium Paket' : 'Premium Package'}">${param.lang == 'de' ? 'Premium Konto' : 'Premium Account'}</option>
                          </select>
                        </div>
                     </div>

                     <div class="row" style="margin-top:2em;">
                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                           <label>${param.lang == 'de' ? 'Geschlecht' : 'Gender'}: <span>*</span></label>
                           <select class="form-control" id="selectSex" name="selectSex">
                             <option value="${param.lang == 'de' ? 'Frau' : 'female'}">${param.lang == 'de' ? 'weiblich' : 'female'}</option>
               					     <option value="${param.lang == 'de' ? 'Mann' : 'male'}">${param.lang == 'de' ? 'm&auml;nnlich' : 'male'}</option>
                           </select>
                        </div>

                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                          <label>${param.lang == 'de' ? 'Unverbindliche Preisindikation' : 'Price Indication (non-binding)'}: </label>
                           <input class="form-control" type="text" id="priceIndicationInCent" name="preisindikation" value="3,00 EUR;" readonly>
                        </div>
                     </div>

                  </fieldset>
                  <button id="triggerStartApplication" class="btn btn-normal btn-color submit" style="background-color:#003679;color:white;margin-top:1.5em;" type="button">${param.lang == 'de' ? 'Versenden' : 'Send'}</button>
               </form>
                  </div>

                  <div id="applicationReceived" class="success alert-success alert" style="display:none">
                     <p>Application received - we get in touch with you shortly!</p>
                     <p>Your application id is: <span id="applicationId"></span></p>
                   </div>
                  <div class="clearfix">
                  </div>
            </div>

        </div>
    </section>
    <!-- ##### Services Area End ###### -->

    <!-- ##### Newsletter Area Start ###### -->
    <section class="newsletter-area section-padding-100 bg-img jarallax" style="background-image: url(img/bg-img/6.jpg);">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-12 col-sm-10 col-lg-8">
                    <div class="nl-content text-center">
                        <h2>${param.lang == 'de' ? 'Abboniere unseren Newsletter' : 'Subscribe to our newsletter'}</h2>
                        <form action="#" method="post">
                            <input type="email" name="nl-email" id="nlemail" placeholder="Your e-mail">
                            <button type="submit">${param.lang == 'de' ? 'Abbonieren' : 'Subscribe'}</button>
                        </form>
                        <p>Curabitur elit turpis, maximus quis ullamcorper sed, maximus eu neque. Cras ultrices erat nec auctor blandit.</p>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ##### Newsletter Area End ###### -->

    <!-- ##### Footer Area Start ##### -->
    <footer class="footer-area section-padding-100-0">
        <div class="container">
            <div class="row">

                <!-- Single Footer Widget -->
                <div class="col-12 col-sm-6 col-lg-3">
                    <div class="single-footer-widget mb-100">
                        <h5 class="widget-title">${param.lang == 'de' ? '&Uuml;ber uns' : 'About Us'}</h5>
                        <!-- Nav -->
                        <nav>
                            <ul>
                                <li><a href="#">Homepage</a></li>
                                <li><a href="#">${param.lang == 'de' ? '&Uuml;ber uns' : 'About Us'}</a></li>
                                <li><a href="#">${param.lang == 'de' ? 'Service &amp' : 'Services &amp; Offers'}</a></li>
                                <li><a href="#">${param.lang == 'de' ? 'Portfolio Pr&auml;sentation' : 'Portfolio Presentation'}</a></li>
                                <li><a href="#">${param.lang == 'de' ? 'Neuigkeiten' : 'The News'}</a></li>
                            </ul>
                        </nav>
                    </div>
                </div>

                <!-- Single Footer Widget -->
                <div class="col-12 col-sm-6 col-lg-3">
                    <div class="single-footer-widget mb-100">
                        <h5 class="widget-title">${param.lang == 'de' ? 'L&ouml;sungen' : 'Solutions'}</h5>
                        <!-- Nav -->
                        <nav>
                            <ul>
                                <li><a href="#">${param.lang == 'de' ? 'Unsere Kredite' : 'Our Accounts'}</a></li>
                                <li><a href="#">${param.lang == 'de' ? 'kommerzielles Handeln' : 'Trading &amp; Commerce'}</a></li>
                                <li><a href="#">${param.lang == 'de' ? 'Banking &amp; Private Equity' : 'Banking &amp; Private Equity'}</a></li>
                                <li><a href="#">${param.lang == 'de' ? 'Industrie &amp; Fabrik' : 'Industrial &amp; Factory'}</a></li>
                                <li><a href="#">${param.lang == 'de' ? 'Finanzl&ouml;sungen' : 'Financial Solutions'}</a></li>
                            </ul>
                        </nav>
                    </div>
                </div>

                <!-- Single Footer Widget -->
                <div class="col-12 col-sm-6 col-lg-3">
                    <div class="single-footer-widget mb-100">
                        <h5 class="widget-title">${param.lang == 'de' ? 'L&ouml;sungen' : 'Solutions'}</h5>
                        <!-- Nav -->
                        <nav>
                            <ul>
                                <li><a href="#">${param.lang == 'de' ? 'Unsere Kredite' : 'Our Accounts'}</a></li>
                                <li><a href="#">${param.lang == 'de' ? 'kommerzielles Handeln' : 'Trading &amp; Commerce'}</a></li>
                                <li><a href="#">${param.lang == 'de' ? 'Banking &amp; Private Equity' : 'Banking &amp; Private Equity'}</a></li>
                                <li><a href="#">${param.lang == 'de' ? 'Industrie &amp Fabrik;' : 'Industrial &amp; Factory'}</a></li>
                                <li><a href="#">${param.lang == 'de' ? 'Finanzl&ouml;sungen' : 'Financial Solutions'}</a></li>
                            </ul>
                        </nav>
                    </div>
                </div>

                <!-- Single Footer Widget -->
                <div class="col-12 col-sm-6 col-lg-3">
                    <div class="single-footer-widget mb-100">
                        <h5 class="widget-title">${param.lang == 'de' ? 'Das neuest vom Neuesten' : 'Latest News'}</h5>

                        <!-- Single News Area -->
                        <div class="single-latest-news-area d-flex align-items-center">
                            <div class="news-thumbnail">
                                <img src="img/bg-img/7.jpg" alt="">
                            </div>
                            <div class="news-content">
                                <a href="#">${param.lang == 'de' ? 'Wie bekommt man den besten Kredit' : 'How to get the best Account'}?</a>
                                <div class="news-meta">
                                    <a href="#" class="post-author"><img src="img/core-img/pencil.png" alt=""> Jane Smith</a>
                                    <a href="#" class="post-date"><img src="img/core-img/calendar.png" alt=""> April 26</a>
                                </div>
                            </div>
                        </div>

                        <!-- Single News Area -->
                        <div class="single-latest-news-area d-flex align-items-center">
                            <div class="news-thumbnail">
                                <img src="img/bg-img/8.jpg" alt="">
                            </div>
                            <div class="news-content">
                                <a href="#">${param.lang == 'de' ? 'Neue Wege f&uuml;r neue Kredite' : 'A new way to get a Account'}</a>
                                <div class="news-meta">
                                    <a href="#" class="post-author"><img src="img/core-img/pencil.png" alt=""> Jane Smith</a>
                                    <a href="#" class="post-date"><img src="img/core-img/calendar.png" alt=""> April 26</a>
                                </div>
                            </div>
                        </div>

                        <!-- Single News Area -->
                        <div class="single-latest-news-area d-flex align-items-center">
                            <div class="news-thumbnail">
                                <img src="img/bg-img/9.jpg" alt="">
                            </div>
                            <div class="news-content">
                                <a href="#">${param.lang == 'de' ? 'Finanzier dir dein Eigenheim' : 'Finance you home'}</a>
                                <div class="news-meta">
                                    <a href="#" class="post-author"><img src="img/core-img/pencil.png" alt=""> Jane Smith</a>
                                    <a href="#" class="post-date"><img src="img/core-img/calendar.png" alt=""> April 26</a>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>

        <!-- Copywrite Area -->
        <div class="copywrite-area">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="copywrite-content d-flex flex-wrap justify-content-between align-items-center">
                            <!-- Footer Logo -->
                            <a href="index.jsp" class="footer-logo"><img src="img/core-img/logo.png" alt=""></a>

                            <!-- Copywrite Text -->
                            <p class="copywrite-text"><a href="#"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </footer>
    <!-- ##### Footer Area Start ##### -->

    <!-- ##### All Javascript Script ##### -->
    <!-- jQuery-2.2.4 js -->
    <script src="js/jquery/jquery-2.2.4.min.js"></script>
    <!-- Popper js -->
    <script src="js/bootstrap/popper.min.js"></script>
    <!-- Bootstrap js -->
    <script src="js/bootstrap/bootstrap.min.js"></script>
    <!-- All Plugins js -->
    <script src="js/plugins/plugins.js"></script>
    <!-- Active js -->
    <script src="js/active.js"></script>

    <%@ include file="../js/scripts.inc" %>
</body>

</html>
