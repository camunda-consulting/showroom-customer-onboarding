<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<!--[if IE 8]>			<html class="ie ie8"> <![endif]-->
<!--[if IE 9]>			<html class="ie ie9"> <![endif]-->
<!--[if gt IE 9]><!-->	<html> <!--<![endif]-->
   <head>
      <meta charset="utf-8">
      <title>Camundanzia</title>
      <meta name="description" content="Pixma Responsive HTML5/CSS3 Template from FIFOTHEMES.COM">
      <meta name="author" content="FIFOTHEMES.COM">
      <!-- Mobile Metas -->
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <!-- Google Fonts -->
      <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'>
      <link href='http://fonts.googleapis.com/css?family=Lato:400,300,400italic,700,300italic' rel='stylesheet' type='text/css'>
      <!-- Library CSS -->
      <link rel="stylesheet" href="css/bootstrap.css">
      <link rel="stylesheet" href="css/bootstrap-theme.css">
      <link rel="stylesheet" href="css/fonts/font-awesome/css/font-awesome.css">
      <link rel="stylesheet" href="css/animations.css" media="screen">
      <link rel="stylesheet" href="css/superfish.css" media="screen">
      <link rel="stylesheet" href="css/revolution-slider/css/settings.css" media="screen">
      <link rel="stylesheet" href="css/prettyPhoto.css" media="screen">
      <!-- Theme CSS -->
      <link rel="stylesheet" href="css/style.css">
      <link rel="stylesheet" href="css/global.css">
      <!-- Skin -->
      <link rel="stylesheet" href="css/colors/blue.css" class="colors">
      <!-- Responsive CSS -->
      <link rel="stylesheet" href="css/theme-responsive.css">
      <!-- Favicons -->
      <link rel="shortcut icon" href="img/ico/favicon.ico">
      <link rel="apple-touch-icon" href="img/ico/apple-touch-icon.png">
      <link rel="apple-touch-icon" sizes="72x72" href="img/ico/apple-touch-icon-72.png">
      <link rel="apple-touch-icon" sizes="114x114" href="img/ico/apple-touch-icon-114.png">
      <link rel="apple-touch-icon" sizes="144x144" href="img/ico/apple-touch-icon-144.png">
      <!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
      <!--[if lt IE 9]>
      <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
      <script src="js/respond.min.js"></script>
      <![endif]-->
      <!--[if IE]>
      <link rel="stylesheet" href="css/ie.css">
      <![endif]-->
   </head>
   <body class="boxed home">
      <div class="page-mask">
            <div class="page-loader"> 

                <div class="spinner"></div>
                Loading...
            </div>

      </div>
      <div class="wrap">
         <!-- Header Start -->
         <header id="header">
<%@ include file="headerTopBar.inc" %>            
<%@ include file="mainHeader.inc" %>
		</header>
         <!-- Header End --> 
         <!-- Content Start -->
         <div id="main">
            <!-- Slider Start-->
            <div class="fullwidthbanner-container">
               <div class="fullwidthbanner rslider">
                  <ul>
                     <!-- THE FIRST SLIDE -->
                      <li data-transition="fade" data-slotamount="7" data-masterspeed="500"  data-saveperformance="on">
                      <img src="img/slider/slider-bg-2.jpg"  alt="Pixma" data-lazyload="img/slider/slider-bg-2.jpg" data-bgposition="center top" data-bgfit="cover" data-bgrepeat="no-repeat">

                        <div class="caption modern_big_bluebg h1 lft tp-caption start"
                           data-x="40"
                           data-y="85"
                           data-speed="700"
                           data-endspeed="800"
                           data-start="0"
                           data-easing="easeOutQuint"
                           data-endeasing="easeOutQuint">
                           <img src="img/Camundanzia_Logo_Web_w.png" height="132" />
                        </div>

                        <div class="caption list_slide lfb tp-caption start" 
                           data-easing="easeOutExpo" 
                           data-start="0" 
                           data-speed="1050" 
                           data-y="240" 
                           data-x="40">
                           <div class="list-slide">
                              
                              <h5 class="dblue">
                              ${param.lang == 'de' ? 'Ihre fiktive Direkt-Versicherung. Nur für Demo-Zwecke.' : 'Your fictional insurance company. For demo purposes only.'}
                               </h5>
                           </div>
                        </div>
<!--
                        <div class="caption lfb caption_button_2 fadeout tp-caption start"
                           data-x="40"
                           data-y="330"
                           data-speed="800"
                           data-endspeed="300"
                           data-start="0"
                           data-hoffset="70"
                           data-easing="easeOutExpo">
                           <a class="btn-special hidden-xs btn-color" href="#">Kfz-Antrag stellen</a>
                        </div>
                     </li>
-->
                  </ul>
               </div>
            </div>
            <!-- Slider End--> 
            <!-- Slogan Start-->
            <div class="slogan bottom-pad-small">
               <div class="container">
                  <div class="row">
                     <div class="slogan-content">
<!--
                        <div class="col-lg-9 col-md-9">
                           <h2 class="slogan-title">Ihre fiktive Direkt-Versicherung. Nur fÃ¼r Demo-Zwecke.</h2>
                        </div>
-->
                        <div class="col-lg-3 col-md-3">
                           <div class="get-started">
                              <a href="application.jsp?lang=${param.lang}" class="btn btn-special btn-color pull-right">${param.lang == 'de' ? 'Jetzt beantragen!' : 'Apply now!'} </a>
                           </div>
                        </div>
                        <div class="clearfix"></div>
                     </div>
                  </div>
               </div>
            </div>
<%@ include file="footer.inc" %>
      </div>
      <!-- Wrap End -->
<%@ include file="scripts.inc" %>
   </body>
</html>