<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title -->
    <title>Camunbankia ${param.lang == 'de' ? 'fiktionales GmbH' :'- The Business Loan fictionality'}</title>

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
                            <a href="#" data-toggle="tooltip" data-placement="bottom" title="Zossener Str. 55, 10961 Berlin"><img src="img/core-img/placeholder.png" alt=""> <span>${param.lang == 'de' ? 'Zossener Str. 55, 10961 Berlin' :'2301 Blake Street, Denver, CO 80205'}</span></a>
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
                                            <li><a href="services.jsp?lang=${param.lang}">${param.lang == 'de' ? 'Kredite' : 'Bank Loans'}</a></li>
                                            <li><a href="documents.jsp?lang=${param.lang}">${param.lang == 'de' ? 'Dokument einreichen' : 'Hand In Documents'}</a></li>
                                            <li><a >${param.lang == 'de' ? 'Einfacher Post' : 'Single Post'}</a></li>
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
                            <a href="#"><img src="img/core-img/call2.png" alt="">${param.lang == 'de' ? '030 664040900' :'+1 619 876 7897'}</a>
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
                        <h2>${param.lang == 'de' ? 'Neuigkeiten' : 'News'}</h2>
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="#">Home</a></li>
                                <li class="breadcrumb-item active" aria-current="page">${param.lang == 'de' ? 'Neuigkeiten' : 'News'}</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ##### Breadcrumb Area End ##### -->

    <!-- Main Content start-->
                <div class="content" style="margin-top:5em;margin-left:5em;">
                   <div class="">
                      <div class="row">

                        <div>
                        <div style="float:left;width:40%;">
                            <!-- Single Service Area -->
                            <div class="col-md-6" style="max-width:100%;">
                                <div class="single-service-area d-flex mb-100">
                                    <div class="icon">
                                        <i class="icon-money-1"></i>
                                    </div>
                                    <div class="text">
                                        <h5>${param.lang == 'de' ? 'Einfache und schnelle Antworten' : 'Easy and fast answer'}</h5>
                                        <p>Morbi ut dapibus dui. Sed ut iaculis elit, quis varius mauris. Integer ut ultricies orci, lobortis egestas sem.</p>
                                    </div>
                                </div>
                            </div>

                            <!-- Single Service Area -->
                            <div class="col-md-6" style="max-width:100%;">
                                <div class="single-service-area d-flex mb-100">
                                  <div class="icon">
                                      <i class="icon-smartphone-1"></i>
                                  </div>
                                    <div class="text">
                                        <h5>${param.lang == 'de' ? 'Kein weiterer Papierkram' : 'No additional papers'}</h5>
                                        <p>Morbi ut dapibus dui. Sed ut iaculis elit, quis varius mauris. Integer ut ultricies orci, lobortis egestas sem.</p>
                                    </div>
                                </div>
                            </div>
                        </div>

                         <div class="col-md-8" id="contact-form" style="max-width: 100%;padding-left:40em;">
                            <form method="post" class="reply" id="contact">
                               <div id="fieldsetForm" style="margin-left:5em;">
                               <fieldset>
                                  <div class="row" >
                                     <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12" style="margin-bottom:2em;">
                                        <label style="margin-bottom:0.5em;">${param.lang == 'de' ? 'Vorgangsnummer' : 'Reference Number'}: <span>*</span></label>
                                        <input class="form-control" id="referenceId" name="name" type="text" value="" required>
                                     </div>
                                  </div>

                                  <div class="row">
                                     <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                    <label style="margin-bottom:0.5em;">PDF ${param.lang == 'de' ? 'Dokument' : 'Document'}: <span>*</span></label>
    		                            <input style="margin-bottom:2em;" id="documentToUpload" name="Dokument hochladen" type="file" accept="application/pdf,application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document"/>
                                     </div>
                                  </div>

                               <button id="triggerUploadDocuments" class="btn btn-normal btn-color submit  bottom-pad" type="button">${param.lang == 'de' ? 'Senden' : 'Send'}</button>
                               </fieldset>
                               </div>
                               <div id="documentsReceived" class="success alert-success alert" style="display:none">${param.lang == 'de' ? 'Dokumente erhalten - wir kontaktieren Sie in k&uuml;rzester Zeit' : 'Documents received - we reach out to you shortly'}!</div>
                               <div class="clearfix">
                               </div>
                            </form>
                         </div>

                       </div>
                      </div>
                      <div class="divider"></div>
                   </div>
                </div>
                <!-- Main Content end-->

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
                                <li><a href="#">${param.lang == 'de' ? 'Unsere Kredite' : 'Our Loans'}</a></li>
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
                                <li><a href="#">${param.lang == 'de' ? 'Unsere Kredite' : 'Our Loans'}</a></li>
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
                                <a href="#">${param.lang == 'de' ? 'Wie bekommt man den besten Kredit' : 'How to get the best loan'}?</a>
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
                                <a href="#">${param.lang == 'de' ? 'Neue Wege f&uuml;r neue Kredite' : 'A new way to get a loan'}</a>
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
</body>

</html>
